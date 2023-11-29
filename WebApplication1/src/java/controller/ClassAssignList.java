/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ClassAssignmentDAL;
import dal.QuesDAL;
import dal.SubjectSettingDAL;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Account;
import model.ClassAssignment;
import model.Question;
import model.SubjectSetting;
import util.Menu;
import util.Paging;

/**
 *
 * @author quany
 */
public class ClassAssignList extends HttpServlet {

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
            out.println("<title>Servlet ClassAssignList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClassAssignList at " + request.getContextPath() + "</h1>");
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

        String pageString = request.getParameter("page");
        String sizeString = request.getParameter("size");
        System.out.println("pageString: " + pageString);
        System.out.println("sizeString: " + sizeString);
        ClassAssignmentDAL classAssignmentDAL = new ClassAssignmentDAL();
        ArrayList<ClassAssignment> list = null;

        Menu m = new Menu();
        m.getNotification(request, response);

        HttpSession session = request.getSession();
        Account loggedInAccount = (Account) session.getAttribute("account");
        int accountID = loggedInAccount.getID();

        if (pageString != null && sizeString != null && !pageString.isEmpty() && !sizeString.isEmpty()) {
            try {
                int page = Integer.parseInt(pageString);
                int size = Integer.parseInt(sizeString);
                System.out.println("page  : " + page);
                System.out.println("size  : " + size);
                System.out.println("account: " + accountID);
                list = classAssignmentDAL.getByPageSize(page, size, accountID);
            } catch (NumberFormatException e) {
            }
        } else {
            System.out.println("else");
            list = classAssignmentDAL.getAll(accountID);
        }

// Search by name
        String searchAsmName = request.getParameter("asmName");
        if (searchAsmName != null && !searchAsmName.isEmpty()) {
            ArrayList<ClassAssignment> searchedList = new ArrayList<>();
            for (ClassAssignment classAssignment : list) {
                if (classAssignment.getAsmName().toLowerCase().contains(searchAsmName.toLowerCase())) {
                    searchedList.add(classAssignment);
                }
            }
            list = searchedList; // Gán danh sách tìm kiếm
            request.setAttribute("asmName", searchAsmName);
        }


        request.setAttribute("list", list);
        request.getRequestDispatcher("view/ClassAssignmentList.jsp").forward(request, response);

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
