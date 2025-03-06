package com.example.springDev.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String password;

    @Column(unique = true)
    private String userEmail;

    private String role;//ROLE_USER,ROLE_ADMIN

    @CreationTimestamp
    private Timestamp createDate;


}
