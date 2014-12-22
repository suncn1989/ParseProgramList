/*
 * File Reader, read all Excel file names.
 */

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;

public class FileReader {
	
	public List readLogFile(String filePath)
	{
		List list = new ArrayList();
		try
		{
			String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists())
			{
				InputStreamReader reader = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(reader);
				String logFileContent = null;
				
				
				while((logFileContent = bufferedReader.readLine()) != null)
				{
					list.add(logFileContent);
				}
				
				
				
				reader.close();
				
				
			}
			else
			{
				System.out.println("Cannot find file!");
			}
		}
		catch(Exception e)
		{
			System.out.println("File Reader ERROR!!!");
		}
		return list;
		
	}
}
