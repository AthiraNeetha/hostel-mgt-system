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
public class form1 extends HttpServlet {

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
            
            String id = request.getParameter("t1");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet form1</title>");            
            out.println("</head>");
            out.println("<body style='background-image: url(back.jpg); background-size: cover; background-repeat: no-repeat; background-position: top;'>");
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/hostel","root", "");
               
                    PreparedStatement stmt=(PreparedStatement)con.prepareStatement("select * from registerug2");
                    ResultSet rs=stmt.executeQuery(); 
                    int count =0;
                    while(rs.next())
                    {
                        if(rs.getString(1).equals(id))
                        {
                            count = 1;
                        }
                    }
               
                
                
                
                if(count == 1)
                {
                      java.sql.PreparedStatement stmt1=con.prepareStatement("select * from registerug2 where appno=?");
                     stmt1.setString(1, id);
                     ResultSet rs1=stmt1.executeQuery(); 
                     int i=0;
                     out.println("<center>");
               
                while(rs1.next())
                {
                    out.println("<br><br>APPLICATION ID : ");
                    out.println("<input type=text size=20 value=\"" + rs1.getString(1) + "\">");
                    
                    out.println("<br><br>&nbsp;&nbsp;&nbsp;NAME :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  ");
                    out.println("<input type=text size=20 value=\"" + rs1.getString(2) + "\">");
                    
                    out.println("<br><br>DOB :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ");
                    out.println("<input type=text size=20 value=\"" + rs1.getString(3) + "\">");
                    
                    out.println("<br><br>GRADUATION TYPE : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ");
                    out.println("<input type=text size=10 value=\"" + rs1.getString(4) + "\">");
                    
                    
                    
                    out.println("<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CONTACT NO :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ");
                    out.println("<input type=text value=\"" + rs1.getString(6) + "\">");
                    
                    out.println("<br><br>EMAIL :&nbsp;&nbsp;&nbsp;&nbsp;  ");
                    out.println("<input type=text size=25 value=\"" + rs1.getString(7) + "\">");
                                    
                    out.println("<br><br>COURSE ENROLLED :  ");
                    out.println("<input type=text size=10 value=\"" + rs1.getString(10) + "\">");
                    
                    out.println("<br><br>YEAR OF ENROLLMENT :  ");
                    out.println("<input type=text size=5 value=\"" + rs1.getString(11) + "\">");
                    
                   out.println("<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ADDRESS :  ");
                    out.println("<input type=text size=50 value=\"" + rs1.getString(5) + "\">");
                }
                }
                if(count ==0 )
                {
                   out.println("<script>alert('NO SUCH STUDENT EXIST...');</script>");
                   request.getRequestDispatcher("viewstudwarden.html").include(request, response); 
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
