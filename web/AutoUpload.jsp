<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.output.*" %>
<%@page import="paperalgorithm.*" %>

<%
    File file;
    int maxFileSize = 50000 * 1024;
    int maxMemSize = 50000 * 1024;
    String timestamp = System.currentTimeMillis() + "";
    String rootpath = request.getServletContext().getRealPath("");
    String uploadDir = rootpath + File.separator + "uploadedfiles" + File.separator + timestamp + File.separator;
    new File(uploadDir).mkdir();

    // Verify the content type
    DiskFileItemFactory factory = new DiskFileItemFactory();
    // maximum size that will be stored in memory
    factory.setSizeThreshold(maxMemSize);
    // Create a new file upload handler
    ServletFileUpload upload = new ServletFileUpload(factory);
    // maximum file size to be uploaded.
    upload.setSizeMax(maxFileSize);
    String graphString = java.net.URLDecoder.decode(request.getParameter("graphString"), "UTF-8");
    String uploadedFileName = "auto_edgelist.txt";
    byte[] bt = graphString.getBytes();
    try {
        file = new File(uploadDir.concat(uploadedFileName));
        FileOutputStream in = new FileOutputStream(file);
        in.write(bt );
        in.close();
        session.setAttribute("uploadDir", uploadDir);
        session.setAttribute("uploadedFilePath", uploadDir.concat(uploadedFileName));
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", "index.jsp#uploadAndScale");
    } catch (Exception ex) {
        System.err.println("EXP(AutoUploadGraph): ".concat(ex.getMessage()));
    }
%>
