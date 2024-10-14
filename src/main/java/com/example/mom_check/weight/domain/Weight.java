package com.example.mom_check.weight.domain;

import com.example.mom_check.common.domain.BaseTimeEntity;
import com.example.mom_check.common.type.WeightStatusType;
import com.example.mom_check.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.UUID;

@Table(name="weight")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Weight extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private LocalDate date;

    @Column(nullable = false)
    private Double weight;

    @Column()
    @Enumerated(EnumType.STRING)
    private WeightStatusType status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Builder
    public Weight(LocalDate date, Double weight, WeightStatusType status, User user) {
        this.date = date;
        this.weight = weight;
        this.status = status;
        this.user = user;
    }

    public void update(WeightStatusType status) {
        this.status = status;
    }
}
