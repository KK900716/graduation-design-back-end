package com.example.pojo.resquest;

import lombok.*;

/**
 * @author ljc
 */
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
