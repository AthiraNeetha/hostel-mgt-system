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
public class suballocate extends HttpServlet {

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
            out.println("<title>Servlet suballocate</title>");            
            out.println("</head>");
            out.println("<body>");
           out.println("<body style='background-image: url(picture5.jpg); background-size: cover; background-repeat: no-repeat; background-position: top;'>");
           out.println("<form action='roomsuballocate'>");
          try
            {
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection con=(java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost/hostel","root","");
                PreparedStatement stmt=con.prepareStatement("select reg.appno ,reg.graduation ,reg.year ,"
                        + "r.seater,r.mess from registerug2 reg INNER JOIN room_mess r ON reg.appno = r.appno");
                
                ResultSet rs=stmt.executeQuery(); 
                int i=0;
                out.println("<center><h1 style=color:Navy blue>REGISTRATION FOR ROOM ALLOCATION</h1></center>");
                out.println("<center>");
                out.println("<table border=1 width=80% height=50% align=center>"
                    + "<tr><th>APP NO </th><th>GRADUATION</th><th>YEAR</th><th>SEATER</th><th>FOOD TYPE</th><th>STATUS</th></tr>");
               
                String appno ,grad,year, seater , mess;
                while(rs.next())
                {
                   appno = rs.getString(1);
                   grad = rs.getString(2);
                   year = rs.getString(3);
                   seater = rs.getString(4);
                   mess = rs.getString(5);
                   
                    
                    out.println("<tr style=color:black>"
                        +"<td>" + appno +"</td>"
                        + "<td>" + grad + "</td>"
                        + "<td>" + year+ "</td>"
                        +"<td>" + seater +"</td>"
                        + "<td>" + mess + "</td>"
                        + "<td>" +"Pending..."+"</td>"
                        + "</tr>");
                }
                out.println("</table>");
                con.close();
                
                 out.println("<br><br><br><center><b><input type='submit' style='height:30px;color:#8000ff' value= 'ALLOCATE ROOM' action='roomsuballocate'></b></center>");
                                       
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
