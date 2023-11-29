<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="model.Quiz_Question" %>
<%@page import="model.GradeItem" %>
<%@page import="model.Question_Answer" %>
<%@page import="model.Student_Answer" %>
<%@page import="model.Quiz_Review" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Review</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }

            h1, h2 {
                color: #333;
            }

            div {
                margin-bottom: 15px;
                padding: 10px;
                border-radius: 5px;
                background-color: #fff;
            }

            div > div {
                margin-left: 20px;
            }

            input[type="radio"] {
                margin-right: 10px;
            }
        </style>

    </head>
    <body>
        <% GradeItem grade = (GradeItem) request.getAttribute("grade"); %>
        <h1>Review <%=grade.getItemName()%></h1>
        <h2>Grade :<%=grade.getGrade()%></h2>
        <h2>Date Taken :<%=grade.getDateTaken()%></h2>
        <%
            List<Quiz_Review> reviews = (List<Quiz_Review>) request.getAttribute("listReview");
            for (Quiz_Review review : reviews) {
                Quiz_Question question = review.getQuestion();
                List<Question_Answer> answers = question.getAnswer();
                String studentAnswer = review.getStudentAnswer();
        %>
        <div>
            <h2><%= question.getTopic() %></h2>
            <% for (Question_Answer answer : answers) { %>
            <div>
                <input type="radio" disabled <%= answer.getAnswer_option().equals(studentAnswer) ? "checked" : "" %>>
                <%= answer.getAnswer_content() %>
            </div>
            <% } %>
            <div>
                Correct answer: <%= review.getCorrectAnswer().getAnswer_content() %>
            </div>
        </div>
        <% } %>
    </body>
</html>
