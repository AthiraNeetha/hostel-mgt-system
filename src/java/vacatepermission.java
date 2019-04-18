/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.year;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static java.sql.Timestamp.from;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KHSCI5MCA16099
 */
public class vacatepermission extends HttpServlet {

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
            
           String app = request.getParameter("t1");
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet vacatepermission</title>");            
            out.println("</head>");
            out.println("<body>");
          try
            {
                Class.forName("com.mysql.jdbc.Driver");
                com.mysql.jdbc.Connection con=(com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/hostel","root","");  
                
               
                   PreparedStatement ps1=con.prepareStatement("select * from registerug2");
                   ResultSet rs=ps1.executeQuery(); 
                 int c=0;
                 while(rs.next())
                 {
                     String id= rs.getString(1);
                     if(id.equals(app))
                     {
                         c++;
                     }
                 }    
                
                if(c ==1)
                {
                     java.util.Date date=new java.util.Date();
                    java.sql.Date sqlDate=new java.sql.Date(date.getTime());
                    PreparedStatement ps2=con.prepareStatement("select year from registerug2 where appno =? ");
                    ps2.setString(1,app);
                    ResultSet rs1=ps2.executeQuery(); 
                    String oldyear ="";
                    String newyear ="";
                    while(rs1.next())
                    {
                        oldyear = rs1.getString(1);
                    }
                    PreparedStatement ps3=con.prepareStatement("SELECT EXTRACT(YEAR FROM ?)");
                    ps3.setDate(1,sqlDate);
                    ResultSet rs2=ps3.executeQuery(); 
                   while(rs2.next())
                   {
                    newyear = rs2.getString(1);
                    }   
                   
                  
                   
                   PreparedStatement stmt1=con.prepareStatement("insert into vacate_details values(?,?,?,?)");
                    stmt1.setString(1,app);
                    stmt1.setDate(2, sqlDate);
                    stmt1.setString(3,oldyear);
                    stmt1.setString(4,newyear);
                    stmt1.executeUpdate();
                    PreparedStatement stmt2=con.prepareStatement("delete from registerug2 where appno =?");
                    stmt2.setString(1,app);
                    stmt2.executeUpdate();
                    
                    out.println("<script>alert('records updated');</script>");
                    request.getRequestDispatcher("visitordetails.html").include(request, response);
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
