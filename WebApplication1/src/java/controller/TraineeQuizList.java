/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.QuizDAL;
import dal.SubjectSettingDAL;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.awt.print.Printable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Chapter;
import model.Quiz;

/**
 *
 * @author ADMIN
 */
public class TraineeQuizList extends HttpServlet {

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
            out.println("<title>Servlet TraineeQuizList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TraineeQuizList at " + request.getContextPath() + "</h1>");
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
        try {
            QuizDAL qd = new QuizDAL();
            int subjectId = Integer.parseInt(request.getParameter("subjectId"));
            if (request.getParameter("msg") != null) {
                String msg = request.getParameter("msg");
                request.setAttribute("msg", msg);
            }
            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("account");
            if (a == null) {
                response.sendRedirect("view/login.jsp");
                return;
            }
            int studentID = a.getID();
            ArrayList<Quiz> lsq = new ArrayList<>();
            lsq = qd.getPracticeQuizzesByStudentId(studentID, subjectId);
            int defaultPage = 1;
            int page;
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                page = defaultPage;
            }
            int numPerPage = 5;
            int start = (page - 1) * numPerPage;
            int end = Math.min(start + numPerPage, lsq.size());
            List<Quiz> pageSettings = new ArrayList<>();
            for (int i = start; i < end; i++) {
                pageSettings.add(lsq.get(i));
            }
            int totalPage = (lsq.size() % numPerPage == 0) ? (lsq.size() / numPerPage) : (lsq.size() / numPerPage + 1);
            SubjectSettingDAL ssd = new SubjectSettingDAL();
            List<Chapter> listchap = ssd.getAllChapterBySubject(subjectId);
            request.setAttribute("sID", subjectId);
            request.setAttribute("lsq", pageSettings);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("currentPage", page);
            request.setAttribute("listChap", listchap);
            request.getRequestDispatcher("view/TraineeQuizList.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(TraineeQuizList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TraineeQuizList.class.getName()).log(Level.SEVERE, null, ex);
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
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        String SubjectIdStr = request.getParameter("subjectID");
        String name = request.getParameter("name");
        String noQSTR = request.getParameter("noQ");
        int noQ = Integer.parseInt(noQSTR);
        String chapterIdSTR = request.getParameter("Chapter");
        int chapterId = Integer.parseInt(chapterIdSTR);
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        if (a == null) {
            response.sendRedirect("view/login.jsp");
            return;
        }
        Chapter c  = ssd.getChapterByID(chapterId);
        Quiz quiz = new Quiz();
        quiz.setChapter(c);
        quiz.setCreatedBy(a);
        quiz.setNoQ(noQ);
        quiz.setQuizName(name);
        quiz.setType("Practice");
        QuizDAL qd = new QuizDAL();
        qd.insertPracticeQuiz(quiz);
        response.getWriter().write("true");
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
