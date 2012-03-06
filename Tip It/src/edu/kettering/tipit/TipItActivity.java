package edu.kettering.tipit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class TipItActivity extends Activity implements OnClickListener, OnCheckedChangeListener {
	
	// Global Variables
	private StringBuffer billText;
    private EditText bill;
    private EditText tip;
    private EditText total;
    private RadioGroup tipGroup;
    
    private double billDecimal;
    private double tipDecimal;
    private double totalDecimal;
    private double percentage;
    
    private Boolean isEnteringDecimal;
    private int currentDecimalCount;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Get the buttons
        Button button0 = (Button)findViewById(R.id.button0);
        Button button1 = (Button)findViewById(R.id.button1);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);
        Button button4 = (Button)findViewById(R.id.button4);
        Button button5 = (Button)findViewById(R.id.button5);
        Button button6 = (Button)findViewById(R.id.button6);
        Button button7 = (Button)findViewById(R.id.button7);
        Button button8 = (Button)findViewById(R.id.button8);
        Button button9 = (Button)findViewById(R.id.button9);
        Button buttonDecimal = (Button)findViewById(R.id.buttonDecimal);
        Button buttonDelete = (Button)findViewById(R.id.buttonDelete);
        
        bill = (EditText)findViewById(R.id.bill);
        tip = (EditText)findViewById(R.id.tip);
        total = (EditText)findViewById(R.id.total);
        tipGroup = (RadioGroup)findViewById(R.id.percentageRadioGroup);
        
        // Add the click listeners
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);       
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonDecimal.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        tipGroup.setOnCheckedChangeListener(this);
        
        billText = new StringBuffer();
        percentage = 0.15;
        isEnteringDecimal = false;
        update();
    }

	@Override
	public void onClick(View v) {
		
		switch(v.getId()) {
			case R.id.button0:
				updateDecimal("0");
				break;
			case R.id.button1:
				updateDecimal("1");
				break;
			case R.id.button2:
				updateDecimal("2");
				break;
			case R.id.button3:
				updateDecimal("3");
				break;
			case R.id.button4:
				updateDecimal("4");
				break;
			case R.id.button5:
				updateDecimal("5");
				break;
			case R.id.button6:
				updateDecimal("6");
				break;
			case R.id.button7:				
				updateDecimal("7");
				break;
			case R.id.button8:
				updateDecimal("8");
				break;
			case R.id.button9:
				updateDecimal("9");
				break;
			case R.id.buttonDecimal:
				
				if (billText.length() == 0) {
					billText.append("0");
				}
				
				if (billText.indexOf(".") == -1) {
					billText.append(".");
					currentDecimalCount = 0;
					isEnteringDecimal = true;
				}
				break;
			case R.id.buttonDelete:
				if (billText.length() != 0) {
					billText.deleteCharAt(billText.length() - 1);
				}
				break;
		}
		
		update();
		
	}
	
	public void updateDecimal(String num) {
		if (billText.indexOf(".") == -1) {
			currentDecimalCount = 0;
			isEnteringDecimal = false;
		}
		
		if (isEnteringDecimal) {
			if (currentDecimalCount++ < 2)
			{
				billText.append(num);
			}
			else {
				// If the decimal was erased, then you can add another and add decimals
				
			}
		}
		else {
			billText.append(num);
		}
	}
	
	public void update() {
		
		if (billText.length() == 0) {
			billDecimal = 0;
			tipDecimal = 0;
			totalDecimal = 0;
		}
		else {
			billDecimal = Double.parseDouble(billText.toString());
			tipDecimal = Double.parseDouble(tip.getText().toString());
			totalDecimal = Double.parseDouble(total.getText().toString());
			
			tipDecimal = billDecimal * percentage;
			totalDecimal = billDecimal + tipDecimal;
		}
		
		// Convert the Tip and Total to 2 decimals
		int tempTip = (int)(tipDecimal * 100);
		tipDecimal = ((double)tempTip) / 100.0;
		
		int tempTotal = (int)(totalDecimal * 100);
		totalDecimal = ((double)tempTotal) / 100.0;
		
		bill.setText(billText);
		tip.setText(Double.toString(tipDecimal));
		total.setText(Double.toString(totalDecimal));
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
			case R.id.percent10:
				percentage = 0.10;
				break;
			case R.id.percent15:
				percentage = 0.15;
				break;
				
			case R.id.percent20:
				percentage = 0.20;
				break;
		}
		
		update();
		
	}
    
}