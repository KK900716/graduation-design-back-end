package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestSocket {
    @Test
    public void test(){
        System.out.println("abc");
    }
//    @Resource
//    AsyncTaskService asyncTaskService;
//    @Test
//    public void testSocket(){
//        for (int i = 0; i < 10; i++) {
//            asyncTaskService.dealWithImg("testURL"+i);
//            asyncTaskService.dealWithImg("testURL"+i);
//            System.out.println(i);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    @Test
//    public void simulationService(){
//        PrintWriter pw=null;
//        BufferedReader is=null;
//        ServerSocket server=null;
//        Socket socket=null;
//        try {
//            server = new ServerSocket(9600);
//            while (true){
//                socket = server.accept();
//                is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                String line = is.readLine();
//                System.out.println("received frome client:" + line);
//                pw = new PrintWriter(socket.getOutputStream());
//                pw.println("this data is from server");
//                pw.flush();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            pw.close();
//            try {
//                is.close();
//                socket.close();
//                server.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
