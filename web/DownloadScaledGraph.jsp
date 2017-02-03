<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.nio.file.Files"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.nio.file.Path"%>
<%@page import="java.nio.file.Paths"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String scaledGraphFilePath = session.getAttribute("uploadDir").toString().concat("scaled.txt");
    //得到想客服端输出的输出流
    OutputStream outputStream = response.getOutputStream();
    //输出文件用的字节数组，每次向输出流发送600个字节
    byte buffer[] = new byte[1024];
    //要下载的文件
    File fileload = new File(scaledGraphFilePath);
    //客服端使用保存文件的对话框
    response.setHeader("Content-disposition", "attachment;filename=" + "scaled.txt");
    //通知客服文件的MIME类型
    response.setContentType("txt/html");

    FileInputStream inputStream = new FileInputStream(fileload);
    int n = 0;
    while ((n = inputStream.read(buffer)) != -1) {
        outputStream.write(buffer, 0, n);
    }
    inputStream.close();
    outputStream.close();
%>
