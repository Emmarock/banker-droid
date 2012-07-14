package ru.totem3006.dataSaver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import ru.totem3006.XmlWorker.XmlWorker;

import android.os.Environment;
import android.util.Log;

public class IrrSaver
{
	public static final String FILE_NAME = "irr_data_file";
	public static final int MAX_RECORD = 5;
	ArrayList<IrrRecord> data = null;
	
	public IrrSaver ()
	{
		// загружаем из файла. если нет то создаём пустым
        String folderToSave = Environment.getExternalStorageDirectory().toString();
		File f = new File(folderToSave, FILE_NAME);
		try 
		{
			FileInputStream is = new FileInputStream(f);
			this.data = XmlWorker.readXml(is);
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			this.data = new ArrayList<IrrRecord>();
			Log.e("Construct error", e.getMessage());
		}
		
		
		Log.d("constructor_finish", this.toString());
	}


	public void insert(IrrRecord rec)
	{
		Collections.sort(this.data);
		//Если элемент хуже всех, то добавляем вместо последнего. Иначе просто добавлем и худший удаляем
		if (this.data.size() < IrrSaver.MAX_RECORD)
		{
			Log.d("insert", "длина списка меньше max");
			this.data.add(rec);
		}
		else
		{
			//в любом случае удаляем худшее и на его место нашего героя
			this.data.remove(this.data.size()-1);
			this.data.add(rec);
			Collections.sort(this.data);
		}

		//Теперь this.data это список из <= MAX_RECORD записей. В котором точно есть rec
		
		// записываем то что сейчас есть
		String folderToSave = Environment.getExternalStorageDirectory().toString();
		File file = new File(folderToSave, FILE_NAME);
		FileOutputStream fos = null;
		
		try
		{
			fos = new FileOutputStream(file);
			
			XmlWorker.writeXml(fos, this.data);
		}
		catch (Exception e)
		{
			Log.e("constructor", e.getMessage());
		}
		finally
		{
			try
			{
				fos.close();
			}
			catch (Exception e)
			{
				//nope
			}
		}

				
	}

	public ArrayList<IrrRecord> getAll()
	{
		return this.data;
	}
	
	@Override
	public String toString()
	{
		if (this.data != null)
			return "Длина списка:\t" + this.data.size();
		
		return "0";
	}
	
}
