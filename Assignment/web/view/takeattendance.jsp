<%-- 
    Document   : takeattendance
    Created on : Oct 14, 2023, 11:06:32 PM
    Author     : luulo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Attendance Page</title>
    <link rel="stylesheet" href="css/takeattendance.css"/>
</head>
<body>
    <div class="title">
        <h1>Attendance for Lecture</h1>
    </div>
    <form action="processAttendance.jsp" method="post">
        <table>
            <tr>
                <th>Student ID</th>
                <th>Student Name</th>
                <th>Student Email</th>
                <th>Attendance</th>
            </tr>
            <tr>
                <td>1</td>
                <td>John Doe</td>
                <td>johndoe@example.com</td>
                <td><input type="radio" name="attendance_1" value="Present"> Present
                    <input type="radio" name="attendance_1" value="Absent"> Absent
                </td>
            </tr>
            <tr>
                <td>2</td>
                <td>Jane Smith</td>
                <td>janesmith@example.com</td>
                <td><input type="radio" name="attendance_2" value="Present"> Present
                    <input type="radio" name="attendance_2" value="Absent"> Absent
                </td>
            </tr>
            <!-- Add more student rows as needed -->
        </table>
        <div class="submit">
            <input type="submit" value="Submit Attendance">
        </div>
    </form>
    <footer>
        <p>Mọi góp ý, thắc mắc xin liên hệ: Phòng dịch vụ sinh viên: Email: <a href="mailto:dichvusinhvien@fe.edu.vn">dichvusinhvien@fe.edu.vn</a>. Điện thoại: (024)7308.13.13</p>
        <p>© Powered by FPT University |  CMS |  library |  books24x7</p>
    </footer>
</body>
</html>
