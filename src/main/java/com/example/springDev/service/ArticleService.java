package com.example.springDev.service;

import com.example.springDev.domain.Article;
import com.example.springDev.domain.User;
import com.example.springDev.dto.ArticleRequestDto;
import com.example.springDev.dto.ArticleResponseDto;
import com.example.springDev.dto.UserInfoDto;
import com.example.springDev.repository.ArticleRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    //게시물 전체 조회
    public List<ArticleResponseDto> articleList(){
        List<Article> articleList=articleRepository.findAll();
        List<ArticleResponseDto> responseDto=new ArrayList<>();

        for(Article article : articleList){
            ArticleResponseDto dto=ArticleResponseDto.builder().id(article.getId()).content(article.getContent()).title(article.getTitle()).createDate(article.getCreateDate()).userInfoDto(UserInfoDto.createUserDto(article.getUser())).build();
            responseDto.add(dto);
        }
        return responseDto;
    }



    //게시글 생성
    public void create(ArticleRequestDto articleRequestDto,User user){

      String title= articleRequestDto.getTitle();
      String content= articleRequestDto.getContent();
        System.out.println("입력된 title"+title);
        System.out.println("입력된 content"+content);
        System.out.println("입력된 게시글의 user:"+user);
        Article article= Article.createArticle(articleRequestDto,user);
        articleRepository.save(article);
    }
    //게시글 삭제
    public void delete(Long articleId){
        Article article=articleRepository.findByArticleId(articleId);
        articleRepository.delete(article);

    }

    //게시물 정보 가져오기
    public Article getArticleInfo(Long articleId){
        Article article=articleRepository.findByArticleId(articleId);
        return article;
    }
}
