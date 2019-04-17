/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
public class staffdetails extends HttpServlet {

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
            out.println("<title>Servlet staffdetails</title>");            
            out.println("</head>");
          out.println("<body style='background-image: url(back.jpg); background-size: cover; background-repeat: no-repeat; background-position: top;'>");
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/hostel","root","");
                PreparedStatement stmt=con.prepareStatement("select * from staff");
                ResultSet rs=stmt.executeQuery(); 
                int i=0;
                out.println("<center><h1 style=color:Navy blue>STAFF DETAILS</h1></center>");
                out.println("<center>");
                out.println("<table border=1 width=80% height=50% align=center>"
                    + "<tr><th>Staff ID </th><th>Staff Name</th><th>Mobile No</th><th>Email ID</th><th>Place</th><th>Password</th></tr>");
                int sno ;
                String sid , sname , smail , splace , spass;
                while(rs.next())
                {
                    sid = rs.getString(1);
                    sname = rs.getString(2);
                    sno= rs.getInt(3);
                    smail = rs.getString(4);
                    splace = rs.getString(5);
                    spass = rs.getString(6);
                    out.println("<tr style=color:#800000>"
                        +"<td>" + sid +"</td>"
                        + "<td>" + sname + "</td>"
                        + "<td>" + sno + "</td>"
                        + "<td>" + smail + "</td>"
                        + "<td>" + splace + "</td>"
                        + "<td>" + spass + "</td>"
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
