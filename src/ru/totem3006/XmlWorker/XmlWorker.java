package ru.totem3006.XmlWorker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.util.Log;

import ru.totem3006.dataSaver.IrrRecord;

public class XmlWorker
{
	public static ArrayList<IrrRecord> readXml(InputStream is)
	{
		ArrayList<IrrRecord> result = null;
		try
		{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(is);
			
			doc.getDocumentElement().normalize();
			 
			
		
			
			NodeList nList = doc.getElementsByTagName("credit_record");
			result = new ArrayList<IrrRecord>();
			for (int i = 0; i < nList.getLength(); i++)
			{
				Node node = nList.item(i);
				
				if (node.getNodeType() == Node.ELEMENT_NODE)
				{
					Element elem = (Element) node;
					
					float irr = Float.valueOf(getTagValue("irr", elem));
					String comment = getTagValue("comment", elem);
					String bank_name = getTagValue("bank_name", elem);
					String credit_name = getTagValue("credit_name", elem);
					
					result.add(new IrrRecord(bank_name, credit_name, comment, irr));
					
				}
			}
		}
		catch (SAXException e) 
		{
			// Ошибка парсера. Считаем документ пустым
			Log.d("read xml", "document is empty");
			result = new ArrayList<IrrRecord>();
		} 
		catch (Exception e) 
		{
			// все остальные ошибки - плохие, но документ мы тоже будем считать пустым
			Log.e("while read xml", e.getMessage());
			result = new ArrayList<IrrRecord>();
		}
		finally
		{
			
		}
		
		return result;
	}

	private static String getTagValue(String sTag, Element eElement)
	{
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
	 
	    Node nValue = (Node) nlList.item(0);
	 
		return nValue.getNodeValue();
	 }


	public static void writeXml(OutputStream os, ArrayList<IrrRecord> list) throws Exception
	{
		
		try
		{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			//Корневой элемент
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("data_base");
			doc.appendChild(rootElement);
			
			//записи
			for (IrrRecord rec : list)
			{
				//Сама запись
				Element creditRecord = doc.createElement("credit_record");
				rootElement.appendChild(creditRecord);
				
				//Информация о
				Element bankName = doc.createElement("bank_name");
				bankName.appendChild(doc.createTextNode(rec.bank_name));
				creditRecord.appendChild(bankName);
				
				Element creditName = doc.createElement("credit_name");
				creditName.appendChild(doc.createTextNode(rec.credit_name));
				creditRecord.appendChild(creditName);
				
				Element comment = doc.createElement("comment");
				comment.appendChild(doc.createTextNode(rec.comment));
				creditRecord.appendChild(comment);
				
				Element irr = doc.createElement("irr");
				irr.appendChild(doc.createTextNode(String.valueOf(rec.irr)));
				creditRecord.appendChild(irr);

			}
			
			
			//Запись в файл
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			
			StreamResult result = new StreamResult(os);
			transformer.transform(source, result);
			
		} 
		catch (Exception e) 
		{
			throw new Exception("[ERROR] while write xml file:\n" + e.getMessage());
		}
	}


}
