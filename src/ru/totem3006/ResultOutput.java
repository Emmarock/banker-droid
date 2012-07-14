package ru.totem3006;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ResultOutput extends Activity
{
	float irrValue = 0;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_output);
               
        Intent intent = getIntent();
        
        
        this.irrValue = intent.getFloatExtra("IRR", 0);
        Log.d("[IRR result]", String.valueOf(this.irrValue));
        
        TextView irrOutput = (TextView) findViewById(R.id.irrOutput);
        irrOutput.setText("IRR: " + String.valueOf(this.irrValue) + '%');
    }

    public void onSaveClick(View v)
    {
    	finish();
    	Intent intent = new Intent(this, SaveScreen.class);
    	intent.putExtra("IRR", this.irrValue);
		startActivity(intent);
    }
    
    public void onCloseClick (View v)
    {
    	/**
    	 *	Переход на окно меню
    	 *	TODO исправить, вероятно на рестарт приложения
    	 */
    	finish();
    	Intent intent = new Intent(this, Menu.class);
		startActivity(intent);
		
    }
    
}