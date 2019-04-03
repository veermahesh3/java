package com.login;
import java.io.IOException;  
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {  
public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  

    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
          
    String n=request.getParameter("username");  
    String p=request.getParameter("userpass"); 
    String cp=request.getParameter("cpass");
      if(p.equals(cp)&&!cp.isEmpty()&&!p.isEmpty())
      {     
     
    try{  
    	Class.forName("com.mysql.jdbc.Driver");  
    	Connection con=DriverManager.getConnection(  
    	"jdbc:mysql://localhost:3306/test","root","");  
    	      
    	PreparedStatement ps=con.prepareStatement(  
    	"insert into tst1 values(?,?)");  
    	ps.setString(1,n);  
    	ps.setString(2,p);  
    	int i=ps.executeUpdate();
    	
    	if(i>0)
    	{
    	 out.print("==========================Registered Successfully======<br>=============Now Login======");  
         RequestDispatcher rd=request.getRequestDispatcher("index.html");  
         rd.include(request,response);  
          }
    
      }
catch(Exception e)
{
out.println(e);
}
  }
    else{  
        out.print("========================Sorry Password Not Match============");  
        RequestDispatcher rd=request.getRequestDispatcher("register.html");  
        rd.include(request,response);  
 
   
      
      }
}
}
