<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.output.*" %>
<%@page import="paperalgorithm.*" %>

<%
    File file;
    
    String timestamp = String.valueOf(System.currentTimeMillis());
    String rootpath = request.getServletContext().getRealPath("");
    String uploadDir = rootpath + File.separator + "uploadedfiles" + File.separator + timestamp + File.separator;
    String uploadedFileName = "draw_edgelist.txt";
    session.setAttribute("uploadDir", uploadDir);
    session.setAttribute("uploadedFilePath", uploadDir.concat(uploadedFileName));
    
    session.setAttribute("isUploaded", "true");
    session.setAttribute("isScaled", "false");
    
    new File(uploadDir).mkdir();
    String graphString = java.net.URLDecoder.decode(request.getParameter("graphString"), "UTF-8");
                   
    byte[] bt = graphString.getBytes();
    try {
        file = new File(uploadDir.concat(uploadedFileName));
        FileOutputStream in = new FileOutputStream(file);
        in.write(bt);
        in.close();      
    } catch (Exception ex) {
        System.err.println("EXP(AutoUploadGraph): ".concat(ex.getMessage()));
    }
%>
