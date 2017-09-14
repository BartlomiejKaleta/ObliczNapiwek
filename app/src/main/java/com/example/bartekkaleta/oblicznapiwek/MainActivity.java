package com.example.bartekkaleta.oblicznapiwek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    //GUI - pierwszy wiersz
    private EditText amountEditText;
    private TextView amountTextView;

    //GUI - drugi wiersz
    private TextView percentTextView;
    private SeekBar percentSeekBar;

    //GUI - trzeci wiersz
    private TextView tiplLabelTextView;
    private TextView tipTextView;

    //GUI - czwarty wiersz
    private TextView totalLabelTextView;
    private TextView totalTextView;

    // Statyczne obiekty do formatowania wartości walutowych i procentowaych
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat  percentFormat = NumberFormat.getPercentInstance();

    //kwota rachunku
    private double billAmount = 0;

    //procent napiwku
    private double tipPercente = 0.15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicjalizacja pol widoków GUI

        amountEditText = (EditText) findViewById(R.id.amountEditText);
        amountTextView = (TextView) findViewById(R.id.amounttextView);

        percentTextView = (TextView) findViewById(R.id.percenttextView);
        percentSeekBar = (SeekBar) findViewById(R.id.percentSeekBar);

        tiplLabelTextView = (TextView) findViewById(R.id.tipLabelTextView);
        tipTextView = (TextView) findViewById(R.id.tipTextView);

        totalLabelTextView = (TextView) findViewById(R.id.totalLabelTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);

        //Nasłuchiwanie zdarzen dla pola EditETxt

        amountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    billAmount = Double.parseDouble(charSequence.toString()) / 100;
                    amountTextView.setText(currencyFormat.format(billAmount));
                } catch (NumberFormatException e){
                    amountTextView.setText("");
                    billAmount = 0.0;
                }
                calculateTipAndTotalAmount();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // Nasłuchiwanie zdarzen dla SeekBar

        percentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tipPercente = i;
                calculateTipAndTotalAmount();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private void calculateTipAndTotalAmount(){
        double tipAmount = billAmount * (tipPercente / 100);
        double totalAmount = billAmount + tipAmount;

        percentTextView.setText(percentFormat.format(tipPercente / 100));
        tipTextView.setText(currencyFormat.format(tipAmount));
        totalTextView.setText(currencyFormat.format(totalAmount));
    }
}
