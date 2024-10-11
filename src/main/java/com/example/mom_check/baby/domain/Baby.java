package com.example.mom_check.baby.domain;

import com.example.mom_check.common.domain.BaseTimeEntity;
import com.example.mom_check.common.type.SexType;
import com.example.mom_check.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@Table(name="baby")
@Entity
@Getter
@NoArgsConstructor
public class Baby extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String expectedBirth;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SexType sex;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false, unique = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Builder
    public Baby(String name, String expectedBirth, SexType sex, User user) {
        this.name = name;
        this.expectedBirth = expectedBirth;
        this.sex = sex;
        this.user = user;
    }
}
