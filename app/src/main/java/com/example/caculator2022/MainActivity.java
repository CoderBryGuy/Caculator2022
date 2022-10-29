package com.example.caculator2022;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewHistory, mTextViewResult;
    private Button mBtnAC, mBtnDel, mBtnDiv, mBtnMulti, mBtnMinus, mBtnPlus, mBtnEquals, mBtnDot,
            mBtn0, mBtn1, mBtn2, mBtn3, mBtn4, mBtn5, mBtn6, mBtn7, mBtn8, mBtn9;

    private String mNumber = null, mStatus = null, mHistory, mCurrentResult;
    private double mFirstNum = 0.0d, mLastNum = 0.0d;

    private boolean mOperator = false;

    private static final String TAG = "MainActivity";

    private static final String
            STATUS_MULTI = "multiplication_status",
            STATUS_DIV = "division_status",
    // tutorial uses 'sum' instead of 'plus'
//            STATUS_PLUS = "plus_status",
    STATUS_MINUS = "minus_status",
            STATUS_SUM = "sum_status";

    DecimalFormat mFormatter = new DecimalFormat("######.######");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: started");

        mTextViewHistory = findViewById(R.id.textViewHistory);
        mTextViewResult = findViewById(R.id.textViewResult);
        mBtnAC = findViewById(R.id.btnAC);
        mBtnDel = findViewById(R.id.btnDel);
        mBtnDiv = findViewById(R.id.btnDiv);
        mBtnMulti = findViewById(R.id.btnMult);
        mBtnMinus = findViewById(R.id.btnMinus);
        mBtnPlus = findViewById(R.id.btnPlus);
        mBtnEquals = findViewById(R.id.btnEquals);
        mBtnDot = findViewById(R.id.btnDot);
        mBtn0 = findViewById(R.id.btn0);
        mBtn1 = findViewById(R.id.btn1);
        mBtn2 = findViewById(R.id.btn2);
        mBtn3 = findViewById(R.id.btn3);
        mBtn4 = findViewById(R.id.btn4);
        mBtn5 = findViewById(R.id.btn5);
        mBtn6 = findViewById(R.id.btn6);
        mBtn7 = findViewById(R.id.btn7);
        mBtn8 = findViewById(R.id.btn8);
        mBtn9 = findViewById(R.id.btn9);

//=================================
//*** number buttons onclick***
//=================================

        mBtn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick(getString(R.string._0_btn));
            }
        });

        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: btn1");
                numberClick(getString(R.string._1_btn));
            }
        });

        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: btn2");
                numberClick(getString(R.string._2_btn));
            }
        });

        mBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: btn3");
                numberClick(getString(R.string._3_btn));
            }
        });

        mBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: btn4");
                numberClick(getString(R.string._4_btn));
            }
        });

        mBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: btn5");
                numberClick(getString(R.string._5_btn));
            }
        });

        mBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: btn6");
                numberClick(getString(R.string._6_btn));
            }
        });

        mBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: btn7");
                numberClick(getString(R.string._7_btn));
            }
        });

        mBtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: btn8");
                numberClick(getString(R.string._8_btn));
            }
        });

        mBtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: btn9");
                numberClick(getString(R.string._9_btn));
            }
        });
//======================================
//*** operator buttons onclick***
//======================================
        mBtnAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNumber = null;
                mStatus = null;
                mTextViewResult.setText("0");
                mTextViewHistory.setText("");
                mFirstNum = 0;
                mLastNum = 0;
            }
        });

        mBtnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: del, mNumber = " + mNumber);
                    mNumber = mNumber.substring(0, mNumber.length()-1);
                    mTextViewResult.setText(mNumber);
            }
        });
        mBtnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: div");
                updateTextViewHistory("/");
                mStatus = STATUS_DIV;
                setOperator();

            }
        });

        mBtnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: multi");
                updateTextViewHistory("*");
                mStatus = STATUS_MULTI;
                setOperator();

            }
        });

        mBtnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: minus");
                updateTextViewHistory("-");
                mStatus = STATUS_MINUS;
                setOperator();


            }
        });

        mBtnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: plus");
                updateTextViewHistory("+");
                mStatus = STATUS_SUM;
                setOperator();
//                if (mOperator) {
//                    switch (mStatus) {
//                        case STATUS_MULTI:
//                            multiply();
//                            break;
//                        case STATUS_DIV:
//                            divide();
//                            break;
//                        case STATUS_SUM:
//                            plus();
//                            break;
//                        case STATUS_MINUS:
//                            minus();
//                            break;
//                        default:
//                            break;
//                    }
//
//                    mStatus = STATUS_SUM;
//                    mOperator = false;
//                    mNumber = null;
//                }
            }
        });


        mBtnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: equals");
                setOperator();
            }
        });

        mBtnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: dot");
                if(!mNumber.contains(".")) {
                    if (mNumber == null) {
                        mNumber = ".0";
                    } else {
                        mNumber += ".";
                    }

                    mTextViewResult.setText(mNumber);
                }else{
                    Log.d(TAG, "onClick: mNumber already has '.' mNumber = " + mNumber);
                }
            }
        });

        Log.d(TAG, "onCreate: ended");
    }

    public void updateTextViewHistory(String operator){
        Log.d(TAG, "updateTextViewHistory: operator = " + operator);
        mHistory = mTextViewHistory.getText().toString();
        mCurrentResult = mTextViewResult.getText().toString();
        mTextViewHistory.setText(mHistory + mCurrentResult + operator);
    }
    public void setOperator() {


        if (mOperator) {
            switch (mStatus) {
                case STATUS_MULTI:
                    Log.d(TAG, "setOperator: multi");
                    multiply();
                    break;
                case STATUS_DIV:
                    Log.d(TAG, "setOperator: div");
                    divide();
                    break;
                case STATUS_SUM:
                    Log.d(TAG, "setOperator: plus");
                    plus();
                    break;
                case STATUS_MINUS:
                    Log.d(TAG, "setOperator: minus");
                    minus();
                    break;
                default:
                    Log.d(TAG, "setOperator: default: btnEquals");
                    //enters if btnEquals clicked
                    mFirstNum = Double.parseDouble(mTextViewResult.getText().toString());
                    mOperator = false;
                    return;
            }

            mOperator = false;
            mNumber = null;
        }
    }

    //======================================
//*** math methods***
//======================================
    public void plus() {
        Log.d(TAG, "plus: started");
        mLastNum = Double.parseDouble(mTextViewResult.getText().toString());
        mFirstNum += mLastNum;
        mTextViewResult.setText(mFormatter.format(mFirstNum));
    }

    public void minus() {
        Log.d(TAG, "minus: started");
        if (mFirstNum == 0) {
            mFirstNum = Double.parseDouble(mTextViewResult.getText().toString());
        } else {
            mLastNum = Double.parseDouble(mTextViewResult.getText().toString());
            mFirstNum -= mLastNum;
        }
        mTextViewResult.setText(mFormatter.format(mFirstNum));
    }

    public void multiply() {
        Log.d(TAG, "multiply: started");

//        if(mFirstNum == 0){
//            mFirstNum =1;
//            mLastNum = Double.parseDouble(mTextViewResult.getText().toString());
//            mFirstNum *= mLastNum;
//        }else{
//        mLastNum = Double.parseDouble(mTextViewResult.getText().toString());
//            mFirstNum *= mLastNum;
//        }

        if (mFirstNum == 0) {
            mFirstNum = 1;
        }

        mLastNum = Double.parseDouble(mTextViewResult.getText().toString());
        mFirstNum *= mLastNum;
        mTextViewResult.setText(mFormatter.format(mFirstNum));
    }

    public void divide() {
        Log.d(TAG, "divide: started");
        if (mFirstNum == 0) {
//            mTextViewResult.setText(R.string.undefined_division);
            mLastNum = Double.parseDouble(mTextViewResult.getText().toString());
            mFirstNum = mLastNum;
        } else {
            mLastNum = Double.parseDouble(mTextViewResult.getText().toString());
            mFirstNum /= mLastNum;
        }
        mTextViewResult.setText(mFormatter.format(mFirstNum));
    }

    public void numberClick(String view) {
        Log.d(TAG, "numberClick: started, view = " + view);
        if (mNumber == null) {
            mNumber = view;
        } else {
           if(mNumber.equals(".0")){
               mNumber = "." + view;
           }else {
               mNumber += view;
           }
        }
        mTextViewResult.setText(mNumber);
        mOperator = true;
    }


}