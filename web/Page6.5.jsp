<%-- 
    Document   : Page6.5
    Created on : 1 Apr, 2016, 1:28:46 PM
    Author     : Anwesha94
--%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="propertycalculation.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%
         String[] args1 = new String[8];
         args1[0]="-i";
         args1[1]=session.getAttribute("filePath")+File.separator+"scaled.txt";
         args1[2]="-m";
         args1[3]="hop";
         args1[4]="-o";
         args1[5]=session.getAttribute("filePath")+File.separator+"t2.txt";
         args1[6]="-s";
         args1[7]="0";
         PropertyCalculation pc=new PropertyCalculation();
         pc.doMain(args1); 
         
          String[] args2 = new String[8];
         args2[0]="-i";
         args2[1]=session.getAttribute("filePath").toString()+session.getAttribute("fileName").toString();
         args2[2]="-m";
         args2[3]="hop";
         args2[4]="-o";
         args2[5]=session.getAttribute("filePath")+File.separator+"ori.txt";
         args2[6]="-s";
         args2[7]="0";
         PropertyCalculation pc1=new PropertyCalculation();
         pc1.doMain(args2); 
         
         response.setStatus(response.SC_MOVED_TEMPORARILY);
         response.setHeader("Location", "Page7.jsp");
         
//          br=new BufferedReader(new FileReader("C://u//effective-diameter.txt"));
//         String dia=br.readLine();
        // -i "C://u//t1.txt" -i2 "C://u//t3.txt" -m "hop" -o "C://u//rad.txt" -s "0"
     %>
  
