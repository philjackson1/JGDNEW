package Util;
import Util.*;

public class GuiDFileReaderProperties 
{
    static String templatePathG =  "TemplatesProperties/GProp.conf";
    static String templatePathC =  "TemplatesProperties/CProp.conf";
            
    
    public static String[][] getProperties(String cast,int no,int x,int y)
	{
		String tmp = GuiDFileHandler.readFromFile(templatePathG); 
		//GProp.conf defines COMMON attributes for all components?
		
		String data[] = tmp.split("\n");
		String prop[][]=new String[19][4];
		int j=0,i;
		for(i=2;i<data.length;i++)
		{
			if(data[i].equals("[END]"))
			  break;
			prop[j++]=data[i].split(",");
		}

		prop[0][2]="untitled_"+String.valueOf(no);
		prop[1][2]=String.valueOf(x);
		prop[2][2]=String.valueOf(y);
		

		String tmp1=GuiDFileHandler.readFromFile(templatePathC);
		//CProp.conf defines ADDITIONAL attributes for all components and defaults?
		
		String data1[] = tmp1.split("\n");

//MSO: Don't assume JComponents have the same properties that awt components. 		
/*		if(cast.startsWith("J"))
		{
			String tp[]=cast.split("J");
			for(i=0;i<data1.length;i++)
			{
				if(data1[i].equals("["+tp[1]+"]"))
				 break;
			}
			for(i=i+1;i<data1.length;i++)
			{
				if(data1[i].equals("[END]"))
					  break;
				prop[j++]=data1[i].split(",");
			}
		}
*/
		for(i=0;i<data1.length;i++)
		{
			if(data1[i].equals("["+cast.trim()+"]"))
			 break;
		}
		for(i=i+1;i<data1.length;i++)
		{
			if(data1[i].equals("[END]"))
				  break;
			prop[j++]=data1[i].split(",");
		}
		if(cast.startsWith("J"))
		{
			for(i=0;i<data1.length;i++)
			{
				if(data1[i].equals("[JComponents]"))
				 break;
			}
			for(i=i+1;i<data1.length;i++)
			{
				if(data1[i].equals("[END]"))
				  break;
				prop[j++]=data1[i].split(",");
			}
		}
		return prop;
	}
}
