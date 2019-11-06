/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.pit.si.pw.lab2.imc;

import br.edu.pit.si.pw.lab2.utils.StringUtils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author root
 */
@WebServlet(name = "ImcServlet", urlPatterns = {"/Imc"})
public class ImcServlet extends HttpServlet {

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
        
        String sPeso = request.getParameter("peso");
        String sAltura = request.getParameter("altura");

        log("Peso: " + sPeso);
        log("Altura: " + sAltura);
        
        float peso = 0;
        float altura = 0;
        float imc = 0;
        if (!StringUtils.isEmpty(sPeso) && !StringUtils.isEmpty(sAltura)){
            peso = Float.parseFloat(sPeso);
            altura = Float.parseFloat(sAltura);
            imc = peso / (altura*altura);
            log("IMC: " + imc);
        }
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>IMC</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ImcServlet at " + request.getContextPath() + "</h1>");
            out.println("Peso: " + peso + " Kg<br/>");
            out.println("Altura: " + altura + " m<br/><br/>");
            out.println("IMC: " + imc + "<br/>");
            out.println("</body>");
            out.println("</html>");
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
        
        String sPeso = request.getParameter("peso");
        String sAltura = request.getParameter("altura");
        
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
