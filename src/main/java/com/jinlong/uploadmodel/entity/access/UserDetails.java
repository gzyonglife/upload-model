package com.jinlong.uploadmodel.entity.access;

import com.alibaba.fastjson.JSON;
import com.jinlong.uploadmodel.entity.data.UserTable;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * user_table
 *
 * @author
 */
@Setter
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {


    private static final long serialVersionUID = 1209684288705712353L;

    private Integer id;
    private String username;
    private String password;
    private Long expirationTime;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetails() {
    }

    public UserDetails(UserTable user, Long expirationTime, String role) {
        this.id = user.getUserId();
        this.username = user.getLoginName();
        this.password = user.getLoginPassword();
        this.authorities = Collections.singleton(new SimpleGrantedAuthority(role));
        this.expirationTime = expirationTime;
    }

    public Long getExpirationTime() {
        return expirationTime;
    }

    public Integer getId() {
        return id;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(List<String> authorities) {
        SimpleGrantedAuthority simpleGrantedAuthority = JSON.parseObject(authorities.get(0), SimpleGrantedAuthority.class);
        this.authorities = Collections.singleton(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // 账户是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账户是否未锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 密码是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 账户是否激活
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "JwtUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}