package com.example.springDev.service;

import com.example.springDev.domain.User;
import com.example.springDev.dto.UserRequestDto;
import com.example.springDev.dto.UserResponseDto;
import com.example.springDev.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinMember(User user){
        String rawPassword = user.getPassword();
        String encPassword=bCryptPasswordEncoder.encode(rawPassword);
        System.out.println("비밀번호 인코딩: "+encPassword);
        user.setPassword(encPassword);
        user.setRole("ROLE_MEMBER");
        userRepository.save(user);
    }

    public User getUserInfo(String userEmail){
        User user = userRepository.findByUserEmail(userEmail);
        return user;
    }
    //json형태로 반환하기
    public UserResponseDto findBy(final UserRequestDto params){
        UserResponseDto entity= userRepository.findByUserEmailAndPassword(params.getEmail(), params.getPassword());
        return entity;
    }

    //회원 탈퇴
    public void delete(int id){
        //이메일로 탈퇴
        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("회원을 찾을 수 없습니다"));
        userRepository.delete(user);
    }
}
