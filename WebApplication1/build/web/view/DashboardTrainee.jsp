<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Director | Dashboard</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <meta name="description" content="Developed By M Abdur Rokib Promy">
        <meta name="keywords" content="Admin, Bootstrap 3, Template, Theme, Responsive">
        <!-- bootstrap 3.0.2 -->
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- Morris chart -->
        <link href="css/morris/morris.css" rel="stylesheet" type="text/css" />
        <!-- jvectormap -->
        <link href="css/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" />
        <!-- Date Picker -->
        <link href="css/datepicker/datepicker3.css" rel="stylesheet" type="text/css" />
        <!-- fullCalendar -->
        <!-- <link href="css/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css" /> -->
        <!-- Daterange picker -->
        <link href="css/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
        <!-- iCheck for checkboxes and radio inputs -->
        <link href="css/iCheck/all.css" rel="stylesheet" type="text/css" />
        <!-- bootstrap wysihtml5 - text editor -->
        <!-- <link href="css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" /> -->
        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <!-- Theme style -->
        <link href="css/style.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"> -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

        <style>


            .box-container {

                display: flex;
                flex-direction: row;
                margin-right: 10px;
                flex-wrap: wrap;

            }

            .box1 {
                width: 250px;
                height: 180px;
                margin-right: 50px;
                border: 2px solid #299be4;
                border-radius: 12px;
                padding: 10px; /* Kho?ng cách n?i dung t? vi?n */
            }



            .course-code {
                font-weight: bold;
                color: black; /* Màu xanh bi?n ??m */
                font-size: 18px;
            }

            .course-code:hover{
                color: #299be4;
                cursor: pointer;
            }

            .class-name {
                color: #299be4; /* Màu xanh bi?n nh?t */
            }

            /* ??nh d?ng cho ngày b?t ??u và ngày k?t thúc */
            .dates {
                margin-top: 10px; /* Kho?ng cách t? tên l?p */
            }
            .blk{
                color: black;
                font-weight: bold;

                display: inline-block;
                width: 35%;
            }

            .box1 div{
                margin-bottom: 5px;
            }

            .box1 div button{
                margin-top: 10px;
                border-radius: 10px;

            }
        </style>
        <script>
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>
    </head>
    <body class="skin-black">
        <!-- header logo: style can be found in header.less -->
        <%@include file = "menu.jsp" %>
        <%@include file = "navbarTrainee2.jsp" %>

        <aside class="right-side">

            <!-- Main content -->
            <div class="container-xl">
                <!--                    <div class="row" style="margin-bottom:5px;">-->



                <div  class="box-container">
                    <c:forEach var="dashboard" items="${list}">
                        <div  class="box1">
                            <div class="course-code"><strong>${dashboard.subjectcode}  - ${dashboard.getSemester()}</strong></div>
                            <div>
                                <span class = "blk">Class:</span>${dashboard.classname}
                            </div>
                            <div>
                                <span class = "blk">Start date:</span>${dashboard.getStartT()} ${dashboard.getStartS()}<br>
                            </div>
                            <div>
                                <span class = "blk">End date:</span>${dashboard.getEndT()} ${dashboard.getEndS()}
                            </div>
                            <div>
                                <button class="btn btn-primary btn-addon btn-sm" onclick = "cd('${dashboard.getClassid()}', '${dashboard.subject}')">
                                    Go to subject
                                </button>
                            </div>
                        </div>

                    </c:forEach>
                </div>
                </body>

            </div>



            <div class="footer-main">
                Copyright &copy Director, 2014
            </div>
        </aside><!-- /.right-side -->



        <!-- jQuery 2.0.2 -->

        <script type = "text/javascript">
            function cd(id, subject) {
                window.location = "lpage?class=" + id + "&subject=" + subject;
            }
        </script>
    </body>
</html>