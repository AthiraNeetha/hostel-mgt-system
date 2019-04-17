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
public class selectvalidate extends HttpServlet {

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
             String no = (request.getParameter("id"));
             String pa = (request.getParameter("pass"));
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet selectvalidate</title>");            
            out.println("</head>");
            out.println("<body>");
             try
                {
                    Class.forName("com.mysql.jdbc.Driver");  

                    java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel","root","");
                    PreparedStatement stmt=con.prepareStatement("select * from registerug2");  
                    ResultSet rs=stmt.executeQuery();
                    int x=0 ;
                    while(rs.next())
                    {
                         String userid = rs.getString(1);
                         String userpass= rs.getString(9);
                         if(userid.equals(no) && userpass.equals(pa))
                        {
                            x++;
                            MyGlobals.Gname = userid;
                            request.getRequestDispatcher("selection.html").include(request, response);
                        }
                    }
                    if(x == 0)
                    {
                        out.println("<script type='text/javascript'>alert('No such user exist ,Try again...');window.location('resiter.html');</script>"); 
                        request.getRequestDispatcher("register.html").include(request,response);
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
