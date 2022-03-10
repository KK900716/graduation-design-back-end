package com.example.utils;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

public class JwtUtil {
    private static final String signature="admin-user-shzu";
    private static final int time=24*3600*1000;

    public static String createToken(String userAccount,String password){
        JwtBuilder jwtBuilder= Jwts.builder();
        return String.valueOf(jwtBuilder
                //header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload
                .claim("userAccount",userAccount)
                .claim("password",password)
                .setSubject("login")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256,signature)
                .compact());
    }
    public static boolean checkToken(String token){
        if (token==null)
            return false;
        try {
            JwtParser jwtParser=Jwts.parser();
            jwtParser.setSigningKey(signature).parseClaimsJws(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public static String getTokenUserAccount(String token){
        JwtParser jwtParser=Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(signature).parseClaimsJws(token);
        Claims claims=claimsJws.getBody();
        return claims.get("userAccount").toString();
    }
    public static String getTokenPassword(String token){
        JwtParser jwtParser=Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(signature).parseClaimsJws(token);
        Claims claims=claimsJws.getBody();
        return claims.get("password").toString();
    }
}
