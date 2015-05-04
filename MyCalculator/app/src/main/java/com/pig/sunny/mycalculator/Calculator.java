package com.pig.sunny.mycalculator;

/**
 * Created by SUNNY on 5/1/2015.
*/


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Calculator extends Activity implements OnClickListener {
    private TextView mCalculatorDisplay;
    private Boolean userIsInTheMiddleOfTypingANumber = false;
    private logic mLogic;
    private static final String DIGITS = "0123456789.";
    private Button Advance;
    String mCalculatorDisplay1;
    Context context;
    Intent intent;

    DecimalFormat df = new DecimalFormat("@###########");

      protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        mLogic = new logic();
        mCalculatorDisplay = (TextView) findViewById(R.id.textView1);

        df.setMinimumFractionDigits(0);
        df.setMinimumIntegerDigits(1);
        df.setMaximumIntegerDigits(8);

        Advance = (Button) findViewById(R.id.buttonAdvance);




          Advance.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  //   String xyz=(String)mCalculatorDisplay.getText();
                  //   Advanceswitch(xyz);
                  mCalculatorDisplay1 = mCalculatorDisplay.getText().toString();
                  intent = new Intent(Calculator.this, AdvanceView.class);
                  intent.putExtra("mCalculatorDisplay1", mCalculatorDisplay1);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);

            }
        });

        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);

        findViewById(R.id.buttonAdd).setOnClickListener(this);
        findViewById(R.id.buttonSubtract).setOnClickListener(this);
        findViewById(R.id.buttonMultiply).setOnClickListener(this);
        findViewById(R.id.buttonDivide).setOnClickListener(this);
        findViewById(R.id.buttonDecimalPoint).setOnClickListener(this);
        findViewById(R.id.buttonEquals).setOnClickListener(this);
        findViewById(R.id.buttonClear).setOnClickListener(this);
        findViewById(R.id.buttonDelete).setOnClickListener(this);

          intent = getIntent();

         mCalculatorDisplay1 = intent.getStringExtra("mCalculatorDisplay1");
         mCalculatorDisplay.setText(mCalculatorDisplay1);

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


//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putDouble("OPERAND", mLogic.getResult());
//        outState.putDouble("MEMORY", mLogic.getMemory());
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        mLogic.setOperand(savedInstanceState.getDouble("OPERAND"));
//        mLogic.setMemory(savedInstanceState.getDouble("MEMORY"));
//        mCalculatorDisplay.setText(df.format(mLogic.getResult()));
//    }
}
