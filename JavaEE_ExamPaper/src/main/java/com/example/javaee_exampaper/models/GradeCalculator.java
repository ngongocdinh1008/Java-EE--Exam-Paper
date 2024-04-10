package com.example.javaee_exampaper.models;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GradeCalculator {

    public static Grade calculateGrade(BigDecimal score1, BigDecimal score2) {
        if (score1 == null || score2 == null) {
            throw new IllegalArgumentException("Điểm không được null!");
        }

        if (score1.compareTo(BigDecimal.ZERO) < 0 || score2.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Điểm không được âm!");
        }

        if (score1.compareTo(BigDecimal.TEN) > 0 || score2.compareTo(BigDecimal.TEN) > 0) {
            throw new IllegalArgumentException("Điểm không được lớn hơn 10!");
        }

        BigDecimal averageScore = (score1.add(score2)).divide(new BigDecimal(2), RoundingMode.HALF_UP);
        return Grade.fromAverageScore(averageScore.doubleValue());
    }
}
