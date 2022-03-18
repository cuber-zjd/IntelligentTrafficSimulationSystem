package com.cuber.utils;

import com.cuber.entity.User;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

/**
 * @author cuber
 * @create 2022/2/25 22:47
 */
public class TokenUtils {
    public static String generateToken(User user){
        System.out.println(user.getId().toString());
        JwtBuilder jwtBuilder= Jwts.builder();
        String jwtToken=jwtBuilder
                .setHeaderParam("type","JWT")
                .setHeaderParam("alg","HS256")
                .claim("username",user.getUsername())
                .claim("password",user.getPassword())
                .setSubject("user-login")
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .setId(UUID.randomUUID().toString())
//                signature
                .signWith(SignatureAlgorithm.HS256,"test")
                .compact();
        return jwtToken;
    }
    public static User testToken(String token){
        JwtParser jwtParser=Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey("test").parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        String username = (String)body.get("username");
        String password = (String)body.get("password");
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

    public static boolean testTokenDate(String token){
        JwtParser jwtParser=Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey("test").parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        if (body.getExpiration().after(new Date())){
            System.out.println("token超时");
            return true;
        }
        return false;
    }
}
