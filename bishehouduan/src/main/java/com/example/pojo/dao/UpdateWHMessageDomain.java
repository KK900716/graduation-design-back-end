package com.example.pojo.dao;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWHMessageDomain {
    private String account;
    private String oldWHName;
    private String newWHName;
}
