package com.anyangProject.anyangNoticeBoardProject.entitiy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    @ManyToOne
    private UserEntity author;

    private LocalDateTime modifyDate;

    @ManyToMany
    Set<UserEntity> voter;
}
