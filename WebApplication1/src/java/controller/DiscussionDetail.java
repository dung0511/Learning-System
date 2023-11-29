/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAL;
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
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.AccountVote;
import model.DiscussionComment;
import model.Notification;
import util.Paging;

/**
 *
 * @author acer
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50)
public class DiscussionDetail extends HttpServlet {

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
            out.println("<title>Servlet DiscussionDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DiscussionDetail at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("id");
        String class_raw = request.getParameter("class");
        int id = 0, page = 1, numPerPage = 3, classID = 0;
        Paging p = new Paging();
        AccountDAL ad = new AccountDAL();
        try {
            id = Integer.parseInt(id_raw);
            classID = Integer.parseInt(class_raw);
            page = Integer.parseInt(request.getParameter("page"));

        } catch (Exception e) {
            System.out.println(e);
        }
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        if (a != null) {
            System.out.println(page);
            DiscussionDAL dd = new DiscussionDAL();
            List<DiscussionComment> list = dd.getAllDiscussionComByDID(id, a.getID());
            if (!list.isEmpty()) {
                int start = p.getStart(page, list.size(), numPerPage);
                int end = p.getEnd(page, list.size(), numPerPage);
                int totalPage = p.getTotalPage(page, list.size(), numPerPage);
                request.setAttribute("page", page);
                request.setAttribute("start", start);
                request.setAttribute("end", end);
                request.setAttribute("size", dd.getDCByPage(list, start, end).size());
                request.setAttribute("totalEntity", list.size());
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("list", dd.getDCByPage(list, start, end));
            } else {
                String ms = "Don't have any comment on this topic";
                request.setAttribute("ms", ms);
            }
            request.setAttribute("id", id);

            List<Account> listAcc = ad.getAllPersonInClass(classID, a.getID());
            request.setAttribute("listAcc", listAcc);
            request.setAttribute("cls", classID);
            request.setAttribute("discussion", dd.getDiscussionByID(id, a.getID()));
            request.getRequestDispatcher("view/DiscussionDetail.jsp").forward(request, response);
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
        DiscussionDAL dd = new DiscussionDAL();
        System.out.println("doalda");
        VoteDAL vd = new VoteDAL();
        Instant instant = Instant.now();
        Timestamp now = Timestamp.from(instant);
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        if (a != null) {
            if (request.getParameter("id") != null) {
                BufferedReader reader = request.getReader();
                String text = reader.readLine();

                while (text != null) {
                    System.out.println(text);
                    int vote = getVote(text);
                    int commentID = getID(text);
                    DiscussionComment dc = dd.getDCByID(commentID, a.getID());
                    if (vd.getVoteDiscussionByComAAcc(a.getID(), commentID) == null) {

                        vd.insertVoteComment(a.getID(), dc.getCd().getDiscussionID(), commentID, vote, now);
                        AccountVote av = vd.getVoteDiscussionByComAAcc(a.getID(), commentID);
                        dc.setNoVote(vote + dc.getNoVote());
                        dd.updateComment(dc);

                    } else {
                        AccountVote av = vd.getVoteDiscussionByComAAcc(a.getID(), commentID);
                        dc.setNoVote(dc.getNoVote() - av.getNoVote());
                        av.setNoVote(vote);
                        vd.updateVoteComment(av);
                        dc.setNoVote(av.getNoVote() + dc.getNoVote());
                        dd.updateComment(dc);

                    }
                    text = reader.readLine();
                }
            } else if (request.getParameter("summerNoteText") != null) {
                NotificationDAL nd = new NotificationDAL();
                String content = request.getParameter("summerNoteText");
                String class_raw = request.getParameter("cls");
                String discussID_raw = request.getParameter("cds");
                int classID = 0, discussID = 0;
                try {
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
                    System.out.println(to.getID());
                    Notification n = new Notification();
                    n.setCd(dd.getDiscussionByID(discussID, a.getID()));
                    n.setDate(now);
                    n.setDc(dc);
                    n.setFrom(a);
                    n.setTo(to);
                    n.setType("Tag");
                    nd.insertN(n);

                }
                listAfterGet(dc, discussID, classID, request, response);
            } else {
                Part filePart = request.getPart("file");
                String htai = now.toString().replaceAll(" ", "");

                htai = htai.replaceAll("\\.", "");
                htai = htai.replaceAll("\\:", "");
                htai = htai.replaceAll("\\-", "");
                System.out.println(htai);
                String uploadDirectory = request.getServletContext().getRealPath("img");
                System.out.println(uploadDirectory);
                filePart.write(("C:\\SWP\\Git\\g1\\Project\\WebApplication1\\web\\img\\" + htai + ".png"));
                filePart.write(("C:\\SWP\\Git\\g1\\Project\\WebApplication1\\build\\web\\img\\" + htai + ".png"));
                System.out.println(uploadDirectory + File.separator + htai + ".png");
                //filePart.write(uploadDirectory + File.separator + htai + ".png");
                System.out.println("2");
                response.getWriter().print("img/" + htai + ".png");
            }
        }
    }

    private void listAfterGet(DiscussionComment dc, int id, int classID, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountDAL ad = new AccountDAL();
        Account a = (Account) session.getAttribute("account");
        int numPerPage = 3;
        Paging p = new Paging();
        if (a != null) {
            DiscussionDAL dd = new DiscussionDAL();
            List<DiscussionComment> list = dd.getAllDiscussionComByDID(id, a.getID());
            int page = (list.size() % numPerPage == 0) ?(list.size() / numPerPage):(list.size() / numPerPage + 1);
            int start = p.getStart(page, list.size(), numPerPage);
            int end = p.getEnd(page, list.size(), numPerPage);
            int totalPage = page;
            request.setAttribute("id", id);
            request.setAttribute("page", page);
            request.setAttribute("start", start);
            request.setAttribute("end", end);
            request.setAttribute("size", dd.getDCByPage(list, start, end).size());
            request.setAttribute("totalEntity", list.size());
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("list", dd.getDCByPage(list, start, end));
            request.setAttribute("msg", "Comment successfully!");

            List<Account> listAcc = ad.getAllPersonInClass(classID, a.getID());
            request.setAttribute("listAcc", listAcc);
            request.setAttribute("cls", classID);
            request.setAttribute("discussion", dd.getDiscussionByID(id, a.getID()));
            request.getRequestDispatcher("view/DiscussionDetail.jsp").forward(request, response);
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
