package backtofront;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import propertycalculation.*;

/**
 *
 * @author v-xinti
 */
@WebServlet(urlPatterns = {"/CalStatistic"})
public class CalStatisticServlet extends HttpServlet {

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
        String isScaled = request.getParameter("isScaled");
        String type = request.getParameter("type");
        HttpSession session = request.getSession();
        
        String uploadedFilePath = session.getAttribute("uploadedFilePath").toString();
        String uploadDir = session.getAttribute("uploadDir").toString();
        
        String inputFilePath = uploadedFilePath;
        String secFilePath = uploadDir.concat("ori.txt");
        String res = "";

        if (Boolean.valueOf(isScaled)) {
            inputFilePath = uploadDir.concat("scaled.txt");
            secFilePath = uploadDir.concat("t2.txt");
        }
        try {
            switch (type) {
                case "size":
                    int[] gNandEsize = NodeEdgeSizeCal.getNodeAndEdgeSize(inputFilePath);
                    res = gNandEsize[0] + ":" + gNandEsize[1];
                    break;
                case "coc":
                    res = BasicStatisticCal.getAvgClusteringCof(inputFilePath, secFilePath, uploadDir);
                    break;
                case "diaAndspl": //Effective Dia & Avg Shortest Path Len                   
                    String[] avplAndDia = BasicStatisticCal
                            .getEffectiveDiaAndAvgShortestPathLen(inputFilePath, secFilePath);                   
                    res = avplAndDia[0] + ":" + avplAndDia[1];
            }
            response.setContentType("text/html");
            response.setHeader("Cache-Control", "no-cache");
            try (PrintWriter out = response.getWriter()) {               
                out.println(res);
            }
        } catch (Exception e) {
            System.err.println("ERROR(CalStatistic)");
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
