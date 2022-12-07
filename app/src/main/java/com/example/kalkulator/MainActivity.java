package com.example.kalkulator;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultView, solutionView;
    MaterialButton buttonC, buttonOpenBracket, buttonCloseBracket;
    MaterialButton buttonDivide, buttonMultiply, buttonSubstract, buttonAdd, buttonEquals;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonClear, buttonPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultView = findViewById(R.id.result_view);
        solutionView = findViewById(R.id.solution_view);

        assignID(buttonC, R.id.button_c);
        assignID(buttonOpenBracket, R.id.button_open_bracket);
        assignID(buttonCloseBracket, R.id.button_close_bracket);
        assignID(buttonDivide, R.id.button_divide);
        assignID(buttonMultiply, R.id.button_multiply);
        assignID(buttonSubstract, R.id.button_substract);
        assignID(buttonAdd, R.id.button_add);
        assignID(buttonEquals, R.id.button_equals);
        assignID(button0, R.id.button_0);
        assignID(button1, R.id.button_1);
        assignID(button2, R.id.button_2);
        assignID(button3, R.id.button_3);
        assignID(button4, R.id.button_4);
        assignID(button5, R.id.button_5);
        assignID(button6, R.id.button_6);
        assignID(button7, R.id.button_7);
        assignID(button8, R.id.button_8);
        assignID(button9, R.id.button_9);
        assignID(buttonClear, R.id.button_clear);
        assignID(buttonPoint, R.id.button_point);

    }

// get btn id onClick event
    void assignID(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//        call action on click btn
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
//        display btn text in solutionView
//        solutionView.setText(buttonText);
//        data to calculate
        String CalcData = solutionView.getText().toString();

//      function btns events
        if(buttonText.equals("DEL")){
            solutionView.setText("");
            resultView.setText("0");
            return;
        }

        if(buttonText.equals("=")){
            solutionView.setText(resultView.getText());
            return;
        }

        if(buttonText.equals("C")){
            CalcData = CalcData.substring(0,CalcData.length()-1);
        }
        else {
            CalcData += buttonText;
        }

        solutionView.setText(CalcData);

        String CalcResult = getResult(CalcData);

        if (!CalcResult.equals("Err")){
            resultView.setText(CalcResult);
        }

    }

    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String CalcResult = (String) context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(CalcResult.endsWith(".0")){
                CalcResult = CalcResult.replace(".0","");
            }
            return CalcResult;
        } catch (Exception e){
            return "Error!";
        }
    }
}