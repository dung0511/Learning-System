/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ChapterDAO;
import dal.DimensionDAO;
import dal.SubjectDAL;
import dal.SubjectSettingDAL;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Chapter;
import model.Dimension;
import model.DimensionType;
import model.Subject;

/**
 *
 * @author ADMIN
 */
public class SubjectSetting extends HttpServlet {

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
            out.println("<title>Servlet SubjectChapter</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubjectChapter at " + request.getContextPath() + "</h1>");
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
        String pageStr = request.getParameter("page");
        int page = pageStr == null ? 1 : Integer.parseInt(pageStr);
        int recordsPerPage = 5;
        try {
            int id = Integer.parseInt(idStr);
            DimensionDAO dd = new DimensionDAO();
            SubjectSettingDAL ssd = new SubjectSettingDAL();
            ArrayList<DimensionType> listDimenType = dd.getDimenTypeBySubjectID(id);
//            ArrayList<Chapter> listChapter = cd.getChapterBySubjectID(id);
            List<model.SubjectSetting> listSetting = ssd.getAllSettingBySubject(id, (page - 1) * recordsPerPage, recordsPerPage);
            int noOfRecords = ssd.getNoOfRecords(id);
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

            request.setAttribute("subjectID", id);
            //request.setAttribute("listChap", listChapter);
            request.setAttribute("listSetting", listSetting);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("dimensionTypes", listDimenType);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/SubjectChapter.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println("ID không hợp lệ");
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/error.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SubjectSetting.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubjectSetting.class.getName()).log(Level.SEVERE, null, ex);
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
                String orderStr = request.getParameter("order");
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                String statusSTR = request.getParameter("status");
                String type = request.getParameter("type");
                String dimensionType = request.getParameter("dimensionType");
                if (name == null || SubjectIdStr == null || orderStr == null || description == null || statusSTR == null || type == null) {
                    response.getWriter().write("Các trường không được để trống");
                    return;
                }
                int status;
                if (statusSTR.equals("active")) {
                    status = 1;
                } else {
                    status = 0;
                }
                int SubjectId = Integer.parseInt(SubjectIdStr);
                int order = Integer.parseInt(orderStr);
                Subject s = sd.getSubjectByID(SubjectId);
                Dimension dimen = new Dimension();
                dimen.setType(dimensionType);
                model.SubjectSetting ss = new model.SubjectSetting();
                ss.setSettingName(name);
                ss.setDescription(description);
                ss.setDisplayOrder(order);
                ss.setDimen(dimen);
                ss.setS(s);
                ss.setStatus(status);

                ss.setSettingType(type);
                if (type.equalsIgnoreCase("dimension")) {
                }
                ss.setStatus(status);

                SubjectSettingDAL ssd = new SubjectSettingDAL();
                boolean isAdded;
                if (type.equalsIgnoreCase("dimension")) {
                    isAdded = ssd.addSubjectSettingDimension(ss);
                } else {
                    isAdded = ssd.addSubjectSettingChapter(ss);
                }

                if (!isAdded) {
                    response.getWriter().write("Thêm mới không thành công");
                } else {
                    response.getWriter().write("true");
                }
            } catch (NumberFormatException e) {
                response.getWriter().write("Nhập sai định dạng các trường");
            } catch (Exception e) {
                System.out.println("SubjectChapterServletAdd:" + e);
                response.getWriter().write("Có lỗi xảy ra trong quá trình thêm mới");
            }
        } else if ("edit".equals(action)) {
            try {
                SubjectDAL sd = new SubjectDAL();
                String SubjectIdStr = request.getParameter("subjectID");
                int SubjectId = Integer.parseInt(SubjectIdStr);
                String idStr = request.getParameter("id");
                String orderStr = request.getParameter("order");

                String name = request.getParameter("name");
                String description = request.getParameter("description");
                String statusSTR = request.getParameter("status");
                if (idStr == null || name == null || orderStr == null || description == null || statusSTR == null) {
                    response.getWriter().write("Các trường không được để trống");
                    return;
                }
                int id = Integer.parseInt(idStr);
                int order = Integer.parseInt(orderStr);
                int status;
                if (statusSTR.equals("active")) {
                    status = 1;
                } else {
                    status = 0;
                }
                Subject s = sd.getSubjectByID(SubjectId);
                model.SubjectSetting ss = new model.SubjectSetting();
                ss.setSettingName(name);
                ss.setDescription(description);
                ss.setDisplayOrder(order);
                ss.setS(s);
                ss.setStatus(status);
                ss.setSettingID(id);
                SubjectSettingDAL ssd = new SubjectSettingDAL();
                boolean isUpdated = ssd.updSubjectSetting(ss);
                if (!isUpdated) {
                    response.getWriter().write("Cập nhật không thành công");
                } else {
                    response.getWriter().write("true");
                }
                response.getWriter().write("true");
            } catch (NumberFormatException e) {
                response.getWriter().write("Nhập sai định dạng các trường");
            } catch (Exception e) {
                System.out.println("SubjectChapterServletUpdate:" + e);
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
