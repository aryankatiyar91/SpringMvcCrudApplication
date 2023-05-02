package com.techpalle.controller;

import java.util.ArrayList;

import javax.servlet.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.techpalle.dao.StudentDao;
import com.techpalle.model.Student;

@Controller
public class StudentContoller 
{
	@RequestMapping(value="student",params="reg")
	public ModelAndView getRegisterPage()
	{
		ModelAndView mv=new ModelAndView();
		// Redirecting to register.jsp page
		mv.setViewName("register.jsp");
		
		return mv;
	}
	
	@RequestMapping("insert")
	public ModelAndView insertStudentServlet(String tbName,String tbEmail,String tbPass,long tbMob)
	{
		ModelAndView mv=new ModelAndView();
		
		// Passing method parameters and fetching the data enter by user
		Student s=new Student(tbName, tbEmail, tbPass, tbMob);
		
		// Calling insert method
		StudentDao.insertStudent(s);
		
		// Calling displayStudentList method
		mv=displayStudentList();
		
		return mv;
	}
	
	@RequestMapping(value="student",params="show")
	public ModelAndView displayStudentList()
	{
		ModelAndView mv=new ModelAndView();
		
		// Call the DAO method
		ArrayList<Student> alStud=StudentDao.getAllStudents();
		
		// we need to redirect o display.jsp with ArrayList Data:
		mv.addObject("student", alStud);
		mv.setViewName("display.jsp");
		
		return mv;
	}
	
	
	
	@RequestMapping("editForm")
	public ModelAndView editStudentdata(HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		
		// Fetching the id from url
		int id=Integer.parseInt(request.getParameter("id"));
		
		// Call the DAO method
		Student s=StudentDao.getOneStudentData(id);
		
		// we need to redirect to display.jsp with ArrayList Data:
		mv.addObject("oneStud", s);
		mv.setViewName("register.jsp");
		
		return mv;
	}
	
	@RequestMapping("update")
	public ModelAndView updateStudentData(HttpServletRequest request,String tbName,String tbEmail,String tbPass,long tbMob)		
	{
		ModelAndView mv=new ModelAndView();
		
		// Fetching the id from jsp page
		int id=Integer.parseInt(request.getParameter("tbId"));
		Student s=new Student(id,tbName, tbEmail, tbPass, tbMob);
		
		// Call the DAO method
		StudentDao.updateStudent(s);
		
		mv=displayStudentList();
		
		return mv;
	}
	
	
	@RequestMapping("delete")
	public ModelAndView deleteStudent(HttpServletRequest request)		
	{
		ModelAndView mv=new ModelAndView();
		
		// Fetching the id from url
		int id=Integer.parseInt(request.getParameter("id"));
		
		// Call the DAO method
		StudentDao.deleteStudent(id);
		
		mv=displayStudentList();
		
		return mv;
	}
}