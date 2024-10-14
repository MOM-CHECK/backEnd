package com.example.mom_check.weight.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name="weight_gain_recommendations")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WeightGainRecommendation {
    @Id
    @Column
    private UUID id;

    @Column(nullable = false)
    private Integer week;

    @Column(nullable = false)
    private String bmi;

    @Column(nullable = false)
    private Double min;

    @Column(nullable = false)
    private Double max;
}
