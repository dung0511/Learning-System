<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Quiz"%>
<%@page import="model.Chapter"%>
<%@page import="model.SubjectSetting"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Quiz List</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
        <style>
            .quiz-block {
                border: 1px solid #000;
                margin-bottom: 10px;
                padding: 10px;
            }
            .quiz-info {
                display: none;

            }
            .pagination {
                text-align: center;
            }
            .toast {
                visibility: hidden;
                max-width: 50px;
                height: 50px;
                margin-bottom: 30px;
                background-color: #333;
                color: #fff;
                text-align: center;
                border-radius: 2px;
                padding: 16px;
                position: fixed;
                z-index: 1;
                left: 50%;
                bottom: 30px;
                font-size: 17px;
                transition: visibility 0.5s, opacity 0.5s linear;
            }
            .show {
                visibility: visible;
                opacity: 1;
            }
            .modal {
                display: none;
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgba(0,0,0,0.4);
            }

            .modal-content {
                background-color: #fefefe;
                margin: 15% auto;
                padding: 20px;
                border: 1px solid #888;
                width: 80%;
            }
            .modal-content {
                background-color: #fefefe;
                margin: 15% auto;
                padding: 20px;
                border: 1px solid #888;
                width: 40%;
            }

            .modal-content form {
                display: flex;
                flex-direction: column;
            }

            .modal-content label {
                margin-top: 10px;
            }

            .modal-content input[type="text"],
            .modal-content input[type="number"] {
                padding: 5px;
                margin-top: 5px;
            }

            .modal-content textarea {
                padding: 5px;
                margin-top: 5px;
                height: 100px;
            }


            .modal-content .radio-group {
                display: flex;
                align-items: center;
            }
            .modal-content input[type="submit"] {
                margin-top: 10px;
                padding: 10px;
                background-color: #4CAF50;
                color: white;
                border: none;
                cursor: pointer;
            }

            .modal-content input[type="submit"]:hover {
                background-color: #45a049;
            }
        </style>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script>
            $(document).ready(function () {
                $(".quiz-block").click(function () {
                    $(this).find(".quiz-info").slideToggle();
                });
            <c:if test="${not empty msg}">
                var toast = document.getElementById("toast");
                toast.className = "show";
                setTimeout(function () {
                    toast.className = toast.className.replace("show", "");
                }, 3000);
            </c:if>
            });
        </script>
    </head>
    <body>
        <div id="toast">${msg}</div>
        <h1>Practice Quiz List</h1>
        <button id="addButton">Add Practice Quiz</button>
        <c:forEach var="quiz" items="${lsq}">
            <div class="quiz-block">
                <h2>${quiz.getQuizName()} </h2>
                <div class="quiz-info">
                    <p>Chapter: ${quiz.getChapter().getChapterName()} </p>
                    <p>Number of question: ${quiz.getNoQ()} </p>
                    <p>Time Limit: ${quiz.getTimeLimit()} </p>
                    <p>Description: ${quiz.getDescription()} </p>
                    <a href ="TakeQuiz?QuizId=${quiz.getQuizID()}">Take quiz</a>
                    <a href ="ReviewQuiz?QuizId=${quiz.getQuizID()}">Review quiz</a>
                </div>
            </div>
        </c:forEach>
        <div class="pagination">
            <a href="TraineeQuizList?subjectId=${sID}&pageSize=${pageSize}&page=${currentPage - 1}">&lt;</a>
            <c:forEach begin="1" end="${totalPage}" varStatus="i">
                <a href="TraineeQuizList?subjectId=${sID}&pageSize=${pageSize}&page=${i.index}"
                   class="${i.index == currentPage ? 'active' : ''}">${i.index}</a>
            </c:forEach>
            <a href="TraineeQuizList?subjectId=${sID}&pageSize=${pageSize}&page=${currentPage + 1}">&gt;</a>
        </div>


        <div id="addPracticeQuizDialog" class="modal">
            <div class="modal-content">
                <span class="close">×</span>
                <div title="Thêm Practice Quiz">
                    <form id="addPracticeQuizForm">
                        <input type="hidden" name="subjectID" value="${sID}">
                        <label for="name">Name:</label><br>
                        <input type="text" id="name" name="name"><br>
                        <label for="noQ">Number of Quiz: </label><br>
                        <input type="number" id="noQ" name="noQ"><br>

                        <label for="Chapter" id="Chapter">Chapter:</label><br>
                        <select id="Chapter" name="Chapter" >
                            <c:forEach var="chapter" items="${listChap}">
                                <option value="${chapter.getChapterID()}">${chapter.getChapterName()}</option>
                            </c:forEach>
                        </select><br>

                        <input type="submit" value="Submit">
                    </form>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
        <script>
            $(document).ready(function () {
                $(document).on('click', '#addButton', function () {
                    $("#addPracticeQuizForm input[type='text'], #addPracticeQuizForm input[type='number']").val("");
                    $("#addPracticeQuizForm input[type='radio']").prop("checked", false);
                    $("#addPracticeQuizForm").css("display", "block");
                });
                $(document).on('click', '.close', function () {
                    $("#addPracticeQuizForm").css("display", "none");
                });
                $(document).on('submit', '#addPracticeQuizForm', function (event) {
                    event.preventDefault();
                    var formData = $(this).serialize();
                    $.ajax({
                        type: "POST",
                        url: "/prj/TraineeQuizList",
                        data: formData,
                        success: function (response) {
                            // handle success
                            if (response == "true" || response == "truetrue") {
                                location.reload();
                                alert("Add successful");
                            } else {
                                alert(response);
                            }
                            $("#addPracticeQuizForm").css("display", "none");
                        },
                        error: function () {
                            alert("Add failed");
                        }
                    });
                });
            });
        </script>
    </body>
</html>
