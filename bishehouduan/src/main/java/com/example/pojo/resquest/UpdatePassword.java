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
public class UpdatePassword {
    private String oldPassword;
    private String newPassword;
}
