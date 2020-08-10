package com.jinlong.uploadmodel.entity.vo;

import lombok.Data;

/**
 * @author jinlong
 */
@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType;
    private String username;
    private String authority;

    public JwtAuthenticationResponse(String accessToken, String username, String authority) {
        this.accessToken = accessToken;
        this.username = username;
        this.authority = authority;
        this.tokenType = "Bearer";
    }

    public JwtAuthenticationResponse(String accessToken, String tokenType, String username, String authority) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.username = username;
        this.authority = authority;
    }
}