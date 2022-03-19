package com.example.pojo.dao;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Page2Insert2 {
    private String account;
    private String name;
    private int count=10;
    private int available=0;
    private int remaining=10;

    public Page2Insert2(String account, String name) {
        this.account = account;
        this.name = name;
    }
}
