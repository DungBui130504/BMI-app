package com.example.a24c;

import android.util.Log;

public class BMICalculating {
    double height;
    double weight;

    public BMICalculating(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }

    public BMICalculating() {
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    protected double calBMI() {
        double result;
         result = this.weight / (this.height/100 * this.height/100);
         return  result;
    }

    protected String diagnosisMessage(double result) {
        if (result < 18.5) {
            return "Ban dang thieu can";
        } else if (result >= 18.5 && result <= 24.9) {
            return "Ban rat can doi";
        } else if (result > 24.9 && result < 30) {
            return "Ban dang thua can";
        } else if (result >= 30 && result <= 35) {
            return "Ban dang beo phi";
        }
        return "Ban dang bi beo phi rat nang (ki)";
    }
}
