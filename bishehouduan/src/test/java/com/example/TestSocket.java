package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@SpringBootTest
public class TestSocket {
    @Test
    public void simulationService(){
        PrintWriter pw=null;
        BufferedReader is=null;
        ServerSocket server=null;
        Socket socket=null;
        try {
            server = new ServerSocket(9600);
            while (true){
                socket = server.accept();
                is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = is.readLine();
                System.out.println("received frome client:" + line);
                pw = new PrintWriter(socket.getOutputStream());
                pw.println("this data is from server");
                pw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pw.close();
            try {
                is.close();
                socket.close();
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
