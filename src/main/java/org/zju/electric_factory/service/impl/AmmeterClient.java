package org.zju.electric_factory.service.impl;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.shiro.session.Session;
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

@Component
public class AmmeterClient {
	@Autowired
	private AmmeterGPRSLinkDAOImpl ammeterGPRSLinkDAOImpl;
	@Autowired
	private AmmeterDAOImpl ammeterDAOImpl;
	@Autowired
	private GPRSModuleDAOImpl gprsModuleDAOImpl;
	@Autowired AmmeterRecordDAOImpl ammeterRecordDAOImpl;
	
	@Autowired
	private AmmeterRecordManagerImpl ammeterRecordManagerImpl;



	public static final int PORT = 5000;
	public static final byte[] HEART_BEAT_HEADER = { (byte) 0x68, (byte) 0x31,
			(byte) 0x00, (byte) 0x31, (byte) 0x00 };
	public static final byte[] gprsMessageTail = { (byte) 0x11, (byte) 0x22,
			(byte) 0x33, (byte) 0x44, (byte) 0x55, (byte) 0x66, (byte) 0x01 };
	public static final int HEART_BEAT_LENGTH = 27;

	public void moniter() throws Exception {
		System.out.println("*******************************start moniter");
		ServerSocket serverSocket = new ServerSocket(PORT);
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
				System.out.println("start to read");
				while (true) {
					//
					byte[] received = new byte[64];
					int count = dataInputStream.read(received);
					byte[] output = AmmeterClient
							.trimByteArray(received, count);
					System.out.println(AmmeterClient.bytesToHexString(output));
					if (headerMatched(output, HEART_BEAT_HEADER,
							HEART_BEAT_HEADER.length)) {
						byte[] gprsIdentifierBytes = new byte[5];
						for (int i = 0; i < 5; i++) {
							gprsIdentifierBytes[5 - i - 1] = output[i
									+ HEART_BEAT_HEADER.length + 2];
						}

						String gprsIdentifier = bytesToHexString(gprsIdentifierBytes);
						System.out.println("gprs:" + gprsIdentifier);
						List<byte[]> ammeterIdentifiers = getAmmeterIdentifierByGprsIdentifier(gprsIdentifier);
						makeQuery(ammeterIdentifiers, dataOutputStream);

					} else {
						System.out.println(AmmeterClient
								.bytesToHexString(output));
						System.out.println("电表读数" + parseIncome(output));
						Integer ammeterValueInteger = parseIncome(output);
						AmmeterRecord ammeterRecord = new AmmeterRecord();
						List<Ammeter> ammeters = ammeterDAOImpl.findBy("name", "000000000030");
						Ammeter ammeter = ammeters.get(0);
						ammeterRecord.setAmmeterId(ammeter.getId());
						ammeterRecord.setAmmeterName(ammeter.getName());
						ammeterRecord.setAmmeterValue(ammeterValueInteger);
						ammeterRecord.setRecordDate(new Date());
						ammeterRecord.setTimeSum(0.0f);
						ammeterRecordManagerImpl.add(ammeterRecord);
					}
				}
			}
		}
	}
	
	

	public void makeQuery(List<byte[]> ammeterIdentifiers,
			DataOutputStream dataOutputStream) throws IOException {
		for (byte[] ammeterIdentifier : ammeterIdentifiers) {
			System.out.println(AmmeterClient
					.bytesToHexString(ammeterIdentifier));
			byte[] b = new byte[26];
			b[0] = (byte) 0xfe;
			b[1] = (byte) 0xfe;
			b[2] = (byte) 0xfe;
			b[3] = (byte) 0x68;
			if (ammeterIdentifier.length != 6) {
				continue;
			}
			for (int i = 0; i < 6; i++) {
				b[i + 4] = ammeterIdentifier[i];
			}
			b[10] = (byte) 0x68;
			b[11] = (byte) 0x11;
			b[12] = (byte) 0x04;
			b[13] = (byte) 0x33;
			b[14] = (byte) 0x33;
			b[15] = (byte) 0x33;
			b[16] = (byte) 0x33;
			b[17] = (byte) 0xE1;
			b[18] = (byte) 0x16;
			b[19] = (byte) 0x11;
			b[20] = (byte) 0x22;
			b[21] = (byte) 0x33;
			b[22] = (byte) 0x44;
			b[23] = (byte) 0x55;
			b[24] = (byte) 0x66;
			b[25] = (byte) 0x01;
			System.out.println("send request:");
			System.out.println(AmmeterClient.bytesToHexString(b));
			dataOutputStream.write(b);
			dataOutputStream.flush();
		}

	}

	public List<byte[]> getAmmeterIdentifierByGprsIdentifier(
			String gprsIdentifier) {
		System.out.println("********************************start getammeterid");
		List<byte[]> ammeterIdentifiers = new ArrayList<byte[]>();
		List<GPRSModule> gprsModules = gprsModuleDAOImpl.findBy("identifier",
				gprsIdentifier);
		System.out.println(gprsModules.get(0).getId());
		if ((null != gprsModules) && (gprsModules.size() > 0)) {
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

	public void getData(List<String> recogCode) {
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
					while (true) {
						byte[] received = new byte[1024];
						// dataOutputStream.write(b);
						// dataOutputStream.flush();
						int count = dataInputStream.read(received);
						byte[] output = AmmeterClient.trimByteArray(received,
								count);

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Integer parseIncome(byte[] output) {
		StringBuilder parsedData = new StringBuilder();
		if (output[12] == (byte) 0x08) {
			byte[] data = new byte[4];
			data[0] = output[17];
			data[1] = output[18];
			data[2] = output[19];
			data[3] = output[20];
			System.out.println(AmmeterClient.bytesToHexString(data));

		
			for (int i = data.length - 1; i >= 0; i--) {
				int unParsedData = data[i] & 0xFF;
				int dataAfterLabel = unParsedData - 0x33;
				parsedData.append(Integer.toHexString(dataAfterLabel));
			}
		}
		return Integer.parseInt(parsedData.toString());
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

	public static void main(String[] args) {
		System.out.println(((byte) 0xfe) & 0xFF);
		String string = "000000000030";
		System.out.println(hexStringToBytes(string).length);
		System.out.println(bytesToHexString(hexStringToBytes(string)));
	}

}
