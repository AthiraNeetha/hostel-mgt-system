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
public class studstatus extends HttpServlet {

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
            out.println("<title>Servlet studstatus</title>");            
            out.println("</head>");
           out.println("<body style='background-image: url(back.jpg); background-size: cover; background-repeat: no-repeat; background-position: top;'>");
            
         try
            {
                 Class.forName("com.mysql.jdbc.Driver");
                com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/hostel","root", "");   
                PreparedStatement ps = con.prepareStatement("select * from registerug2");
                ResultSet rs = ps.executeQuery();
               
                out.println("<h1 align=center>Student List</h1>");
               
               
                out.println("<form action='save' method='post'><table border=1 width=50% height=50% align=center>"
                        + "<tr>"
                        + "<th>Student ID </th>"
                        + "<th>Student Name</th>"
                        +"<th>Status</th>"
                        + "</tr>");
                    
                while(rs.next())
                {
                    out.println("<tr align=center>"
                            + "<td> <input type=text value=\"" + rs.getString(1) + "\" name=ids></td>"
                                    + "<td> <input type='text' value=\"" + rs.getString(2) +  "\" name='names'></td>"
                                        +"<td width='20%'><select name='status'>"
                                            + "<option>IN</option>"
                                            + "<option>OUT</option>"+"</td>"
                                            + "</tr>");
                   
                }
                 
                out.println("</table><br><br>");
        }
        catch(Exception e)
        {
           out.println(e);
        }
            
           out.println("\n<center>");
           out.print("<input type='submit' value='SAVE'>"); 
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
