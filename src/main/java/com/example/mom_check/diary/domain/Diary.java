package com.example.mom_check.diary.domain;

import com.example.mom_check.common.domain.BaseTimeEntity;
import com.example.mom_check.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@Table(name="diary")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String icon;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Builder
    public Diary(String title, String icon, String content, String date, User user) {
        this.title = title;
        this.icon = icon;
        this.content = content;
        this.date = date;
        this.user = user;
    }

    public void update(String title, String icon, String content) {
        this.title = title;
        this.icon = icon;
        this.content = content;
    }
}
