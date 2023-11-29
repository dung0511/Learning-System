/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAL;
import dal.ClassDAL;
import dal.MajorDAL;
import dal.SubjectDAL;
import dal.SystemSettingDAL;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Major;
import model.Subject;
import util.Paging;

/**
 *
 * @author acer
 */
public class ClassList extends HttpServlet {

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
            out.println("<title>Servlet ClassList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClassList at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("act") == null || request.getParameter("act").equals("")) {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        } else {
            switch (request.getParameter("act")) {
                case "list": {
                    listClassGet(request, response);
                    break;
                }
                case "add": {
                    adudClass(request, response);
                    break;
                }
                case "upd": {
                    adudClass(request, response);
                    break;
                }
                case "act": {
                    activateDeactivate(request, response);
                    break;
                }
            }
        }
    }

    void activateDeactivate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id_raw = request.getParameter("id");
        String sj_raw = request.getParameter("sj");
        System.out.println(sj_raw);
        int id = 0, sj = 0;
        try {
            sj = Integer.parseInt(sj_raw);
            id = Integer.parseInt(id_raw);
        } catch (Exception e) {
            System.out.println(e);
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        }

        ClassDAL cd = new ClassDAL();
        if (cd.getClassByIDASjASem(id, sj).getActivate() == 1) {
            cd.deactivateClass(id, sj);
        } else {
            cd.activateClass(id, sj);
        }
        response.sendRedirect("clist?act=list");
    }

    void listAfterAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClassDAL cd = new ClassDAL();
        SubjectDAL sd = new SubjectDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        AccountDAL ad = new AccountDAL();
        String condition = "";
        condition += searchByStatus(request, response);
        condition += searchByTrainer(request, response);
        condition += order(request, response);
        List<Account> listAcc = ad.getAllSubjectManager();

        String subject = request.getParameter("subject");
        String sem_raw = request.getParameter("sem");
        request.setAttribute("ds", 1);
        String sem = "";
        if (subject == null || sem_raw == null || subject.equals("") || sem_raw.equals("")) {
            subject = "0";
            sem = "0";
        } else {
            sem = sem_raw;
        }

        request.setAttribute("subject", subject);
        request.setAttribute("sem", sem);

        List<Subject> listSJ = sd.getAllSubject(" 1 = 1");
        List<String> listSemester = ssd.getAllSemester();

        Paging p = new Paging();
        int page = cd.getAllClass().size() % 5 == 0 ? (cd.getAllClass().size() / 5) : (cd.getAllClass().size() / 5 + 1);

        if (request.getParameter("search") == null || request.getParameter("search").equals("")) {

            List<model.Class> list = cd.getAllClass();
            int start = p.getStart(page, list.size(), 5);
            int end = p.getEnd(page, list.size(), 5);
            request.setAttribute("search", "");

            request.setAttribute("listSJ", listSJ);
            request.setAttribute("listT", ad.getAllTrainer());

            request.setAttribute("listSemester", listSemester);

            if (!list.isEmpty()) {
                request.setAttribute("page", page);
                request.setAttribute("totalPage", p.getTotalPage(page, list.size(), 5));
                request.setAttribute("list", cd.getClassByPage(start, end, list));
            } else {
                request.setAttribute("ms", "Không tìm thấy lớp nào");
            }
            request.setAttribute("msg", "Add successfully!");

            request.setAttribute("totalEntity", cd.getAllClass().size());
            request.getRequestDispatcher("view/classManagement.jsp").forward(request, response);
        } else {
            String key = request.getParameter("search");
            List<model.Class> list = new ArrayList<>();

            list = cd.getAllClass();
            int start = p.getStart(page, list.size(), 5);
            int end = p.getEnd(page, list.size(), 5);

            request.setAttribute("search", key);
            request.setAttribute("listSJ", listSJ);

            request.setAttribute("listSemester", listSemester);
            request.setAttribute("listT", ad.getAllTrainer());

            if (!list.isEmpty()) {
                request.setAttribute("page", page);
                request.setAttribute("totalPage", p.getTotalPage(page, list.size(), 5));
                request.setAttribute("list", cd.getClassByPage(start, end, list));
                request.setAttribute("totalEntity", cd.getAllClass().size());
            } else {
                request.setAttribute("ms", "Không tìm thấy lớp nào");
            }
            request.setAttribute("msg", "Add successfully!");
            request.getRequestDispatcher("view/classManagement.jsp").forward(request, response);
        }

    }

    void adudClassPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClassDAL cd = new ClassDAL();
        MajorDAL md = new MajorDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();

        if (request.getParameter("add") != null && request.getParameter("add").equals("1")) {
            String name = request.getParameter("name");
            String sem_raw = request.getParameter("semester");
            String sj_raw = request.getParameter("subject");
            String order_raw = request.getParameter("order");
            String start_raw = request.getParameter("sd");
            String end_raw = request.getParameter("ed");
            String stat_raw = request.getParameter("status");
            String trainer = request.getParameter("trainer");
            String description = request.getParameter("description");
            String message = "";
            int sj = 0, order = 0, stat = 0, sem = 0, trainerID = 0;

            try {
                sj = Integer.parseInt(sj_raw);
                order = Integer.parseInt(order_raw);
                stat = Integer.parseInt(stat_raw);
                sem = Integer.parseInt(sem_raw);
                trainerID = Integer.parseInt(trainer);
                if (Date.valueOf(start_raw).after(Date.valueOf(end_raw))) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(e);
                message = "Invalid information. End date must greater than start date";
            }
            if (cd.getClassByNameASj(name, sj) != null) {
                message = "Invalid information. This class has already studied this subject ";
            }
            if (!message.equals("")) {
                AccountDAL ad = new AccountDAL();
                request.setAttribute("listT", ad.getAllTrainer());
                request.setAttribute("listSS", ssd.getAllSemesterByO());
                SubjectDAL sd = new SubjectDAL();
                request.setAttribute("listS", sd.getAllSubject(" 1 = 1 "));
                request.setAttribute("name", name);
                request.setAttribute("sem", sem);
                request.setAttribute("sj", sj);
                request.setAttribute("o", order);
                request.setAttribute("status", stat);
                request.setAttribute("sd", start_raw);
                request.setAttribute("ed", end_raw);
                request.setAttribute("trainer", trainer);
                request.setAttribute("ms", message);
                request.setAttribute("description", description);
                request.getRequestDispatcher("view/NewClass.jsp").forward(request, response);
            } else {
                cd.insClass(name, sem, sj, stat, Date.valueOf(start_raw), Date.valueOf(end_raw), null, null, trainerID, order, description);
            }
            //request.setAttribute("ms", "Đã tồn tại 1 lớp với ID = " + id);
            //request.getRequestDispatcher("view/adudClass.jsp").forward(request, response);
            //request.setAttribute("ms", "Thông tin của lớp với ID = " + id + " vừa được thêm vào khác so với các bản ghi khác. Vui lòng kiểm tra lại");
            //request.getRequestDispatcher("view/classManagement.jsp").forward(request, response);
            //cd.insClass(id, name, sem, sj, status, startDate, endDate, startTime, endTime, trainerID);
            // model.Class c = cd.getClassByIDASjASem(id, sj);
            //request.setAttribute("c", c);
            listAfterAdd(request, response);

        } else if (request.getParameter("upd") != null && request.getParameter("upd").equals("1")) {

            String name = request.getParameter("name");
            String sem_raw = request.getParameter("semester");
            String sj_raw = request.getParameter("subject");
            String order_raw = request.getParameter("order");
            String start_raw = request.getParameter("sd");
            String end_raw = request.getParameter("ed");
            String stat_raw = request.getParameter("status");
            String trainer = request.getParameter("trainer");
            String id_raw = request.getParameter("id");
            String page_raw = request.getParameter("page");
            String description = request.getParameter("description");
            System.out.println("des" + description);
            String message = "";
            int sj = 0, order = 0, stat = 0, sem = 0, trainerID = 0, id = 0, page = 0;
            try {
                id = Integer.parseInt(id_raw);
                page = Integer.parseInt(page_raw);
                sj = Integer.parseInt(sj_raw);
                order = Integer.parseInt(order_raw);
                stat = Integer.parseInt(stat_raw);
                sem = Integer.parseInt(sem_raw);
                trainerID = Integer.parseInt(trainer);
                if (Date.valueOf(start_raw).after(Date.valueOf(end_raw))) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(e);
                message = "Invalid information. End date must greater than start date";
            }
            if (cd.getClassByNameASj(name, sj) != null && cd.getClassByNameASj(name, sj).getClassID() != id) {
                message = "Invalid information. This class has already studied this subject ";
            }
            if (!message.equals("")) {
                AccountDAL ad = new AccountDAL();
                model.Class old = cd.getClassByID(id);

                request.setAttribute("listT", ad.getAllTrainer());
                request.setAttribute("listSS", ssd.getAllSemesterByO());
                SubjectDAL sd = new SubjectDAL();
                request.setAttribute("listS", sd.getAllSubject(" 1 = 1 "));
                request.setAttribute("c", old);
                request.setAttribute("page", page);
                request.setAttribute("ms", message);
                request.getRequestDispatcher("view/CDetail.jsp").forward(request, response);
            } else {
                //  public void updClass(int classID, String className, int subjectIDOld, int activate, int subjectID, int semester, Date start, Date end, Time startT, Time endT, int trainerID, int order) {
                model.Class old = cd.getClassByID(id);
               cd.updClass(id, name, old.getS().getSubjectID(), stat, sj, sem, Date.valueOf(start_raw), Date.valueOf(end_raw), Time.valueOf("00:00:00"),  Time.valueOf("23:59:00"), trainerID, order, description);
                AccountDAL ad = new AccountDAL();
                ad.updateStudentClass(id, sj);
                listAfterUpd(page, request, response);
            }

        } else {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        }
    }

    private String order(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String condition = "";
        if (request.getParameter("order") != null && request.getParameter("sort") != null) {
            System.out.println(request.getParameter("order") + " " + request.getParameter("sort"));
            if (request.getParameter("order").equals("0")) {
                switch (request.getParameter("sort")) {
                    case "ID": {
                        condition = "ORDER BY class_id DESC";
                        break;
                    }
                    case "Name": {
                        condition = "ORDER BY class_name DESC";
                        break;
                    }
                    case "Semester": {
                        condition = "ORDER BY semester DESC";
                        break;
                    }
                    case "SD": {
                        condition = "ORDER BY start_date DESC";
                        break;
                    }
                    case "ED": {
                        condition = "ORDER BY end_date DESC";
                        break;
                    }
                    case "Trainer": {
                        condition = "ORDER BY trainer DESC";
                        break;
                    }
                    case "Status": {
                        condition = "ORDER BY status DESC";
                        break;
                    }
                    case "Order": {
                        condition = "ORDER BY display_order DESC";
                        break;
                    }
                }
                request.setAttribute("sort", request.getParameter("sort"));
                request.setAttribute("order", request.getParameter("order"));
            } else if (request.getParameter("order").equals("1")) {
                switch (request.getParameter("sort")) {
                    case "ID": {
                        condition = "ORDER BY class_id ASC";
                        break;
                    }
                    case "Name": {
                        condition = "ORDER BY class_name ASC";
                        break;
                    }
                    case "Subject": {
                        condition = "ORDER BY subject_id ASC";
                        break;
                    }
                    case "Semester": {
                        condition = "ORDER BY semester ASC";
                        break;
                    }
                    case "SD": {
                        condition = "ORDER BY start_date ASC";
                        break;
                    }
                    case "ED": {
                        condition = "ORDER BY end_date ASC";
                        break;
                    }
                    case "Trainer": {
                        condition = "ORDER BY trainer ASC";
                        break;
                    }
                    case "Status": {
                        condition = "ORDER BY status ASC";
                        break;
                    }
                    case "Order": {
                        condition = "ORDER BY display_order ASC";
                        break;
                    }
                }
                request.setAttribute("sort", request.getParameter("sort"));
                request.setAttribute("order", request.getParameter("order"));
            } else {
                condition = "";
            }
        }
        return condition;
    }

    private String searchByStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String condition = "";
        if (request.getParameter("status") != null) {
            if (request.getParameter("status").equals("1")) {
                condition = " status = 1 ";
                request.setAttribute("status", 1);
            } else if (request.getParameter("status").equals("2")) {
                condition = " status = 0 ";
                request.setAttribute("status", 0);
            } else {
                condition = " 1 = 1 ";
            }
        } else {
            condition = "  1 = 1 ";
        }
        return condition;
    }

    void adudClass(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MajorDAL md = new MajorDAL();
        ClassDAL cd = new ClassDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        SubjectDAL sd = new SubjectDAL();

        List<Major> listM = md.getAllMajor();

        List<String> listStat = new ArrayList<>();
        listStat.add("Active");
        listStat.add("Inactive");

        List<String> listSem = ssd.getAllSemester();
        AccountDAL ad = new AccountDAL();
        request.setAttribute("listT", ad.getAllTrainer());
        if (request.getParameter("act").equals("add")) {
            request.setAttribute("listSS", ssd.getAllSemesterByO());
            request.setAttribute("listS", sd.getAllSubject(" 1 = 1 "));
            request.getRequestDispatcher("view/NewClass.jsp").forward(request, response);
        } else if (request.getParameter("act").equals("upd")) {
            String id_raw = request.getParameter("id");
            String sj_raw = request.getParameter("sj");
            String page_raw = request.getParameter("page");
            int classID = 0, subjectID = 0, sj = 0, page = 0;

            try {
                sj = Integer.parseInt(sj_raw);
                classID = Integer.parseInt(id_raw);
                page = Integer.parseInt(page_raw);
            } catch (Exception e) {
                System.out.println(e);
                request.getRequestDispatcher("view/error.jsp").forward(request, response);
            }

            model.Class c = cd.getClassByIDASjASem(classID, sj);
            request.setAttribute("c", c);
            request.setAttribute("page", page);
            request.setAttribute("upd", "1");
            request.setAttribute("listT", ad.getAllTrainer());
            request.setAttribute("listSS", ssd.getAllSemesterByO());
            request.setAttribute("listS", sd.getAllSubject(" 1 = 1 "));
            request.getRequestDispatcher("view/CDetail.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        }
    }

    private String searchByTrainer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String condition = "";
        if (request.getParameter("trainer") != null) {
            if (request.getParameter("trainer").equals("0")) {
                condition = " AND 1 = 1 ";
            } else {
                try {
                    condition = " AND trainer = " + Integer.parseInt(request.getParameter("trainer")) + " ";
                    request.setAttribute("trainer", Integer.parseInt(request.getParameter("trainer")));
                } catch (Exception e) {
                    condition = " AND 1 = 1 ";
                }
            }
        } else {
            condition = " AND 1 = 1 ";
        }
        return condition;
    }

    void listClassGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClassDAL cd = new ClassDAL();
        SubjectDAL sd = new SubjectDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        AccountDAL ad = new AccountDAL();
        String condition = "";
        condition += searchByStatus(request, response);
        condition += searchByTrainer(request, response);
        condition += order(request, response);
        List<Account> listAcc = ad.getAllSubjectManager();

        String subject = request.getParameter("subject");
        String sem_raw = request.getParameter("sem");
        request.setAttribute("ds", 1);
        String sem = "";
        if (subject == null || sem_raw == null || subject.equals("") || sem_raw.equals("")) {
            subject = "0";
            sem = "0";
        } else {
            sem = sem_raw;
        }

        request.setAttribute("subject", subject);
        request.setAttribute("sem", sem);

        List<Subject> listSJ = sd.getAllSubject(" 1 = 1 ");
        List<String> listSemester = ssd.getAllSemester();

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
            System.out.println("da" + sem);
            int sem1;
            if (sem.equals("0") || sem.equals("")) {
                sem1 = 0;
            } else {
                sem1 = ssd.getSettingByName(sem).getSetting_id();
            }
            List<model.Class> list = cd.getClassByFilter(subject, sem1, condition);
            int start = p.getStart(page, list.size(), 5);
            int end = p.getEnd(page, list.size(), 5);
            request.setAttribute("search", "");

            request.setAttribute("listSJ", listSJ);
            request.setAttribute("listT", ad.getAllTrainer());

            request.setAttribute("listSemester", listSemester);

            if (!list.isEmpty()) {
                request.setAttribute("page", page);
                request.setAttribute("totalPage", p.getTotalPage(page, list.size(), 5));
                request.setAttribute("list", cd.getClassByPage(start, end, list));
            } else {
                request.setAttribute("ms", "Không tìm thấy lớp nào");
            }
            request.setAttribute("totalEntity", cd.getClassByFilter(subject, sem1, condition).size());
            request.getRequestDispatcher("view/classManagement.jsp").forward(request, response);
        } else {
            String key = request.getParameter("search");
            List<model.Class> list = new ArrayList<>();
            int sem1;
            if (sem.equals("0") || sem.equals("")) {
                sem1 = 0;
            } else {
                sem1 = ssd.getSettingByName(sem).getSetting_id();
            }
            list = cd.getClassBySearchAFilter(key, subject, sem1, condition);
            int start = p.getStart(page, list.size(), 5);
            int end = p.getEnd(page, list.size(), 5);

            request.setAttribute("search", key);
            request.setAttribute("listSJ", listSJ);

            request.setAttribute("listSemester", listSemester);
            request.setAttribute("listT", ad.getAllTrainer());

            if (!list.isEmpty()) {
                request.setAttribute("page", page);
                request.setAttribute("totalPage", p.getTotalPage(page, list.size(), 5));
                request.setAttribute("list", cd.getClassByPage(start, end, list));
                request.setAttribute("totalEntity", cd.getClassBySearchAFilter(key, subject, sem1, condition).size());
            } else {
                request.setAttribute("ms", "Không tìm thấy lớp nào");
            }
            request.getRequestDispatcher("view/classManagement.jsp").forward(request, response);
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
            adudClassPost(request, response);
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

    private void listAfterUpd(int page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassDAL cd = new ClassDAL();
        SubjectDAL sd = new SubjectDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        AccountDAL ad = new AccountDAL();
        String condition = "";
        condition += searchByStatus(request, response);
        condition += searchByTrainer(request, response);
        condition += order(request, response);
        List<Account> listAcc = ad.getAllSubjectManager();

        String subject = request.getParameter("subject");
        String sem_raw = request.getParameter("sem");
        request.setAttribute("ds", 1);
        String sem = "";
        if (subject == null || sem_raw == null || subject.equals("") || sem_raw.equals("")) {
            subject = "0";
            sem = "0";
        } else {
            sem = sem_raw;
        }

        request.setAttribute("subject", subject);
        request.setAttribute("sem", sem);

        List<Subject> listSJ = sd.getAllSubject(" 1 = 1 ");
        List<String> listSemester = ssd.getAllSemester();

        Paging p = new Paging();

        if (request.getParameter("search") == null || request.getParameter("search").equals("")) {

            List<model.Class> list = cd.getAllClass();
            int start = p.getStart(page, list.size(), 5);
            int end = p.getEnd(page, list.size(), 5);
            request.setAttribute("search", "");

            request.setAttribute("listSJ", listSJ);
            request.setAttribute("listT", ad.getAllTrainer());

            request.setAttribute("listSemester", listSemester);

            if (!list.isEmpty()) {
                request.setAttribute("page", page);
                request.setAttribute("totalPage", p.getTotalPage(page, list.size(), 5));
                request.setAttribute("list", cd.getClassByPage(start, end, list));
            } else {
                request.setAttribute("ms", "Không tìm thấy lớp nào");
            }
            request.setAttribute("msg", "Add successfully!");

            request.setAttribute("totalEntity", cd.getAllClass().size());
            request.getRequestDispatcher("view/classManagement.jsp").forward(request, response);
        } else {
            String key = request.getParameter("search");
            List<model.Class> list = new ArrayList<>();

            list = cd.getAllClass();
            int start = p.getStart(page, list.size(), 5);
            int end = p.getEnd(page, list.size(), 5);

            request.setAttribute("search", key);
            request.setAttribute("listSJ", listSJ);

            request.setAttribute("listSemester", listSemester);
            request.setAttribute("listT", ad.getAllTrainer());

            if (!list.isEmpty()) {
                request.setAttribute("page", page);
                request.setAttribute("totalPage", p.getTotalPage(page, list.size(), 5));
                request.setAttribute("list", cd.getClassByPage(start, end, list));
                request.setAttribute("totalEntity", cd.getAllClass().size());
            } else {
                request.setAttribute("ms", "Không tìm thấy lớp nào");
            }
            request.setAttribute("msg", "Update successfully!");
            request.getRequestDispatcher("view/classManagement.jsp").forward(request, response);
        }
    }

}
