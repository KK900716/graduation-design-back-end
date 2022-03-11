package com.example.pojo.resquest;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    private String userAccount;
    private String userPassword;
    private String verificationCode;
    private String uid;
}
