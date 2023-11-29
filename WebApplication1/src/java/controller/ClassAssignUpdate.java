package controller;

import dal.ClassAssignmentDAL;
import dal.ClassDAL;
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
import model.ClassAssignment;
import model.Lesson;
import model.Subject;
import model.SubjectSetting;
import util.Menu;

/**
 *
 * @author quany
 */
public class ClassAssignUpdate extends HttpServlet {

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
            out.println("<title>Servlet ClassAssignUpdate</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClassAssignUpdate at " + request.getContextPath() + "</h1>");
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

        ClassAssignmentDAL classassignmentDAL = new ClassAssignmentDAL();
        int asmID = Integer.parseInt(request.getParameter("asmID"));

        ClassAssignment classassignment = classassignmentDAL.getAsmID(asmID);
        System.out.println(classassignment);

        Menu m = new Menu();
        m.getNotification(request, response);

        String subjectid = request.getParameter("subjectid");
        System.out.println("subject id " + subjectid);

        int subjectIdInt = -1; // Giá trị mặc định nếu không thể chuyển đổi

        if (subjectid != null && !subjectid.isEmpty()) {
            try {
                subjectIdInt = Integer.parseInt(subjectid);
                ArrayList<SubjectSetting> lists = classassignmentDAL.getAllSettingBySettingID(subjectIdInt);

                for (SubjectSetting list : lists) {
                    System.out.println("sub: " + list);
                }

                request.setAttribute("lists", lists);
            } catch (NumberFormatException e) {
                e.printStackTrace(); // Xử lý lỗi theo logic của ứng dụng.
            }
        } else {
            request.setAttribute("lists", null);
        }

        request.setAttribute("classassignment", classassignment);

        request.getRequestDispatcher("view/ClassAssignmentUpdate.jsp").forward(request, response);

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
            int asmID = Integer.parseInt(request.getParameter("asmID"));
            String asmName = request.getParameter("asmName");
            String asmDes = request.getParameter("asmDes");

            String dlString = request.getParameter("dl");
            SimpleDateFormat dlDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date parsedDL = dlDateFormat.parse(dlString);
            Timestamp dl = new Timestamp(parsedDL.getTime());

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

            String clasString = request.getParameter("clas");
            int clasID;
            model.Class clas = null;
            if (clasString != null && !clasString.isEmpty()) {
                clasID = Integer.parseInt(clasString);
                System.out.println(clasID);
                ClassDAL classDAL = new ClassDAL();
                clas = classDAL.getClassByID(clasID);
            }

//            HttpSession session = request.getSession();
//            Account account = (Account) session.getAttribute("account");
//            System.out.println(account);
//            String createdAtString = request.getParameter("createdAt");
//            SimpleDateFormat createdAtDateFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm");
//            Date parsedCreatedAt = createdAtDateFormat.parse(createdAtString);
//            Timestamp createdAt = new Timestamp(parsedCreatedAt.getTime());
            int status = Integer.parseInt(request.getParameter("status"));
            // Kiểm tra xem các đối tượng (subject, subjectsetting, clas, account) có bị null không
            if (subject != null && subjectsetting != null && clas != null) {
                // Tạo đối tượng ClassAssignment từ các thông tin thu thập
                ClassAssignment classassignment = new ClassAssignment(asmID, asmName, asmDes, subject, subjectsetting, clas, dl, status);

                ClassAssignmentDAL classassignmentDAL = new ClassAssignmentDAL();
                classassignmentDAL.update(classassignment);
                
                HttpSession session = request.getSession();
                session.setAttribute("msg", "UPDATE SUCCESFULLY !");
                
                response.sendRedirect("ClassAssignList?page=1&size=5");
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
