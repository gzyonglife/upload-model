package com.jinlong.uploadmodel.entity.access;

import com.jinlong.uploadmodel.entity.dto.UserTable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * user_table
 *
 * @author
 */
public class JwtUser implements UserDetails {


    private static final long serialVersionUID = 1209684288705712353L;

    private long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser() {
    }

    // 写一个能直接使用user创建jwtUser的构造器
    public JwtUser(UserTable user, String role) {
        id = user.getUserId();
        username = user.getLoginName();
        password = user.getLoginPassword();
        authorities = Collections.singleton(new SimpleGrantedAuthority(role));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
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