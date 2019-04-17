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
public class roomsuballocate extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet roomsuballocate</title>");            
            out.println("</head>");
            out.println("<body>");
           try
            {
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection con=(java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost/hostel","root","");
                PreparedStatement stmt=con.prepareStatement("select reg.appno ,reg.graduation ,reg.year ,"
                        + "r.seater,r.mess from registerug2 reg INNER JOIN room_mess r ON reg.appno = r.appno");
                
                ResultSet rs=stmt.executeQuery(); 
                int i=0;
                int count=0;
                String appno ,grad,year, seater , mess;
                while(rs.next())
                {
                    appno = rs.getString(1);
                    grad = rs.getString(2);
                    year = rs.getString(3);
                    seater = rs.getString(4);
                    mess = rs.getString(5);
                   
                  /*out.println(appno);
                  out.println(grad);
                  out.println(year);
                  out.println(seater);
                  out.println(mess);*/
                  
                    PreparedStatement stmt1=con.prepareStatement("select * from vacant_room");
                    ResultSet rs1=stmt1.executeQuery();
                    String roomno , roomgrad , roomyear , roomseat ,roomvacant;
                    while(rs1.next())
                    {
                        roomno = rs1.getString(1);
                        roomgrad = rs1.getString(2);
                        roomyear = rs1.getString(3);
                        roomseat = rs1.getString(4);
                        roomvacant = rs1.getString(5);
                        int roo = Integer.parseInt(roomvacant);
                        /*out.println(roomno);
                        out.println(roomgrad);
                        out.println(roomyear);
                        out.println(roomseat);
                        out.println(roo);*/
                        if(grad.equals(roomgrad) && year.equals(roomyear) && seater.equals(roomseat))
                        {
                             int c= 0;
                             PreparedStatement stmt4=con.prepareStatement("select * from stud_room");
                             ResultSet rs2=stmt4.executeQuery(); 
                             while(rs2.next())
                             {
                                  String username = rs2.getString(1);                                               
                                  if(username.equals(appno)) 
                                  {
                                      c++;
                                  }
                             }
                            
                           if(roo > 0 && c==0)
                           {
                           PreparedStatement stmt2=con.prepareStatement("insert into stud_room values(?,?,?)");
                           stmt2.setString(1, appno);
                           stmt2.setString(2,roomno);
                           stmt2.setString(3,mess);
                           stmt2.executeUpdate();
                           roo = roo-1;
                           PreparedStatement stmt3=con.prepareStatement("update vacant_room set vacancy=? where rno=?");
                           stmt3.setInt(1, roo);
                           stmt3.setString(2, roomno);
                           stmt3.executeUpdate();
                           PreparedStatement stmt5=con.prepareStatement("delete from room_mess where appno=?");
                           stmt5.setString(1, appno);
                           stmt5.executeUpdate();
                           }
                         if(c > 1)
                        {
                             PreparedStatement stmt5=con.prepareStatement("delete from room_mess where appno=?");
                            stmt5.setString(1, appno);
                            stmt5.executeUpdate();
                        }
                       }
                    }
                    
                }
                out.println("<script type='text/javascript'>alert('ROOMS ARE ALOCATED');</script>"); 
                          request.getRequestDispatcher("studroomdisplay").include(request, response);
             
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
