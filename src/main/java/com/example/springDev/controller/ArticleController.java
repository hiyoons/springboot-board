package com.example.springDev.controller;

import com.example.springDev.domain.Article;
import com.example.springDev.domain.User;
import com.example.springDev.dto.ArticleRequestDto;
import com.example.springDev.dto.ArticleResponseDto;
import com.example.springDev.service.ArticleService;
import com.example.springDev.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userService;

    //전체 게시글 조회
    @GetMapping("/article/all")
    public List<ArticleResponseDto> allArticle(){
        List<ArticleResponseDto> articleResponseDtoList=articleService.articleList();
        return articleResponseDtoList;
    }

    //게시물 상세 조회
    @GetMapping("/article/{articleId}")
    public Article detailArticle(@PathVariable("articleId") Long articleId){
        //Article 정보 가져오기
        Article article=articleService.getArticleInfo(articleId);
        return article;
    }


    //게시글 생성
    @PostMapping("/article/{articleId}")
    public ResponseEntity<String> create(@RequestBody ArticleRequestDto articleRequestDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        //유저 정보 가져오기
        User user=userService.getUserInfo(email);

        if(email==null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 후에 이용하세요");
        articleService.create(articleRequestDto,user);
        return ResponseEntity.status(HttpStatus.OK).body("게시글 생성 완료✅");
    }

    //게시물 삭제
    @DeleteMapping("/article/{articleId}")
    public ResponseEntity<String> delete(@PathVariable("articleId") Long articleId){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String email=authentication.getName();
        User user=userService.getUserInfo(email);
        //Article 정보 가져오기
        Article article=articleService.getArticleInfo(articleId);

        if(user==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 후에 이용하세요");
        }
        if (user.getId()!=article.getUser().getId()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("이 글의 작성자가 아닙니다😥");

        }

        articleService.delete(articleId);
        return ResponseEntity.status(HttpStatus.OK).body("게시물 삭제 성공😊");
    }


    //게시물 수정

}
