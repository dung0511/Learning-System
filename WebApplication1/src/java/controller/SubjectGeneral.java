/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.SubjectDAL;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Subject;

/**
 *
 * @author ADMIN
 */
public class SubjectGeneral extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SubjectGeneral</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubjectGeneral at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id"); 
        try {
            int id = Integer.parseInt(idStr);
            SubjectDAL sd = new SubjectDAL();
            Subject subject = sd.getSubjectByID(id);
            request.setAttribute("subject", subject);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/SubjectGeneral.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println("ID không hợp lệ");
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/error.jsp");
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String description = request.getParameter("description");
        String statusStr = request.getParameter("status");
        int status = Integer.parseInt(statusStr);
        
        SubjectDAL sd = new SubjectDAL();
        sd.updateSubjectInfo(id, code, name, description,status);
        
        response.setContentType("text/plain");
        response.getWriter().write("Cập nhật thành công!");
        // Gửi lại HTML cho tab "General" với thông tin đã được cập nhật
        doGet(request, response);
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
