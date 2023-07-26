package com.likelion.market.service;

import com.likelion.market.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomUser implements UserDetails {

    @Getter
    private Long id;
    private String username;
    private String password;

    @Getter
    private String email;

    @Getter
    private String phone;

    @Getter
    private String address;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Entity 에서 가져올 Data
    public static CustomUser fromEntity(UserEntity entity) {
        return CustomUser.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .build();
    }

    // 새 계정만들기 위한 작업
    public UserEntity newUser() {
        UserEntity entity = new UserEntity();

        entity.setUsername(username);
        entity.setPassword(password);
        entity.setEmail(email);
        entity.setPhone(phone);
        entity.setAddress(address);

        return entity;
    }

    @Override
    public String toString() {
        return "CustomUserDetails{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='[PROTECTED]'" +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", phone='" + address + '\'' +
                '}';
    }
}
