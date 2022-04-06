package com.example.pojo.dao;

import lombok.*;

/**
 * @author ljc
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WareHouse {
    private String id;
    private int userWarehouseId;
    private String state="处理中";

    public WareHouse(String id,int userWarehouseId) {
        this.id=id;
        this.userWarehouseId = userWarehouseId;
    }
}
