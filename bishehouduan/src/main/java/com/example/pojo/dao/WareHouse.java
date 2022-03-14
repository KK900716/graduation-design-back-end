package com.example.pojo.dao;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WareHouse {
    private String id;
    private int userWarehouse_id;
    private String state="处理中";

    public WareHouse(String id,int userWarehouse_id) {
        this.id=id;
        this.userWarehouse_id = userWarehouse_id;
    }
}
