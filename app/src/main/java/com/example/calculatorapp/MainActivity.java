package com.example.calculatorapp;

import static android.content.Context.*;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.mozilla.javascript.Scriptable;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_result, tv_solution;
    MaterialButton button_c, button_open_bracket, button_close_bracket;
    MaterialButton button_divide, button_multiple, button_subtract, button_add, button_equalto;
    MaterialButton button_0, button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9;
    MaterialButton button_ac, button_dot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_result = findViewById(R.id.tv_result);
        tv_solution = findViewById(R.id.tv_solution);

        assignId(button_c, R.id.button_c);
        assignId(button_open_bracket, R.id.button_open_bracket);
        assignId(button_close_bracket, R.id.button_close_bracket);
        assignId(button_divide, R.id.button_divide);
        assignId(button_multiple, R.id.button_multiple);
        assignId(button_add, R.id.button_add);
        assignId(button_subtract, R.id.button_subtract);
        assignId(button_equalto, R.id.button_equalto);
        assignId(button_0, R.id.button_0);
        assignId(button_1, R.id.button_1);
        assignId(button_2, R.id.button_2);
        assignId(button_3, R.id.button_3);
        assignId(button_4, R.id.button_4);
        assignId(button_5, R.id.button_5);
        assignId(button_6, R.id.button_6);
        assignId(button_7, R.id.button_7);
        assignId(button_8, R.id.button_8);
        assignId(button_9, R.id.button_9);
        assignId(button_ac, R.id.button_ac);
        assignId(button_dot, R.id.button_dot);


    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = tv_solution.getText().toString();

        if (buttonText.equals("AC")) {
            tv_solution.setText("");
            tv_result.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            tv_solution.setText(tv_result.getText());
            return;
        }
        if (buttonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }
        tv_solution.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Err")) {
            tv_result.setText(finalResult);
        }

    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            return "Err";
        }
    }
}

