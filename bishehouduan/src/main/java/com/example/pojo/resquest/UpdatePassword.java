package com.example.pojo.resquest;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePassword {
    private String oldPassword;
    private String newPassword;
}
