<%-- 
    Document   : SubjectChapter
    Created on : Oct 14, 2023, 7:03:59 AM
    Author     : ADMIN
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Chapter"%>
<%@page import="model.SubjectSetting"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Chapter List</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 8px;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            th {
                background-color: #4CAF50;
                color: white;
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
    </head>
    <body>
        <h1>Chapter List</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Display Order</th>
                <th>Type</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${listSetting}" var="setting">
                <tr>
                    <td class="id">${setting.getSettingID()}</td>
                    <td class="name">${setting.getSettingName()}</td>
                    <td class="description">${setting.getDescription()}</td>
                    <td class="order">${setting.getDisplayOrder()}</td>
                    <td class="type">${setting.getSettingType()}</td>
                    <td class="status">
                        <c:choose>
                            <c:when test="${setting.getStatus() == 1}">Active</c:when>
                            <c:otherwise>Inactive</c:otherwise>
                        </c:choose>
                    </td>
                    <td><button id ="editButton" class="editButton" data-id="${setting.getSettingID()}">Edit</button></td>
                </tr>
            </c:forEach>

            <div class="pagination" style="text-align: center; margin-top: 20px;">
                <a href="SubjectChapter?id=${subjectID}&page=${currentPage - 1}">«</a>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <span class="active">${i}</span>
                        </c:when>
                        <c:otherwise>
                            <a href="SubjectChapter?id=${subjectID}&page=${i}">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <a href="SubjectChapter?id=${subjectID}&page=${currentPage + 1}">»</a>
            </div>

        </table>

        <button id="addButton">Add Setting</button>

        <!--        <div id="editChapterDialog" title="Edit Setting" style="display: none;">
                    <form id="editChapterForm">
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="subjectID" value="${subjectID}">
                        <label for="name">Id:</label><br>
                        <input type="text" id="id" name="id" readonly><br>
                        <label for="name">Name:</label><br>
                        <input type="text" id="name" name="name"><br>
                        <label for="order">Order:</label><br>
                        <input type="number" id="order" name="order"><br>
                        <label for="order">Type</label><br>
                        <input type="text" id="type" name="type" readonly><br>
                        <label for="description">Description:</label><br>
                        <input type="text" id="description" name="description"><br>
                        <label for="status">Status:</label><br>
                        <input type="radio" id="active" name="status" value="active">
                        <label for="active">Active</label><br>
                        <input type="radio" id="inactive" name="status" value="inactive">
                        <label for="inactive">Inactive</label><br>
                        <input type="submit" value="Submit">
                    </form>
                </div>-->
        <div id="editChapterDialog" class="modal">
            <div class="modal-content">
                <span class="close">×</span>
                <div title="Edit Setting">
                    <form id="editChapterForm">
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="subjectID" value="${subjectID}">
                        <label for="id">Id:</label><br>
                        <input type="text" id="id" name="id" readonly><br>
                        <label for="name">Name:</label><br>
                        <input type="text" id="name" name="name"><br>
                        <label for="order">Order:</label><br>
                        <input type="number" id="order" name="order"><br>
                        <label for="type">Type:</label><br>
                        <input type="text" id="type" name="type" readonly><br>
                        <label for="description">Description:</label><br>
                        <textarea id="description" name="description"></textarea><br>
                        <label for="status">Status:</label><br>
                        <div class="radio-group">
                            <input type="radio" id="active" name="status" value="active">
                            <label for="active">Active</label><br>
                            <input type="radio" id="inactive" name="status" value="inactive">
                            <label for="inactive">Inactive</label><br>
                        </div>
                        <input type="submit" value="Submit">
                    </form>
                </div>
            </div>
        </div>

        <!--        <div id="addChapterDialog" title="Add new Setting" style="display: none;">
                    <form id="addChapterForm">
                        <input type="hidden" name="action" value="add">
                        <input type="hidden" name="subjectID" value="${subjectID}">
                        <label for="name">Name:</label><br>
                        <input type="text" id="name" name="name"><br>
                        <label for="order">Order:</label><br>
                        <input type="number" id="order" name="order"><br>
        
                        <label for="Type">Type:</label><br>
                        <input type="radio" id="chapter" name="type" value="Chapter">
                        <label for="Chapter">Chapter</label><br>
                        <input type="radio" id="dimension" name="type" value="Dimension">
                        <label for="Dimension">Dimension</label><br>
        
                        <label for="dimensionType" id="dimensionTypeLabel" style="display: none;">Dimension Type:</label><br>
                        <select id="dimensionType" name="dimensionType" style="display: none;">
        <c:forEach var="dimensionType" items="${dimensionTypes}">
            <option value="${dimensionType.getId()}">${dimensionType.getName()}</option>
        </c:forEach>
    </select><br>

    <label for="description">Description:</label><br>
    <input type="text" id="description" name="description"><br>
    <label for="status">Status:</label><br>
    <input type="radio" id="active" name="status" value="active">
    <label for="active">Active</label><br>
    <input type="radio" id="inactive" name="status" value="inactive">
    <label for="inactive">Inactive</label><br>
    <input type="submit" value="Submit">
</form>
</div>-->
        <div id="addChapterDialog" class="modal">
            <div class="modal-content">
                <span class="close">×</span>
                <div title="Add new Setting">
                    <form id="addChapterForm">
                        <input type="hidden" name="action" value="add">
                        <input type="hidden" name="subjectID" value="${subjectID}">
                        <label for="name">Name:</label><br>
                        <input type="text" id="name" name="name"><br>
                        <label for="order">Order:</label><br>
                        <input type="number" id="order" name="order"><br>

                        <label for="Type">Type:</label><br>
                        <div class="radio-group">
                            <input type="radio" id="chapter" name="type" value="Chapter">
                            <label for="Chapter">Chapter</label><br>
                            <input type="radio" id="dimension" name="type" value="Dimension">
                            <label for="Dimension">Dimension</label><br>
                        </div>

                        <label for="dimensionType" id="dimensionTypeLabel" style="display: none;">Dimension Type:</label><br>
                        <select id="dimensionType" name="dimensionType" style="display: none;">
                            <c:forEach var="dimensionType" items="${dimensionTypes}">
                                <option value="${dimensionType.getId()}">${dimensionType.getName()}</option>
                            </c:forEach>
                        </select><br>

                        <label for="description">Description:</label><br>
                        <textarea id="description" name="description"></textarea><br>
                        <label for="status">Status:</label><br>
                        <div class="radio-group">
                            <input type="radio" id="active" name="status" value="active">
                            <label for="active">Active</label><br>
                            <input type="radio" id="inactive" name="status" value="inactive">
                            <label for="inactive">Inactive</label><br>
                        </div>
                        <input type="submit" value="Submit">
                    </form>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>

        <script>
            $(document).ready(function () {
                $(document).on('click', '.editButton', function () {
                    var row = $(this).closest("tr");
                    //var id = row.find(".id").text();
                    var id = $(this).data("id");
                    var name = row.find(".name").text();
                    var orderText = row.find(".order").text();
                    var description = row.find(".description").text();
                    var status = row.find(".status").text();
                    var type = row.find(".type").text();
                    var order = parseInt(orderText);
                    if (isNaN(order)) {
                        alert("Order phải là một số.");
                        return;
                    }
                    $("#editChapterForm #id").val(id);
                    $("#editChapterForm #name").val(name);
                    $("#editChapterForm #order").val(order);
                    $("#editChapterForm #type").val(type);
                    $("#editChapterForm #description").val(description);
                    if (status === "Active") {
                        $("#editChapterForm #active").prop("checked", true);
                    } else if (status === "Inactive") {
                        $("#editChapterForm #inactive").prop("checked", true);
                    }


                    $("#editChapterDialog").css("display", "block");
                });
                $(document).on('click', '.close', function () {
                    $("#editChapterDialog").css("display", "none");
                });
                $(document).on('submit', '#editChapterForm', function (event) {
                    event.preventDefault();
                    var formData = $(this).serialize();
                    $.ajax({
                        type: "POST",
                        url: "/prj/SubjectChapter",
                        data: formData,
                        success: function (response) {
                            // handle success
                            if (response == "true" || response == "truetrue") {
                                location.reload();
                                alert("Update successful");
                            } else {
                                alert(response);
                            }
                            $("#editChapterDialog").css("display", "none");
                        },
                        error: function () {
                            alert("Update failed");
                        }
                    });
                });
                $(document).on('click', '#addButton', function () {
                    $("#addChapterForm input[type='text'], #addChapterForm input[type='number']").val("");
                    $("#addChapterForm input[type='radio']").prop("checked", false);
                    $("#addChapterDialog").css("display", "block");
                });
                $(document).on('click', '.close', function () {
                    $("#addChapterDialog").css("display", "none");
                });
                $(document).on('submit', '#addChapterForm', function (event) {
                    event.preventDefault();
                    var formData = $(this).serialize();
                    $.ajax({
                        type: "POST",
                        url: "/prj/SubjectChapter",
                        data: formData,
                        success: function (response) {
                            // handle success
                            if (response == "true" || response == "truetrue") {
                                location.reload();
                                alert("Add successful");
                            } else {
                                alert(response);
                            }
                            $("#addChapterDialog").css("display", "none");
                        },
                        error: function () {
                            alert("Add failed");
                        }
                    });
                });
                $("input[name='status']").change(function () {
                    if (this.value == 'dimension') {
                        $("#dimensionType").show();
                        $("#dimensionTypeLabel").show();
                    } else {
                        $("#dimensionType").hide();
                        $("#dimensionTypeLabel").hide();
                    }
                });
            });

        </script>
    </body>
</html>
