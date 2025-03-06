package com.example.springDev.dto;

import com.example.springDev.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class UserRequestDto {
    private String email;
    private String password;

    public User toEntity(){
        return User.builder().userEmail(email).password(password).build();
    }
}
