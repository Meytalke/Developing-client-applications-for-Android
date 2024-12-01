package com.example.myfirstapplicationhit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView result;
    private String opration = "";
    private double num1;
    private double num2;
    private double resOfEqual;
    private boolean isNewCalculation = true;
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
        result = findViewById(R.id.textViewResult);
        result.setText("");
    }
    public void numFunc(View view) {
        Button numButton = (Button) view;
        if (isNewCalculation) {
            result.setText("");
            isNewCalculation = false;
        }
        String currentText = result.getText().toString();
        if(currentText.equals("Invalid input")) {
            result.setText(numButton.getText().toString());
        }
        else {
            result.append(numButton.getText().toString());
        }
    }
    public void oprationFunc(View view) {
        Button oprationButton = (Button) view;
        String currentText = result.getText().toString();
        if (currentText.isEmpty() || currentText.equals("Invalid input")) {
            result.setText("Invalid input");
            return;
        }
        opration = oprationButton.getText().toString();
        if (opration.equals("xʸ")) {
            opration = "^";
        }
        try {
            num1 = Double.parseDouble(currentText);
        } catch (NumberFormatException e) {
            result.setText("Invalid input");
            return;
        }
        result.setText("");
    }
    public void equalFunc(View view) {
        if (opration.isEmpty()) {
            result.setText("No operation selected");
            return;
        }
        if (result.getText().toString().isEmpty() && !opration.equals("√")) {
            result.setText("Please enter a number");
            return;
        }
        try {
            if (!opration.equals("√")) {
                num2 = Double.parseDouble(result.getText().toString());
            }
        } catch (NumberFormatException e) {
            result.setText("Invalid input");
            return;
        }

        if (result.getText().toString().equals("Invalid")) {
            result.setText("");
            return;
        }

        resOfEqual = num1;

        switch (opration) {
            case "+":
                resOfEqual += num2;
                break;
            case "-":
                resOfEqual -= num2;
                break;
            case "*":
                resOfEqual *= num2;
                break;
            case "/":
                if (num2 != 0) {
                    resOfEqual /= num2;
                } else {
                    result.setText("Invalid input\nCannot divide by zero!");
                    return;
                }
                break;
            case "^":
                resOfEqual = Math.pow(num1, num2);
                break;
            case "%":
                resOfEqual = num1 * (num2 / 100);
                break;
            case "√":
                if (num1 >= 0) {
                    resOfEqual = Math.sqrt(num1);
                } else {
                    result.setText("Invalid input\nCannot calculate square root of a negative number!");
                    return;
                }
                break;
            default:
                result.setText("Invalid Operation!");
                return;
        }
        result.setText(String.format("%.2f", resOfEqual));
        num1 = resOfEqual;
    }
    public void clearFunc(View view) {
        num1=0;
        num2=0;
        result.setText("");
        isNewCalculation = true;
        opration = "";
        resOfEqual=0;
    }
    public void decimalFunc(View view){
        String currentText = result.getText().toString();
        if (currentText.isEmpty()) {
            result.setText("0.");
            isNewCalculation = false;
            return;
        }
        if (currentText.contains(".")) {
            return;
        }
        result.append(".");
        isNewCalculation = false;
    }
}