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
public class UpdateWareHouseMessageDomain {
    private String account;
    private String oldWareHouseName;
    private String newWareHouseName;
}
