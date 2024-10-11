package com.example.mom_check.user.domain;

import com.example.mom_check.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@Table(name="initial_physical")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InitialPhysical extends BaseTimeEntity {
    @Id
    @Column(name="user_id")
    private UUID id;

    @Column(nullable = false)
    private Double height;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private Double bmi;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false, unique = true, referencedColumnName = "id")
    @MapsId
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Builder
    public InitialPhysical(Double height, Double weight, Double bmi, User user) {
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.user = user;
    }
}
