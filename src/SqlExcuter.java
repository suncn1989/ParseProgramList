/*
 * Excute SQL sentences.
 */

import java.net.URLEncoder;
import java.sql.*;

public class SqlExcuter {
	
	public void ExcuteSqlSentences()
	{
		try
		{
			String url = "jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=utf8";
			String usr = "root";
			String password = "";
			//Insert Strings
			String sname ="青岛中华美食好";
			//String cname = URLEncoder.encode(sname,"gbk");
			String s1 = "replace into test_t values('" + sname + "','0')";
			
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			Connection conn = DriverManager.getConnection(url, usr, password);
			
			Statement stmt = conn.createStatement();
			/*
			System.out.println(s1);
			
			stmt.executeQuery(s1);
			*/
			
			ResultSet rs = stmt.executeQuery("select * from test_t");
			
			while (rs.next())
			{
				String name = rs.getString("pre_name");
				System.out.println("########"+name);
			}
			
			rs.close();
			conn.close();
		}
		catch (Exception ex)
		{
			System.out.println("ERROR!!");
			ex.printStackTrace();
		}
	}
	
	public void ExcuteSQLProgram(ProgramDto pt)
	{
		String url = "jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=utf8";
		String usr = "root";
		String password = "";
		//Insert Strings
		String s1 = "replace into test_t values('" 
					+ pt.getChannel_name()
					+ "','"
					+ pt.getPre_name()
					+ "','"
					+ pt.getPlay_date()
					+ "','"
					+ pt.getStrat_time()
					+ "','"
					+ pt.getEnd_time()
					+ "','"
					+ pt.getsaved_days()
					+ "','"
					+ pt.getAllow_record()
					+ "','"
					+ pt.getTVOD_type()
					+ "','"
					+ pt.getTVOD_unit()
					+ "','"
					+ pt.getTVOD_price()
					+ "','"
					+ pt.getAllow_personal()
					+ "','"
					+ pt.getPersonal_record_type()
					+ "','"
					+ pt.getPersonal_price_unit()
					+ "','"
					+ pt.getPersonal_record_price()
					+ "','"
					+ pt.getPre_description()
					+ "','"
					+ pt.getAuto_code()
					+"')";
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			Connection conn = DriverManager.getConnection(url, usr, password);
			
			Statement stmt = conn.createStatement();
			System.out.println(s1);
			stmt.executeQuery(s1);
			
			/*
			ResultSet rs = stmt.executeQuery("select * from test_table");
			
			while (rs.next())
			{
				String name = rs.getString("name");
				System.out.println("########"+name);
			}
			
			rs.close();
			*/
			conn.close();
		}
		catch (Exception ex)
		{
			System.out.println("2ERROR!!");
			ex.printStackTrace();
		}
	}
}
