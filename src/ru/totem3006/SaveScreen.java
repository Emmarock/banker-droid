package ru.totem3006;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import ru.totem3006.dataSaver.*;

// TODO написать нормальные android:hint в поля для ввода везде
public class SaveScreen extends Activity
{
	float irrVal;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_screen);
        
        Intent intent = getIntent();
        
        this.irrVal = intent.getFloatExtra("IRR", 0);
        
        TextView irrLabel = (TextView) findViewById(R.id.irrLabel);
        irrLabel.setText("IRR: " + this.irrVal + '%');
    }

    public void onSaveClick (View v)
    {
    	/*
    	 * Получаем данные. Если заполнено не было - указываем это
    	 * TODO replace форматных символов
    	 */
    	EditText bankName = (EditText) findViewById(R.id.bankNameInput);
    	String bankNameStr = bankName.getText().toString();
    	if (bankNameStr.isEmpty())
    	{
    		bankNameStr = "Empty";
    	};
    	
    	EditText creditName = (EditText) findViewById(R.id.creditNameInput);
    	String creditNameStr = creditName.getText().toString();
    	if (creditNameStr.isEmpty())
    	{
    		creditNameStr = "Empty";
    	}
    	
    	EditText comment = (EditText) findViewById(R.id.commentInput);
    	String commentStr = comment.getText().toString();
    	if (commentStr.isEmpty())
    	{
    		commentStr = "Empty";
    	}
    	
    	//сохраняем
    	IrrSaver ds = new IrrSaver();
    	IrrRecord rec = new IrrRecord(bankNameStr, creditNameStr, commentStr, this.irrVal);
    	ds.insert(rec);
    	//в меню
    	finish();
    	Intent intent = new Intent(this, Menu.class);
		startActivity(intent);
    }
    
    public void onCancelClick (View v)
    {
    	//переходит в меню
    	finish();
    	Intent intent = new Intent(this, Menu.class);
		startActivity(intent);
    }
    
}