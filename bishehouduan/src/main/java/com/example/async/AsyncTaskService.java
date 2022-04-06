package com.example.async;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;

/**
 * @author ljc
 */
@Service
public class AsyncTaskService {
    private final static Integer LOCK = 0;
    @Value("${user.sendPython.port}")
    private int port;
    @Value("${user.sendPython.ip}")
    private String ip;
    /**
     * com.example.async.AsyncTaskService.dealWithImg():
     * 连接python服务器处理图片
     * @author ljc
     * @date 2022/4/6~17:59
     * @param url 图片地址
     */
    @Async
    public void dealWithImg(String url) {
        PrintWriter printWriter=null;
        BufferedReader bufferedReader=null;
        Socket socket=null;
        synchronized (LOCK){
            try {
                socket = new Socket(ip,port);
                printWriter=new PrintWriter(socket.getOutputStream());
                bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
    //            发送数据
                printWriter.println("%%"+url+"%%");
                printWriter.flush();
    //            接受数据
                String line=bufferedReader.readLine();
                System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (printWriter!=null) {
                    printWriter.close();
                }
                try {
                    if (socket!=null) {
                        socket.close();
                    }
                    if (bufferedReader!=null) {
                        bufferedReader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
