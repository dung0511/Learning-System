<%-- 
    Document   : userMgm
    Created on : Sep 17, 2023, 3:04:41 PM
    Author     : Acer
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
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
            body {
                color: #566787;
                background: #f5f5f5;
                font-family: 'Varela Round', sans-serif;
                font-size: 13px;
            }

            .searchArea{
                width: 200px;
                height: 25px;
            }

            .searchArea input{
                width: 100%;
                height: 100%;
            }

            .searchBtn{
                width: 25px;
                border: 1px solid black;
                margin-left: 10px;
                height: 25px;
                cursor: pointer;
            }



            .search{
                margin-bottom: 20px;
                display: flex;
            }
            .table-responsive {
                margin: 30px 0;
            }
            .table-wrapper {
                min-width: 1000px;
                background: #fff;
                padding: 20px 25px;
                border-radius: 3px;
                box-shadow: 0 1px 1px rgba(0,0,0,.05);
            }
            .table-title {
                padding-bottom: 15px;
                background: #299be4;
                color: #fff;
                padding: 16px 30px;
                margin: -20px -25px 10px;
                border-radius: 3px 3px 0 0;
            }
            .table-title h2 {
                margin: 5px 0 0;
                font-size: 24px;
            }
            .table-title .btn {
                color: #566787;
                float: right;
                font-size: 13px;
                background: #fff;
                border: none;
                min-width: 50px;
                border-radius: 2px;
                border: none;
                outline: none !important;
                margin-left: 10px;
            }
            .table-title .btn:hover, .table-title .btn:focus {
                color: #566787;
                background: #f2f2f2;
            }
            .table-title .btn i {
                float: left;
                font-size: 21px;
                margin-right: 5px;
            }
            .table-title .btn span {
                float: left;
                margin-top: 2px;
            }
            table.table tr th, table.table tr td {
                border-color: #e9e9e9;
                padding: 12px 15px;
                vertical-align: middle;
            }
            table.table tr th:first-child {
                width: 60px;
            }
            table.table tr th:last-child {
                width: 100px;
            }
            table.table-striped tbody tr:nth-of-type(odd) {
                background-color: #fcfcfc;
            }
            table.table-striped.table-hover tbody tr:hover {
                background: #f5f5f5;
            }
            table.table th i {
                font-size: 13px;
                margin: 0 5px;
                cursor: pointer;
            }
            table.table td:last-child i {
                opacity: 0.9;
                font-size: 22px;
                margin: 0 5px;
            }
            table.table td a {
                font-weight: bold;
                color: #566787;
                display: inline-block;
                text-decoration: none;
            }
            table.table td a:hover {
                color: #2196F3;
            }
            table.table td a.settings {
                color: #2196F3;
            }
            table.table td a.delete {
                color: #F44336;
            }
            table.table td i {
                font-size: 19px;
            }
            table.table .avatar {
                border-radius: 50%;
                vertical-align: middle;
                margin-right: 10px;
            }
            .status {
                font-size: 30px;
                margin: 2px 2px 0 0;
                display: inline-block;
                vertical-align: middle;
                line-height: 10px;
            }
            .text-success {
                color: #10c469;
            }
            .text-info {
                color: #62c9e8;
            }
            .text-warning {
                color: #FFC107;
            }
            .text-danger {
                color: #ff5b5b;
            }
            .pagination {
                float: right;
                margin: 0 0 5px;
            }
            .pagination li a {
                border: none;
                font-size: 13px;
                min-width: 30px;
                min-height: 30px;
                color: #999;
                margin: 0 2px;
                line-height: 30px;
                border-radius: 2px !important;
                text-align: center;
                padding: 0 6px;
            }
            .pagination li a:hover {
                background-color: black;
                color: white;
            }
            .pagination li.active a, .pagination li.active a.page-link {
                background-color: black;
                color: white;
            }
            .pagination li.active a:hover {
                background-color: black;
                color: white;
            }
            .pagination li.disabled i {
                color: #ccc;
            }
            .pagination li i {
                font-size: 16px;
                padding-top: 6px
            }
            .hint-text {
                float: left;
                margin-top: 10px;
                font-size: 13px;
            }

            .a{
                width: 15px;
                height: 15px;
                margin-right: 3px;
                background-color: black;
            }

            .searchArea{
                width: 200px;
                height: 25px;
                margin-left: 10px;
            }
            .sj{
                margin-left: 20px;
            }

            .searchArea input{
                width: 100%;
                height: 100%;
            }

            .searchText{

            }

            .searchBtn{
                width: 25px;
                border: 1px solid black;
                margin-left: 10px;
                height: 25px;
                cursor: pointer;
            }
            .searchText{
                text-align: left;
            }
            form{
                text-align: left;
            }

            .search{
                margin-top: 20px;
                margin-bottom: 20px;
                display: flex;
                font-size: 20px;
                justify-content: flex-start;
                margin-left: 28px;
                margin-right: 0px;
            }

            .filterArea{
                display: flex;
                margin-bottom: 20px;
                margin-left: 37%;
                font-size: 20px;
            }

            .filterArea div{
                margin-right: 20px;
            }

            .page-item .active {
                background-color: black;
                color: white;
            }

            .ms{
                font-size: 30px;
                text-align: center;
                margin-top: 20px;
            }

            .sjn{
                font-size: 22px;
                font-weight: bold;
                padding-bottom: 10px;
                cursor: pointer;
            }

            .sjn:hover{
                color: blue;
            }

            .nme{
                font-size: 17px;
                padding-bottom: 4px;
                height: 50px;
            }

            .cn{
                font-size: 17px;
                padding-bottom: 4px;
            }

            .dte{
                font-size: 17px;
                padding-bottom: 4px;
            }

            .course{
                border: 1px solid black;
                margin-right: 100px;
                margin-bottom: 50px;
                padding: 10px;
                width: 400px;
                border-radius: 12px;
            }

            .yc{
                display: flex;
                flex-direction: column;
                font-size: 20px;
            }

            .yc div{
                margin-bottom: 10px;
            }
            .footer-main{
                position:fixed;
                bottom:0;
                left: 50%;
            }
            .blk{
                color: black;
                font-weight: bold;
            }

            .ttle{
                font-size: 30px;
                font-weight: bold;
                text-align: center;
                margin-bottom: 20px;
                color: #299be4
            }

            .sbm{
                text-align: center;
                width: 100px;
                border-radius: 12px;
                margin-left: 45%;
                margin-top: 20px;
            }

            .linkRoad{
                font-size: 25px;
                margin-bottom: 15px;
            }

            .sbm input{
                width: 100%;
                background-color: red;
                color: white;
                text-align: center;
            }

            .ms{
                margin-top: 20px;
            }

            /*table*/
            table{
                margin-top: 20px;
                margin: 0 auto;
                border-radius: 12px;
                border-spacing: 0px;
            }

            tr{
                border-bottom: 2px solid black;
            }

            td{
                padding: 10px;
            }
            th{
                padding: 10px;
                border-bottom: 2px solid black;
            }

            .th{

            }

            .ttr{
                background-color: crimson;
                color: white;
            }

            tr{
                border-bottom: 2px solid black;
            }



            td{
                border-bottom: 1px solid black;
            }

            td a{
                text-decoration: none;
                color: black;
            }



            .rd{
                color: red;
            }


            .body{
                padding-top: 40px;
                padding-left: 90px;
                padding-right: 110px;
            }

            .msAdd{
                color: red;
                margin-right: 20px;
                margin-left: 45%;
            }

            table{
                margin-bottom: 20px;
            }
            .blk{
                color: black;
                font-weight: bold;

                display: inline-block;
                width: 20%;
            }

            .sort{
                width: 20px;
            }

            th:hover{
                background-color: #d4cccb;
                cursor: pointer;
            }

            .searchArea{
                display: flex;
                flex-direction: row;
                align-items: center;
            }
            .imgS{
                width: 35px;
            }

            .imgS:hover{
                cursor: pointer;
            }

            body{
                font-size: 10px;
            }

            .ms{
                color: red;
            }

            .middle{
                text-align: center

            }

            .msg{
                color: #299be4;
                text-align: center

            }

            .button-container {
                display: flex;
            }

            .button {
                margin-right: 10px;
                text-decoration: none;
                display: flex;
                align-items: center;
                cursor: pointer;
            }

            .button-icon {
                width: 30px; /* Điều chỉnh kích thước hình ảnh theo ý muốn */
                height: 30px;
                margin-right: 5px; /* Tạo khoảng cách giữa hình ảnh và văn bản */
            }

            .delete-button {
                background: none;
                border: none;
                padding: 0;
                cursor: pointer;
                font-size: 0; /* Ẩn văn bản bên trong nút */
            }

            .delete-button i {
                font-size: 24px; /* Điều chỉnh kích thước của biểu tượng theo ý muốn */
                 color: red;
            }

        </style>
        <script>
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
          <![endif]-->

        <style type="text/css">

        </style>

    </head>
    <body class="skin-black">
        <%@include file="menu.jsp" %>
        <%@include file="navbarSubjectManager.jsp" %>


        <aside class="left-side sidebar-offcanvas">
            <!-- sidebar: style can be found in sidebar.less -->
            <section class="sidebar">
                <!-- Sidebar user panel -->
                <div class="user-panel">
                    <div class="pull-left image">
                        <img src="img/26115.jpg" class="img-circle" alt="User Image" />
                    </div>
                    <div class="pull-left info">
                        <p>Hello, Jane</p>

                        <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                    </div>
                </div>
                <!-- search form -->

                <!-- /.search form -->
                <!-- sidebar menu: : style can be found in sidebar.less -->
                <ul class="sidebar-menu">
                    <li>
                        <a href="DashboardController">
                            <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fa fa-list"></i> <span>Subject Setting</span>
                        </a>
                    </li>

                    <li>
                        <a href="QuestionList">
                            <i class="fa fa-list"></i> <span>Question List</span>
                        </a>
                    </li>

                    <li>
                        <a href="#">
                            <i class="fa fa-list"></i> <span>Quiz List</span>
                        </a>
                    </li>

                    <li>
                        <a href="llist?act=list">
                            <i class="fa fa-list"></i> <span>Lesson List</span>
                        </a>
                    </li>
                    <li>
                        <a href="clist?act=list">
                            <i class="fa fa-list"></i> <span>Class List</span>
                        </a>
                    </li>

                </ul>
            </section>
            <!-- /.sidebar -->
        </aside>



        <aside class="right-side">
            <div class="container-xl">
                <div class = "ttle">
                    Question Management
                </div>
                <div class = "sj">
                    Chapter: <select name="subjectsetting" id="subjectsetting" onchange = "sbmit()">
                        <option value = "0">All</option>
                        <c:forEach items="${lists}" var="chapter">
                            <option value="${chapter.settingID}">${chapter.settingName}</option>
                        </c:forEach>
                    </select>
                </div>
                <form action="QuestionList"  method="get" class="middle">
                    Topic: <input type="text" name="topic" value="${topic}" /> 
                    <button type="submit">Search</button>     
                    <a href="QuestionList?page=1&size=5">Clear</a>
                </form>
                <div class="table-responsive">
                    <div class="table-wrapper">
                        <div class="table-title">
                            <div class="row">
                                <div class="col-sm-5">
                                    <h2><b>Question List</b></h2>
                                </div>
                                <div class="col-sm-7">
                                    <a href="QuestionAdd" class="btn btn-secondary"><i class="material-icons">&#xE147;</i> <span>Add New Question </span></a>
                                </div>
                            </div>
                        </div>


                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Subject</th>
                                    <th>Chapter</th>
                                    <th>Topic</th>
                                    <th>Created At</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="ques" items="${list}">
                                    <tr>
                                        <td>${ques.quesID}</td>
                                        <td>${ques.subject.subjectCode}</td>
                                        <td>${ques.subjectsetting.settingName}</td>
                                        <td>${ques.topic}</td>
                                        <td>${ques.createdAt}</td>
                                        <td>
                                            <c:if test = "${ques.getStatus() == 1}">
                                                <span class="status text-success">&bull;</span> Active
                                            </c:if>
                                            <c:if test = "${ques.getStatus() == 0}">
                                                <span class="status text-warning">&bull;</span> Inactive
                                            </c:if>
                                        </td>
                                        <td>
                                            <a href="QuestionDetail?quesID=${ques.quesID}" class="settings" title="View" data-toggle="tooltip"><img class ="a" src = "img/viewIcon.jpg"/></a> 
                                            <a href="ClassAssignUpdate?asmID=${ca.asmID}&subjectid=${ca.subject.subjectID}" class="settings" title="Update" data-toggle="tooltip"><img class ="a" src = "img/updateIcon.jpg"/></a> 
                                            <form action="QuestionDelete" method="post" onsubmit="return confirmDelete();">
                                                <input type="hidden" name="quesID" value="${ques.quesID}">
                                                <button type="submit" class="delete-button" title="Delete" data-toggle="tooltip">
                                                    <i class="material-icons">&#xE5C9;</i>
                                                </button>
                                            </form>

                                            <script>
                                                function confirmDelete() {
                                                    return confirm("Do you want to delete this question ?");
                                                }
                                            </script>
                                        </td>

                                    </tr>
                                </c:forEach>  
                            </tbody>
                        </table>
                        <p class='msg'>${msg}</p> 

                        <c:set var="msg" value="${null}" scope="session"></c:set>

                            <div class="clearfix">
                                <div class="hint-text">Showing <b>${list.size()}</b></div>
                            <ul class="pagination">
                                <li class="page-item"><a href="QuestionList?page=1&size=5" class="page-link">1</a></li>
                                <li class="page-item"><a href="QuestionList?page=2&size=5" class="page-link">2</a></li>
                                <li class="page-item"><a href="QuestionList?page=3&size=5" class="page-link">3</a></li>
                                <li class="page-item"><a href="QuestionList?page=4&size=5" class="page-link">4</a></li>
                                <li class="page-item"><a href="QuestionList?page=5&size=5" class="page-link">5</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>  
        </aside>  
        <%@include file = "footer.jsp" %>
        <!-- jQuery 2.0.2 -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <script src="js/jquery.min.js" type="text/javascript"></script>

        <!-- jQuery UI 1.10.3 -->
        <script src="js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <!-- daterangepicker -->
        <script src="js/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>

        <script src="js/plugins/chart.js" type="text/javascript"></script>

        <!-- datepicker
        <script src="js/plugins/datepicker/bootstrap-datepicker.js" type="text/javascript"></script>-->
        <!-- Bootstrap WYSIHTML5
        <script src="js/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script>-->
        <!-- iCheck -->
        <script src="js/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
        <!-- calendar -->
        <script src="js/plugins/fullcalendar/fullcalendar.js" type="text/javascript"></script>

        <!-- Director App -->
        <script src="js/Director/app.js" type="text/javascript"></script>

        <!-- Director dashboard demo (This is only for demo purposes) -->
        <script src="js/Director/dashboard.js" type="text/javascript"></script>
        <!-- Director for demo purposes -->
        <script type="text/javascript">
                                                $('input').on('ifChecked', function (event) {
                                                    // var element = $(this).parent().find('input:checkbox:first');
                                                    // element.parent().parent().parent().addClass('highlight');
                                                    $(this).parents('li').addClass("task-done");
                                                    console.log('ok');
                                                });
                                                $('input').on('ifUnchecked', function (event) {
                                                    // var element = $(this).parent().find('input:checkbox:first');
                                                    // element.parent().parent().parent().removeClass('highlight');
                                                    $(this).parents('li').removeClass("task-done");
                                                    console.log('not');
                                                });

        </script>
        <script>
            $('#noti-box').slimScroll({
                height: '400px',
                size: '5px',
                BorderRadius: '5px'
            });

            $('input[type="checkbox"].flat-grey, input[type="radio"].flat-grey').iCheck({
                checkboxClass: 'icheckbox_flat-grey',
                radioClass: 'iradio_flat-grey'
            });
        </script>
        <script type="text/javascript">
            $(function () {
                "use strict";
                //BAR CHART
                var data = {
                    labels: ["January", "February", "March", "April", "May", "June", "July"],
                    datasets: [
                        {
                            label: "My First dataset",
                            fillColor: "rgba(220,220,220,0.2)",
                            strokeColor: "rgba(220,220,220,1)",
                            pointColor: "rgba(220,220,220,1)",
                            pointStrokeColor: "#fff",
                            pointHighlightFill: "#fff",
                            pointHighlightStroke: "rgba(220,220,220,1)",
                            data: [65, 59, 80, 81, 56, 55, 40]
                        },
                        {
                            label: "My Second dataset",
                            fillColor: "rgba(151,187,205,0.2)",
                            strokeColor: "rgba(151,187,205,1)",
                            pointColor: "rgba(151,187,205,1)",
                            pointStrokeColor: "#fff",
                            pointHighlightFill: "#fff",
                            pointHighlightStroke: "rgba(151,187,205,1)",
                            data: [28, 48, 40, 19, 86, 27, 90]
                        }
                    ]
                };
                new Chart(document.getElementById("linechart").getContext("2d")).Line(data, {
                    responsive: true,
                    maintainAspectRatio: false,
                });

            });
            // Chart.defaults.global.responsive = true;
        </script>   


    </body>
</html>