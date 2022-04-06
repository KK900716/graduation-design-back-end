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
public class UpdatePasswordDomain {
    private String account;
    private String oldPassword;
    private String newPassword;
}
