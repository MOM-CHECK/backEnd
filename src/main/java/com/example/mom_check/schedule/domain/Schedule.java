package com.example.mom_check.schedule.domain;

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

@Table(name="schedule")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String time;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Builder
    public Schedule(String date, String time, String color, String content, User user) {
        this.date = date;
        this.time = time;
        this.color = color;
        this.content = content;
        this.user = user;
    }

    public void update(String date, String time, String color, String content) {
        this.date = date;
        this.time = time;
        this.color = color;
        this.content = content;
    }
}
