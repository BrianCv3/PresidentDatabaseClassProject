<%-- 
    Document   : index
    Created on : Mar 16, 2019, 6:13:29 PM
    Author     : Brian
--%>

<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.util.Scanner"%>
<%@page import="sun.misc.IOUtils"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="java.io.File"%>
<%@page import="java.io.File"%>
<%@page import="java.io.InputStream"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript">
        function showpresidents(sel)
        {
            var PresidentSelect = (sel.options[sel.selectedIndex].text);
            $.ajax
                    (
                            {
                                //communicating with server's and passing the name                       
                                url: "PresidentNameServlet",
                                data: "PresidentKey=" + PresidentSelect,
                                success: function (serverdata)
                                {
                                    $("#show").html(serverdata);
                                }
                            }
                    )
        }
        </script>
        <style>
            #output
            {
                display: none;
            }
        </style>
    </head>
    <body>
        <form action="PresidentNameServlet" method="post">           
            <%
                //will read csv and populate a select element
                String fileName
                        = "C:/Users/Brian/Desktop/Hwk6.csv";
                InputStream ins = new FileInputStream(fileName);
                try
                {
                    if (ins == null)
                    {
                        out.println("File not Found");
                    } else
                    {
                        InputStreamReader isr = new InputStreamReader(ins);
                        BufferedReader reader = new BufferedReader(isr);

                        String fileLine = "";
                        int firstReading = 1;
                        out.println("<h2>Which president would you like to know about?<h2>"
                                + "<select id='PresidentList' onchange='showpresidents(this)'>");
                        out.println("<option value=''>Select a President</option>");
                        while ((fileLine = reader.readLine()) != null)
                        {
                            if (firstReading == 1)
                            {
                                firstReading = 2;
                            } else
                            {
                                String lineArray[] = fileLine.split(",");
                                String faculty = lineArray[1];
                                out.println("<option value='" + faculty + "'>" + faculty + "</option>");
                            }
                        }
                        out.println("</select>");
                    }
                } catch (Exception e)
                {
                }
            %>


            <br/>
        </form>
    </body>
</html>

