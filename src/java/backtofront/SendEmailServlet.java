/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backtofront;

import java.io.IOException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author v-xinti
 */
@WebServlet(name = "SendEmail", urlPatterns = {"/SendEmail"})
public class SendEmailServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("name");
        if(name == null || name.equals("")){
            name = "Anonym";
        }
        String email = request.getParameter("email");
        String comments = request.getParameter("comments");
        
        if(email == null || email.trim().isEmpty()
                || comments == null || comments.trim().isEmpty()){
            return;
        }
        
        String[] args = new String[3];
        args[0] = email;
        args[1] = "Comments from ".concat("[" + name + "]");
        args[2] = comments;

        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "txsing@gmail.com";
        String password = "zrrfouhbtqibwxvl";
        
        // outgoing message information
        String mailTo = "a0054808@u.nus.edu";
        String sender = args[0];
        String subject = args[1];
        String message = sender.concat(": ").concat(args[2]);

        PlainTextEmailSender mailer = new PlainTextEmailSender();
        try {
            mailer.sendPlainTextEmail(host, port, mailFrom, password, mailTo,
                    subject, message);
            System.out.println("Email sent.");
        } catch (AddressException ex) {
            System.err.println("EXP(SendMail): "+ex.getMessage());
            System.err.println("\t"+ex.getStackTrace()[0]);
        } catch (MessagingException ex){
            System.err.println("EXP(SendMail): "+ex.getMessage());
            System.err.println("\t"+ex.getStackTrace()[0]);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
