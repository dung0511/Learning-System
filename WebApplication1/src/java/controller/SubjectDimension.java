/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ChapterDAO;
import dal.DimensionDAO;
import dal.SubjectDAL;
import dal.SystemSettingDAL;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Chapter;
import model.Dimension;
import model.DimensionType;
import model.Subject;
import model.SystemSetting;

/**
 *
 * @author ADMIN
 */
public class SubjectDimension extends HttpServlet {

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
            out.println("<title>Servlet SubjectDimension</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubjectDimension at " + request.getContextPath() + "</h1>");
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
        String idStr = request.getParameter("id");
        try {
            int id = Integer.parseInt(idStr);
            DimensionDAO dd = new DimensionDAO();
            ArrayList<DimensionType> listDimension = dd.getDimenTypeBySubjectID(id);
            request.setAttribute("subjectID", id);
            request.setAttribute("listDimen", listDimension);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/SubjectDimension.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println("ID không hợp lệ");
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/error.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDimension.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubjectDimension.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            try {
                SubjectDAL sd = new SubjectDAL();
                String SubjectIdStr = request.getParameter("subjectID");
                String name = request.getParameter("name");
                String statusSTR = request.getParameter("status");
                if (SubjectIdStr == null || name == null || statusSTR == null) {
                    response.getWriter().write("Các trường không được để trống");
                    return;
                }
                int Subjectid = Integer.parseInt(SubjectIdStr);
                boolean status;
                if (statusSTR.equals("active")) {
                    status = true;
                } else {
                    status = false;
                }
                Subject s = sd.getSubjectByID(Subjectid);
                
                DimensionType dt = new DimensionType();                
                dt.setName(name);
                dt.setSubject(s);
                dt.setStatus(status);
                DimensionDAO dd = new DimensionDAO();
                
                boolean isAdded = dd.addDimensionType(dt);
                if (!isAdded) {
                    response.getWriter().write("Thêm mới không thành công");
                } else {
                    response.getWriter().write("true");
                }
            } catch (NumberFormatException e) {
                response.getWriter().write("Nhập sai định dạng các trường");
            } catch (Exception e) {
                System.out.println("SubjectDimensionServletAdd:" + e);
                response.getWriter().write("Có lỗi xảy ra trong quá trình thêm mới");
            }
        } else if ("edit".equals(action)) {
            try {
                String idStr = request.getParameter("id");
                String name = request.getParameter("name");
                String statusSTR = request.getParameter("status");
                if (idStr == null || name == null || statusSTR == null ) {
                    response.getWriter().write("Các trường không được để trống");
                    return;
                }
                int id = Integer.parseInt(idStr);

                boolean status;
                if (statusSTR.equals("active")) {
                    status = true;
                } else {
                    status = false;
                }

                DimensionType dt = new DimensionType();                
                dt.setName(name);
                dt.setId(id);
                dt.setStatus(status);
                DimensionDAO dd = new DimensionDAO();

                boolean isUpdated = dd.updDimensionType(dt);
                if (!isUpdated) {
                    response.getWriter().write("Cập nhật không thành công");
                } else {
                    response.getWriter().write("true");
                }
                response.getWriter().write("true");
            } catch (NumberFormatException e) {
                response.getWriter().write("Nhập sai định dạng các trường");
            } catch (Exception e) {
                System.out.println("SubjectDimensionServletUpdate:" + e);
                response.getWriter().write("Có lỗi xảy ra trong quá trình cập nhật");
            }
        }
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
