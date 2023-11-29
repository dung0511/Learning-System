<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User" %>
<%@page import="model.Account" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/css/bootstrap.min.css">
        <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
        <style type="text/css">
            body {
                margin-top: 20px;
                background: #f8f8f8;
            }
            .container {
                margin-top: 20px;
            }
            .form-group {
                margin-bottom: 20px;
            }
            .hidden-message {
                display: none;
            }
        </style>
    </head>
    <body>
        <%@include file = "menu.jsp" %>
        <%@include file = "navbar.jsp" %>
        <%
            Account account = (Account) request.getAttribute("accountDetail");
            if (account == null) {
                response.sendRedirect("view/login.jsp");
                return;
            }
            String errorMessage = request.getAttribute("errorMessage") != null ? (String) request.getAttribute("errorMessage") : "";
            String successMessage = request.getAttribute("successMessage") != null ? (String) request.getAttribute("successMessage") : "";
        %>
        <div class="container">
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <h2>Edit Profile</h2>
                    <form action="accountController" method="post">
                        <input type="hidden" name="username" value="<%=account.getUser()%>" />
                        <div class="form-group">
                            <label for="fullname">Full Name</label>
                            <input type="text" class="form-control" id="fullname" name="fullname" value="<%= account.getFullName() %>"/>
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="text" class="form-control" id="email" name="email" value="<%= account.getEmail() %>"/>
                        </div>
                        <div class="form-group">
                            <label for="mobileNumber">Mobile Number</label>
                            <input type="text" class="form-control" id="mobileNumber" name="mobileNumber" value="<%= account.getMobile() %>"/>
                        </div>
                        <div class='row'>
                            <div class='col-md-6'>
                                <button type='submit' class='btn btn-primary btn-block'>Update Account</button>
                            </div>
                        </div>
                    </form> 
                    <br>
                    <form action='changePassword'>
                        <input type='hidden' name='username' value='<%=account.getUser()%>' />
                        <div class='row'>
                            <div class='col-md-6'>
                                <button type='submit' class='btn btn-primary btn-block'>Change Password</button>
                            </div>
                        </div>
                    </form>
                    <% if (errorMessage != null) {  %>
                    <div class="alert alert-danger mt-3 hidden-message "> ">
                        <%= errorMessage %>
                    </div>
                    <% } %>
                    <% if (successMessage != null) {  %>
                    <div class="alert alert-danger mt-3 hidden-message "> ">
                        <%= successMessage %>
                    </div>
                    <% } %>
                </div>
            </div>
        </div>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
        <script src='https://code.jquery.com/jquery-1.10.2.min.js'></script>
        <script src='https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/js/bootstrap.bundle.min.js'></script>
        <%@include file = "footer.jsp" %>
        <script>
            function showToastMessage(type, message) {
                if (type === "success") {
                    toastr.success(message);
                } else if (type === "error") {
                    toastr.error(message);
                }
            }
        </script>
        <script>
            $(document).ready(function () {
            <% if (!errorMessage.isEmpty()) {  %>
                showToastMessage("error", "<%= errorMessage %>");
            <% } %>
            <% if (!successMessage.isEmpty()) {  %>
                showToastMessage("success", "<%= successMessage %>");
            <% } %>
            });
        </script>
    </body>
</html>
