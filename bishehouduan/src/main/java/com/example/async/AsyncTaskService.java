package com.example.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class AsyncTaskService {
    @Async
    public void dealWithImg(File newFile) {
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
