package com.example.a24c;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    BMICalculating patient = new BMICalculating();
    Button calBtn, quitBtn, subScreenBtn, subQuitBtn;
    EditText patientHeight, patientWeight, patientName, BMI, diagnosis;
    TextView subTvName, subTvHeight, subTvWeight, subTvDiagnosis;
    ConstraintLayout subScreen;
    boolean isError = true;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toast.makeText(this, "Welcome to BMI app", Toast.LENGTH_SHORT).show();

        calBtn = findViewById(R.id.calBtn);
        patientHeight = findViewById(R.id.etHeight);
        patientWeight = findViewById(R.id.etWeight);
        patientName =  findViewById(R.id.etName);
        BMI = findViewById(R.id.etBMI);
        diagnosis = findViewById(R.id.etDiagnosis);
        quitBtn = findViewById(R.id.quitBtn);

        calBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                subScreenBtn.setVisibility(View.VISIBLE);

                try {
                    Editable heightValue = patientHeight.getText();
                    Editable weightValue = patientWeight.getText();

                    if (heightValue == null || weightValue == null) {
                        throw new NullPointerException();
                    }

                    patient.setHeight(Double.parseDouble(heightValue.toString()));
                    patient.setWeight(Double.parseDouble(weightValue.toString()));

                    if ((Double.parseDouble(heightValue.toString()) < 0 ||
                            Double.parseDouble(weightValue.toString()) < 0)) {
                        throw new IllegalArgumentException();
                    }

                    BMI.setText(Double.toString(patient.calBMI()));
                    diagnosis.setText(patient.diagnosisMessage(patient.calBMI()));

                    isError = false;
                }
                catch (IllegalArgumentException i) {
                    Toast.makeText(MainActivity.this, "Nhap du lieu sai", Toast.LENGTH_SHORT).show();
                }
                catch (NullPointerException n) {
                    Toast.makeText(MainActivity.this, "Khong duoc de trong", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Loi khong xac dinh", Toast.LENGTH_SHORT).show();
                }
                finally {
                    if (isError) {
                        Toast.makeText(MainActivity.this, "Vui long nhap lai chinh xac", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Thanh cong!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        subScreen = findViewById(R.id.subScreen);
        subScreenBtn = findViewById(R.id.subScreenBtn);
        subTvHeight = findViewById(R.id.subTvHeight);
        subTvWeight = findViewById(R.id.subTvWeight);
        subTvName = findViewById(R.id.subTvName);
        subTvDiagnosis = findViewById(R.id.subTvDiagnosis);

        subScreenBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                subScreen.setVisibility(View.VISIBLE);

                PrintProfile profile = new PrintProfile();


                subTvName.setText("Ten: " + patientName.getText().toString());
                subTvHeight.setText("Chieu cao: " + patientHeight.getText().toString());
                subTvWeight.setText("Can nang: " + patientWeight.getText().toString());
                subTvDiagnosis.setText("Tinh trang: " + profile.printDiagnosis(Double.parseDouble(patientHeight.getText().toString()), Double.parseDouble(patientWeight.getText().toString())));
            }
        });

        subQuitBtn = findViewById(R.id.subQuitBtn);

        subQuitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subScreen.setVisibility(View.GONE);
            }
        });

    }
}