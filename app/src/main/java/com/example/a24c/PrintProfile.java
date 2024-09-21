package com.example.a24c;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class PrintProfile extends BMICalculating{

    public PrintProfile(double height, double weight) {
        super(height, weight);
    }

    public PrintProfile() {
    }

    protected String printDiagnosis(double height, double weight) {
        try {
            double check = weight / (height/100 * height/100);
            if (check < 18.5) {
                return "Thieu can";
            } else if (check >= 18.5 && check <= 24.9) {
                return "Can doi";
            } else if (check > 24.9 && check < 30) {
                return "Thua can";
            } else if (check >= 30 && check <= 35) {
                return "Beo phi";
            } else if (check > 35) {
                return "Beo phi rat nang (ki)";
            }
        }
        catch (Exception e) {
            Log.d("Error", "Loi khong co du lieu");
        }
        return "";
    }
}
