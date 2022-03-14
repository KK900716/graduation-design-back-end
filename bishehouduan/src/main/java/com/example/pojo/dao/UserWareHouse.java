package com.example.pojo.dao;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserWareHouse {
    private int id;
    private String name;
    private int count;
    private int available;
    private int remaining;
    private int userInfo_id;
}
