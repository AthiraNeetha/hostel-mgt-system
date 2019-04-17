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
public class myparent extends HttpServlet {

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
             String no = (request.getParameter("t1"));
            String pass = (request.getParameter("t2"));
            String  pname= (request.getParameter("t3"));
            String paddress = request.getParameter("t4");
            String pph = (request.getParameter("t5"));
            String pemail = request.getParameter("t6");
            String  lname= (request.getParameter("t7"));
            String laddress = request.getParameter("t8");
            String lph = (request.getParameter("t9"));
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet myparent</title>");            
            out.println("</head>");
            out.println("<body>");
             try
            {
                Class.forName("com.mysql.jdbc.Driver");
                com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/hostel","root", "");
                
                PreparedStatement stmt=(PreparedStatement)con.prepareStatement("select * from registerug2");
                ResultSet rs=stmt.executeQuery(); 
                int count=0;
                while(rs.next())
                {
                    if(rs.getString(1).equals(no))
                    {
                        count = 1;
                    }
                }
               if(count == 1)
                {
                       PreparedStatement stmt1=(PreparedStatement)con.prepareStatement("select * from parent");
                        ResultSet rs1=stmt1.executeQuery(); 
                        int count1=0;
                        while(rs1.next())
                        {
                            if(rs1.getString(1).equals(no))
                            {
                                count1 = 1;
                            }
                        }
                        if(count1 == 1)
                        {
                           out.println("<script type='text/javascript'>alert('You are already registered..');</script>");
                           request.getRequestDispatcher("parent.html").include(request, response);
                        }
                      if(count1 == 0)
                      {
                      PreparedStatement ps1 = (PreparedStatement)con.prepareStatement("insert into parent values(?,?,?,?,?,?,?,?,?)");
                      ps1.setString(1,no);
                      ps1.setString(2, pass);
                      ps1.setString(3,pname);
                      ps1.setString(4,paddress);
                      ps1.setString(5, pph);
                      ps1.setString(6, pemail);
                      ps1.setString(7,lname);
                      ps1.setString(8,laddress);
                      ps1.setString(9, lph);
                      ps1.executeUpdate();
                      out.println("<script type='text/javascript'>alert('Registration successful...');</script>");
                      request.getRequestDispatcher("parent.html").include(request, response);
                      
                     }
                }
                 if(count==0)
                {
                     out.println("<script type='text/javascript'>alert('No Such Admission exist , Try again...');</script>");
                      request.getRequestDispatcher("parentregister.html").include(request, response);
                }
                       con.close();
                
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
