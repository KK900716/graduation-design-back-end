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
public class UpdateWareHouseMessage {
    private String oldWareHouseName;
    private String newWareHouseName;
}
