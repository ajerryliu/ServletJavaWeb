package com.walker.baseDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connecter
{
	
	private static final String url = "jdbc:oracle:thin:@localhost:1521";
	private static final String user = "scott";
	private static final String password = "123456";

	public static Connection getConnect()
	{
		Connection conn = null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			return conn;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static void closeConnect()
	{
		Connection con = getConnect();
		if (con != null)
		{
			try
			{
				con.close();
			}
			catch (SQLException e)
			{
				System.out.println("关闭连接失败");
			}
		}
	}
}
