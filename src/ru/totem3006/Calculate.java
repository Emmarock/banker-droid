package ru.totem3006;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import ru.ptaha.*;

public class Calculate extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculate_body);
    }

    public void onCalculateClick(View v)
    {    	
    	//Получаем значения из форм
    	//размер
    	EditText amount = (EditText) findViewById(R.id.amountInput);
    	String amountStr = amount.getText().toString();
    	float amountVal = 0;
    	if (!amountStr.isEmpty()) amountVal = Float.parseFloat(amountStr);
    	Log.d("[Amount val]", String.valueOf(amountVal));
    	//срок
    	
    	EditText creditTerm = (EditText) findViewById(R.id.creditTermInput);
    	String creditTermStr = creditTerm.getText().toString();
    	int creditTermVal = 0;
    	if (!creditTermStr.isEmpty()) creditTermVal = Integer.parseInt(creditTermStr);
    	Log.d("[creditTermVal]", String.valueOf(creditTermVal));
    	//ставка
    	EditText interestRate = (EditText) findViewById(R.id.interestRateInput);
    	String interestRateStr = interestRate.getText().toString();    	
    	float interestRateVal = 0;
    	if (!interestRateStr.isEmpty()) interestRateVal = Float.parseFloat(interestRateStr);
    	Log.d("[interestRateVal]", String.valueOf(interestRateVal));

    	//начальный платёж
    	EditText initialPayment = (EditText) findViewById(R.id.initialPaymentInput);
    	String initialPaymentStr = initialPayment.getText().toString();
    	float initialPaymentVal = 0;
    	if (!initialPaymentStr.isEmpty()) initialPaymentVal = Float.parseFloat(initialPaymentStr);
    	Log.d("[initialPaymentVal]", String.valueOf(initialPaymentVal));

    	//месячный платеж
    	EditText monthlyPayment = (EditText) findViewById(R.id.monthlyPaymentInput);
    	String monthlyPaymentStr = monthlyPayment.getText().toString();
    	float monthlyPaymentVal = 0;
    	if (!monthlyPaymentStr.isEmpty()) monthlyPaymentVal = Float.parseFloat(monthlyPaymentStr);
    	Log.d("[monthlyPaymentVal]", String.valueOf(monthlyPaymentVal));

    	//квартальный платёж
    	EditText quarterlyPayment = (EditText) findViewById(R.id.quarterlyPaymentInput);
    	String quarterlyPaymentStr = quarterlyPayment.getText().toString();
    	float quarterlyPaymentVal = 0;
    	if (!quarterlyPaymentStr.isEmpty()) quarterlyPaymentVal = Float.parseFloat(quarterlyPaymentStr);
    	Log.d("[quarterlyPaymentVal]", String.valueOf(quarterlyPaymentVal));

    	//годовой платёж
    	EditText annualPayment = (EditText) findViewById(R.id.annualPaymentInput);
    	String annualPaymentStr = annualPayment.getText().toString();
    	float annualPaymentVal = 0;
    	if (!annualPaymentStr.isEmpty()) annualPaymentVal = Float.parseFloat(annualPaymentStr);
    	Log.d("[annualPaymentVal]", String.valueOf(annualPaymentVal));

    	CountIRR irr = new CountIRR(amountVal, creditTermVal, interestRateVal, initialPaymentVal, monthlyPaymentVal, quarterlyPaymentVal, annualPaymentVal);
    	
    	float irrVal = (float) irr.count();
    	Log.d("[IRR calculate]", String.valueOf(irrVal));

    	finish();
    	Intent intent = new Intent(this, ResultOutput.class);
    	intent.putExtra("IRR", irrVal);
    	startActivity(intent);
    	
    }
    
}