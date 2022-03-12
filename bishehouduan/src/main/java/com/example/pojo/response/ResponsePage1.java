package com.example.pojo.response;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePage1 {
    private String account;
    private String password;
    private String name;
    private String permission;
    private float balance;
    private int available;
}
