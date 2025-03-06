package com.example.springDev.dto;

import com.example.springDev.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class UserResponseDto {
    private int id;
    private String email;
    private String userName;
    private String password;
    private String role;
    private Timestamp createDate;

    //생성자 선언
    public UserResponseDto(User user){
        this.id= user.getId();
        this.email= user.getUserEmail();
        this.password= user.getPassword();
        this.userName= user.getUserName();
        this.role= user.getRole();
        this.createDate= user.getCreateDate();
    }
}
