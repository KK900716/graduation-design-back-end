package com.example.pojo.resquest;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWareHouseMessage {
    private String oldWHName;
    private String newWHName;
}
