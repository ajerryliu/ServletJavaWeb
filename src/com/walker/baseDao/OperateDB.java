package com.walker.baseDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperateDB
{
	/**
	 * 获取查询结果
	 * 
	 * @param sql
	 *           查询语句
	 * @return 结果
	 */
	public ResultSet getResultByQuery(String sql)
	{
		Connection con = Connecter.getConnect();
		System.out.println("connect object"+con);
		ResultSet result = null;
		if (con != null)
		{
			try
			{
			Statement state = con.createStatement();
			result = state.executeQuery(sql);
			System.out.println("+++"+result);
			}
			catch (SQLException e)
			{
			e.printStackTrace();
			}

		}
		return result;
	}

	/**
	 * 插入 更新 删除 数据执行方法
	 * 
	 * @param sql
	 *           dml语句
	 */
	public void getResultByIUD(String sql)
	{
		Connection con = Connecter.getConnect();
		Statement state = null;
		if (con != null)
		{
			try
			{
			state = con.createStatement();
			state.executeUpdate(sql);
			}
			catch (SQLException e)
			{
			System.out.println("can not get statement");
			} finally
			{
			try
			{
				//关闭资源
				state.close();
				Connecter.closeConnect();
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}

	}

	/**
	 * 转换查询结果 遍历了整个resultSet
	 * 
	 * @param resultSet
	 * @return 转换后的list
	 */
	public List<List<Object>> resultSetToList(ResultSet resultSet)
	{

		List<List<Object>> list = new ArrayList<List<Object>>();
		try
		{
			ResultSetMetaData metaDate = resultSet.getMetaData();
			System.out.println(resultSet.next());
			// 使用完一次后 result 光标就会在末尾 第二次使用就会在末尾 此时返回 false
			while (resultSet.next())
			{
			//Map<String, Object> map = new HashMap<String, Object>();
			List<Object> subList = new ArrayList<Object>();
			for (int i = 1; i < metaDate.getColumnCount()+1; i++)
			{
				// resultSet.getObject(i) 返回当前行的指定列值
				//map.put(metaDate.getColumnName(i), resultSet.getObject(i));
				subList.add(resultSet.getObject(i));
			}
			list.add(subList);
			}
		}
		catch (SQLException e)
		{
			System.out.println("获取metaDate 失败");
			e.printStackTrace();
		} finally
		{
			// 关闭各种资源
			try
			{
			resultSet.close();
			Connecter.closeConnect();
			}
			catch (SQLException e)
			{

			e.printStackTrace();
			}

		}
		return list;
	}
	  public static void main(String[] args) { OperateDB op = new OperateDB();
	  ResultSet set = op.getResultByQuery("select * from emp");
	  System.out.println("set"+set);
	  System.out.println(op.resultSetToList(set)); }
	 

}
