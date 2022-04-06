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
public class ResponsePage3Context {
    private String name;
    private int count;
    private int available;
    private int remaining;
}
