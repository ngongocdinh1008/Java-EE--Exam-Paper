package com.example.javaee_exampaper.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GradeCalculator {
    public static String calculateGrade(BigDecimal score1, BigDecimal score2) {
        BigDecimal averageScore = (score1.add(score2)).divide(new BigDecimal(2), RoundingMode.HALF_UP);
        if (averageScore.compareTo(new BigDecimal(8.0)) >= 0) {
            return "A";
        } else if (averageScore.compareTo(new BigDecimal(7.0)) >= 0) {
            return "B";
        } else if (averageScore.compareTo(new BigDecimal(6.0)) >= 0) {
            return "C";
        } else if (averageScore.compareTo(new BigDecimal(5.0)) >= 0) {
            return "D";
        } else {
            return "F";
        }
    }
}
