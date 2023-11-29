/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAL;
import dal.ClassDAL;
import dal.DiscussionDAL;
import dal.NotificationDAL;
import dal.VoteDAL;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.BufferedReader;
import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Account;
import model.AccountVote;
import model.DiscussionComment;
import model.DiscussionThread;
import model.Notification;
import model.Week;
import util.FormatDate;
import util.GetAllWeekBetween2Date;
import util.Paging;

/**
 *
 * @author acer
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ClassDiscussion extends HttpServlet {

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
            out.println("<title>Servlet ClassDiscussion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClassDiscussion at " + request.getContextPath() + "</h1>");
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
    protected String order(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String order = "";
        if (request.getParameter("order") == null || request.getParameter("order").equals("")) {
            order = "";
            request.setAttribute("order", 0);
        } else {
            if (request.getParameter("order").equals("0")) {
                order = "";
                request.setAttribute("order", 0);
            } else if (request.getParameter("order").equals("1")) {
                order = " ORDER BY discussion_date DESC";
                request.setAttribute("order", 1);
            } else if (request.getParameter("order").equals("2")) {
                order = " ORDER BY noVote DESC";
                request.setAttribute("order", 2);
            } else {
                order = "";
                request.setAttribute("order", 0);
            }
        }
        return order;
    }

    private String searchByKey(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String condition = "";
        if (request.getParameter("search") == null
                || request.getParameter("search").equals("")) {
            condition = "";
        } else {
            condition = " AND discussion_topic LIKE '%" + request.getParameter("search") + "%'";
            request.setAttribute("key", request.getParameter("search"));
        }
        return condition;
    }

    private String searchByWeek(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DiscussionDAL dd = new DiscussionDAL();
        String condition = "";
        if (request.getParameter("w") == null
                || request.getParameter("w").equals("")
                || request.getParameter("w").equals("0")) {
            condition = "";
        } else {
            try {
                FormatDate fd = new FormatDate();
                Date d = fd.formatStringToDateSQL(request.getParameter("w"));
                request.setAttribute("d", d);
                Date e = dd.getSundayByMonday(d);
                condition = " AND discussion_date BETWEEN '" + d + "' AND '" + e + "'";
            } catch (Exception e) {
                System.out.println(e);
                condition = "";
            }
        }
        return condition;
    }

    private String searchByFilterQ(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String condition = "";
        if (request.getParameter("q") == null
                || request.getParameter("q").equals("")) {
            condition = "";
            request.setAttribute("q", 0);
        } else {
            if (request.getParameter("q").equals("0")) {
                condition = "";
                request.setAttribute("q", 0);
            } else if (request.getParameter("q").equals("1")) {
                HttpSession session = request.getSession();
                Account a = (Account) session.getAttribute("account");
                if (a != null) {
                    condition = " AND class_discussion.account_id =" + a.getID();
                    request.setAttribute("q", 1);
                }
            } else if (request.getParameter("q").equals("2")) {
                condition += " AND noC = 0";
                request.setAttribute("q", 2);
            } else {
                condition = "";
                request.setAttribute("q", 0);
            }
        }
        return condition;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String class_raw = request.getParameter("class");
        DiscussionDAL dd = new DiscussionDAL();
        AccountDAL ad = new AccountDAL();
        ClassDAL cd = new ClassDAL();
        Paging p = new Paging();
        int classID = 0, page = 1;
        try {
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            if (class_raw != null) {
                classID = Integer.parseInt(class_raw);
            }
        } catch (Exception e) {
            System.out.println(e);
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        }
        String condition = "", order = "";
        LocalDate startDate = LocalDate.of(2023, 10, 26);

        // Ngày kết thúc
        LocalDate endDate = LocalDate.of(2023, 11, 25);

        condition += searchByKey(request, response);
        condition += searchByFilterQ(request, response);
        condition += searchByWeek(request, response);
        order += order(request, response);
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        if (a != null) {
            List<model.ClassDiscussion> list = dd.getAllDiscussionByClassASearch(classID, a.getID(), condition, order);
            List<DiscussionThread> listDC = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                listDC.add(new DiscussionThread(list.get(i), dd.getAllDiscussionComByDID(list.get(i).getDiscussionID(), a.getID())));
            }
            List<Week> listW = getAllWeekBetweenClassDate(cd.getClassByID(classID).getStartDate(), cd.getClassByID(classID).getEndDate());
            List<Account> listAcc = ad.getAllPersonInClass(classID, a.getID());
            request.setAttribute("listAcc", listAcc);
            if (!list.isEmpty()) {
                int numPerPage = 3;
                for (int i = 0; i < listDC.size(); i++) {
                    System.out.println(listDC.get(i).getDd().size());
                }
                int start = p.getStart(page, list.size(), numPerPage);
                int end = p.getEnd(page, list.size(), numPerPage);
                int totalPage = p.getTotalPage(page, list.size(), numPerPage);
                request.setAttribute("listW", listW);
                request.setAttribute("cls", classID);
                request.setAttribute("start", start);
                request.setAttribute("end", end);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("totalEntity", list.size());
                request.setAttribute("size", dd.getCDByPage(list, start, end).size());
                request.setAttribute("page", page);
                
                request.setAttribute("list", dd.getDTByPage(listDC, start, end));
                request.getRequestDispatcher("view/ClassDiscussion.jsp").forward(request, response);
            } else {
                request.setAttribute("ms", "Don't have any discussion");
                request.setAttribute("cls", classID);
                request.setAttribute("listW", listW);
                request.getRequestDispatcher("view/ClassDiscussion.jsp").forward(request, response);
            }
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
        Instant instant = Instant.now();
        Timestamp now = Timestamp.from(instant);

        if (request.getParameter("summerNoteText") == null && request.getParameter("class") == null && request.getParameter("reply") == null) {
            // Chuyển đổi thành kiểu dữ liệu Timestamp
            Part filePart = request.getPart("file");
            String htai = now.toString().replaceAll(" ", "");

            htai = htai.replaceAll("\\.", "");
            htai = htai.replaceAll("\\:", "");
            htai = htai.replaceAll("\\-", "");

            String uploadDirectory = request.getServletContext().getRealPath("img");
            filePart.write(("C:\\SWP\\Git\\g1\\Project\\WebApplication1\\web\\img\\" + htai + ".png"));
            filePart.write(uploadDirectory + File.separator + htai + ".png");
            response.getWriter().print("img/" + htai + ".png");
        } else if (request.getParameter("topic") != null) {
            HttpSession session = request.getSession();
            ClassDAL cdl = new ClassDAL();
            DiscussionDAL dd = new DiscussionDAL();
            Account a = (Account) session.getAttribute("account");
            if (a != null) {
                String content = request.getParameter("summerNoteText");
                String topic = request.getParameter("topic");
                String cls_raw = request.getParameter("cls");
                System.out.println(content + " " + topic + " " + cls_raw);

                int classID = 0;
                try {
                    classID = Integer.parseInt(cls_raw);
                } catch (Exception e) {
                    System.out.println(e);
                    request.getRequestDispatcher("view/error.jsp").forward(request, response);
                }
                model.ClassDiscussion cd = new model.ClassDiscussion();
                cd.setAccount(a);
                cd.setCls(cdl.getClassByID(classID));
                cd.setDiscussionContent(content);
                cd.setDiscussionDate(now);
                cd.setDiscussionTopic(topic);
                cd.setStatus(true);
                int id = dd.addDiscussion(cd);
                Paging p = new Paging();
                model.ClassDiscussion c1 = dd.getDiscussionByID(id, a.getID());
                listAfterAddOrUpd(c1, classID, request, response);
                // doGet(request, response);
            }

        } else if (request.getParameter("reply") != null) {
            NotificationDAL nd = new NotificationDAL();
            DiscussionDAL dd = new DiscussionDAL();
            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("account");
            String content = request.getParameter("summerNoteTextReply");
            String class_raw = request.getParameter("cls");
            String discussID_raw = request.getParameter("id");
            String page_raw = request.getParameter("page");
            if (a != null) {
                int classID = 0, discussID = 0, page = 0;
                try {
                    page = Integer.parseInt(page_raw);
                    classID = Integer.parseInt(class_raw);
                    discussID = Integer.parseInt(discussID_raw);
                } catch (Exception e) {
                    System.out.println(e);
                }
                AccountDAL ad = new AccountDAL();
                List<Account> listAcc = ad.getAllPersonInClass(classID, a.getID());
                List<String> username = new ArrayList<>();
                for (int i = 0; i < listAcc.size(); i++) {
                    if (content.contains("@" + listAcc.get(i).getUser())) {
                        if (!username.isEmpty()) {
                            if (!username.contains(listAcc.get(i).getUser())) {
                                username.add(listAcc.get(i).getUser());
                            }
                        } else {
                            username.add(listAcc.get(i).getUser());
                        }
                    }
                }
                DiscussionComment dc = new DiscussionComment();
                dc.setAccount(a);
                dc.setCd(dd.getDiscussionByID(discussID, a.getID()));
                dc.setComment(content);
                dc.setDate(now);
                dc.setNoVote(0);
                int cmID = dd.addComment(dc);
                dc.setCommentID(cmID);
                for (int i = 0; i < username.size(); i++) {

                    Account to = ad.getAccountByUsername(username.get(i));
                    Notification n = new Notification();
                    n.setCd(dd.getDiscussionByID(discussID, a.getID()));
                    n.setDate(now);
                    n.setDc(dc);
                    n.setFrom(a);
                    n.setTo(to);
                    n.setType("Tag");
                    n.setPage(page);
                    nd.insertN(n);
                }
                listAfterAddOrUpd(request.getParameter("page"), dd.getDiscussionByID(discussID, a.getID()), classID, request, response);
            }

        } else {
            BufferedReader reader = request.getReader();
            String text = reader.readLine();
            DiscussionDAL dd = new DiscussionDAL();
            VoteDAL vd = new VoteDAL();
            while (text != null) {
                HttpSession session = request.getSession();
                Account a = (Account) session.getAttribute("account");
                if (a != null) {
                    int vote = getVote(text);
                    int id = getID(text);
                    model.ClassDiscussion cd = dd.getDiscussionByID(id, a.getID());
                    if (vote == 0) {
                        cd.setNoVote(cd.getNoVote() - 1);
                        dd.updateDiscussion(cd);
                        AccountVote av = vd.getVoteDiscussionByDiscussAAcc(a.getID(), id);
                        vd.deleteVoteDiscussion(av);
                    } else {
                        cd.setNoVote(cd.getNoVote() + 1);
                        dd.updateDiscussion(cd);
                        // Chuyển đổi thành kiểu dữ liệu Timestamp
                        vd.insertVoteDiscussion(a.getID(), id, vote, now);
//                }

                    }
                    text = reader.readLine();
                }
            }
        }
    }

    private void listAfterAddOrUpd(String p1, model.ClassDiscussion cd, int classID, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        DiscussionDAL dd = new DiscussionDAL();
        ClassDAL cdl = new ClassDAL();
        Paging p = new Paging();
        if (a != null) {
            List<model.ClassDiscussion> list = dd.getAllDiscussionByClassASearch(classID, a.getID(), "", "");
            List<DiscussionThread> listDC = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                listDC.add(new DiscussionThread(list.get(i), dd.getAllDiscussionComByDID(list.get(i).getDiscussionID(), a.getID())));
            }
            List<Week> listW = getAllWeekBetweenClassDate(cdl.getClassByID(classID).getStartDate(), cdl.getClassByID(classID).getEndDate());
            if (!list.isEmpty()) {

                int numPerPage = 3;
                int page = Integer.parseInt(p1);
                int start = p.getStart(page, list.size(), numPerPage);
                int end = p.getEnd(page, list.size(), numPerPage);
                int totalPage = p.getTotalPage(page, list.size(), numPerPage);
                System.out.println(start + " " + end + " " + totalPage);
                request.setAttribute("listW", listW);
                request.setAttribute("cls", classID);
                request.setAttribute("start", start);
                request.setAttribute("end", end);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("totalEntity", list.size());
                request.setAttribute("size", dd.getCDByPage(list, start, end).size());
                request.setAttribute("page", page);
                request.setAttribute("list", dd.getDTByPage(listDC, start, end));
                request.setAttribute("msg", "Post successfully!");
                AccountDAL ad = new AccountDAL();
                List<Account> listAcc = ad.getAllPersonInClass(classID, a.getID());
                request.setAttribute("listAcc", listAcc);
                request.getRequestDispatcher("view/ClassDiscussion.jsp").forward(request, response);
            }
        }
    }

    private void listAfterAddOrUpd(model.ClassDiscussion cd, int classID, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        DiscussionDAL dd = new DiscussionDAL();
        ClassDAL cdl = new ClassDAL();
        Paging p = new Paging();
        if (a != null) {
            List<model.ClassDiscussion> list = dd.getAllDiscussionByClass(classID);
            List<DiscussionThread> listDC = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                listDC.add(new DiscussionThread(list.get(i), dd.getAllDiscussionComByDID(list.get(i).getDiscussionID(), a.getID())));
            }
            List<Week> listW = getAllWeekBetweenClassDate(cdl.getClassByID(classID).getStartDate(), cdl.getClassByID(classID).getEndDate());
            if (!list.isEmpty()) {

                int numPerPage = 3;
                int page = (list.size() % numPerPage == 0) ? (list.size() / numPerPage) : (list.size() / numPerPage + 1);
                int start = p.getStart(page, list.size(), numPerPage);
                int end = p.getEnd(page, list.size(), numPerPage);
                int totalPage = page;
                System.out.println(start + " " + end + " " + totalPage);
                request.setAttribute("listW", listW);
                request.setAttribute("cls", classID);
                request.setAttribute("start", start);
                request.setAttribute("end", end);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("totalEntity", list.size());
                request.setAttribute("size", dd.getCDByPage(list, start, end).size());
                request.setAttribute("page", page);
                request.setAttribute("list", dd.getDTByPage(listDC, start, end));
                request.setAttribute("msg", "Post successfully!");
                request.getRequestDispatcher("view/ClassDiscussion.jsp").forward(request, response);
            }
        }
    }

    private int getVote(String text) {
        int vote = 0, cnt = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.substring(0, i).equals("vote=")) {
                cnt = i;
            } else if (text.substring(i, i + 1).equals("&")) {
                vote = Integer.parseInt(text.substring(cnt, i));
                break;
            }
        }
        return vote;
    }

    private int getID(String text) {
        int id = 0, cnt = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.substring(i, i + 3).equals("id=")) {
                cnt = i + 3;
                break;
            }

        }
        id = Integer.parseInt(text.substring(cnt));
        return id;
    }

    private List<Week> getAllWeekBetweenClassDate(Date start, Date end) {
        LocalDate s = start.toLocalDate();
        LocalDate e = end.toLocalDate();

        GetAllWeekBetween2Date g = new GetAllWeekBetween2Date();
        return g.getAllWeeksBetweenTwoDates(s, e);
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
