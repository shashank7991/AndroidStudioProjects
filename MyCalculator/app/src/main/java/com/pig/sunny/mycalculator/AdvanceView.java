package com.pig.sunny.mycalculator;

/**
 * Created by SUNNY on 5/1/2015.
 */


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class AdvanceView extends Activity implements View.OnClickListener {

    String xyz;
    private Button btnBasic;
    private TextView mCalculatorDisplay;
    private Boolean userIsInTheMiddleOfTypingANumber = false;
    private logic mLogic;
    private static final String DIGITS = "0123456789.";
    private Button Advance;
    String mCalculatorDisplay1;
    Context context;
    Intent intent;

    DecimalFormat df = new DecimalFormat("@###########");


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.advanceview);

        mLogic = new logic();
        mCalculatorDisplay = (TextView) findViewById(R.id.textView1);

        df.setMinimumFractionDigits(0);
        df.setMinimumIntegerDigits(1);
        df.setMaximumIntegerDigits(8);

        btnBasic = (Button) findViewById(R.id.buttonBasic);

        mCalculatorDisplay = (TextView) findViewById(R.id.textView1);

        intent = getIntent();
        mCalculatorDisplay1 = intent.getStringExtra("mCalculatorDisplay1");
        mCalculatorDisplay.setText(mCalculatorDisplay1);


        btnBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalculatorDisplay1 = mCalculatorDisplay.getText().toString();

                Intent intent = new Intent(AdvanceView.this, Calculator.class);
                intent.putExtra("mCalculatorDisplay1", mCalculatorDisplay1);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonClear).setOnClickListener(this);
        findViewById(R.id.buttonDelete).setOnClickListener(this);
        findViewById(R.id.buttonE).setOnClickListener(this);
        findViewById(R.id.buttonPercentage).setOnClickListener(this);
        findViewById(R.id.buttonImaginary).setOnClickListener(this);
        findViewById(R.id.buttonSquareRoot).setOnClickListener(this);
        findViewById(R.id.buttonExponential).setOnClickListener(this);
        findViewById(R.id.buttonPi).setOnClickListener(this);
        findViewById(R.id.buttonNaturalLog).setOnClickListener(this);
        findViewById(R.id.buttonLog).setOnClickListener(this);
        findViewById(R.id.buttonTangent).setOnClickListener(this);
        findViewById(R.id.buttonSine).setOnClickListener(this);
        findViewById(R.id.buttonCosine).setOnClickListener(this);
        findViewById(R.id.buttonFactorial).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        String buttonPressed = ((Button) v).getText().toString();

        if (DIGITS.contains(buttonPressed)) {


            if (userIsInTheMiddleOfTypingANumber)
            {

                if (buttonPressed.equals(".") && mCalculatorDisplay.getText().toString().contains("."))
                {
                }
                else
                {
                    mCalculatorDisplay.append(buttonPressed);
                }

            }
            else
            {

                if (buttonPressed.equals("."))
                {
                    mCalculatorDisplay.setText(0 + buttonPressed);
                }
                else
                {
                    mCalculatorDisplay.setText(buttonPressed);
                }

                userIsInTheMiddleOfTypingANumber = true;
            }

        }
        else
        {
            // operation was pressed
            if (userIsInTheMiddleOfTypingANumber)
            {

                mLogic.setOperand(Double.parseDouble(mCalculatorDisplay.getText().toString()));
                userIsInTheMiddleOfTypingANumber = false;
            }

            mLogic.performOperation(buttonPressed);
            mCalculatorDisplay.setText(df.format(mLogic.getResult()));

        }

    }

//    public void onClick(View v) {
//
//        String buttonPressed = ((Button) v).getText().toString();
//
//            // operation was pressed
//            if (userIsInTheMiddleOfTypingANumber)
//            {
//
//                mLogic.setOperand(Double.parseDouble(mCalculatorDisplay.getText().toString()));
//                userIsInTheMiddleOfTypingANumber = false;
//            }
//
//            mLogic.performOperation(buttonPressed);
//            mCalculatorDisplay.setText(df.format(mLogic.getResult()));
//
//    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save variables on screen orientation change
        outState.putDouble("OPERAND", mLogic.getResult());
        outState.putDouble("MEMORY", mLogic.getMemory());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore variables on screen orientation change
        mLogic.setOperand(savedInstanceState.getDouble("OPERAND"));
        mLogic.setMemory(savedInstanceState.getDouble("MEMORY"));
        mCalculatorDisplay.setText(df.format(mLogic.getResult()));
    }
}
