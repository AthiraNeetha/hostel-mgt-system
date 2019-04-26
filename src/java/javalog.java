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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KHSCI5MCA16099
 */
public class javalog extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            String Gname = request.getParameter("t1");
            String pass = request.getParameter("t2");
            String drop = request.getParameter("t3");
            
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet javalog</title>");            
            out.println("</head>");
            
            out.println("<body>");
            
            
           if(drop.equals("STUDENT"))
            {
                try
                {
                    Class.forName("com.mysql.jdbc.Driver");  

                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel","root",""); 
                    PreparedStatement stmt=con.prepareStatement("select * from registerUG2");  
                    ResultSet rs=stmt.executeQuery();       
                    int x=0 ;
                    while(rs.next())
                    {
                         String username = rs.getString(1);
                         String userpass= rs.getString(9);
                         if(username.equals(Gname) && userpass.equals(pass))
                        {
                            MyGlobals.StudentName = rs.getString(2);
                            MyGlobals.Gname = rs.getString(1);
                            request.setAttribute("NAME", MyGlobals.StudentName); 
                            x++;
                       }
                    }
                    if( x == 0)
                    {
                        out.println("<script type='text/javascript'>alert(Invalid Student);</script>");
                        request.getRequestDispatcher("login.html").include(request, response);
                    }
                    if (x == 1)
                    {
                       request.getRequestDispatcher("aint.jsp").forward(request, response);
                    }
                }catch(Exception e)
                {
                  out.println(e);  
                 }
            } 
           
           
           
           
           if(drop.equals("ADMINISTRATOR"))
            {
                try
                {
                    Class.forName("com.mysql.jdbc.Driver");  

                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel","root",""); 
                    PreparedStatement stmt=con.prepareStatement("select * from admin_login");  
                    ResultSet rs=stmt.executeQuery();       
                    int x=0 ;
                    while(rs.next())
                    {
                         String username = rs.getString(1);
                         String userpass= rs.getString(2);
                         if(username.equals(Gname) && userpass.equals(pass))
                        {
                            request.setAttribute("NAME", Gname); 
                            x++;
                        }
                    }
                    if( x == 0)
                    {
                        out.println("<script type='text/javascript'>alert(Invalid user..);window.location('login.html');</script>"); 
                        request.getRequestDispatcher("login.html").include(request, response);
                        
                    }
                    if (x == 1)
                    {
                        request.getRequestDispatcher("secret.html").forward(request, response);
                    }
                }catch(Exception e)
                {
                  out.println(e);  
                 }
            } 
           
           
             
           if(drop.equals("WARDEN"))
            {
                try
                {
                    Class.forName("com.mysql.jdbc.Driver");  

                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel","root",""); 
                    PreparedStatement stmt=con.prepareStatement("select * from staff");  
                    ResultSet rs=stmt.executeQuery();       
                    int x=0 ;
                    while(rs.next())
                    {
                         String username = rs.getString(1);
                         String userpass= rs.getString(6);
                         if(username.equals(Gname) && userpass.equals(pass))
                        {
                            request.setAttribute("NAME", Gname); 
                            x++;
                        }
                    }
                    if( x == 0)
                    {
                        out.println("<script type='text/javascript'>alert('Invalid user');window.location('login.html');</script>"); 
                        request.getRequestDispatcher("login.html").include(request, response);
                    }
                    if (x == 1)
                    {
                       request.getRequestDispatcher("wardenhtml.html").forward(request, response);
                    }
                }catch(Exception e)
                {
                  out.println(e);  
                 }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(javalog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(javalog.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(javalog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(javalog.class.getName()).log(Level.SEVERE, null, ex);
        }
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
