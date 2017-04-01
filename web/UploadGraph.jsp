<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.output.*" %>
<%@page import="paperalgorithm.*" %>
<%@page import="backtofront.EdgeListConvertor" %>

<%
    File file;
    int maxFileSize = 50000 * 1024;
    int maxMemSize = 50000 * 1024;
    String timestamp = String.valueOf(System.currentTimeMillis());
    String rootpath = request.getServletContext().getRealPath("");
    String uploadDir = rootpath + File.separator + "uploadedfiles" + File.separator + timestamp + File.separator;   
    new File(uploadDir).mkdir();
    
    // Verify the content type
    String contentType = request.getContentType();
    if ((contentType.indexOf("multipart/form-data") >= 0)) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);
        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);
        String uploadedFileName = "";
        try {
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);
            // Process the uploaded file items
            Iterator i = fileItems.iterator();
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    // Get the uploaded file parameters
                    uploadedFileName = fi.getName();
                    // Write the file
                    file = new File(uploadDir.concat(uploadedFileName));
                    fi.write(file);
                    EdgeListConvertor.getEdgeListFile(file);
                }
            }
            session.setAttribute("uploadDir", uploadDir);
            session.setAttribute("uploadedFilePath", uploadDir.concat(uploadedFileName));
            session.setAttribute("scaledNodeSize", null);
            session.setAttribute("scaledEdgeSize", null);
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", "index.jsp#uploadAndScale");
        } catch (Exception ex) {
            System.err.println("EXP(UploadGraph): ".concat(ex.getMessage()));
        }
    } else {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet upload</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>No file uploaded</p>");
        out.println("</body>");
        out.println("</html>");
    }
%>
