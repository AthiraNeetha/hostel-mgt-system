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
public class selectionjava extends HttpServlet {

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
            String room = request.getParameter("rad2");
            String mess = request.getParameter("rad3");
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet staffadd</title>");            
            out.println("</head>");
            out.println("<body>");
         
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                com.mysql.jdbc.Connection con=(com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/hostel","root","");  
                PreparedStatement stmt=con.prepareStatement("select * from room_mess");
                ResultSet rs=stmt.executeQuery(); 
                int c=0;
                while(rs.next())
                {
                    String id= rs.getString(1);
                    if(id.equals(MyGlobals.Gname))
                    {
                        c++;
                    }
                }   
                PreparedStatement stmt1=con.prepareStatement("select * from stud_room");
                ResultSet rs1=stmt1.executeQuery(); 
                int c=0;
                while(rs.next())
                {
                    String id= rs.getString(1);
                    if(id.equals(MyGlobals.Gname))
                    {
                        c++;
                    }
                }   
                if(c ==0 )
                {
                PreparedStatement ps1=con.prepareStatement("insert into room_mess values(?,?,?)");
                ps1.setString(1,MyGlobals.Gname);
                ps1.setString(2,room);
                ps1.setString(3, mess);
               
                ps1.executeUpdate();
                out.println("<script>alert('details added');</script>");
                request.getRequestDispatcher("register.html").include(request, response);
                
                }
               else
                {
                   out.println("<script>alert('Your requirements are already noted...');</script>");
                   request.getRequestDispatcher("register.html").include(request, response);
                  
                }
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
