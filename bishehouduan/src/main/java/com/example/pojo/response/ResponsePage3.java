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
public class ResponsePage3 {
    private String name;
    private int count;
    private int available;
    private int remaining;
}
