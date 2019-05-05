package com.cloud.example.data;

import lombok.*;

/**
 * Created by Srikar on May, 2019
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String name;
    private long contact;
    private String email;
}
