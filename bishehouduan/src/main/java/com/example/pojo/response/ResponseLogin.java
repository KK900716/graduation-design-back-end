package com.example.pojo.response;

import lombok.*;

/**
 * @author ljc
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseLogin {
    private String state;
    private String token;
}
