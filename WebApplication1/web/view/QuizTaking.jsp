<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Quiz_Question"%>
<%@page import="model.Question"%>
<%@page import="model.Question_Answer"%>
<%@page import="java.util.ArrayList" %>
<%@ page import="com.google.gson.Gson" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Quiz</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }

            #timer {
                font-size: 1.2em;
                color: #333;
                background-color: #fff;
                padding: 10px;
                border-radius: 5px;
                margin-bottom: 20px;
            }

            #quizForm {
                width: 80%;
                margin: 0 auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 5px;
            }

            .question {
                margin-bottom: 20px;
            }

            .question p {
                font-weight: bold;
            }

            #prev, #next, input[type="submit"] {
                background-color: #333;
                color: #fff;
                border: none;
                padding: 10px 20px;
                border-radius: 5px;
                cursor: pointer;
            }

            #prev:hover, #next:hover, input[type="submit"]:hover {
                background-color: #666;
            }
        </style>
    </head>
    <body>
        <div id="timer">Thời gian còn lại: 
            <% if (request.getAttribute("time_limit") != null) { %>
            <%=request.getAttribute("time_limit")%>:00
            <% } else { %>
            Không giới hạn
            <% } %>
        </div>

        <form id="quizForm" action="TakeQuiz" method="post">
            <input type='hidden' name='quizid' value='<%=request.getAttribute("QuizId")%>' />
            <div id="questionContainer">
                <% ArrayList<Quiz_Question> listQuestion = (ArrayList<Quiz_Question>) request.getAttribute("listQuestion");
                   for (int i = 0; i < listQuestion.size(); i++) { 
                       Quiz_Question qq = listQuestion.get(i);
                %>
                <div class="question" id="<%=i%>" style="display: none;">
                    <p>Câu <%=i + 1%>/<%=request.getAttribute("noQ")%>: <%=qq.getTopic()%></p>
                    <% for (Question_Answer answer : qq.getAnswer()) { %>
                    <input type="radio" name="answer<%=qq.getQuestion_id()%>" value="<%=answer.getAnswer_option()%>"> <%=answer.getAnswer_option()%>. <%=answer.getAnswer_content() %><br>
                    <% } %>
                </div>
                <% } %>
            </div>
            <input type="button" id="prev" value="Quay lại" />
            <input type="button" id="next" value="Tiếp theo" />
            <input type="submit" value="Nộp bài" />
        </form>


        <% 
            Gson gson = new Gson();
            String json = gson.toJson(listQuestion);
        %>
        <script>
            $(document).ready(function () {
                var currentQuestion = 0;
                var questions = JSON.parse('<%=json%>'); // Danh sách câu hỏi từ Servlet
                var startTime = new Date().getTime(); // Lưu lại thời điểm bắt đầu
                var topics = [];
            <% for (Quiz_Question qq : listQuestion) { %>
                topics.push("<%=qq.getTopic()%>");
            <% } %>
                function showQuestion(index) {
                    $(".question").hide(); // Ẩn tất cả các câu hỏi
                    $("#" + index).show(); // Hiển thị câu hỏi hiện tại
                    $("#" + index).find("p").text("Câu " + (index + 1) + "/" + <%=request.getAttribute("noQ")%> + ": " + topics[index]); // Cập nhật nội dung của thẻ <p>
                }

                $("#prev").click(function () {
                    if (currentQuestion > 0) {
                        currentQuestion--;
                        showQuestion(currentQuestion);
                    }
                });

                $("#next").click(function () {
                    if (currentQuestion < questions.length - 1) {
                        currentQuestion++;
                        showQuestion(currentQuestion);
                    }
                });
                showQuestion(currentQuestion);
                var timeLimit = <%=request.getAttribute("time_limit") != null ? request.getAttribute("time_limit") : "null"%>;
                if (timeLimit == null) {
                    $("#timer").text("Thời gian còn lại: Không giới hạn");
                } else {
                    timeLimit *= 60; // Chuyển đổi từ phút sang giây
                    var timer = setInterval(function () {
                        var minutes = Math.floor(timeLimit / 60);
                        var seconds = timeLimit % 60;
                        $("#timer").text("Thời gian còn lại: " + minutes + ":" + (seconds < 10 ? "0" : "") + seconds);
                        timeLimit--;
                        if (timeLimit < 0) {
                            clearInterval(timer);
                            $("#quizForm").submit(); // Tự động nộp bài khi hết thời gian
                        }
                    }, 1000);
                }
                $("#quizForm").submit(function () {
                    var elapsedTimeMillis = new Date().getTime() - startTime; // Tính thời gian đã trôi qua (mili giây)
                    var elapsedTimeMinutes = elapsedTimeMillis / 1000 / 60; // Chuyển đổi từ mili giây sang phút
                    $("<input />").attr("type", "hidden")
                            .attr("name", "elapsedTime")
                            .attr("value", elapsedTimeMinutes)
                            .appendTo("#quizForm"); // Thêm elapsedTime vào form như một trường ẩn
                });
            });
        </script>
    </body>
</html>