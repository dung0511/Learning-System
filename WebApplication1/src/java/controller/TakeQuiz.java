/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.GradeDAL;
import dal.QuizDAL;
import dal.QuizQuestionDAO;
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
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Quiz;
import model.Quiz_Question;
import model.Student_Answer;

/**
 *
 * @author ADMIN
 */
public class TakeQuiz extends HttpServlet {

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
            out.println("<title>Servlet TakeQuiz</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TakeQuiz at " + request.getContextPath() + "</h1>");
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
            QuizQuestionDAO qqd = new QuizQuestionDAO();
            QuizDAL qd = new QuizDAL();
            int quizId = Integer.parseInt(request.getParameter("QuizId"));
            //int chapterID = Integer.parseInt(request.getParameter("ChapterId"));
            ArrayList<Quiz_Question> lsqq = new ArrayList<>();
            Quiz quiz = new Quiz();
            quiz = qd.getQuizByID(quizId);
            String type = quiz.getType();
            if (type.equalsIgnoreCase("Fixed")) {
                lsqq = qqd.getQuizQuestionsByQuizId(quizId);
            } else if (type.equalsIgnoreCase("Random")) {
                lsqq = qqd.getRandomQuestionsByChapter(quiz.getChapter().getChapterID(), quiz.getNoQ());
            } else if (type.equalsIgnoreCase("Practice")) {
                lsqq = qqd.getRandomQuestionsByChapter(quiz.getChapter().getChapterID(), quiz.getNoQ());
            }
            //System.out.println(lsqq.get(1).getTopic());

            Double time_limit = quiz.getTimeLimit();
            int noQ = quiz.getNoQ();
            request.setAttribute("listQuestion", lsqq);
            request.setAttribute("time_limit", time_limit);
            request.setAttribute("noQ", noQ);
            request.setAttribute("QuizId", quizId);
            request.getRequestDispatcher("view/QuizTaking.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(TakeQuiz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TakeQuiz.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            StudentAnswerDAO sad = new StudentAnswerDAO();
            GradeDAL gd = new GradeDAL();
            QuizDAL qd = new QuizDAL();
            String elapsedTimeParam = request.getParameter("elapsedTime");
            double time_taken = elapsedTimeParam != null ? Double.parseDouble(elapsedTimeParam) : null;
            int quizid = Integer.parseInt(request.getParameter("quizid"));
            Quiz quiz = new Quiz();
            quiz = qd.getQuizByID(quizid);
            String type = quiz.getType();
            int subjectId = quiz.getSubject().getSubjectID();
            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("account");
            int studentID = a.getID();
//            int studentID = 1;
            int count = gd.getMaxCount(studentID, quizid);
            count++;
            // Lấy câu trả lời từ form
            ArrayList<Student_Answer> answers = new ArrayList<>();
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                if (paramName.startsWith("answer")) {
                    int questionId = Integer.parseInt(paramName.substring(6)); // Lấy id câu hỏi từ tên tham số
                    String answerOption = request.getParameter(paramName);
                    Student_Answer answer = new Student_Answer();
                    answer.setStudent_id(studentID);
                    answer.setQuiz_id(quizid);
                    answer.setQuestion_id(questionId);
                    answer.setAnswer_option(answerOption);
                    answer.setCount(count);
                    answers.add(answer);
                }
            }
            sad.saveStudentQuiz(answers);
            double score = sad.calculateScore(studentID, quizid, count);
            boolean check = gd.saveStudentQuizGrade(studentID, quizid, count, score, time_taken);
            String msg = "";
            if (check) {
                msg = "Lưu bài thành công!";
            } else {
                msg = "Có lỗi xảy ra trong quá trình lưu bài. Vui lòng thử lại";
            }
            //đưa về trang nào đó cùng với toast message msg
            request.setAttribute("msg", msg);
            request.setAttribute("subjectId", subjectId);
            if (type.equalsIgnoreCase("Fixed")) {
                request.getRequestDispatcher("view/test32.jsp").forward(request, response);
                return;
            } else if (type.equalsIgnoreCase("Random")) {
                request.getRequestDispatcher("view/test32.jsp").forward(request, response);
                return;
            } else if (type.equalsIgnoreCase("Practice")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/TraineeQuizList");
                dispatcher.forward(request, response);
                return;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TakeQuiz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TakeQuiz.class.getName()).log(Level.SEVERE, null, ex);
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
