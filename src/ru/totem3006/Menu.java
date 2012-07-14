package ru.totem3006;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Menu extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        String bankName = "Faltz";
        String creditName = "TotemCred";
        String comment = "Haha! Commentz!";
        float irrVal = (float) 5.3;
        
        String prew = "Try this one!";
        //SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory);
        FileOutputStream fos = null;
        try {
			fos = openFileOutput("abbat", Context.MODE_PRIVATE);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(prew);
			os.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			FileInputStream fis = openFileInput("abbat");
			ObjectInputStream is = new ObjectInputStream(fis);
			String sss = (String) is.readObject();
			is.close();
			Log.d("[S AFTER]", sss);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    public void onCalculateClick (View v)
    {
    	// Переключение на другое окно
    	finish();
    	Intent intent = new Intent(this, Calculate.class);
		startActivity(intent);
    }
    
    public void onLoadClick (View v)
    {
    	finish();
    	Intent intent = new Intent(this, LoadPage.class);
		startActivity(intent);
    }
}