package com.example.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;

@Service
public class AsyncTaskService {
    private final static Integer lock= 0;
    @Async
    public void dealWithImg(File file) {
        PrintWriter printWriter=null;
        BufferedReader bufferedReader=null;
        Socket socket=null;
        synchronized (lock){
            try {
                socket = new Socket("127.0.0.1",9600);
                printWriter=new PrintWriter(socket.getOutputStream());
                bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
    //            发送数据
                printWriter.println(file.getPath());
                printWriter.flush();
    //            接受数据
                String line=bufferedReader.readLine();
                System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                printWriter.close();
                try {
                    socket.close();
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
