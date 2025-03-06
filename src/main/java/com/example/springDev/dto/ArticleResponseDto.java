package com.example.springDev.dto;

import com.example.springDev.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;

    //유저 메이저 정보 포함
    private UserInfoDto userInfoDto;

    public static ArticleResponseDto createArticleDto(Article nov){
        UserInfoDto userInfo=nov.getUser()!=null?UserInfoDto.createUserDto(nov.getUser()):null;

        return new ArticleResponseDto(
                nov.getId(),
                nov.getTitle(),
                nov.getContent(),
                nov.getCreateDate(),
                userInfo

        );
    }
}
