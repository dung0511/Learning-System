<%-- 
    Document   : SubjectDimension
    Created on : Oct 16, 2023, 1:26:32 AM
    Author     : ADMIN
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Dimension"%>
<%@page import="model.DimensionType"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dimension List</title>
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
        <h1>Dimension List</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${listDimen}" var="dimension">
                <tr>
                    <td class="id">${dimension.getId()}</td>
                    <td class="name">${dimension.getName()}</td>
                    <td class="status">
                        <c:choose>
                            <c:when test="${dimension.isStatus()}">Active</c:when>
                            <c:otherwise>Inactive</c:otherwise>
                        </c:choose>
                    </td>
                    <td><button id ="editButton" class="editButton" data-id="${dimension.getDimensionID()}">Edit</button></td>
                </tr>
            </c:forEach>
        </table>
        <button id="addButton">Add Dimension</button>  

        <!--
                <div id="editDimensionDialog" title="Edit Dimension Type" style="display: none;">
                    <form id="editDimensionForm">
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="subjectID" value="${subjectID}">
                        <label for="name">Id:</label><br>
                        <input type="text" id="id" name="id" readonly><br>
                        <label for="name">Name:</label><br>
                        <input type="text" id="name" name="name"><br>
                        <label for="status">Status:</label><br>
                        <input type="radio" id="active" name="status" value="active">
                        <label for="active">Active</label><br>
                        <input type="radio" id="inactive" name="status" value="inactive">
                        <label for="inactive">Inactive</label><br>
                        <input type="submit" value="Submit">
                    </form>
                </div>-->
        <div id="editDimensionDialog" class="modal">
            <div class="modal-content">
                <span class="close">×</span>
                <div title="Edit Dimension Type">
                    <form id="editDimensionForm">
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="subjectID" value="${subjectID}">
                        <label for="id">Id:</label><br>
                        <input type="text" id="id" name="id" readonly><br>
                        <label for="name">Name:</label><br>
                        <input type="text" id="name" name="name"><br>
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

        <!--        <div id="addDimensionDialog" title="Add New Dimension Type" style="display: none;">
                    <form id="addDimensionForm">
                        <input type="hidden" name="action" value="add">
                        <input type="hidden" name="subjectID" value="${subjectID}">
                        <label for="name">Name:</label><br>
                        <input type="text" id="name" name="name"><br>
                        <label for="status">Status:</label><br>
                        <input type="radio" id="active" name="status" value="active">
                        <label for="active">Active</label><br>
                        <input type="radio" id="inactive" name="status" value="inactive">
                        <label for="inactive">Inactive</label><br>
                        <input type="submit" value="Submit">
                    </form>
                </div>-->
        <div id="addDimensionDialog" class="modal">
            <div class="modal-content">
                <span class="close">×</span>
                <div title="Add New Dimension Type">
                    <form id="addDimensionForm">
                        <input type="hidden" name="action" value="add">
                        <input type="hidden" name="subjectID" value="${subjectID}">
                        <label for="name">Name:</label><br>
                        <input type="text" id="name" name="name"><br>
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
                    var status = row.find(".status").text();

                    $("#editDimensionForm #id").val(id);
                    $("#editDimensionForm #name").val(name);
                    if (status === "Active") {
                        $("#editDimensionForm #active").prop("checked", true);
                    } else if (status === "Inactive") {
                        $("#editDimensionForm #inactive").prop("checked", true);
                    }


                    $("#editDimensionDialog").css("display", "block");
                });
                $(document).on('click', '.close', function () {
                    $("#editDimensionDialog").css("display", "none");
                });

                $(document).on('submit', '#editDimensionForm', function (event) {
                    event.preventDefault();
                    var formData = $(this).serialize();
                    $.ajax({
                        type: "POST",
                        url: "/prj/SubjectDimension",
                        data: formData,
                        success: function (response) {
                            // handle success
                            if (response == "true"|| response == "truetrue") {
                                location.reload();
                                alert("Update successful");
                            } else {
                                alert(response);
                            }
                            $("#editDimensionDialog").css("display", "none");
                        },
                        error: function () {
                            alert("Update failed");
                        }
                    });
                });
                $(document).on('click', '#addButton', function () {
                    $("#addDimensionForm input[type='text']").val("");
                    $("#addDimensionForm input[type='radio']").prop("checked", false);
                    $("#addDimensionDialog").css("display", "block");
                });
                $(document).on('click', '.close', function () {
                    $("#addDimensionDialog").css("display", "none");
                });

                $(document).on('submit', '#addDimensionForm', function (event) {
                    event.preventDefault();
                    var formData = $(this).serialize();
                    $.ajax({
                        type: "POST",
                        url: "/prj/SubjectDimension",
                        data: formData,
                        success: function (response) {
                            // handle success
                            if (response == "true"|| response == "truetrue") {
                                location.reload();
                                alert("Add successful");
                            } else {
                                alert(response);
                            }
                            $("#addDimensionDialog").css("display", "none");
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
