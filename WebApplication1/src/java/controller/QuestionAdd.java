/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.QuesAsnDAL;
import dal.QuesDAL;
import dal.SubjectDAL;
import dal.SubjectSettingDAL;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Question;
import model.Subject;
import model.SubjectSetting;
import util.Menu;

/**
 *
 * @author quany
 */
public class QuestionAdd extends HttpServlet {

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
            out.println("<title>Servlet QuestionAdd</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuestionAdd at " + request.getContextPath() + "</h1>");
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
        QuesDAL quesDAL = new QuesDAL();
        Menu m = new Menu();
        m.getNotification(request, response);
        HttpSession session = request.getSession();
        String msg = (String) session.getAttribute("msg");
        if (msg != null) {
            response.getWriter().write(msg);
            session.removeAttribute("msg");
        }
        String subjectid = request.getParameter("subjectid");
        System.out.println("subject id " + subjectid);
        int subjectIdInt = -1;
        if (subjectid != null) {
            subjectIdInt = Integer.parseInt(subjectid);
            ArrayList<SubjectSetting> lists = quesDAL.getAllSettingBySettingID(subjectIdInt);

            for (SubjectSetting list : lists) {
                System.out.println("sub :" + list);
            }

            request.setAttribute("lists", lists);
        } else {
            request.setAttribute("lists", null);
        }

        request.getRequestDispatcher("view/QuestionAdd.jsp").forward(request, response);
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
            // Lấy giá trị của các tham số từ request
            String topic = request.getParameter("topic");
            
            
            
            String questionString = request.getParameter("question");
            int quesID;
            Question qe = null;
            if (questionString != null && !questionString.isEmpty()) {
                quesID = Integer.parseInt(questionString);
                System.out.println(quesID);
                QuesDAL quesDAL = new QuesDAL();
                qe = quesDAL.getQuesByID(quesID);
            }

            String subjectString = request.getParameter("subject");
            int subjectID;
            Subject subject = null;
            if (subjectString != null && !subjectString.isEmpty()) {
                subjectID = Integer.parseInt(subjectString);
                System.out.println(subjectID);
                SubjectDAL subjectDAL = new SubjectDAL();
                subject = subjectDAL.getSubjectByID(subjectID);
            }

            String subjectsettingString = request.getParameter("subjectsetting");
            int subjectsettingID;
            SubjectSetting subjectsetting = null;
            if (subjectsettingString != null && !subjectsettingString.isEmpty()) {
                subjectsettingID = Integer.parseInt(subjectsettingString);
                System.out.println(subjectsettingID);
                SubjectSettingDAL subjectsettingDAL = new SubjectSettingDAL();
                subjectsetting = subjectsettingDAL.getAllSettingBySettingID(subjectsettingID);
            }

            int displayOrder = Integer.parseInt(request.getParameter("displayOrder"));

            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("account");
            System.out.println(account);

            String createdAtString = request.getParameter("createdAt");
            SimpleDateFormat createdAtDateFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm");
            Date parsedCreatedAt = createdAtDateFormat.parse(createdAtString);
            Timestamp createdAt = new Timestamp(parsedCreatedAt.getTime());

            int status = Integer.parseInt(request.getParameter("status"));

            String[] answerOptions = request.getParameterValues("answerOption");
            String[] answerContents = request.getParameterValues("answerContent");
            String[] isKeys = request.getParameterValues("isKey");
            QuesAsnDAL qaDAL = new QuesAsnDAL();

            if (answerOptions != null && answerContents != null && isKeys != null
                    && answerOptions.length == answerContents.length && answerContents.length == isKeys.length) {
                for (int i = 0; i < answerOptions.length; i++) {
                    String answerOption = answerOptions[i];
                    String answerContent = answerContents[i];
                    int isKey = Integer.parseInt(isKeys[i]);

                    // Thực hiện lưu dữ liệu vào cơ sở dữ liệu ở đây, ví dụ sử dụng qaDAL
                    qaDAL.addAsnwer(qe.getQuesID(), answerOption, answerContent, isKey);
                }
            }

            if (subject != null && subjectsetting != null && account != null) {
                Question question = new Question(subject, subjectsetting, topic, displayOrder, account, createdAt, status);
                System.out.println("Q add" + question);

                QuesDAL quesDAL = new QuesDAL();

                quesDAL.addQues(subject.getSubjectID(), subjectsetting.getSettingID(), topic, displayOrder, account.getID(), createdAt, status);

                session.setAttribute("msg", "ADD SUCCESSFULLY!");

                response.sendRedirect("QuestionList?page=1&size=5");
            }
        } catch (ParseException ex) {
            response.getWriter().write("Có lỗi xảy ra trong quá trình xử lý ngày tháng.");

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
