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
public class studroomdisplay extends HttpServlet {

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
            out.println("<title>Servlet studroomdisplay</title>");            
            out.println("</head>");
              out.println("<body style='background-image: url(picture5.jpg); background-size: cover; background-repeat: no-repeat; background-position: top;'>");
          try
            {
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection con=(java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost/hostel","root","");
                PreparedStatement stmt=con.prepareStatement("select reg.appno ,reg.name ,s.rno from registerug2 reg INNER JOIN stud_room s ON reg.appno = s.appno  ORDER BY rno ASC");
               
                ResultSet rs=stmt.executeQuery(); 
                int i=0;
                out.println("<center><h1 style=color:Navy blue>STUDENT ROOM ALLOCATION DETAILS</h1></center>");
                out.println("<center>");
                out.println("<table border=1 width=50% height=50% align=center>"
                    + "<tr><th>STUDENT ID </th><th> STUDENT NAME </th><th>ROOM NO</th><th>STATUS</th></tr>");
               
                String sname , add ,nam ;
                while(rs.next())
                {
                    sname = rs.getString(1);
                    add = rs.getString(2);
                    nam = rs.getString(3);
                     out.println("<tr style=color:black>"
                        +"<td>"+"<center>" + sname +"<center>"+"</td>"
                        + "<td>" + "<center>" +add + "<center>" +"</td>"
                        + "<td>" +"<center>" + nam + "<center>" +"</td>"
                        + "<td>" +"<center>" + "Room Alloted..." + "<center>" +"</td>"
                        + "</tr>");
                }
                out.println("</table>");
                con.close();
            }
            catch(Exception e)
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
