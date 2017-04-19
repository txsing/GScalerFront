<%-- 
    Document   : Temp
    Created on : 20 Mar, 2016, 6:46:39 PM
    Author     : Anwesha94
--%>

<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.File"%>
<%@page import="propertycalculation.*" %>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="paperalgorithm.*" %>
<%
    //gets the path of the file which was uploaded
    String uploadedFilePath = session.getAttribute("uploadedFilePath").toString();
    String outputDir = session.getAttribute("uploadDir").toString();
    
    String scaledNodeSize = request.getParameter("scaledNodeSize");
    String scaledEdgeSize = request.getParameter("scaledEdgeSize");
    
    String[] args = new String[4];
    args[0] = uploadedFilePath;
    args[1] = outputDir;
    args[2] = scaledNodeSize;
    args[3] = scaledEdgeSize;

    Gscaler scaler = new Gscaler();
    
    long start = new java.util.Date().getTime();
    session.setAttribute("start", start);
 
    scaler.run(args);
    
    long end = new java.util.Date().getTime();
    session.setAttribute("end", end);

    //session.setAttribute("fp", uploadedFilePath);

    FileInputStream  fin = new FileInputStream(outputDir.concat("exception.txt"));
    if (fin.available() == 0) {
            session.setAttribute("isScaled", true);
    } else {
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", "exceptions.jsp");
    }
%>