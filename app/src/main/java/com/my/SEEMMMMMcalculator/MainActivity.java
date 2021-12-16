package com.my.SEEMMMMMcalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity {


    private static final String AD_UNIT_ID = "ca-app-pub-1738524015819512/6313774095";
    private double SEMexcess = 0;
    private double SEMpositive = 0;
    private double SEMnegative = 0;
    private String resultStr = "";
    private String steps = "";
    private String floatStr = "";
    private String floatStr2 = "";
    private double opt = 0;

    private ArrayList<String> calculatorSelection = new ArrayList<>();

    private LinearLayout linear1;
    private LinearLayout lowerlayout;
    private LinearLayout upperlayout;
    private TextView textview1;
    private LinearLayout linear7;
    private LinearLayout linear5;
    private LinearLayout linear6;
    private TextView textview2;
    private TextView textview3;
    private TextView textview4;
    private EditText edittext1;
    private EditText edittext2;
    private EditText edittext3;
    private TextView textview5;
    private Spinner spinner1;
    private EditText editoperand1;
    private EditText editoperand2;
    private LinearLayout linear8;
    private LinearLayout linear9;
    private Button calculatebtn;
    private Button resetbtn;
    private TextView textview6;
    private TextView result;
    private Button watchadsbtn;
    private QMUITopBar topbar;
    private AlertDialog.Builder dialog;

    private RewardedAd mRewardedAd;
    private ProgressDialog pb;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        initialize(_savedInstanceState);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {
        topbar = (QMUITopBar) findViewById(R.id.topbar);
        pb = new ProgressDialog(this);
        pb.setMessage("Loading");
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        lowerlayout = (LinearLayout) findViewById(R.id.lowerlayout);
        upperlayout = (LinearLayout) findViewById(R.id.upperlayout);
        textview1 = (TextView) findViewById(R.id.textview1);
        linear7 = (LinearLayout) findViewById(R.id.linear7);
        linear5 = (LinearLayout) findViewById(R.id.linear5);
        linear6 = (LinearLayout) findViewById(R.id.linear6);
        textview2 = (TextView) findViewById(R.id.textview2);
        textview3 = (TextView) findViewById(R.id.textview3);
        textview4 = (TextView) findViewById(R.id.textview4);
        edittext1 = (EditText) findViewById(R.id.edittext1);
        edittext2 = (EditText) findViewById(R.id.edittext2);
        edittext3 = (EditText) findViewById(R.id.edittext3);
        textview5 = (TextView) findViewById(R.id.textview5);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        editoperand1 = (EditText) findViewById(R.id.editoperand1);
        editoperand2 = (EditText) findViewById(R.id.editoperand2);
        linear8 = (LinearLayout) findViewById(R.id.linear8);
        linear9 = (LinearLayout) findViewById(R.id.linear9);
        calculatebtn = (Button) findViewById(R.id.calculatebtn);
        resetbtn = (Button) findViewById(R.id.resetbtn);
        textview6 = (TextView) findViewById(R.id.textview6);
        result = (TextView) findViewById(R.id.result);
        watchadsbtn = (Button) findViewById(R.id.watchadsbtn);
        dialog = new AlertDialog.Builder(this);

        topbar.setTitle("SEEMMMMM Calculator");
        topbar.setBackgroundAlpha(0);


        edittext1.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
                final String _charSeq = _param1.toString();
                if (!(edittext1.getText().toString().equals("") || edittext1.getText().toString().equals("-"))) {
                    SEMexcess = Double.parseDouble(edittext1.getText().toString());
                }
                if ((SEMexcess > 99) || (SEMexcess < 0)) {
                    edittext1.requestFocus();
                    result.setText("The excess cannot exceed 2 digit or being negative!");
                    edittext1.setTextColor(0xFFE91E63);
                } else {
                    result.setText("");
                    edittext1.setTextColor(0xFF000000);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {

            }

            @Override
            public void afterTextChanged(Editable _param1) {

            }
        });

        edittext2.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
                final String _charSeq = _param1.toString();
                if (!(edittext2.getText().toString().equals("") || edittext2.getText().toString().equals("-"))) {
                    SEMpositive = Double.parseDouble(edittext2.getText().toString());
                }
                if ((SEMpositive < 0) || (SEMpositive > 9)) {
                    edittext2.requestFocus();
                    result.setText("Positive representation must between 0 to 9");
                    edittext2.setTextColor(0xFFE91E63);
                } else {
                    result.setText("");
                    edittext2.setTextColor(0xFF000000);
                }
                if (SEMpositive == SEMnegative) {
                    edittext2.requestFocus();
                    result.setText("Positive representation cannot be same with negative representation");
                    edittext2.setTextColor(0xFFE91E63);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {

            }

            @Override
            public void afterTextChanged(Editable _param1) {

            }
        });

        edittext3.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
                final String _charSeq = _param1.toString();
                if (!(edittext3.getText().toString().equals("") || edittext3.getText().toString().equals("-"))) {
                    SEMnegative = Double.parseDouble(edittext3.getText().toString());
                }
                if ((SEMnegative < 0) || (SEMnegative > 9)) {
                    edittext3.requestFocus();
                    result.setText("Negative representation must between 0 to 9");
                    edittext3.setTextColor(0xFFE91E63);
                } else {
                    result.setText("");
                    edittext3.setTextColor(0xFF000000);
                }
                if (SEMpositive == SEMnegative) {
                    edittext3.requestFocus();
                    result.setText("Positive representation cannot be same with negative representation");
                    edittext3.setTextColor(0xFFE91E63);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {

            }

            @Override
            public void afterTextChanged(Editable _param1) {

            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
                final int _position = _param3;
                opt = spinner1.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> _param1) {

            }
        });

        calculatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                result.setTextColor(0xFF000000);
                resultStr = "";
                steps = "";
                floatStr = editoperand1.getText().toString();
                floatStr2 = editoperand2.getText().toString();
                if ((floatStr.length() == 8) && (floatStr2.length() == 8)) {
                    if (!(((SEMexcess == -1) && (spinner1.getSelectedItemPosition() == 2)) || ((SEMpositive == -1) || (SEMnegative == -1)))) {
                        resultStr = "SEEMMMMM : ";
                        int calcResult = 0;
                        int operation = (int) opt;
                        java.text.DecimalFormat decimalForm = new java.text.DecimalFormat("00000");
                        int sign1 = Integer.parseInt(String.valueOf(floatStr.charAt(0)));

                        int sign2 = Integer.parseInt(String.valueOf(floatStr2.charAt(0)));

                        int operand1 = Integer.parseInt(floatStr.substring(3, 8));

                        int operand2 = Integer.parseInt(floatStr2.substring(3, 8));

                        if (sign1 != (int) SEMnegative && sign1 != (int) SEMpositive) {
                            resultStr += "The first input has invalid sign (Assume positive)\n";
                        } else if (sign1 == (int) SEMnegative) {
                            operand1 = -operand1;
                        } else {
                        }

                        if (sign2 != (int) SEMnegative && sign2 != (int) SEMpositive) {
                            resultStr += "The second input has invalid sign (Assume positive)\n";
                        } else if (sign2 == (int) SEMnegative) {
                            operand2 = -operand2;
                        } else {
                        }

                        int exponent1 = Integer.parseInt(floatStr.substring(1, 3));

                        int exponent2 = Integer.parseInt(floatStr2.substring(1, 3));

                        switch (operation) {
                            case 0:
                            case 1:
                                int diff = exponent1 - exponent2;
                                if (diff > 0) {
                                    for (int i = 0; i < diff; i++) {
                                        operand2 /= 10;
                                    }
                                    exponent2 = exponent1;
                                } else if (diff < 0) {
                                    for (int i = diff; i < 0; i++) {
                                        operand1 /= 10;
                                    }
                                    exponent1 = exponent2;
                                }
                                steps += "1. Normalize the exponent\n";
                                steps += "Operand1: " + sign1 + exponent1 + decimalForm.format(Math.abs(operand1)) + "\n";
                                steps += "Operand2: " + sign2 + exponent2 + decimalForm.format(Math.abs(operand2)) + "\n";
                                if (operation == 0) {
                                    calcResult = operand1 + operand2;
                                    steps += "2. Perform addition on mantissa\n";
                                    steps += " \t" + decimalForm.format(operand1) + "\n+\t" + decimalForm.format(operand2) + "\n" + "========\n\t" + calcResult + "\n========\n";
                                } else {
                                    calcResult = operand1 - operand2;
                                    steps += "2. Perform subtraction on mantissa\n";
                                    steps += " \t" + decimalForm.format(operand1) + "\n-\t" + decimalForm.format(operand2) + "\n" + "========\n\t" + calcResult + "\n========\n";
                                }

                                if (calcResult > 99999 || calcResult < -99999) {
                                    steps += "Result matissa exceed 5 digits, last digit truncated\n" + "Exponent : " + exponent1 + " + 1\n";
                                    exponent1++;
                                    calcResult /= 10;
                                    steps += "New mantissa: " + Math.abs(calcResult) + "\n";
                                }
                                if (calcResult < 0) {
                                    resultStr += (int) SEMnegative;
                                } else {
                                    resultStr += (int) SEMpositive;
                                }
                                resultStr += exponent1;
                                resultStr += Math.abs(calcResult);
                                steps += "\nResult in SEEMMMMM: " + resultStr;
                                break;

                            case 2:
                                steps += "1. Calculate exponent using formulae\n" + "Exponent = " + exponent1 + "+" + exponent2 + "-" + (int) SEMexcess;
                                exponent1 = (int) exponent1 + exponent2 - (int) SEMexcess;
                                double mulResult = ((double) operand1 / 100000) * ((double) operand2 / 100000);
                                String mulStr = String.format("%.8f", mulResult);

                                steps += "\t = " + exponent1 + "\n" + "2. Perform multiplication on mantissa" + "\n";
                                steps += " \t" + ((double) operand1 / 100000) + "\nx\t" + ((double) operand2 / 100000) + "\n" + "===========\n\t" + mulStr + "\n===========\n";
                                steps += "3. Move the decimal places\n" + "Exponent : " + exponent1 + "-";
                                for (int i = mulStr.indexOf('.') + 1, j = 0; i < mulStr.length(); i++) {
                                    if (mulStr.charAt(i) == '0') {
                                        j++;
                                        exponent1--;
                                    } else {
                                        calcResult += Integer.parseInt(mulStr.substring(i, i + 5));
                                        steps += j + " = " + exponent1 + "\n" + "After moving : 0." + calcResult + "\n";
                                        break;
                                    }
                                }
                                if (mulResult < 0) {
                                    resultStr += (int) SEMnegative;
                                } else {
                                    resultStr += (int) SEMpositive;
                                }
                                resultStr += exponent1;
                                resultStr += Math.abs(calcResult);
                                steps += "\nResult: " + resultStr;
                                break;
                            default:
                                resultStr += "Invalid operation";
                        }

                        SketchwareUtil.showMessage(getApplicationContext(), "Calculation done");
                        result.setText(resultStr);
                    } else {
                        result.setTextColor(0xFFE91E63);
                        result.setText("Please fill in the parameters");
                    }
                } else {
                    result.setTextColor(0xFFE91E63);
                    result.setText("Invalid operand!");
                }
            }
        });

        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                editoperand1.setText("");
                editoperand2.setText("");
                result.setText("");
                editoperand1.requestFocus();
                result.setTextColor(0xFFE91E63);
            }
        });

        watchadsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (mRewardedAd == null) {
                    loadRewardedAd();
                }
                showRewardedVideo();
            }
        });
    }

    private void initializeLogic() {
        calculatorSelection.add("ADD");
        calculatorSelection.add("SUBTRACT");
        calculatorSelection.add("MULTIPLY");
        spinner1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, calculatorSelection));
        ((ArrayAdapter) spinner1.getAdapter()).notifyDataSetChanged();
        SEMexcess = -1;
        SEMpositive = -1;
        SEMnegative = -1;
    }

    @Override
    protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
        super.onActivityResult(_requestCode, _resultCode, _data);

        switch (_requestCode) {

            default:
                break;
        }
    }

    @Deprecated
    public void showMessage(String _s) {
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
    }

    @Deprecated
    public int getLocationX(View _v) {
        int _location[] = new int[2];
        _v.getLocationInWindow(_location);
        return _location[0];
    }

    @Deprecated
    public int getLocationY(View _v) {
        int _location[] = new int[2];
        _v.getLocationInWindow(_location);
        return _location[1];
    }

    @Deprecated
    public int getRandom(int _min, int _max) {
        Random random = new Random();
        return random.nextInt(_max - _min + 1) + _min;
    }

    @Deprecated
    public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
        ArrayList<Double> _result = new ArrayList<Double>();
        SparseBooleanArray _arr = _list.getCheckedItemPositions();
        for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
            if (_arr.valueAt(_iIdx))
                _result.add((double) _arr.keyAt(_iIdx));
        }
        return _result;
    }

    @Deprecated
    public float getDip(int _input) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
    }

    @Deprecated
    public int getDisplayWidthPixels() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    @Deprecated
    public int getDisplayHeightPixels() {
        return getResources().getDisplayMetrics().heightPixels;
    }

    private void showRewardedVideo() {

        if (mRewardedAd == null) {
            Log.d("TAG", "The rewarded ad wasn't ready yet.");
            return;
        }

        mRewardedAd.setFullScreenContentCallback(
                new FullScreenContentCallback() {
                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when ad is shown.
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        // Called when ad fails to show.
                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        mRewardedAd = null;
                        MainActivity.this.loadRewardedAd();
                    }
                });

        Activity activityContext = MainActivity.this;
        mRewardedAd.show(
                activityContext,
                new OnUserEarnedRewardListener() {
                    @Override
                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                        dialog.setTitle("Working steps");
                        dialog.setMessage(steps);
                        QMUIDialog dialog = new QMUIDialog.MessageDialogBuilder(MainActivity.this)
                                .setMessage(steps)
                                .setTitle("Working Steps")
                                .addAction(0, "Ok", new QMUIDialogAction.ActionListener() {
                                    @Override
                                    public void onClick(QMUIDialog qmuiDialog, int i) {
                                        qmuiDialog.dismiss();
                                    }
                                })
                                .show();

                    }
                });
    }

    private void loadRewardedAd() {
        if (mRewardedAd == null) {
            pb.show();
            AdRequest adRequest = new AdRequest.Builder().build();
            RewardedAd.load(
                    this,
                    AD_UNIT_ID,
                    adRequest,
                    new RewardedAdLoadCallback() {
                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            mRewardedAd = null;
                            pb.dismiss();
                            Toast.makeText(MainActivity.this, "Ads Failed To Load", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                            MainActivity.this.mRewardedAd = rewardedAd;
                            pb.dismiss();
                        }
                    });
        }
    }
}
