import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet(urlPatterns = {"/PresidentNameServlet"})
public class PresidentNameServlet extends HttpServlet
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>President Info</title>");
            out.println("<script>"
                    + "function reload()"
                    + "{"
                    + "location.reload();"
                    + "}"
                    + "</script>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>President Info</h1>");
            
            //reading the passing inputs
            String pnameIn = request.getParameter("PresidentKey");
            
            try{
            //now we are going to connect to the database logindb
            //we need 3 parameters to do the connection
            String database = "jdbc:mysql://localhost/presidentdb";
            String user = "root";
            String password = "";
            
            //need to load the jdbc Driver
            Class.forName("com.mysql.jdbc.Driver");
            
            //to connect we need to create an object of class Connection
            Connection mycon = DriverManager.getConnection(database,user,password);
            
            //let's create a String variable that holds the SQL select command
            String SQLselect = "select * from presidentdb_table where Name='" +
                    pnameIn+"'";
                    
            //In order to run an SQL select command in JSP we need to create an
            //object of the class Statement
            Statement mystat = mycon.createStatement();
            
            //we can now run the select command, it will produce an object of class ResultSet
            ResultSet results = mystat.executeQuery(SQLselect);
            
            if(results.next())
            {
                //moving logical pointer back to the first record
                //results.beforeFirst();
                
                //getting the fields' values
                String pName = results.getString("name");
                String party = results.getString("party");
                String PresidentInfo = "</br></br>President " + pName +" was a member of the "+party.replace(","," and the")+" party.";
                
                
                if(PresidentInfo.contains("Washington"))
                {
                 out.println("<img src ='gwashington.jpeg'>");   
                }else if(PresidentInfo.contains("John Adams"))                     
                {
                 out.println("<img src ='johnadams.jpeg'>");      
                }else if(PresidentInfo.contains("Jefferson"))                     
                {
                out.println("<img src ='thomasjefferson.jpeg'>");                     
                }else if(PresidentInfo.contains("Madison"))                     
                {
                 out.println("<img src ='jamesmadison.jpeg'>");      
                }else if(PresidentInfo.contains("John Quincy"))                     
                {
                 out.println("<img src ='quincyadams.jpeg'>");      
                }else if(PresidentInfo.contains("Jackson"))                     
                {
                out.println("<img src ='andrewjackson.jpeg'>");       
                }else if(PresidentInfo.contains("Buren"))                     
                {
                out.println("<img src ='vanburen.jpeg'>");       
                }else if(PresidentInfo.contains("Harrison"))                     
                {
                out.println("<img src ='harrison.jpeg'>");       
                }else if(PresidentInfo.contains("Tyler"))                     
                {
                out.println("<img src ='tyler.jpeg'>");       
                }else if(PresidentInfo.contains("Monroe"))                     
                {
                out.println("<img src ='jamesmonroe.jpeg'>");       
                }
                                
                if (PresidentInfo.contains("and the"))
                {                   
                    if (PresidentInfo.contains("None"))
                    {
                      out.println(PresidentInfo.replace("None","").replace("and the",""));         
                    }
                    else
                    {
                     out.println(PresidentInfo.replace("party","parties"));   
                    }
                }
                else 
                {
                    out.println(PresidentInfo);
                }
                
                

            }
            else out.println("No match for this president.");
            }catch(Exception e) 
            {
                out.println(e);
            }
            out.println("</br></br><button onclick='reload()'>Select Another President</button>");
            out.println("</body>");
            out.println("</html>");
        }
        catch(Exception e){}
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
