package com.pig.sunny.mycalculator;

/**
 * Created by SUNNY on 5/1/2015.
 */


public class logic {
    private double mOperand;
    private double mWaitingOperand;
    private String mWaitingOperator;
    private double mCalculatorMemory;

    public static final String ADD = "+";
    public static final String SUBTRACT = "-";
    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";

    public static final String CLEAR = "C";
    public static final String SQUAREROOT = "√";

    public static final String INVERT = "1/x";
    /* public static final String TOGGLESIGN = "+/-";*/
    public static final String SINE = "sin";
    public static final String COSINE = "cos";
    public static final String TANGENT = "tan";
    public static final String FACTORIAL = "!";
    public static final String PIE = "π";
    public static final String PERCENTAGE = "%";
    public static final String EXPONENTIAL = "^";
    public static  final String E = "e";
    public static  final String NATURALLOG = "ln";
    public static  final String LOG = "log";


    public logic() {
        mOperand = 0;
        mWaitingOperand = 0;
        mWaitingOperator = "";
        mCalculatorMemory = 0;
    }

    public void setOperand(double operand) {
        mOperand = operand;
    }

    public double getResult()
    {
        return mOperand;
    }

    public void setMemory(double calculatorMemory)
    {
        mCalculatorMemory = calculatorMemory;
    }

    public double getMemory()
    {
        return mCalculatorMemory;
    }

    public String toString()
    {
        return Double.toString(mOperand);
    }

    protected double performOperation(String operator) {

        switch (operator) {
            case CLEAR:
                mOperand = 0;
                mWaitingOperator = "";
                mWaitingOperand = 0;
                break;

            case INVERT:
                if (mOperand != 0) {
                    mOperand = 1 / mOperand;
                }
                break;

            case SINE:
                mOperand = Math.sin(Math.toRadians(mOperand));
                break;

            case COSINE:
                mOperand = Math.cos(Math.toRadians(mOperand));
                break;

            case TANGENT:
                mOperand = Math.tan(Math.toRadians(mOperand));
                break;

            case FACTORIAL:
                double fact = 1;
                if (mOperand == 0)
                {
                    mOperand = 1;

                }
                else
                {
                    for (int i = 1; i <= mOperand; i++) {
                        fact *= i;
                    }
                    mOperand = fact;
                }
                break;

            case PERCENTAGE:
                mOperand = mOperand/100;
                break;

            case SQUAREROOT:
                mOperand = Math.sqrt(mOperand);
                break;

            case PIE:
                if(mOperand==0)
                {
                    mOperand=3.1415926535897932384626433832795;
                }
                else
                {
                    mOperand = mOperand * 3.1415926535897932384626433832795;
                }
                break;

            case E:
                if(mOperand==0)
                {
                    mOperand=2.7182818284590452353602874713527;
                }
                else
                {
                    mOperand = mOperand * 2.7182818284590452353602874713527;
                }
                break;

            case NATURALLOG:
                mOperand = Math.log(mOperand);
            break;

            case LOG :
                mOperand = Math.log10(mOperand);
                break;

            default:
                performWaitingOperation();
                mWaitingOperator = operator;
                mWaitingOperand = mOperand;
        }
        return mOperand;
    }

    protected void performWaitingOperation() {

        switch (mWaitingOperator) {

            case ADD:
                mOperand = mWaitingOperand + mOperand;
            break;

            case SUBTRACT:
                mOperand = mWaitingOperand - mOperand;
            break;

            case MULTIPLY:
                mOperand = mWaitingOperand * mOperand;
            break;

            case DIVIDE:
                if (mOperand != 0)
                {
                    mOperand = mWaitingOperand / mOperand;
                }
            break;

            case EXPONENTIAL:
                mOperand = nonNegInt(mOperand,mWaitingOperand);
            break;
        }
    }

    public static double nonNegInt(double base, double pow)
    {
        if (pow == 0)
            return 1;
        else if(pow < 0)
            return (1 / nonNegInt(base, -pow));
        else
            return base * nonNegInt(base,pow-1);
    }
}
