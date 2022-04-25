package com.cuber.entity.dto;

import lombok.Data;

/**
 * @author cuber
 * @create 2022/3/23 14:01
 */
@Data
public class LoginDTO {
    Integer userid;
    String username;
    String password;
    String token;
}
