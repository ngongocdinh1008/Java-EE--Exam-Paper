package com.example.javaee_exampaper.models;

public enum Grade {
    A(8.0),
    B(7.0),
    C(6.0),
    D(5.0),
    F(0.0);

    private final double minScore;

    Grade(double minScore) {
        this.minScore = minScore;
    }

    public static Grade fromAverageScore(double averageScore) {
        for (Grade grade : values()) {
            if (averageScore >= grade.minScore) {
                return grade;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return name();
    }
}
