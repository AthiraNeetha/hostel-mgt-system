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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KHSCI5MCA16099
 */
public class AllPass extends HttpServlet {

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
            String name = request.getParameter("t1");
            String cur =  request.getParameter("t2");
            String newpass = request.getParameter("t3");
            String user = request.getParameter("t5");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AllPass</title>");            
            out.println("</head>");
            out.println("<body>");
             try
                {
                    Class.forName("com.mysql.jdbc.Driver");  

                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel","root","");
                    
                    if(user.equals("STUDENT"))
                    {
                    PreparedStatement stmt=con.prepareStatement("select * from registerug2");  
                    ResultSet rs=stmt.executeQuery();
                    int x=0 ;
                    while(rs.next())
                    {
                         String username = rs.getString(1);
                         String userpass= rs.getString(9);
                         if(username.equals(name) && userpass.equals(cur))
                        {
                            x++;
                        }
                    }
                    
                    if(x == 1)
                    {
                         PreparedStatement stmt1=con.prepareStatement("update registerug2 set password =? where appno=? and password=?");
                         stmt1.setString(1,newpass);
                         stmt1.setString(2,name);
                         stmt1.setString(3, cur);
                         stmt1.executeUpdate();
                          out.println("<script type='text/javascript'>alert('Password changed successfully....');window.location('changepass.html');</script>"); 
                          request.getRequestDispatcher("changepass.html").include(request, response);
                    }
                    if(x == 0)
                    {
                        out.println("<script type='text/javascript'>alert('No such user exist ,Try again...');window.location('changepass.html');</script>"); 
                    }
                    }
                    
                    
                    if(user.equals("PARENT"))
                    {
                    PreparedStatement stmt=con.prepareStatement("select * from parent");  
                    ResultSet rs=stmt.executeQuery();
                    int x=0 ;
                    while(rs.next())
                    {
                         String username = rs.getString(3);
                         String userpass= rs.getString(2);
                         if(username.equals(name) && userpass.equals(cur))
                        {
                            x++;
                        }
                    }
                    
                    if(x == 1)
                    {
                         PreparedStatement stmt1=con.prepareStatement("update parent set password =? where pname=? and password=?");
                         stmt1.setString(1,newpass);
                         stmt1.setString(2,name);
                         stmt1.setString(3, cur);
                         stmt1.executeUpdate();
                         out.println("<script type='text/javascript'>alert('Updated...');window.location('changepass.html');</script>"); 
                         request.getRequestDispatcher("changepass.html").include(request,response);
                    }
                    if(x == 0)
                    {
                        out.println("<script type='text/javascript'>alert('No such user exist ,Try again...');window.location('changepass.html');</script>"); 
                    }
                    }
                    
                    
                    if(user.equals("WARDEN"))
                    {
                    PreparedStatement stmt=con.prepareStatement("select * from staff");  
                    ResultSet rs=stmt.executeQuery();
                    int x=0 ;
                    while(rs.next())
                    {
                         String username = rs.getString(1);
                         String userpass= rs.getString(6);
                         if(username.equals(name) && userpass.equals(cur))
                        {
                            x++;
                        }
                    }
                    
                    if(x == 1)
                    {
                         PreparedStatement stmt1=con.prepareStatement("update staff set password =? where name=? and password=?");
                         stmt1.setString(1,newpass);
                         stmt1.setString(2,name);
                         stmt1.setString(3, cur);
                         stmt1.executeUpdate();
                         out.println("<script type='text/javascript'>alert('Updated...');window.location('changepass.html');</script>"); 
                         request.getRequestDispatcher("changepass.html").include(request,response);
                    }
                    if(x == 0)
                    {
                        out.println("<script type='text/javascript'>alert('No such user exist ,Try again...');window.location('changepass.html');</script>"); 
                    }
                    }
                    
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

    private void forward(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
