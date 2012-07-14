package ru.totem3006;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import ru.totem3006.dataSaver.IrrRecord;
import ru.totem3006.dataSaver.IrrSaver;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class LoadPage extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_page);
        
        // загружаем данные
        IrrSaver is = new IrrSaver();
        ArrayList<IrrRecord> isLoad = is.getAll();
        
        
        String tmp = "";        
        if (isLoad != null)
        {
	        for (int i = 0; i < isLoad.size(); i++)
	        {
	        	tmp += isLoad.get(i).toOutput() + "\n============\n";
	        }
        }
        else
        {
        	tmp = "Error";
        }
        TextView tw = (TextView) findViewById(R.id.load0);
        tw.setText(tmp);
    }
   
    
    
    public void onBackClick(View v)
    {
    	finish();
    	Intent intent = new Intent(this, Menu.class);
    	startActivity(intent);
    }
    
    public void onCleanClick(View v)
    {
    	//очищаем файл сохранения и перезагружаем старницу
    	String folderToSave = Environment.getExternalStorageDirectory().toString();
		File file = new File(folderToSave, IrrSaver.FILE_NAME);
		FileOutputStream fos = null;
		try
		{
			String emptyStr = "";
			fos = new FileOutputStream(file);
			fos.write(emptyStr.getBytes());
		}
		catch (Exception e)
		{
			Log.e("onCleanClick", e.getMessage());
		}
		finally
		{
			try
			{
				fos.close();
				
				finish();
		    	Intent intent = new Intent(this, LoadPage.class);
		    	startActivity(intent);
			}
			catch (Exception e)
			{
				//pass
			}
		}
		
		
    }
}