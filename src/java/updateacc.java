/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KHSCI5MCA16099
 */
public class updateacc extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet updateacc</title>");            
            out.println("</head>");
            out.println("<body>");
            //out.println("<body style='background-image: url(border1.jpg);  background-repeat: no-repeat; background-position: top;'>");
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection con=(java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost/hostel","root","");
                PreparedStatement stmt=con.prepareStatement("select * from registerug2 where appno=?");
                stmt.setString(1, MyGlobals.Gname);
                ResultSet rs=stmt.executeQuery(); 
                int i=0;
              
                out.println("<form action = accountupdate >");
                 out.println("<center>");
                out.println("<fieldset style=\"width: 500px;height :300px;border-color:blueviolet\">");
                 
                while(rs.next())
                {
                    out.println("<br><br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;APPLICATION ID : ");
                    out.println(rs.getString(1));
                   
                    out.println("<br><br>NAME : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ");
                    out.println(rs.getString(2));
                    
                    out.println("<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CONTACT NO : ");
                    out.print("<input type=text name=t1 value=\"" + rs.getString(6) + "\">");
                    out.print("&nbsp;&nbsp;*");
                    out.println("<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;EMAIL :  ");
                    out.print("<input type=text size=30 name=t2 value=\"" + rs.getString(7) + "\">");
                    out.print("&nbsp;&nbsp;*");
                   
                             
                }
                
            }catch(Exception e)
            {
                out.println(e);
            }
            out.println("<br><br><input type='submit' value='SAVE'>");
             out.println("<br><br><br><p style=color:red;>* &nbsp;:&nbsp; Fields to update if required</p>");
            out.println("</fieldset>");
            out.println("</center>");
            out.println("</form>");
          
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
