package com.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.junit.Test;

import com.entity.Stu;
public class StuDaoImp implements StuDao {
	String driver="org.hsqldb.jdbcDriver";
	String url="jdbc:hsqldb:hsql://localhost/";//hsqldb默认server 不需要写数据库名字，如果写要写别名！
	String user="sa";
	String pass="";
	//	insert into stu (username,stuclass) values ('mxy','qw');
	/**
	 * 
   	String username;
	String stuclass;
	int id;
	double java;
	double math;
	double linux;
	 * 
	 */
	public  List<Stu> findAll()throws Exception {
		List<Stu> stus=new ArrayList<Stu>();		
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url,user,pass);
		String sql="select * from stu";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();		
		while(rs.next()){
			Stu stu=new Stu();
			stu.setId(rs.getLong("id"));
			stu.setUsername(rs.getString("username"));
			stu.setStuclass(rs.getString("stuclass"));
			stu.setLinux(rs.getDouble("linux"));
			stu.setMath(rs.getDouble("math"));
			stu.setJava(rs.getDouble("java"));
			stus.add(stu);
		}
		rs.close();pstmt.close();con.close();
		//System.out.println(stus);
		return stus;

	}
	@Override
	public boolean delById(long id)throws Exception {
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url,user,pass);
		String sql="delete from stu where id="+id;
		PreparedStatement pstmt=con.prepareStatement(sql);
		int row=pstmt.executeUpdate();
		pstmt.close();con.close();
		return row>0;
	}
	@Override
	public boolean delByName(String name)throws Exception {
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url,user,pass);
		String sql="delete from stu where username='"+name+"'";
		PreparedStatement pstmt=con.prepareStatement(sql);
		int row=pstmt.executeUpdate();
		pstmt.close();con.close();
		return row>0;
	}
	@Override
	public Stu findById(long id) throws Exception {
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url,user,pass);
		String sql="select * from stu where id="+id;
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		Stu stu=new Stu();			
		while(rs.next()){
			stu.setId(rs.getInt("id"));
			stu.setUsername(rs.getString("username"));
			stu.setStuclass(rs.getString("stuclass"));
			stu.setLinux(rs.getDouble("linux"));
			stu.setMath(rs.getDouble("math"));
			stu.setJava(rs.getDouble("java"));	   
		}
		rs.close();pstmt.close();con.close();	
		return stu;
	}
	@Override
	public boolean update(Stu stu) throws Exception {
		Class.forName(driver);
		PreparedStatement pstmt = null;
		System.out.println(stu);
		int row = 0;
		Connection con=DriverManager.getConnection(url,user,pass);
		String sql="update stu set username=? ,stuclass=? ,linux=?, math=? ,java=? where id=?"; 
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1,stu.getUsername());		
		pstmt.setString(2,stu.getStuclass());
		pstmt.setDouble(3, stu.getLinux());
		pstmt.setDouble(4, stu.getMath());
		pstmt.setDouble(5,stu.getJava());
		pstmt.setLong(6,stu.getId());		
		row=pstmt.executeUpdate();
		pstmt.close();con.close();
		return row>0;		
	}
	@Override
	public boolean add(Stu stu) throws Exception{
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url,user,pass);
		//System.out.println("导向这里");
		String sql="insert into stu (username,stuclass,linux,math,java,id) values (?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setLong(6,stu.getId());
		pstmt.setString(1,stu.getUsername());		
		pstmt.setString(2,stu.getStuclass());
		pstmt.setDouble(3, stu.getLinux());
		pstmt.setDouble(4, stu.getMath());
		pstmt.setDouble(5,stu.getJava());			
		int row=pstmt.executeUpdate();
		pstmt.close();con.close();
		return row>0;

	}
	/**
	 * 在读取excel 里面 判断是否有新的stu在excel
	 * 如果有就 调用updata
	 * 没有add
	 */
	@Override
	public boolean judgeidexist(long id) throws Exception {
		Boolean x;
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url,user,pass);
		String sql="select * from stu where id="+id;
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		if(rs.next()){
			x = true;//数据库有这个id
		}
		else 
		{
			x= false;//结果集为空； 
		}
		rs.close();stmt.close();con.close();
		return x;

	}
	////自己测试///////////////////////////////////////////////////
	@Test
	public void find() throws Exception{
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url,user,pass);
		String sql="select * from stu where id="+2;
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		Stu stu=new Stu();
		if(rs.next()){			
			stu.setId(2);
			stu.setUsername(rs.getString("username"));			    
		}
		rs.close();pstmt.close();con.close();
		System.out.println(stu);		
	}
	@Test
	public void delByName()throws Exception {
		String m="mxy1";

		Class.forName(driver);
		Connection con=DriverManager.getConnection(url,user,pass);
		// String hsql = "From Student where first_name = '"+ first_name+"'";
		//易错,俩边单引号包裹
		String sql="delete from stu where username= '"+m+"'";
		PreparedStatement pstmt=con.prepareStatement(sql);
		int row=pstmt.executeUpdate();
		pstmt.close();con.close();

	}



}
