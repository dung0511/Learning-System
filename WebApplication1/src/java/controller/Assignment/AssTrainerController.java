/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Assignment;

import dal.AsmDAL;
import dal.ClassDAL;
import dal.SubjectSettingDAL;
import dal.VideoDAL;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Account;
import model.Assignment;
import model.AssignmentSubmit;
import model.Chapter;
import model.LessonIChapter;
import util.ValidInput;

/**
 *
 * @author Acer
 */
@WebServlet(name="AssTrainerController", urlPatterns={"/AssTrainerController"})
public class AssTrainerController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AssTrainerController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AssTrainerController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
                    listAssGet(request, response);
                    break;
                }
                case "viewAss":{
                    viewAss(request, response);
                    break;
                }
                
            }
        }
        
    }
    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String name = request.getParameter("name");
        String grade = request.getParameter("grade");
        String submitId = request.getParameter("submitId");
        response.getWriter().println("<h1>Update grade for " +name  + " successful .....!</h1>");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listAssGet(HttpServletRequest request, HttpServletResponse response) {
        try{
            if (request.getParameter("subject") == null
                || request.getParameter("class") == null
                || request.getParameter("subject").equals("")
                || request.getParameter("class").equals("")) 
        {
            response.sendRedirect("view/error.jsp");
        } else { 
            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("account");
            ValidInput v = new ValidInput();

            int subject = v.validInt(request.getParameter("subject"), request, response);
            int cl = v.validInt(request.getParameter("class"), request, response);
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            AsmDAL dao = new AsmDAL();
            int count = dao.getTotalAssForTrainer(cl, subject);
            int endP = count / 5;
            if (count % 5 != 0) {
                endP++;
            }
            List<Assignment> asmList = dao.pagggingAssForTrainer(cl,subject,index);
            if(!asmList.isEmpty()){
            request.setAttribute("countA", count);
            request.setAttribute("endP", endP);
            request.setAttribute("asmList", asmList);
            request.setAttribute("tag", index);
            request.setAttribute("subject", subject);
            request.setAttribute("cl", cl);
            String msg = "Add successfully";
            System.out.println(msg);
            request.setAttribute("msg", msg);
            }else{
                request.setAttribute("ms", "Don't have any Assignment");
            }
            request.getRequestDispatcher("view/AssForTrainer.jsp").forward(request, response);
        } 
        }catch(Exception e){
            
        }
        
    }

    private void viewAss(HttpServletRequest request, HttpServletResponse response) {
        try{
            if (request.getParameter("subject") == null
                || request.getParameter("class") == null
                || request.getParameter("subject").equals("")
                || request.getParameter("class").equals("")) 
            {
            response.sendRedirect("view/error.jsp");
            } else { 
            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("account");
            ValidInput v = new ValidInput();
            
            int asmID = v.validInt(request.getParameter("asmID"), request, response);
            int subject = v.validInt(request.getParameter("subject"), request, response);
            int cl = v.validInt(request.getParameter("class"), request, response);
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            AsmDAL dao = new AsmDAL();
            int count = dao.getTotalStInAss(asmID, cl);
            int endP = count / 5;
            if (count % 5 != 0) {
                endP++;
            }
            List<AssignmentSubmit> asmList = dao.pagggingStInAss(asmID,cl,index);
            if(!asmList.isEmpty()){
            request.setAttribute("countA", count);
            request.setAttribute("endP", endP);
            request.setAttribute("asmList", asmList);
            request.setAttribute("tag", index);
            request.setAttribute("subject", subject);
            request.setAttribute("cl", cl);
            String msg = "Add successfully";
            System.out.println(msg);
            request.setAttribute("msg", msg);
            }else{
                request.setAttribute("ms", "Don't have any Assignment");
            }
            request.getRequestDispatcher("view/StInAss.jsp").forward(request, response);
        } 
            
        }catch(Exception e){
            
        }
    }

}
