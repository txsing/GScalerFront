<%-- 
    Document   : SendMail.jsp
    Created on : Feb 3, 2017, 8:44:32 PM
    Author     : v-xinti
--%>
<%@page import="propertycalculation.PlainTextEmailSender" %>
<%
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String comments = request.getParameter("comments");
    String[] args = new String[3];
    args[0] = email;
    args[1] = "Comments from ".concat("[" + name + "]");
    args[2] = comments;

    String host = "smtp.gmail.com";
    String port = "587";
    String mailFrom = "txsing@gmail.com";
    String password = "zrrfouhbtqibwxvl";
    // outgoing message information
    String mailTo = "txsing@icloud.com";
    String sender = args[0];
    String subject = args[1];
    String message = sender.concat(": ").concat(args[2]);

    PlainTextEmailSender mailer = new PlainTextEmailSender();

    try {
        mailer.sendPlainTextEmail(host, port, mailFrom, password, mailTo,
                subject, message);
        System.out.println("Email sent.");
    } catch (Exception ex) {
        System.out.println("Failed to sent email.");
        ex.printStackTrace();
    }
%>
