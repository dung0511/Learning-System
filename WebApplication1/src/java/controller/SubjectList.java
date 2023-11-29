/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAL;
import dal.SubjectDAL;
import dal.SubjectSettingDAL;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Subject;
import util.Paging;

/**
 *
 * @author acer
 */
public class SubjectList extends HttpServlet {

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
            out.println("<title>Servlet SubjectList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubjectList at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private String order(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String condition = "";
        if (request.getParameter("sort") != null && request.getParameter("order") != null) {
            if (request.getParameter("order").equals("0")) {
                switch (request.getParameter("sort")) {
                    case "ID": {
                        condition = " ORDER BY subject_id DESC ";
                        break;
                    }
                    case "Code": {
                        condition = " ORDER BY subject_code DESC ";
                        break;
                    }
                    case "Name": {
                        condition = " ORDER BY subject_name DESC ";
                        break;
                    }
                    case "Manager": {
                        condition = " ORDER BY manager DESC ";
                        break;
                    }
                    case "Order": {
                        condition = " ORDER BY display_order DESC ";
                        break;
                    }
                    case "Status": {
                        condition = " ORDER BY status DESC ";
                        break;
                    }
                    default: {
                        condition = "";
                        break;
                    }
                }
                request.setAttribute("sort", request.getParameter("sort"));
                request.setAttribute("order", request.getParameter("order"));
            } else if (request.getParameter("order").equals("1")) {
                switch (request.getParameter("sort")) {
                    case "ID": {
                        condition = " ORDER BY subject_id ASC ";
                        break;
                    }
                    case "Code": {
                        condition = " ORDER BY subject_code ASC ";
                        break;
                    }
                    case "Name": {
                        condition = " ORDER BY subject_name ASC ";
                        break;
                    }
                    case "Manager": {
                        condition = " ORDER BY manager ASC ";
                        break;
                    }
                    case "Order": {
                        condition = " ORDER BY display_order ASC ";
                        break;
                    }
                    case "Status": {
                        condition = " ORDER BY status ASC ";
                        break;
                    }
                    default: {
                        condition = "";
                        break;
                    }
                }
                request.setAttribute("sort", request.getParameter("sort"));
                request.setAttribute("order", request.getParameter("order"));
            }
        }
        return condition;
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
        if (request.getParameter("act") == null || request.getParameter("act").equals("")) {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        } else {
            switch (request.getParameter("act")) {
                case "list": {
                    listSubjectGet(request, response);
                    break;
                }
                case "view": {
                    getSubjectDetail(request, response, request.getParameter("id"));
                    break;
                }
                case "upd": {
                    updSubjectGet(request, response);
                    break;
                }
                case "act": {
                    activateDeactivate(request, response);
                    break;
                }
                case "add": {
                    addSubjectGet(request, response);
                    break;
                }
                default: {
                    request.getRequestDispatcher("view/error.jsp").forward(request, response);
                    break;
                }
            }
        }
    }

    private String searchByStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String condition = "";
        if (request.getParameter("status") != null) {
            if (request.getParameter("status").equals("0")) {
                condition = " 1 = 1 ";
            } else if (request.getParameter("status").equals("1")) {
                condition = " status = 1 ";
                request.setAttribute("status", 1);
            } else if (request.getParameter("status").equals("2")) {
                condition = " status = 0 ";
                request.setAttribute("status", 0);
            } else {
                condition = "1 = 1 ";
            }
        } else {
            condition = " 1 = 1 ";
        }
        return condition;
    }

    void activateDeactivate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") == null) {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        } else {
            String id_raw = request.getParameter("id");
            int id = Integer.parseInt(id_raw);
            SubjectDAL sd = new SubjectDAL();

            if (sd.getSubjectByID(id).getStatus() == 1) {
                sd.deactivateSubject(id);
            } else {
                sd.activateSubject(id);
            }

            response.sendRedirect("sjlist?act=list");
        }
    }

    void updSubjectPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountDAL ad = new AccountDAL();
        if (request.getParameter("id") == null || request.getParameter("name") == null || request.getParameter("des") == null) {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);

        } else {
            String id_raw = request.getParameter("id");
            int id = Integer.parseInt(id_raw);
            String code = request.getParameter("code");
            String name = request.getParameter("name");
            String des = request.getParameter("des");
            String m = request.getParameter("manager");
            Account a = ad.getAccountByUsername(m);
            SubjectDAL sd = new SubjectDAL();
            if (sd.getSubjectByID(id) == null) {
                request.getRequestDispatcher("view/error.jsp").forward(request, response);
            } else {
                sd.updSubjectDetail(id, name, des, code, a.getID());
                Subject s = sd.getSubjectByID(id);
                request.setAttribute("ms", "Sửa môn học thành công");
                request.setAttribute("s", s);
                request.setAttribute("upd", 1);
                request.setAttribute("list", ad.getAllSubjectManager());
                request.getRequestDispatcher("view/subjectManagement.jsp").forward(request, response);
            }
        }
    }

    void listSubjectGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        String condition = searchByStatus(request, response);
        condition += order(request, response);
        List<Account> listAcc = ad.getAllSubjectManager();

        Paging p = new Paging();
        int page = 0;
        if (request.getParameter("page") == null) {
            page = 1;
        } else {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (Exception e) {
                System.out.println(e);
                request.getRequestDispatcher("view/error.jsp").forward(request, response);
            }
        }

        if (request.getParameter("search") == null || request.getParameter("search").equals("")) {
            List<Subject> list;
            if (request.getParameter("mng") == null || request.getParameter("mng").equals("")) {
                list = sd.getAllSubject(condition);
                request.setAttribute("man", "0");
            } else {
                request.setAttribute("man", Integer.parseInt(request.getParameter("mng")));
                list = sd.getAllSubjectByMng(request.getParameter("mng"), condition);
            }
            int start = p.getStart(page, list.size(), 5);
            int end = p.getEnd(page, list.size(), 5);
            request.setAttribute("search", "");
            if (!list.isEmpty()) {
                request.setAttribute("page", page);
                request.setAttribute("totalPage", p.getTotalPage(page, list.size(), 5));
                request.setAttribute("list", sd.getSubjectByPage(start, end, list));

                request.setAttribute("size", sd.getSubjectByPage(start, end, list).size());
            } else {
                request.setAttribute("size", 0);
                request.setAttribute("ms", "Không tìm thấy môn học nào");
            }
            request.setAttribute("ds", "1");
            request.setAttribute("mng", listAcc);
            request.setAttribute("totalEntity", sd.getAllSubject(condition).size());
            request.getRequestDispatcher("view/subjectManagement.jsp").forward(request, response);
        } else {
            String key = request.getParameter("search");
            List<Subject> list;
            if (request.getParameter("mng") == null || request.getParameter("mng").equals("")) {
                list = sd.getSubjectBySearch(key, condition);
                request.setAttribute("man", "0");
            } else {
                request.setAttribute("man", request.getParameter("mng"));
                list = sd.getSubjectBySearchAMng(key, request.getParameter("mng"), condition);
            }
            int start = p.getStart(page, list.size(), 5);
            int end = p.getEnd(page, list.size(), 5);
            request.setAttribute("search", key);
            if (!list.isEmpty()) {
                request.setAttribute("page", page);
                request.setAttribute("totalPage", p.getTotalPage(page, list.size(), 5));
                request.setAttribute("list", sd.getSubjectByPage(start, end, list));

                request.setAttribute("size", sd.getSubjectByPage(start, end, list).size());
            } else {
                request.setAttribute("size", 0);
                request.setAttribute("ms", "Không tìm thấy môn học nào");
            }
            request.setAttribute("ds", "1");
            request.setAttribute("mng", listAcc);
            request.setAttribute("totalEntity", sd.getAllSubject(condition).size());
            request.getRequestDispatcher("view/subjectManagement.jsp").forward(request, response);
        }
    }

    void updSubjectGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        if (request.getParameter("id") == null) {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        } else {
            String id = request.getParameter("id");

            if (sd.getSubjectByCode(id) == null) {
                request.getRequestDispatcher("view/error.jsp").forward(request, response);
            } else {
                Subject s = sd.getSubjectByCode(id);
                request.setAttribute("list", ad.getAllSubjectManager());
                request.setAttribute("s", s);
                request.setAttribute("upd", "1");
                request.getRequestDispatcher("view/subjectManagement.jsp").forward(request, response);
            }
        }
    }

    void getSubjectDetail(HttpServletRequest request, HttpServletResponse response, String id)
            throws ServletException, IOException {
        Paging p = new Paging();
        SubjectDAL sd = new SubjectDAL();

        SubjectSettingDAL ssd = new SubjectSettingDAL();
        if (sd.getSubjectByCode(id) == null) {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        } else {
            int numPerPage = 5;
            String page_raw = request.getParameter("page");
            int page = 1;
            if (page_raw != null) {
                try {
                    page = Integer.parseInt(page_raw);
                } catch (Exception e) {
                    System.out.println(e);
                    request.getRequestDispatcher("view/error.jsp").forward(request, response);
                }
            }

            request.setAttribute("s", sd.getSubjectByCode(id));
            if (ssd.getAllSettingBySubject(id) != null) {
                int start = p.getStart(page, ssd.getAllSettingBySubject(id).size(), numPerPage);
                int end = p.getEnd(page, ssd.getAllSettingBySubject(id).size(), numPerPage);
                int totalPage = p.getTotalPage(page, ssd.getAllSettingBySubject(id).size(), numPerPage);
                if (!ssd.getAllSettingBySubject(id).isEmpty()) {
                    request.setAttribute("list", ssd.getListByPage(start, end, ssd.getAllSettingBySubject(id)));
                    request.setAttribute("size", ssd.getListByPage(start, end, ssd.getAllSettingBySubject(id)).size());
                } else {
                    request.setAttribute("size", 0);
                    request.setAttribute("msg", "Không có setting nào của môn học này");
                }
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("page", page);
                request.setAttribute("totalEntity", ssd.getAllSettingBySubject(id).size());
            } else {
                request.setAttribute("msg", "Không có setting nào của môn học này");
            }
            request.setAttribute("id", id);
            request.getRequestDispatcher("view/subjectDetail.jsp").forward(request, response);
        }
    }

    void addSubjectGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAL ad = new AccountDAL();
        request.setAttribute("listA", ad.getAllSubjectManager());
        request.setAttribute("listAcc", ad.getAllSubjectManager());
        request.setAttribute("act", "add");
        request.setAttribute("add", "1");
        request.getRequestDispatcher("view/NewSubject.jsp").forward(request, response);
    }

    void addSubjectPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String des = request.getParameter("description");
        String m = request.getParameter("manager");
        String status_raw = request.getParameter("status");
        String order_raw = request.getParameter("order");
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();

        int managerID = 0, stat = 0, order = 0;
        try {
            managerID = Integer.parseInt(m);
            stat = Integer.parseInt(status_raw);
            order = Integer.parseInt(order_raw);
        } catch (Exception e) {
            System.out.println(e);
        }
        String ms = "";
        if (sd.getSubjectByCode(code) != null) {
            ms = "This code has already existed";
        } else if (code.length() > 6) {
            ms = "Code maximum length is 6";
        }
        if (!ms.equals("")) {
            request.setAttribute("ms", ms);
            request.setAttribute("code", code);
            request.setAttribute("name", name);
            request.setAttribute("manager", managerID);
            request.setAttribute("description", des);
            request.setAttribute("o", order);
            request.setAttribute("status", stat);
            request.setAttribute("listAcc", ad.getAllSubjectManager());
            request.setAttribute("act", "add");
            request.setAttribute("add", "1");
            request.getRequestDispatcher("view/NewSubject.jsp").forward(request, response);
        } else {
            try {
                sd.insertSubject(code, name, des, managerID, order, stat);
            } catch (Exception e) {
                System.out.println(e);
            }
            listAfterAdd(request, response);
        }
    }

    private void listAfterAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        String condition = searchByStatus(request, response);
        condition += order(request, response);
        List<Account> listAcc = ad.getAllSubjectManager();

        Paging p = new Paging();
        int page = sd.getAllSubject(" 1 = 1 ").size() % 5 == 0 ? (sd.getAllSubject(" 1 = 1 ").size() / 5) : (sd.getAllSubject(" 1 = 1 ").size() / 5 + 1);

        if (request.getParameter("search") == null || request.getParameter("search").equals("")) {
            List<Subject> list;
            if (request.getParameter("mng") == null || request.getParameter("mng").equals("")) {
                list = sd.getAllSubject(condition);
                request.setAttribute("man", "0");
            } else {
                request.setAttribute("man", Integer.parseInt(request.getParameter("mng")));
                list = sd.getAllSubjectByMng(request.getParameter("mng"), condition);
            }
            int start = p.getStart(page, list.size(), 5);
            int end = p.getEnd(page, list.size(), 5);
            request.setAttribute("search", "");
            if (!list.isEmpty()) {
                request.setAttribute("page", page);
                request.setAttribute("totalPage", p.getTotalPage(page, list.size(), 5));
                request.setAttribute("list", sd.getSubjectByPage(start, end, list));

                request.setAttribute("size", sd.getSubjectByPage(start, end, list).size());
            } else {
                request.setAttribute("size", 0);
                request.setAttribute("ms", "Không tìm thấy môn học nào");
            }
            request.setAttribute("MSG", "Add successfully!");
            request.setAttribute("ds", "1");
            request.setAttribute("mng", listAcc);
            request.setAttribute("totalEntity", sd.getAllSubject(condition).size());
            request.getRequestDispatcher("view/subjectManagement.jsp").forward(request, response);
        } else {
            String key = request.getParameter("search");
            List<Subject> list;
            if (request.getParameter("mng") == null || request.getParameter("mng").equals("")) {
                list = sd.getSubjectBySearch(key, condition);
                request.setAttribute("man", "0");
            } else {
                request.setAttribute("man", request.getParameter("mng"));
                list = sd.getSubjectBySearchAMng(key, request.getParameter("mng"), condition);
            }
            int start = p.getStart(page, list.size(), 5);
            int end = p.getEnd(page, list.size(), 5);
            request.setAttribute("search", key);
            if (!list.isEmpty()) {
                request.setAttribute("page", page);
                request.setAttribute("totalPage", p.getTotalPage(page, list.size(), 5));
                request.setAttribute("list", sd.getSubjectByPage(start, end, list));

                request.setAttribute("size", sd.getSubjectByPage(start, end, list).size());
            } else {
                request.setAttribute("size", 0);
                request.setAttribute("ms", "Không tìm thấy môn học nào");
            }
            request.setAttribute("msg", "Add successfully!");
            System.out.println("da");
            request.setAttribute("ds", "1");
            request.setAttribute("mng", listAcc);
            request.setAttribute("totalEntity", sd.getAllSubject(condition).size());
            request.getRequestDispatcher("view/subjectManagement.jsp").forward(request, response);
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
        if (request.getParameter("act") == null || request.getParameter("act").equals("")) {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        } else {
            switch (request.getParameter("act")) {
                case "upd": {
                    updSubjectPost(request, response);
                    break;
                }
                case "add": {
                    addSubjectPost(request, response);
                    break;
                }
                default: {
                    request.getRequestDispatcher("view/error.jsp").forward(request, response);
                    break;
                }
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
