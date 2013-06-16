package org.zju.electric_factory.service.impl;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zju.electric_factory.service.GPRSAmmeterClientMannager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * User: vincent
 * Date: 6/9/13
 * Time: 6:56 PM
 */
@Service
public class GPRSAmmeterClientManagerImpl implements GPRSAmmeterClientMannager {
    @Value("${ammeter.recorder.port}")
    private String port;

    @Override
    public void startRecording(String s) throws IOException {
        ServerSocket recorder = startRecorder();
        if(null != recorder){
            while(true){
                Socket GPRSSocket = recorder.accept();
                invokeRecord(GPRSSocket);
            }
        }

    }

    private ServerSocket startRecorder() throws IOException {
        ServerSocket recorderSocket = null;
        if ((null != port) && (StringUtils.isNumeric(port))) {
            recorderSocket = new ServerSocket(Integer.parseInt(port));
        }
        return recorderSocket;
    }

    private void invokeRecord(Socket socket) {
        Runnable recordRunnable = new Runnable() {
            @Override
            public void run() {

            }
        };
    }


}
