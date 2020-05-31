package com.jinlong.uploadmodel.util;

import com.jinlong.uploadmodel.entity.dto.UserTable;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtils {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String SUBJECT = "congge";

    public static final long EXPIRITION = 1000 * 24 * 60 * 60 * 7;

    public static final String APPSECRET_KEY = "congge_secret";

    private static final String ROLE_CLAIMS = "rol";

    public static String generateJsonWebToken(UserDetails  user) {

        if (user.getAuthorities() == null || user.getUsername() == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, "rol");

        String token = Jwts
                .builder()
                .setSubject(SUBJECT)
                .setClaims(map)
                .claim("authorities", user.getAuthorities())
                .claim("name", user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
        return token;
    }

    /**
     * 生成token
     *
     * @param username
     * @param role
     * @return
     */
    public static String createToken(String username, String role) {

        Map<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, role);

        String token = Jwts
                .builder()
                .setSubject(username)
                .setClaims(map)
                .claim("username", username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
        return token;
    }

    public static Claims checkJWT(String token) {
        try {
            final Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    public static String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("username").toString();
    }

    /**
     * 获取用户角色
     *
     * @param token
     * @return
     */
    public static String getUserRoleFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("rol").toString();
    }

    /**
     * 是否过期
     *
     * @param token
     * @return
     */
    public static boolean isExpiration(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date());
    }


    public static boolean validateToken(String authToken, UserDetails userDetails) {

        return true;
    }
}