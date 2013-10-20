package org.zju.electric_factory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zju.electric_factory.dao.impl.AmmeterDAOImpl;
import org.zju.electric_factory.dao.impl.AmmeterGPRSLinkDAOImpl;
import org.zju.electric_factory.dao.impl.AmmeterRecordDAOImpl;
import org.zju.electric_factory.dao.impl.GPRSModuleDAOImpl;
import org.zju.electric_factory.entity.Ammeter;
import org.zju.electric_factory.entity.AmmeterGPRSLink;
import org.zju.electric_factory.entity.AmmeterRecord;
import org.zju.electric_factory.entity.GPRSModule;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.sql.*;
import java.util.*;
import java.util.Date;

@Component
public class AmmeterClient {
    @Autowired
    private AmmeterGPRSLinkDAOImpl ammeterGPRSLinkDAOImpl;
    @Autowired
    private AmmeterDAOImpl ammeterDAOImpl;
    @Autowired
    private GPRSModuleDAOImpl gprsModuleDAOImpl;
    @Autowired
    private AmmeterRecordDAOImpl ammeterRecordDAOImpl;
    @Autowired
    private AmmeterRecordManagerImpl ammeterRecordManagerImpl;

    private Map<String, AmmeterRecord> ammeterRecordMap = new HashMap<String, AmmeterRecord>();
    private Socket clientSocket;


    public static final int PORT = 5000;
    public static final byte[] HEART_BEAT_HEADER = {(byte) 0x68, (byte) 0x31,
            (byte) 0x00, (byte) 0x31, (byte) 0x00};
    public static final byte[] gpsMessageTail = {(byte) 0x11, (byte) 0x22,
            (byte) 0x33, (byte) 0x44, (byte) 0x55, (byte) 0x66, (byte) 0x01};
    public static final byte[] timeSumDataLabel = {(byte) 0x34, (byte) 0x34, (byte) 0x33, (byte) 0x3B};
    public static final byte[] ammeterValueDataLabel = {(byte) 0x33, (byte) 0x33, (byte) 0x33, (byte) 0x33};
    public static final byte[] header = {(byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0x68};
    public static final byte[] splitCode = {(byte) 0x68};
    public static final byte[] readOperateCode = {(byte) 0x11};
    public static final byte[] readOperateDataLen = {(byte) 0x04};
    public static final byte[] endCode = {(byte) 0x16};

    private static final String dbURL = "jdbc:mysql://localhost:3306/electric_factory?useUnicode=true&characterEncoding=UTF-8";
    private static final String username = "root";
    private static final String password = "123456";

    private TimerTask updateToDBTask;

    {
        updateToDBTask = new TimerTask() {
            @Override
            @Transactional
            public void run() {
                Connection con = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection(dbURL, username, password);
                    con.setAutoCommit(false);
                    PreparedStatement qpstmt = con.prepareStatement("SELECT ID FROM AMMETER WHERE AMMETER_NAME = ?");
                    PreparedStatement pstmt = con.prepareStatement("INSERT INTO AMMETER_RECORD (AMMETER_ID, AMMETER_NAME, AMMETER_VALUE, RECORD_TIME, TIME_SUM) VALUES (?, ?, ?, ?, ?)");
                    System.out.println("write to db");
                    if (null != ammeterRecordMap && ammeterRecordMap.size() > 0) {
                        for (Map.Entry<String, AmmeterRecord> entry : ammeterRecordMap.entrySet()) {
                            AmmeterRecord ammeterRecord = entry.getValue();
                            System.out.println("add record" + entry.getKey() + ":" + entry.getValue());
                            qpstmt.setString(1,ammeterRecord.getAmmeterName());
                            ResultSet resultSet = qpstmt.executeQuery();
                            Long ammeterId= null;
                            while(resultSet.next()){
                                ammeterId =resultSet.getLong("ID");
                            }

//                          pstmt.setLong(1, ammeterRecord.getAmmeterId());
                            pstmt.setLong(1, ammeterId);
                            pstmt.setString(2, ammeterRecord.getAmmeterName());
                            pstmt.setFloat(3, ammeterRecord.getAmmeterValue());
                            pstmt.setTimestamp(4, new Timestamp(new Date().getTime()));
                            pstmt.setFloat(5, ammeterRecord.getTimeSum() == null ? 0f : ammeterRecord.getTimeSum());
                            pstmt.addBatch();
                        }
                        pstmt.executeBatch();
                        con.commit();
                        ammeterRecordMap.clear();
                        pstmt.close();
                        qpstmt.close();
                    } else {
                        System.out.println("没有数据");
                    }
                } catch (ClassNotFoundException e) {
                    System.out.println("找不到驱动程序类 ，加载驱动失败！");
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } finally {
                    if(null != con){
                        try {
                            con.close();
                        } catch (SQLException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                    }
                }

            }
        };
    }

    public AmmeterClient() {
    }

    private void init() {
        Timer timer = new Timer();
        timer.schedule(updateToDBTask, 0, 30000);
    }

    public void moniter() throws Exception {
        init();
        System.out.println("*******************************start moniter");
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                if (null != clientSocket) {
                    System.out.println("ammeter gprs connected");
                    SocketAddress socketAddress = clientSocket
                            .getRemoteSocketAddress();
                    System.out.println(socketAddress.toString());
                    InputStream socketReader = clientSocket.getInputStream();
                    OutputStream socketWriter = clientSocket.getOutputStream();

                    DataInputStream dataInputStream = new DataInputStream(
                            socketReader);
                    DataOutputStream dataOutputStream = new DataOutputStream(
                            socketWriter);
                    System.out.println("start to read");
//                    closeSocketTimer.schedule(closeSocketTimeTask, 6000);
                    while (true) {
                        byte[] received = new byte[64];
                        int count = dataInputStream.read(received);
                        byte[] output = AmmeterClient
                                .trimByteArray(received, count);
                        System.out.println("message received" + AmmeterClient.bytesToHexString(output));
                        if (headerMatched(output, HEART_BEAT_HEADER,
                                HEART_BEAT_HEADER.length)) {
                        String gprsIdentifier = getGprsIdentifyFromHeartBeatMsg(output);
                        List<byte[]> ammeterIdentifiers = getAmmeterIdentifierByGprsIdentifier(gprsIdentifier);
                            System.out.println(gprsIdentifier);
//                        makeQuery(ammeterIdentifiers, dataOutputStream);
                            //test
//                            List<byte[]> ammeterIdentifiersForTest = new ArrayList<byte[]>();
//                            byte[] ammeterForTest = {(byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa, (byte) 0xaa};
//                            ammeterIdentifiersForTest.add(ammeterForTest);
                            makeAmmeterValueQuery(ammeterIdentifiers, dataOutputStream);
                            makeTimeSumQuery(ammeterIdentifiers, dataOutputStream);
                        } else {
                            System.out.println(AmmeterClient
                                    .bytesToHexString(output));

                            parseIncome(output);

                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

        }
    }

    private String getGprsIdentifyFromHeartBeatMsg(byte[] heartBeatMsg) {
        byte[] gprsIdentifierBytes = new byte[5];
        for (int i = 0; i < 5; i++) {
            gprsIdentifierBytes[5 - i - 1] = heartBeatMsg[i
                    + HEART_BEAT_HEADER.length + 2];
        }
        String gprsIdentifier = bytesToHexString(gprsIdentifierBytes);
        System.out.println("gprs:" + gprsIdentifier);
        return gprsIdentifier;
    }

    private int appendMessage(byte[] message, byte[] obj, int offset) {
        System.arraycopy(obj, 0, message, offset, obj.length);
        return obj.length;
    }

    private byte[] getReqQueryMessage(byte[] ammeterIdentifier, byte[] dataLabel) {
        byte[] queryMessage = new byte[26];
        int offset = 0;
        offset += appendMessage(queryMessage, header, offset);
        offset += appendMessage(queryMessage, ammeterIdentifier, offset);
        offset += appendMessage(queryMessage, splitCode, offset);
        offset += appendMessage(queryMessage, readOperateCode, offset);
        offset += appendMessage(queryMessage, readOperateDataLen, offset);
        offset += appendMessage(queryMessage, dataLabel, offset);
        offset += appendMessage(queryMessage, getCheckSum(queryMessage, offset), offset);
        offset += appendMessage(queryMessage, endCode, offset);
        offset += appendMessage(queryMessage, gpsMessageTail, offset);
        return queryMessage;
    }

    private void makeTimeSumQuery(List<byte[]> ammeterIdentifiers,
                                  DataOutputStream dataOutputStream) throws IOException {
        for (byte[] ammeterIdentifier : ammeterIdentifiers) {
            System.out.println(AmmeterClient
                    .bytesToHexString(ammeterIdentifier));
            if (ammeterIdentifier.length != 6) {
                byte[] tmp = new byte[6];
                int i = 6 - ammeterIdentifier.length + 1;
                System.arraycopy(ammeterIdentifier, 0, tmp, i, ammeterIdentifier.length);
                ammeterIdentifier = tmp;
            }
            byte[] queryMessage = getReqQueryMessage(ammeterIdentifier, timeSumDataLabel);
//            todo continue tomorrow
            System.out.println("send request:");
            System.out.println(AmmeterClient.bytesToHexString(queryMessage));
            dataOutputStream.write(queryMessage);
            dataOutputStream.flush();
        }
    }

    private void


    makeAmmeterValueQuery(List<byte[]> ammeterIdentifiers,
                          DataOutputStream dataOutputStream) throws IOException {
        for (byte[] ammeterIdentifier : ammeterIdentifiers) {
            System.out.println(AmmeterClient
                    .bytesToHexString(ammeterIdentifier));
            if (ammeterIdentifier.length != 6) {
                byte[] tmp = new byte[6];
                int i = 6 - ammeterIdentifier.length + 1;
                System.arraycopy(ammeterIdentifier, 0, tmp, i, ammeterIdentifier.length);
                ammeterIdentifier = tmp;
            }
            byte[] queryMessage = getReqQueryMessage(ammeterIdentifier, ammeterValueDataLabel);
//            todo continue tomorrow
            System.out.println("send request:");
            System.out.println(AmmeterClient.bytesToHexString(queryMessage));
            dataOutputStream.write(queryMessage);
            dataOutputStream.flush();
        }
    }

    private byte[] getCheckSum(byte[] queryMessage, int length) {
        int sum = 0;
        for (int i = 3; i < length; i++) {
            sum += queryMessage[i] & 0xFF;
        }
        byte[] checksum = {(byte) sum};
        return checksum;
    }

    public List<byte[]> getAmmeterIdentifierByGprsIdentifier(
            String gprsIdentifier) {
        System.out.println("********************************start getammeterid");
        List<byte[]> ammeterIdentifiers = new ArrayList<byte[]>();
        List<GPRSModule> gprsModules = gprsModuleDAOImpl.findBy("identifier",
                gprsIdentifier);

        if ((null != gprsModules) && (gprsModules.size() > 0)) {
            System.out.println(gprsModules.get(0).getId());
            List<AmmeterGPRSLink> ammeterGPRSLinks = ammeterGPRSLinkDAOImpl
                    .findBy("gprsId", gprsModules.get(0).getId());
            System.out.println("ammeter id"
                    + ammeterGPRSLinks.get(0).getAmmeterId());
            for (AmmeterGPRSLink ammeterGPRSLink : ammeterGPRSLinks) {
                List<Ammeter> ammeters = ammeterDAOImpl.findBy("id",
                        ammeterGPRSLink.getAmmeterId());
                for (Ammeter ammeter : ammeters) {
                    System.out.println(ammeter.getName());
                    System.out
                            .println(AmmeterClient
                                    .bytesToHexString(reverseBytes(hexStringToBytes(ammeter
                                            .getName()))));
                    ammeterIdentifiers
                            .add(reverseBytes(hexStringToBytes(ammeter
                                    .getName())));
                }
            }
        }
        return ammeterIdentifiers;
    }


    public static byte[] reverseBytes(byte[] code) {
        for (int i = 0; i < code.length / 2; i++) {
            byte temp = code[i];
            code[i] = code[code.length - 1 - i];
            code[code.length - 1 - i] = temp;
        }
        return code;

    }

    public boolean headerMatched(byte[] src, byte[] dest, int length) {
        for (int i = 0; i < length; i++) {
            if (src[i] != dest[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * get ammeter identify from message
     *
     * @param message
     * @return
     */
    private String getAmmeterIdentifyFromMessage(byte[] message) {
        String ammeterIdentify = null;
        if (null != message) {
            byte[] data = new byte[6];
            System.arraycopy(message, 4, data, 0, 6);
            reverseBytes(data);
            ammeterIdentify = bytesToHexString(data);
        }
        return ammeterIdentify;
    }

    private byte[] getDataLabel(byte[] message) {
        byte[] dataLabel = null;
        if (null != message) {
            dataLabel = new byte[4];
            System.arraycopy(message, 13, dataLabel, 0, 4);
        }
        return dataLabel;
    }

    public String getParsedDataContent(byte[] message) {
        StringBuilder dataContent = new StringBuilder();
        if (null != message) {
            byte[] data = new byte[4];
            System.arraycopy(message, 17, data, 0, 4);
            for (int i = data.length - 1; i >= 0; i--) {

                int unParsedData = data[i] & 0xFF;
                int dataAfterLabel = unParsedData - 0x33;
                dataContent.append(Integer.toHexString(dataAfterLabel));
            }
        }
        return dataContent.toString();
    }

    public String getParsedTimeSumDataContent(byte[] message) {
        StringBuilder dataContent = new StringBuilder();
        if (null != message) {
            byte[] data = new byte[3];
            System.arraycopy(message, 17, data, 0, 3);
            for (int i = data.length - 1; i >= 0; i--) {

                int unParsedData = data[i] & 0xFF;
                int dataAfterLabel = unParsedData - 0x33;
                dataContent.append(Integer.toHexString(dataAfterLabel));
            }
        }
        return dataContent.toString();
    }

    public void parseIncome(byte[] message) {
            String ammeterIdentify = getAmmeterIdentifyFromMessage(message);
            byte[] dataLabel = getDataLabel(message);

            AmmeterRecord ammeterRecord = null;
            if (ammeterRecordMap.containsKey(ammeterIdentify)) {
                ammeterRecord = ammeterRecordMap.get(ammeterIdentify);

            } else {
                ammeterRecord = new AmmeterRecord();
            }
            ammeterRecord.setAmmeterName(ammeterIdentify);
            if (Arrays.equals(dataLabel, ammeterValueDataLabel)) {
                String dataContent = getParsedDataContent(message);
                System.out.println("电量读取值为：" + dataContent);
                ammeterRecord.setAmmeterValue(Float.parseFloat(dataContent) / 100);
            } else if (Arrays.equals(dataLabel, timeSumDataLabel)) {
                String dataContent = getParsedTimeSumDataContent(message);
                ammeterRecord.setTimeSum(Float.parseFloat(dataContent) / 10);
            }
            if(null != ammeterRecord) {
                ammeterRecordMap.put(ammeterIdentify, ammeterRecord);
            }


    }

    public void test() {
        // String serverHost = "211.140.18.108";
        int port = 5000;
        try {

            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                if (null != clientSocket) {
                    System.out.println("ammeter gprs connected");
                    SocketAddress socketAddress = clientSocket
                            .getRemoteSocketAddress();
                    System.out.println(socketAddress.toString());
                    InputStream socketReader = clientSocket.getInputStream();
                    OutputStream socketWriter = clientSocket.getOutputStream();

                    DataInputStream dataInputStream = new DataInputStream(
                            socketReader);
                    DataOutputStream dataOutputStream = new DataOutputStream(
                            socketWriter);

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(socketReader));
                    PrintWriter out = new PrintWriter(socketWriter);

                    byte[] b = new byte[26];
                    // 68 75 06 00 08 08 12
                    b[0] = (byte) 0xfe;
                    b[1] = (byte) 0xfe;
                    b[2] = (byte) 0xfe;
                    b[3] = (byte) 0x68;
                    b[4] = (byte) 0x75;
                    b[5] = (byte) 0x06;
                    b[6] = (byte) 0x00;
                    b[7] = (byte) 0x08;
                    b[8] = (byte) 0x08;
                    b[9] = (byte) 0x12;
                    b[10] = (byte) 0x68;
                    b[11] = (byte) 0x11;
                    b[12] = (byte) 0x04;
                    b[13] = (byte) 0x33;
                    b[14] = (byte) 0x33;
                    b[15] = (byte) 0x33;
                    b[16] = (byte) 0x33;
                    b[17] = (byte) 0x4E;
                    b[18] = (byte) 0x16;
                    b[19] = (byte) 0x11;
                    b[20] = (byte) 0x22;
                    b[21] = (byte) 0x33;
                    b[22] = (byte) 0x44;
                    b[23] = (byte) 0x55;
                    b[24] = (byte) 0x66;
                    b[25] = (byte) 0x01;
                    // b[0] = (byte) 0x68;
                    // b[1] = (byte) 0x75;
                    // b[2] = (byte) 0x06;
                    // b[3] = (byte) 0x00;
                    // b[4] = (byte) 0x08;
                    // b[5] = (byte) 0x08;
                    // b[6] = (byte) 0x12;
                    // b[7] = (byte) 0x68;
                    // b[8] = (byte) 0x11;
                    // b[9] = (byte) 0x04;
                    // b[10] = (byte) 0x33;
                    // b[11] = (byte) 0x33;
                    // b[12] = (byte) 0x33;
                    // b[13] = (byte) 0x33;
                    // b[14] = (byte) 0x4E;
                    // b[15] = (byte) 0x16;

                    System.out.println("request sent");

                    dataOutputStream.write(b);
                    dataOutputStream.flush();
                    // out.print(b);
                    // out.flush();

                    while (true) {
                        byte[] received = new byte[1024];
                        dataOutputStream.write(b);
                        dataOutputStream.flush();
                        int count = dataInputStream.read(received);
                        byte[] output = AmmeterClient.trimByteArray(received,
                                count);
                        // dataInputStream.readFully(received);
                        System.out.println(AmmeterClient
                                .bytesToHexString(output));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] trimByteArray(byte[] input, int size) {
        byte[] output = new byte[size];
        System.arraycopy(input, 0, output, 0, size);
        return output;
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));

        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static int byteArrayToInt(byte[] b) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (b[i] & 0x000000FF) << shift;
        }
        return value;
    }

    public static void main(String[] args) {


        System.out.println((byte) 81);

//        System.out.println(((byte) 0xfe) & 0xFF);
//        String string = "000000000030";
//        System.out.println(hexStringToBytes(string).length);
//        System.out.println(bytesToHexString(hexStringToBytes(string)));
    }

}
