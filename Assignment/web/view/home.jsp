<%-- 
    Document   : home
    Created on : Oct 16, 2023, 12:59:51 AM
    Author     : luulo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
    <link rel="stylesheet" type="text/css" href="css/home.css">
    <script>
        function viewTimetable() {
            window.location.href = 'timetable';
        }
        function takeAttendance() {
            window.location.href = 'takeattendance';
        }
        function logout() {
            var conf = confirm("Do you want to logout?");
            if(conf) {
                window.location.href = 'logout';
            }
        }
    </script>
</head>
<body>
    <div class="header">
        <button onclick="logout()">Logout</button>
    </div>
    <div class="container">
        <h1>Welcome to the Home Page</h1>

        <div class="section">
            <h2>Timetable</h2>
            <button id="timetable" onclick="viewTimetable()">View Timetable</button>
        </div>

        <div class="section">
            <h2>Take Attendance</h2>
            <button id="takeattendance" onclick="takeAttendance()">Take Attendance</button>
        </div>

        <div class="section">
            <h2>Insert</h2>
            <button id="insert">Insert</button>
        </div>
    </div>
</body>
</html>

