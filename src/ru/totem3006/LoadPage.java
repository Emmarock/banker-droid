package ru.totem3006;

import java.util.ArrayList;

import ru.totem3006.dataSaver.IrrRecord;
import ru.totem3006.dataSaver.IrrSaver;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
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
        /*
        // выводим даннные
        String list_arr[]={"Апельсин","Банан","Яблоко","Груша"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.load_page, R.id.load0, list_arr);
        
        ListView list = (ListView) findViewById(R.id.load0);
        list.setAdapter(adapter);
        */
    }
   
    
    
    public void onBackClick(View v)
    {
    	finish();
    	Intent intent = new Intent(this, Menu.class);
    	startActivity(intent);
    }
}