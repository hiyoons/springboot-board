package com.example.springDev.domain;

import com.example.springDev.dto.ArticleRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Builder
@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name="user_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private User user;

    public static Article createArticle(ArticleRequestDto articleRequestDto,User user){
        return new Article(
                null,
                articleRequestDto.getTitle(),
                articleRequestDto.getContent(),
                LocalDateTime.now(),
                user
        );
    }

}
