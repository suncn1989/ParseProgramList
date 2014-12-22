/*
 * Excel Reader
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.awt.image.BufferedImage;
import java.awt.*;

import com.sun.image.codec.jpeg.*;

import java.io.*;

import javax.imageio.ImageIO;


public class ExcelReader {
	
	public static void main(String[] args) throws IOException
	{
		
		//func("jsws",String time);
		
		ExcelReader excelReader = new ExcelReader();
		SqlExcuter sqlExcuter = new SqlExcuter();
		FileReader fileReader = new FileReader();
		
		String fileName = "./resources/list.log";
		
		ProgramDto pxls = null;
		
		/*
		List<ProgramDto> list = excelReader.readXls();
		
		for (int i=0; i<list.size(); i++)
		{
			pxls = (ProgramDto) list.get(i);
			System.out.println("*****" + pxls.getChannel_name() + "*******" + pxls.getPre_name());
		}
		*/
		
		//sqlExcuter.ExcuteSqlSentences();

		
		List al = fileReader.readLogFile(fileName);
		
		for (int i=0; i<al.size(); i++)
		{
			//System.out.println("^^^^^^^^^"+al.get(i));
			List<ProgramDto> list = excelReader.readXls("./resources/"+al.get(i));
			for (int j = 0; j<list.size(); j++)
			{
				pxls = (ProgramDto) list.get(j);
				//sqlExcuter.ExcuteSQLProgram(pxls);
				System.out.println("*****" + pxls.getChannel_name() + "*******" + pxls.getPre_name());
			}
		}
		//sqlExcuter.ExcuteSqlSentences();
		
		String headpname = "节目名称";
		String headstime = "开始时间";
		String headetime = "结束时间";
		int flag = 0;
		
		StringToPic stringToPic = new StringToPic();
		
		//Generate pics based on the program list.
		try
		{
			
			FileOutputStream outputStream = new FileOutputStream("aa.jpg");
			
			
			
			List<ProgramDto> list_test = excelReader.readXls("./resources/"+al.get(0));
			
			int imagebgwidth = 400;
			int imagebgheight = list_test.size()*16;
			
			BufferedImage bi = new BufferedImage(imagebgwidth, imagebgheight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bi.createGraphics();
			g.setBackground(Color.WHITE);
			g.setColor(Color.GRAY);
			java.awt.FontMetrics fm=g.getFontMetrics();
			g.clearRect(0, 0, 400, list_test.size()*16);

			
			//Write head text contents.
			stringToPic.drawString(g, fm, headpname, headstime, headetime, flag);
			
			//Write detail text contents.
			for (int n = 0; n<list_test.size(); n++)
			{
				pxls = (ProgramDto) list_test.get(n);
				//sqlExcuter.ExcuteSQLProgram(pxls);
				//System.out.println("*****" + pxls.getChannel_name() + "*******" + pxls.getPre_name());
				flag++;
				stringToPic.drawString(g, fm, pxls.getPre_name(), pxls.getStrat_time(), pxls.getEnd_time(), flag);
				
			}
			
			
			//Draw background pic.
			File file = new File("./resources/iptv_bg.JPG");
			Image image = ImageIO.read(file);
			int imagewaterwidth = image.getWidth(null);
			int imagewaterheight = image.getHeight(null);
			
			//BufferedImage bufferedImage = new BufferedImage(imagewaterwidth, imagewaterheight, BufferedImage.TYPE_INT_RGB);
			//Graphic2D gbg =
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.2f));
			int imagewaterxp = (imagebgwidth - imagewaterwidth)/2;
			int imagewateryp = (imagebgheight - imagewaterheight)/2;
			g.drawImage(image, imagewaterxp, imagewateryp, imagewaterwidth, imagewaterheight, null);
			
			g.dispose();
			
			
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outputStream);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
			param.setQuality(0.5f, false);
			encoder.setJPEGEncodeParam(param);
			encoder.encode(bi);
			outputStream.close();
			System.out.println("OK!");
			
		}
		catch(Exception e)
		{
			System.out.println("ERROR!!");
		}
		
		
	}
	
	private List<ProgramDto> readXls(String filePath) throws IOException
	{
		//InputStream is = new FileInputStream("./20141202.CCTV-1.xls");
		InputStream is = new FileInputStream(filePath);
		HSSFWorkbook hssfworkbook = new HSSFWorkbook(is);
		ProgramDto programDto = null;
		List<ProgramDto> list = new ArrayList<ProgramDto>();
		
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		SimpleDateFormat outputDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//Sheets
		for (int numSheet = 0; numSheet < hssfworkbook.getNumberOfSheets(); numSheet++)
		{
			HSSFSheet hssfSheet = hssfworkbook.getSheetAt(numSheet);
			if (hssfSheet == null)
			{
				continue;
			}
			
			//Rows
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++)
			{
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null)
				{
					continue;
				}
				programDto = new ProgramDto();
				
				//Cell
				HSSFCell cn = hssfRow.getCell(0);
				if (cn == null)
				{
					continue;
				}
				programDto.setChannel_name(getValue(cn));
				
				HSSFCell pn = hssfRow.getCell(1);
				if (pn == null)
				{
					continue;
				}
				programDto.setPre_name(getValue(pn));
				
				//Start time for Date format.
				HSSFCell st = hssfRow.getCell(2);
				if (st == null)
				{
					continue;
				}
				//programDto.setStrat_time(getValue(st));
				Date startDate;
				try {
					startDate = dateTimeFormat.parse(getValue(st));
					String saveStartDate = outputDateTime.format(startDate);
					programDto.setStrat_time(saveStartDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("!!!!!!!!!!!"+programDto.getStrat_time());
				
				//End time for Date format.
				HSSFCell et = hssfRow.getCell(3);
				if (et == null)
				{
					continue;
				}
				//programDto.setEnd_time(getValue(et));
				Date endDate;
				try {
					endDate = dateTimeFormat.parse(getValue(et));
					String saveEndDate = outputDateTime.format(endDate);
					programDto.setEnd_time(saveEndDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//Get play_date
				if (programDto.getStrat_time() != null)
				{
					String[] temp =  (programDto.getStrat_time()).split(" ");
					programDto.setPlay_date(temp[0]);
				}
				System.out.println("!!!!!!!!!!!"+programDto.getPlay_date());

				
				HSSFCell sd = hssfRow.getCell(4);
				if (sd == null)
				{
					continue;
				}
				if (sd.getCellType() == sd.CELL_TYPE_NUMERIC)
				{
					programDto.setsaved_days((int)sd.getNumericCellValue());
				}
				else
				{
					programDto.setsaved_days(Integer.parseInt(getValue(sd)));
					//System.out.println("@@@@@@@"+sd.getCellType());
				}
				HSSFCell ar = hssfRow.getCell(5);
				if (ar == null)
				{
					continue;
				}
				if (ar.getCellType() == sd.CELL_TYPE_NUMERIC)
				{
					programDto.setAllow_record((int)ar.getNumericCellValue());
				}
				else
				{
					programDto.setAllow_record(Integer.parseInt(getValue(ar)));
				}
				
				
				HSSFCell Tt = hssfRow.getCell(6);
				if (Tt == null)
				{
					continue;
				}
				if (Tt.getCellType() == Tt.CELL_TYPE_NUMERIC)
				{
					programDto.setTVOD_type((int)Tt.getNumericCellValue());
				}
				else
				{
					programDto.setTVOD_type(Integer.parseInt(getValue(Tt)));
					//System.out.println("@@@@@@@"+Tt.getCellType());
				}
				HSSFCell Tu = hssfRow.getCell(7);
				if (Tu == null)
				{
					continue;
				}
				if (Tu.getCellType() == Tu.CELL_TYPE_NUMERIC)
				{
					programDto.setTVOD_unit((int)Tu.getNumericCellValue());
				}
				else
				{
					programDto.setTVOD_unit(Integer.parseInt(getValue(Tu)));
					//System.out.println("@@@@@@@"+Tu.getCellType());
				}
				HSSFCell Tp = hssfRow.getCell(8);
				if (Tp == null)
				{
					continue;
				}
				if (Tp.getCellType() == Tp.CELL_TYPE_NUMERIC)
				{
					programDto.setTVOD_price((int)Tp.getNumericCellValue());
				}
				else
				{
					programDto.setTVOD_price(Integer.parseInt(getValue(Tp)));
					//System.out.println("@@@@@@@"+Tp.getCellType());
				}
				HSSFCell ap = hssfRow.getCell(9);
				if (ap == null)
				{
					continue;
				}
				if (ap.getCellType() == ap.CELL_TYPE_NUMERIC)
				{
					programDto.setAllow_personal((int)ap.getNumericCellValue());
				}
				else
				{
					programDto.setAllow_personal(Integer.parseInt(getValue(ap)));
					//System.out.println("@@@@@@@"+ap.getCellType());
				}
				HSSFCell prt = hssfRow.getCell(10);
				if (prt == null)
				{
					continue;
				}
				if (prt.getCellType() == prt.CELL_TYPE_NUMERIC)
				{
					programDto.setPersonal_record_type((int)prt.getNumericCellValue());
				}
				else
				{
					programDto.setPersonal_record_type(Integer.parseInt(getValue(prt)));
				}
				HSSFCell ppu = hssfRow.getCell(11);
				if (ppu == null)
				{
					continue;
				}
				if (ppu.getCellType() == ppu.CELL_TYPE_NUMERIC)
				{
					programDto.setPersonal_price_unit((int)ppu.getNumericCellValue());
				}
				else
				{
					programDto.setPersonal_price_unit(Integer.parseInt(getValue(ppu)));
				}
				HSSFCell prp = hssfRow.getCell(12);
				if (prp == null)
				{
					continue;
				}
				if (prp.getCellType() == prp.CELL_TYPE_NUMERIC)
				{
					programDto.setPersonal_record_price((int)prp.getNumericCellValue());
				}
				else
				{
					programDto.setPersonal_record_price(Integer.parseInt(getValue(prp)));
				}
				HSSFCell pd = hssfRow.getCell(13);
				if (pd == null)
				{
					continue;
				}
				programDto.setPre_description(getValue(pd));
				
				HSSFCell ac = hssfRow.getCell(14);
				//if (ac == null)
				//{
					programDto.setAuto_code(-1);
				//}

				
				list.add(programDto);
				
			}
					
		}
		
		return list;
	}
	
	
	//Read every cell
	private String getValue(HSSFCell hssfCell)
	{
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN)
		{
			//Return bool
			return String.valueOf(hssfCell.getBooleanCellValue());
		}
		else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC)
		{
			//Return numberic
			return String.valueOf(hssfCell.getNumericCellValue());
		}
		else
		{
			//Return string
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
}
