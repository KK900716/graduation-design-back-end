package com.example.Service;

import com.example.pojo.resquest.UpdatePassword;
import org.springframework.stereotype.Service;

public interface Page4Service {
    boolean updatePassword(String account, UpdatePassword updatePassword);
}
