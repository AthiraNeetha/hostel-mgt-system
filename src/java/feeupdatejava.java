/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Character.UnicodeScript.values;
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
public class feeupdatejava extends HttpServlet {

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
            String year1 = request.getParameter("y1");
            String year2 = request.getParameter("y2");
            String[] attend = (request.getParameterValues("names"));
            String[] depts = (request.getParameterValues("depts"));
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet feeupdatejava</title>"); 
            out.println("<script>function isNumberKey1(evt)\n" +
"              {\n" +
"                var charCode = (evt.which) ? evt.which : event.keyCode\n" +
"                if (charCode > 31 && (charCode < 48 || charCode > 57))\n" +
"                {\n" +
"                    alert(\"Numbers only..!!\");\n" +
"                    return false;\n" +
"                }\n" +
"                     return true;\n" +
"            }</script>");
            out.println("</head>");
            out.println("<body>");
               try
            {
                Class.forName("com.mysql.jdbc.Driver");
                com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/hostel","root", "");
                PreparedStatement ps = con.prepareStatement("select * from fee_struct");
               ResultSet rs = ps.executeQuery();
               int count = attend.length;
               if(year1.equals("") || year2.equals(""))
               {
                    out.println("<script>alert('Enter year');window.location.assign('updatefee');</script>");
                    request.getRequestDispatcher("updatefee.java").include(request, response);
               }
               int[] values = new int[count];
               for(int i= 0;i<attend.length;i++)
               {
                    values[i] = Integer.parseInt(attend[i]);
                    
               }
              
                for(int i=0; i<count; i++)
                {
                    PreparedStatement ps1 = con.prepareStatement("update  fee_struct set s_year=? , e_year=? , fee=? where area=? ");
                    ps1.setString(1, year1);
                    ps1.setString(2, year2);
                    ps1.setInt(3, values[i]);
                    ps1.setString(4, depts[i]);
                    ps1.executeUpdate();
                }
                
               con.close();
            }
            catch(Exception e)
            {
               out.println(e);
            }
           
            out.println("<script>alert('Record updated...!!!');window.location.assign('viewfee');</script>");
            
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
