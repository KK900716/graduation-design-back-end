package com.example.pojo.dao;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private int id;
    private String account;
    private String password;
    private String name;
    private String permission;
    private float balance;
    private int available;
}
