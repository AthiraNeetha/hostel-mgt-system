/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KHSCI5MCA16099
 */
public class wardenstudview extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            String id = request.getParameter("t1");
            String grad = request.getParameter("t2");
            String course = request.getParameter("t3");
            String year = request.getParameter("t4");
          
             
             int count=0;
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet wardenstudview</title>");            
            out.println("</head>");
            out.println("<body style='background-image: url(picture5.jpg); background-size: cover; background-repeat: no-repeat; background-position: top;'>");
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/hostel","root", "");
                if(!(id.equals(null)))
                {
                    PreparedStatement stmt=(PreparedStatement)con.prepareStatement("select * from registerug2");
                    ResultSet rs=stmt.executeQuery(); 
                  
                    while(rs.next())
                    {
                        if(rs.getString(1).equals(id))
                        {
                            count = 1;
                        }
                    }
                }
                
                
                
                if(count == 1)
                {
                     java.sql.PreparedStatement stmt=con.prepareStatement("select * from registerug2 where appno=?");
                     stmt.setString(1, id);
                     ResultSet rs=stmt.executeQuery(); 
                     int i=0;
                     out.println("<center>");
               
                while(rs.next())
                {
                    out.println("<br><br>APPLICATION ID : ");
                    out.println("<input type=text size=20 value=\"" + rs.getString(1) + "\">");
                    
                    out.println("<br><br>&nbsp;&nbsp;&nbsp;NAME :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  ");
                    out.println("<input type=text size=20 value=\"" + rs.getString(2) + "\">");
                    
                    out.println("<br><br>DOB :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ");
                    out.println("<input type=text size=20 value=\"" + rs.getString(3) + "\">");
                    
                    out.println("<br><br>GRADUATION TYPE : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ");
                    out.println("<input type=text size=10 value=\"" + rs.getString(4) + "\">");
                    
                    
                    
                    out.println("<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CONTACT NO :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ");
                    out.println("<input type=text value=\"" + rs.getString(6) + "\">");
                    
                    out.println("<br><br>EMAIL :&nbsp;&nbsp;&nbsp;&nbsp;  ");
                    out.println("<input type=text size=25 value=\"" + rs.getString(7) + "\">");
                                    
                    out.println("<br><br>COURSE ENROLLED :  ");
                    out.println("<input type=text size=10 value=\"" + rs.getString(10) + "\">");
                    
                    out.println("<br><br>YEAR OF ENROLLMENT :  ");
                    out.println("<input type=text size=5 value=\"" + rs.getString(11) + "\">");
                    
                   out.println("<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ADDRESS :  ");
                    out.println("<input type=text size=50 value=\"" + rs.getString(5) + "\">");
                    
                }
                }
                if(id.equals("") && !(grad.equals("")) && course.equals("") && year.equals(""))
                {
                    
                    java.sql.PreparedStatement ps1=con.prepareStatement("select * from registerug2 where graduation = ?");
                    ps1.setString(1,grad);
                    ResultSet rs=ps1.executeQuery(); 
                    int i=0;
                   
                    out.println("<center><h1 style=color:Navy blue>STUDENT DETAILS</h1></center>");
                    out.println("<center>");
                    out.println("<table border=1 width=100% height=50% align=center>"
                    + "<tr><th>Application No </th><th>Stud Name</th><th>DOB</th><th>Graduation Type</th>"
                        + "<th>Address</th><th>Contact</th><th>Email</th><th>Course</th><th>Year</th></tr>");
             String sid ,name ,dob , gradu ,addr,phno ,email ,cou ,yea ;
               
                while(rs.next())
                {
                    
                    sid  = rs.getString(1);
                    name = rs.getString(2);
                    dob = rs.getString(3);
                   gradu = rs.getString(4);
                   addr  = rs.getString(5);
                   phno = rs.getString(6);
                   email = rs.getString(7);
                   cou  = rs.getString(10);
                   yea = rs.getString(11);
                    out.println("<tr style=color:#1a53ff>"
                        +"<td>" + sid +"</td>"
                        + "<td>" +name + "</td>"
                        + "<td>" + dob + "</td>"
                        + "<td>" + gradu + "</td>"
                        + "<td>" + addr + "</td>" 
                        + "<td>" + phno+ "</td>"
                        + "<td>" + email + "</td>"
                        + "<td>" + cou + "</td>"
                        + "<td>" + yea + "</td>"
                        + "</tr>");
                }
                out.println("</table>");
                }
                
                if(id.equals("") && !(grad.equals("")) && !(course.equals("")) && year.equals(""))
                {
                    
                    java.sql.PreparedStatement ps2=con.prepareStatement("select * from registerug2 where graduation = ? and course=?");
                    ps2.setString(1,grad);
                    ps2.setString(2, course);
                    ResultSet rs=ps2.executeQuery(); 
                    int i=0;
                   
                    out.println("<center><h1 style=color:Navy blue>STUDENT DETAILS</h1></center>");
                    out.println("<center>");
                    out.println("<table border=1 width=100% height=50% align=center>"
                    + "<tr><th>Application No </th><th>Stud Name</th><th>DOB</th><th>Graduation Type</th>"
                        + "<th>Address</th><th>Contact</th><th>Email</th><th>Course</th><th>Year</th></tr>");
             String sid ,name ,dob , gradu ,addr,phno ,email ,cou ,yea ;
               
                while(rs.next())
                {
                    
                    sid  = rs.getString(1);
                    name = rs.getString(2);
                    dob = rs.getString(3);
                   gradu = rs.getString(4);
                   addr  = rs.getString(5);
                   phno = rs.getString(6);
                   email = rs.getString(7);
                   cou  = rs.getString(10);
                   yea = rs.getString(11);
                    out.println("<tr style=color:#1a53ff>"
                        +"<td>" + sid +"</td>"
                        + "<td>" +name + "</td>"
                        + "<td>" + dob + "</td>"
                        + "<td>" + gradu + "</td>"
                        + "<td>" + addr + "</td>" 
                        + "<td>" + phno+ "</td>"
                        + "<td>" + email + "</td>"
                        + "<td>" + cou + "</td>"
                        + "<td>" + yea + "</td>"
                        + "</tr>");
                }
                out.println("</table>");
                }
                
                if(id.equals("") && !(grad.equals("")) && !(course.equals("")) && !(year.equals("")))
                {
                    
                    java.sql.PreparedStatement ps3=con.prepareStatement("select * from registerug2 where graduation = ? and course=? and year =?");
                    ps3.setString(1,grad);
                    ps3.setString(2,course);
                    ps3.setString(3,year);
                    ResultSet rs=ps3.executeQuery(); 
                    int i=0;
                   
                    out.println("<center><h1 style=color:Navy blue>STUDENT DETAILS</h1></center>");
                    out.println("<center>");
                    out.println("<table border=1 width=100% height=50% align=center>"
                    + "<tr><th>Application No </th><th>Stud Name</th><th>DOB</th><th>Graduation Type</th>"
                        + "<th>Address</th><th>Contact</th><th>Email</th><th>Course</th><th>Year</th></tr>");
             String sid ,name ,dob , gradu ,addr,phno ,email ,cou ,yea ;
               
                while(rs.next())
                {
                    
                    sid  = rs.getString(1);
                    name = rs.getString(2);
                    dob = rs.getString(3);
                   gradu = rs.getString(4);
                   addr  = rs.getString(5);
                   phno = rs.getString(6);
                   email = rs.getString(7);
                   cou  = rs.getString(10);
                   yea = rs.getString(11);
                    out.println("<tr style=color:#1a53ff>"
                        +"<td>" + sid +"</td>"
                        + "<td>" +name + "</td>"
                        + "<td>" + dob + "</td>"
                        + "<td>" + gradu + "</td>"
                        + "<td>" + addr + "</td>" 
                        + "<td>" + phno+ "</td>"
                        + "<td>" + email + "</td>"
                        + "<td>" + cou + "</td>"
                        + "<td>" + yea + "</td>"
                        + "</tr>");
                }
                out.println("</table>");
                }
               
              if(id.equals("") && !(grad.equals("")) && (course.equals("")) && !(year.equals("")))
                {
                    
                    java.sql.PreparedStatement ps4=con.prepareStatement("select * from registerug2 where graduation = ? and year =?");
                    ps4.setString(1,grad);
                   
                    ps4.setString(2,year);
                    ResultSet rs=ps4.executeQuery(); 
                    int i=0;
                   
                    out.println("<center><h1 style=color:Navy blue>STUDENT DETAILS</h1></center>");
                    out.println("<center>");
                    out.println("<table border=1 width=100% height=50% align=center>"
                    + "<tr><th>Application No </th><th>Stud Name</th><th>DOB</th><th>Graduation Type</th>"
                        + "<th>Address</th><th>Contact</th><th>Email</th><th>Course</th><th>Year</th></tr>");
             String sid ,name ,dob , gradu ,addr,phno ,email ,cou ,yea ;
               
                while(rs.next())
                {
                    
                    sid  = rs.getString(1);
                    name = rs.getString(2);
                    dob = rs.getString(3);
                   gradu = rs.getString(4);
                   addr  = rs.getString(5);
                   phno = rs.getString(6);
                   email = rs.getString(7);
                   cou  = rs.getString(10);
                   yea = rs.getString(11);
                    out.println("<tr style=color:#1a53ff>"
                        +"<td>" + sid +"</td>"
                        + "<td>" +name + "</td>"
                        + "<td>" + dob + "</td>"
                        + "<td>" + gradu + "</td>"
                        + "<td>" + addr + "</td>" 
                        + "<td>" + phno+ "</td>"
                        + "<td>" + email + "</td>"
                        + "<td>" + cou + "</td>"
                        + "<td>" + yea + "</td>"
                        + "</tr>");
                }
                out.println("</table>");
                }
              
              if(id.equals("") && (grad.equals("")) && !(course.equals("")) && !(year.equals("")))
                {
                    
                    java.sql.PreparedStatement ps5=con.prepareStatement("select * from registerug2 where  course=? and year =?");
                   
                    ps5.setString(1,course);
                    ps5.setString(2,year);
                    ResultSet rs=ps5.executeQuery(); 
                    int i=0;
                   
                    out.println("<center><h1 style=color:Navy blue>STUDENT DETAILS</h1></center>");
                    out.println("<center>");
                    out.println("<table border=1 width=100% height=50% align=center>"
                    + "<tr><th>Application No </th><th>Stud Name</th><th>DOB</th><th>Graduation Type</th>"
                        + "<th>Address</th><th>Contact</th><th>Email</th><th>Course</th><th>Year</th></tr>");
             String sid ,name ,dob , gradu ,addr,phno ,email ,cou ,yea ;
               
                while(rs.next())
                {
                    
                    sid  = rs.getString(1);
                    name = rs.getString(2);
                    dob = rs.getString(3);
                   gradu = rs.getString(4);
                   addr  = rs.getString(5);
                   phno = rs.getString(6);
                   email = rs.getString(7);
                   cou  = rs.getString(10);
                   yea = rs.getString(11);
                    out.println("<tr style=color:#1a53ff>"
                        +"<td>" + sid +"</td>"
                        + "<td>" +name + "</td>"
                        + "<td>" + dob + "</td>"
                        + "<td>" + gradu + "</td>"
                        + "<td>" + addr + "</td>" 
                        + "<td>" + phno+ "</td>"
                        + "<td>" + email + "</td>"
                        + "<td>" + cou + "</td>"
                        + "<td>" + yea + "</td>"
                        + "</tr>");
                }
                out.println("</table>");
                }
              
              if(id.equals("") && (grad.equals("")) && !(course.equals("")) && (year.equals("")))
                {
                    
                    java.sql.PreparedStatement ps6=con.prepareStatement("select * from registerug2 where course=?");
                   
                    ps6.setString(1,course);
                   
                    ResultSet rs=ps6.executeQuery(); 
                    int i=0;
                   
                    out.println("<center><h1 style=color:Navy blue>STUDENT DETAILS</h1></center>");
                    out.println("<center>");
                    out.println("<table border=1 width=100% height=50% align=center>"
                    + "<tr><th>Application No </th><th>Stud Name</th><th>DOB</th><th>Graduation Type</th>"
                        + "<th>Address</th><th>Contact</th><th>Email</th><th>Course</th><th>Year</th></tr>");
             String sid ,name ,dob , gradu ,addr,phno ,email ,cou ,yea ;
               
                while(rs.next())
                {
                    
                    sid  = rs.getString(1);
                    name = rs.getString(2);
                    dob = rs.getString(3);
                   gradu = rs.getString(4);
                   addr  = rs.getString(5);
                   phno = rs.getString(6);
                   email = rs.getString(7);
                   cou  = rs.getString(10);
                   yea = rs.getString(11);
                    out.println("<tr style=color:#1a53ff>"
                        +"<td>" + sid +"</td>"
                        + "<td>" +name + "</td>"
                        + "<td>" + dob + "</td>"
                        + "<td>" + gradu + "</td>"
                        + "<td>" + addr + "</td>" 
                        + "<td>" + phno+ "</td>"
                        + "<td>" + email + "</td>"
                        + "<td>" + cou + "</td>"
                        + "<td>" + yea + "</td>"
                        + "</tr>");
                }
                out.println("</table>");
                }
                
              if(id.equals("") && (grad.equals("")) && (course.equals("")) && !(year.equals("")))
                {
                    
                    java.sql.PreparedStatement ps7=con.prepareStatement("select * from registerug2 where year =?");
                  
                    ps7.setString(1,year);
                    ResultSet rs=ps7.executeQuery(); 
                    int i=0;
                   
                    out.println("<center><h1 style=color:Navy blue>STUDENT DETAILS</h1></center>");
                    out.println("<center>");
                    out.println("<table border=1 width=100% height=50% align=center>"
                    + "<tr><th>Application No </th><th>Stud Name</th><th>DOB</th><th>Graduation Type</th>"
                        + "<th>Address</th><th>Contact</th><th>Email</th><th>Course</th><th>Year</th></tr>");
             String sid ,name ,dob , gradu ,addr,phno ,email ,cou ,yea ;
               
                while(rs.next())
                {
                    
                    sid  = rs.getString(1);
                    name = rs.getString(2);
                    dob = rs.getString(3);
                   gradu = rs.getString(4);
                   addr  = rs.getString(5);
                   phno = rs.getString(6);
                   email = rs.getString(7);
                   cou  = rs.getString(10);
                   yea = rs.getString(11);
                    out.println("<tr style=color:#1a53ff>"
                        +"<td>" + sid +"</td>"
                        + "<td>" +name + "</td>"
                        + "<td>" + dob + "</td>"
                        + "<td>" + gradu + "</td>"
                        + "<td>" + addr + "</td>" 
                        + "<td>" + phno+ "</td>"
                        + "<td>" + email + "</td>"
                        + "<td>" + cou + "</td>"
                        + "<td>" + yea + "</td>"
                        + "</tr>");
                }
                out.println("</table>");
                }
            }catch(Exception e)
            {
                out.println(e);
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
