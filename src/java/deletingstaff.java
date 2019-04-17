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
public class deletingstaff extends HttpServlet {

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
            String sid = (request.getParameter("t1"));
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet deletingstaff</title>");            
            out.println("</head>");
             out.println("<body style='background-image: url(imaging.jpeg); background-size: cover; background-repeat: no-repeat; background-position: top;'>");
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                com.mysql.jdbc.Connection con=(com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/hostel","root",""); 
                PreparedStatement stmt=con.prepareStatement("select * from staff");
                ResultSet rs=stmt.executeQuery(); 
                int c=0;
                while(rs.next())
                {
                    String id= rs.getString(1);
	            if(id.equals(sid))
                    {
                        PreparedStatement ps=con.prepareStatement("delete from staff where id=?");  
                        ps.setString(1,sid);    
                        ps.executeUpdate();  
                        out.println("<script>alert('staff details is removed');</script>");
                        request.getRequestDispatcher("deletestaff.html").include(request, response);
                       // out.println("<script type='text/javascript'>alert('The staff details is removed');window.location('adminpage.html');</script>");
                        c++;
                    }
                }
                if(c==0)
                {
                     out.println("<script>alert('Entered id dosen't exist');</script>");
                    request.getRequestDispatcher("deletestaff.html").include(request, response);
                    //out.println("<script type='text/javascript'>alert('ENTERED ID DOESNOT EXIST');window.location('DDatabase.html');</script>");
                }
                con.close(); 
            }
           catch(Exception e)
            {
                out.println("Error Message is : " + e);
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
