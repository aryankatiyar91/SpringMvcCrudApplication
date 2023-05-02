package com.techpalle.dao;

import java.sql.*;
import java.util.ArrayList;

import com.techpalle.model.*;

// Below class is used to write JDBC code
public class StudentDao 
{
	private static Connection con=null;
	private static PreparedStatement ps=null;
	private static Statement stm=null;
	private static ResultSet rs=null;
	
	private static final String studentInsertQuery="insert into student(name,email,pw,mobile) values(?,?,?,?)";
	private static final String studentListQuery="select * from student";
	private static final String studentEditQuery="select * from student where sno=?";
	private static final String studentUpdateQuery="update student set name=?,email=?,pw=?,mobile=? where sno=?";
	private static final String studentDeleteQuery="delete from student where sno=?";
	
	
	// Method for delete operation
	public static void deleteStudent(int id)
	{
		try 
		{
			con=JdbcSteps.getConnectionDef();
			ps= con.prepareStatement(studentDeleteQuery);
			ps.setInt(1, id);
			
			ps.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			JdbcSteps.finallyBlock();
		}
	}
	
	// Method for update operation
	public static void updateStudent(Student s) 
	{
		try 
		{
			con=JdbcSteps.getConnectionDef();
			ps= con.prepareStatement(studentUpdateQuery);
			ps.setString(1, s.getName());
			ps.setString(2, s.getEmail());
			ps.setString(3, s.getPassword());
			ps.setLong(4, s.getMobile());
			ps.setInt(5, s.getSno());
			
			ps.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			JdbcSteps.finallyBlock();
		}
	}
	
	// Method for fetch single row data
	public static Student getOneStudentData(int id)
	{
		Student s=null;
		try 
		{
			con=JdbcSteps.getConnectionDef();
			ps=con.prepareStatement(studentEditQuery);
			ps.setInt(1, id);
			
			rs= ps.executeQuery();
			
			rs.next();
			int i= rs.getInt("sno");
			String n =rs.getString("name");
			String e=rs.getString("email");
			String p=rs.getString("pw");
			Long m=rs.getLong("mobile");
			
			s=new Student(i,n, e, p,m);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			JdbcSteps.finallyBlock();
		}
		return s;
	}
	
	
	// Method for fetch all row data from table
	public static ArrayList<Student> getAllStudents() 
	{
		ArrayList<Student> al=new ArrayList<>();
		try 
		{
			con =JdbcSteps.getConnectionDef();
			stm=con.createStatement();
			rs =stm.executeQuery(studentListQuery);
			
			while(rs.next())
			{
				int n = rs.getInt("sno");
				String name =rs.getString("name");
				String email=rs.getString("email");
				String pass=rs.getString("pw");
				Long mobile=rs.getLong("mobile");
				
				Student c=new Student(n,name, email, pass,mobile);
				
				al.add(c);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			JdbcSteps.finallyBlock();
		}
		return al;
	}
	
	
	// Method for insert operation
	public static void insertStudent(Student s)
	{
		try 
		{
			con=JdbcSteps.getConnectionDef();
			ps=con.prepareStatement(studentInsertQuery);
			ps.setString(1, s.getName());
			ps.setString(2, s.getEmail());
			ps.setString(3, s.getPassword());
			ps.setLong(4, s.getMobile());
			
			ps.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			JdbcSteps.finallyBlock();
		}
	}
}
