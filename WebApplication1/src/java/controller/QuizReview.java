/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.GradeDAL;
import dal.QuizDAL;
import dal.StudentAnswerDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.GradeItem;
import model.Quiz;
import model.Quiz_Review;

/**
 *
 * @author ADMIN
 */
public class QuizReview extends HttpServlet {

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
            out.println("<title>Servlet QuizReview</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuizReview at " + request.getContextPath() + "</h1>");
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
            int quizid = Integer.parseInt(request.getParameter("quizid"));
            if (request.getParameter("count") != null) {
                int count = Integer.parseInt(request.getParameter("count"));
            }
            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("account");
            int studentID = a.getID();
            //int studentID = 1;
            StudentAnswerDAO sad = new StudentAnswerDAO();
            GradeDAL gd = new GradeDAL();
            QuizDAL qd = new QuizDAL();
            int maxcount = gd.getMaxCount(studentID, quizid);
            if (maxcount == 0) {
                String msg = "Bạn chưa làm bài quiz này";
                Quiz quiz = new Quiz();
                quiz = qd.getQuizByID(quizid);
                int subjectId = quiz.getSubject().getSubjectID();
                request.setAttribute("subjectId", subjectId);
                request.setAttribute("msg", msg);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/TraineeQuizList");
                dispatcher.forward(request, response);
                return;
            }
            GradeItem grade = gd.GetGradeByQuizIdAndCount(studentID, quizid, maxcount);
            ArrayList<Quiz_Review> listReview = new ArrayList<>();
            listReview = sad.getReviewsByStudentAns(studentID, quizid, maxcount);
            System.out.println(grade.getItemName());
            request.setAttribute("grade", grade);
            request.setAttribute("listReview", listReview);
            request.getRequestDispatcher("view/QuizReview.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(QuizReview.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuizReview.class.getName()).log(Level.SEVERE, null, ex);
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
