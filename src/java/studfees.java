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
public class studfees extends HttpServlet {

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
            String seater = request.getParameter("t5");
          
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet studfees</title>");            
            out.println("</head>");
            out.println("<body bgcolor=lavender>");
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection con=(java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost/hostel","root","");
                PreparedStatement stmt=con.prepareStatement("select * from fee_struct where area = ?");
                stmt.setString(1, seater);
                String year1="" , year2="";
                int fee=0 ,mess=0;
                ResultSet rs=stmt.executeQuery();
                while(rs.next())
                {
                    fee = rs.getInt(2);
                    year1 = rs.getString(3);
                    year2 = rs.getString(4);
                }
                 PreparedStatement stmt1=con.prepareStatement("select fee from fee_struct where area = ?");
                stmt1.setString(1, "Mess Charge");
              
                ResultSet rs1=stmt1.executeQuery();
                while(rs1.next())
                {
                    mess = rs1.getInt(1);
                }
                out.println("<center style=font-size:20px>");
                out.println("<br><br>HOSTEL FEE STRUCTURE FOR ACADEMIC YEAR " + year1 +" - " +year2);
                out.println("<br><br><br>"+seater+" :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ");
                    out.println(fee);
                    out.println("<br><br>Mess Charge: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ");
                    out.println(mess);
                    int sum = fee + mess;
                    out.println("<br><br>------------------------------------------------------");
                    out.println("<br><br>total :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "+sum);
                    out.println("</center>");
            }catch(Exception e)
            {
                out.println(e);
            }
              out.println("</center>");
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
