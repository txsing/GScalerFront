<%-- 
    Document   : Page2
    Created on : 21 Mar, 2016, 12:55:29 PM
    Author     : Anwesha94
--%>
<%@page import="java.io.File"%>
<%@page import="propertycalculation.*" %>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>

        <link rel="stylesheet" type="text/css" href="ns1.css">
        <link rel="nus icon" type="image/png" href="unnamed.png"/>
    </head>
    <body>
        <%
            String[] args1 = new String[6];
            String temp1 = session.getAttribute("fp") + "";
            args1[0] = "-i";
            args1[1] = temp1;
            args1[2] = "-o";
            args1[3] = session.getAttribute("filePath") + File.separator + "ori.txt";
            args1[4] = "-m";
            args1[5] = "degree";
            PropertyCalculation pc = new PropertyCalculation();
            pc.doMain(args1);
        %>
        <div class="form">

            <h1 class="head" ><strong>GscalerCloud:Synthetically Scaling Graphs<strong></h1>
                        <h2 style="color:white">Download File</h2><br>
                        <form action="DownloadFile.jsp">
                            <a>   <input type="submit" value="Download" style="height:50px;width:300px" class="button button-block">
                                </form></a> 
                                <%
                                    BufferedReader br = new BufferedReader(new FileReader(session.getAttribute("filePath") + File.separator + "out1.txt"));
                                    String edge, nodes;
                                    edge = br.readLine();
                                    nodes = br.readLine();

                                %>
                            <form action="Page3.jsp" method="post">
                                <div class="tab-content">


                                    <h1 style="text-align:left">Basic Statistics:-</h1>
                                    <div>
                                        <h3 class="header" style="color: white">
                                            Execution  Time:
                                            <% double temp = ((long) session.getAttribute("end") - (long) session.getAttribute("start")) / 1000.0;%>
                                        </h3>



                                        <h3 class="header" style="color: white">
                                            <%=temp%>
                                            <span class="req"></span>
                                        </h3>

                                    </div>




                                    <!--            <div>	 
                                                <h3 class="header" style="color: ">
                                                  Number of Nodes:-<span class="req"></span>
                                                </h3>
                                                  
                                                <h3 class="header" style="color: white">
                                                  <span class="req"></span>
                                                
                                                
                                                </h3>
                                                </div>-->



                                    <div>
                                        <h3 class="header" style="color: white">
                                            Number of Nodes:<span class="req"></span>
                                        </h3>
                                        <h3 class="header" style="color: white">
                                            <span class="req"> </span>

                                            <%=nodes%>
                                        </h3>
                                        <br>

                                    </div>


                                    <!--             <div>
                                              <h3 class="header" style="color: #13232f">
                                                  Number of Edges:-<span class="req"></span>
                                                </h3>
                                                <h3 class="header" style="color: white">
                                                  <span class="req"> </span>
                                                   
                                                
                                                </h3>
                                                <br><br>
                                                             <br>
                                                 </div>-->

                                    <div>
                                        <h3 class="header" style="color: white">
                                            Number of Edges:<span class="req"></span>
                                        </h3>
                                        <h3 class="header" style="color: white">
                                            <span class="req"> </span>

                                            <%=edge%>
                                        </h3>
                                        <br>
                                    </div>

                                    <br> <br> <br> <br> <br>
                                    <button type="submit" class="button button-block"/>Similarity Comparison</button> 
                            </form>
                            </div>

                            </body>
                            </html>