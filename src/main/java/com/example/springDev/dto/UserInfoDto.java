package com.example.springDev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.springDev.domain.User;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserInfoDto {
    private int id;
    private String userName;

    public static UserInfoDto createUserDto(User user){
        return new UserInfoDto(user.getId(),user.getUserName());
    }
}
