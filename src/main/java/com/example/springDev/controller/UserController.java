package com.example.springDev.controller;

import com.example.springDev.domain.User;
import com.example.springDev.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<Void> join(@RequestBody User user){
        System.out.println("회원가입 회원"+ user);
        userService.joinMember(user);
        System.out.println("가입완료 ✅");
        return ResponseEntity.ok().build();
    }


    @GetMapping("/loginOk")
    public ResponseEntity<Map<String,String>> loginOk(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        System.out.println("memberEmail authentication"+email);
        String authorities = authentication.getAuthorities().toString();



        Map<String,String> userInfo=createUserInfo(email,authorities);

        return ResponseEntity.ok(userInfo);
    }

    @GetMapping("/logoutOk")
    public ResponseEntity<Void> logoutOk(){
        System.out.println("로그아웃 완료");
        return ResponseEntity.ok().build();
    }



    @GetMapping("/user")
    public ResponseEntity<User> getMemberPage(){
        System.out.println("회원 페이지입니다!");

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String email=authentication.getName();

        //유저정보
        User user = userService.getUserInfo(email);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/user/delete")
    public ResponseEntity<String> delete(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        System.out.println("탈퇴하려는 이메일: "+email);
        System.out.println("권한 확인: "+authentication.getAuthorities());
        User user = userService.getUserInfo(email);
        System.out.println("유저 정보: "+user);
        if(email!=null){
            userService.delete(user.getId());
            return ResponseEntity.ok().body("탈퇴 성공😊");
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("탈퇴 실패💥");
        }
    }
    private Map<String, String> createUserInfo(String email, String authorities) {
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("email", email);
        userInfo.put("authorities", authorities);
        return userInfo;
    }
}
