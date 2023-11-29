<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Dashboard</title>
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
        <%@include file="navbarTrainer.jsp" %>
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
            </section>
            <!-- /.sidebar -->
        </aside>

        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
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
                    <form action="#" method="get" class="sidebar-form">
                        <div class="input-group">
                            <input type="text" name="q" class="form-control" placeholder="Search..."/>
                            <span class="input-group-btn">
                                <button type='submit' name='seach' id='search-btn' class="btn btn-flat"><i class="fa fa-search"></i></button>
                            </span>
                        </div>
                    </form>
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
                        <li>
                            <a href="ClassAssignList">
                                <i class="fa fa-list"></i> <span>Class Assignment List</span>
                            </a>
                        </li>

                    </ul>
                </section>


                <!-- /.sidebar -->
            </aside>

            <aside class="right-side">

                <!-- Main content -->
                <section class="content">
                    <head>
                        <style>
                            /* ??t chi?u r?ng v? chi?u cao c?a th? body v? html l? 100% ?? chi?m to?n b? m?n h?nh */
                            body, html {
                                height: 100%;
                                margin: 0;
                            }

                            /* ??t chi?u r?ng v? chi?u cao c?a ? c?n hi?n th? l? 100% */
                            .full-screen-box {
                                width: 100%;
                                height: 100%;
                                color: #299be4;

                            }

                            .panel-heading{
                                font-weight: bold;
                                background-color: #299be4;
                                color: white;
                                font-weight: bold;
                                font-size: 20px;
                            }


                        </style>
                    </head>
                    <body>

                        <!-- ? c?n hi?n th? to?n m?n h?nh -->
                        <div class="full-screen-box">
                            <section class="panel">
                                <header class="panel-heading">
                                    Update Assignment
                                </header>
                                <div class="panel-body">
                                    <form action="ClassAssignUpdate" method="post" role="form">
                                        <input type="hidden" name="asmID" value="${classassignment.asmID}">
                                        <div class="form-group">
                                            <label >Assignment:</label>
                                            <textarea name="asmName" rows="4" cols="50" placeholder="Type the name" required="">${classassignment.asmName}</textarea>
                                        </div>
                                        <div class="form-group">
                                            <label>Description:</label>
                                            <textarea name="asmDes" rows="4" cols="50" placeholder="Type the name" required="">${classassignment.asmDes}</textarea>
                                        </div>
                                        <div class="form-group">
                                            <label for="subject">Select Subject:</label>
                                            <select name="subject" id="subject" onchange="handleSelect(this)" >
                                                <option value="0">Subject</option>
                                                <option value="1">SWP391</option>
                                                <option value="2">PRN211</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="subjectsetting">Select Chapter:</label>
                                            <select name="subjectsetting" id="subjectsetting"  >
                                                <c:choose>
                                                    <c:when test="${empty lists}">
                                                        <option value="0">Select subject first</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:forEach items="${lists}" var="chapter">
                                                            <option value="${chapter.settingID}">${chapter.settingName}</option>
                                                        </c:forEach>
                                                    </c:otherwise>
                                                </c:choose>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="clas">Select Class:</label>
                                            <select name="clas" id="clas"  >
                                                <option value="0">Class</option>
                                                <option value="1">SE1730</option>
                                                <option value="8">SE1731</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Deadline:</label>
                                            <input type="datetime-local" name="dl" value="${classassignment.dl}"  >
                                        </div>
                                        <div>
                                            <input type="hidden" name="createdAt" id="createdAt">
                                        </div>
                                        <div class="form-group" name="status">
                                            <label>Status:</label>
                                            <label>
                                                <input type="radio" name="status" value="1" checked> Active
                                            </label>
                                            <label>
                                                <input type="radio" name="status" value="0"> Inactive
                                            </label>
                                        </div>
                                        <button type="submit" value="Update" class="btn btn-info" onclick="setRealTime()"  >Update</button>
                                        <a href="ClassAssignList?page=1&size=5" class="btn btn-info">Close</i></a>
                                    </form>

                                    <script>




                                        function handleSelect(elm) {

                                            var asmID = document.getElementById("asmID").value; // L?y giá tr? asmID t? m?t tr??ng input ho?c select box
                                            var subjectID = elm.value; // L?y giá tr? subjectID t? tham s? truy?n vào hàm
                                            window.location = "ClassAssignUpdate?asmID=" + asmID + "&subjectID=" + subjectID;
                                        }

                                        var savedSubject = localStorage.getItem("selectedSubject");
                                        if (savedSubject) {
                                            document.getElementById("subject").value = savedSubject;
                                        }


                                        function handleAddClick() {
                                            var selectedSubject = document.getElementById("subject").value;
                                            var selectedClass = document.getElementById("class").value;
                                            if (selectedSubject === "0" || selectedClass === "0") {
                                                alert("Please select a subject and class before adding.");
                                            } else {
                                                // Th?c hi?n hành ??ng sau khi ng??i dùng ?ã ch?n môn h?c và l?p h?c.
                                            }
                                        }

                                        function formatTimeSQL(t) {
                                            var a = t.toString();
                                            var b = "";
                                            var count = 0, k = 0;
                                            for (var i = 0; i < a.length; i++) {
                                                if (a.charAt(i) == ':' && count < 1) {
                                                    b = b.concat(a.substring(k, i));
                                                    k = i;
                                                    count++;
                                                } else if (a.charAt(i) == ':' && count == 1) {
                                                    b = b.concat(a.substring(k, i));
                                                    break;
                                                }
                                            }
                                            return b;
                                        }

                                        function setRealTime() {
                                            var createdAtField = document.getElementById("createdAt");
                                            var currentTime = new Date();
                                            var formattedTime = formatTimeSQL(currentTime);
                                            createdAtField.value = formattedTime;
                                        }
                                    </script>
                                    <p>${msg}</p> 
                                </div>
                            </section>
                        </div>

                    </body>

                    </div>

                    <!-- row end -->
                </section><!-- /.content -->

            </aside><!-- /.right-side -->

        </div><!-- ./wrapper -->


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
        <script type = "text/javascript">
            function learn(subject, id) {
                window.location = "lpage?subject=" + subject + "&class=" + id;
            }
        </script>
    </body>
</html>