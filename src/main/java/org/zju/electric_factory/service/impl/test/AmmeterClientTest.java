package org.zju.electric_factory.service.impl.test;

import org.junit.Before;
import org.zju.electric_factory.service.impl.AmmeterClient;

import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-10-18
 * Time: 上午1:33
 * To change this template use File | Settings | File Templates.
 */
public class AmmeterClientTest {


    private TimerTask updateToDBTask;

    {
        updateToDBTask = new TimerTask() {
            @Override
            public void run() {

                try
                {
                    Socket client = new Socket("127.0.0.1", 5001);
                    System.out.println("Just connected to "
                            + client.getRemoteSocketAddress());
//                    byte[] heartBeat =
                    InputStream socketReader = client.getInputStream();
                    OutputStream socketWriter = client.getOutputStream();
//                    68:31:00:31:00:68:c9:94:05:31:08:10:02:60:00:00:01:00:0e:16:1a:01:16:02:01:00:00
                    byte [] heatbeat = {(byte) 0x68, (byte) 0x31, (byte) 0x00, (byte) 0x31,(byte) 0x00, (byte) 0x68,
                            (byte) 0xC9, (byte) 0x94, (byte) 0x05,(byte) 0x31, (byte) 0x08,(byte) 0x10,(byte) 0x02,
                            (byte) 0x60, (byte) 0x00,(byte) 0x00,(byte) 0x01,(byte) 0x00,(byte) 0x0e,(byte) 0x16,
                            (byte) 0x1a,(byte) 0x01,(byte) 0x16,(byte) 0x1a,(byte) 0x01,(byte) 0x16,(byte) 0x02,
                            (byte) 0x01,(byte) 0x00,(byte) 0x00};
                    DataOutputStream out =
                            new DataOutputStream(socketWriter);

                    out.write(heatbeat);
                    InputStream inFromServer = client.getInputStream();
                    DataInputStream in =
                            new DataInputStream(inFromServer);
                    while(true){
                        String msg = in.readUTF();
                        System.out.println("Server says " + msg);
                        if(!msg.equals("")) {
                            break;
                        }
                    }

                    client.close();
                }catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        };
    }

    @Before
    public void setUp() throws Exception {
        Timer timer = new Timer();
        timer.schedule(updateToDBTask, 0, 5000);

    }

    @org.junit.Test
    public void testMoniter() throws Exception {
        AmmeterClient ammeterClient = new AmmeterClient();
        ammeterClient.moniter();
    }


}
