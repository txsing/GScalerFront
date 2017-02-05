<%-- 
    Document   : DegreeComparison
    Created on : 5 Feb, 2017, 2:25:50 PM
    Author     : txsing
--%>
<%@ page import="java.io.*,java.util.*" %>
<%@page import="propertycalculation.InOutDegreeDisCal" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String scaledFilePath = session.getAttribute("uploadDir").toString().concat("scaled.txt");
    String oriFilePath = session.getAttribute("uploadedFilePath").toString();
    
    int scaledNodeSize = Integer.parseInt(session.getAttribute("scaledNodeSize").toString());
    int oriNodeSize = Integer.parseInt(session.getAttribute("originalnode").toString());
    
    String outDegreePlotString = InOutDegreeDisCal.computeInOutDegreeDistribution(oriFilePath, scaledFilePath, 
            oriNodeSize, scaledNodeSize, 0);
    String inDegreePlotString = InOutDegreeDisCal.computeInOutDegreeDistribution(oriFilePath, scaledFilePath, 
            oriNodeSize, scaledNodeSize, 1);

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
