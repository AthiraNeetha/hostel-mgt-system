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
public class studroomstatus extends HttpServlet {

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
            out.println("<title>Servlet studroomstatus</title>");            
            out.println("</head>");
            out.println("<body bgcolor=lavender>");
             try
            {
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection con=(java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost/hostel","root","");
               
                PreparedStatement stmt=con.prepareStatement("select * from stud_room where appno=?");
                stmt.setString(1, MyGlobals.Gname);
                ResultSet rs=stmt.executeQuery();
                String appno ="" ,room="";
                int count = 0;
                while(rs.next())
                {
                    appno  = rs.getString(1);
                    room = rs.getString(2);
                        
                }
               
                if(appno.equals(MyGlobals.Gname))
              {
                   out.println("<center style=font-size:25px;>");
                   out.println("<br><br><br><br>STATUS : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                   out.println("Room Alloted");
                   out.println("<br><br>ROOM NO :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                   out.println(room);
                   out.println("</center>");
             }
                if(appno.equals(""))
                {
                   out.println("<center style=font-size:25px>");
                   out.println("<br><br><br><br>STATUS : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                   out.println("Pending.....");
                   out.println("</center>");
                }
               
            }catch(Exception e)
            {
                
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