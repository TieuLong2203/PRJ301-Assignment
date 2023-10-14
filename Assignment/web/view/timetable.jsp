<%-- 
    Document   : WeeklyTimetable
    Created on : Oct 14, 2023, 10:03:54 PM
    Author     : luulo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Weekly Timetable</title>
    <link rel="stylesheet" href="css/timetable.css">
</head>
<body>
    <h1>Weekly Timetable for <span id="studentName">Student Name</span></h1>
    <p>Note: These activities do not include extra-curricular activities, such as club activities...</p>
    <p>Chú thích: Các hoạt động trong bảng dưới không bao gồm hoạt động ngoại khóa, ví dụ như hoạt động câu lạc bộ...</p>
    <p>Các phòng bắt đầu bằng AL thuộc tòa nhà Alpha. VD: AL...</p>
    <p>Các phòng bắt đầu bằng BE thuộc tòa nhà Beta. VD: BE,..</p>
    <p>Các phòng bắt đầu bằng G thuộc tòa nhà Gamma. VD: G201,...</p>
    <p>Các phòng tập bằng đầu bằng R thuộc khu vực sân tập Vovinam.</p>
    <p>Các phòng bắt đầu bằng DE thuộc tòa nhà Delta. VD: DE,..</p>
    <p>Little UK (LUK) thuộc tầng 5 tòa nhà Delta</p>
    
    <label for="startDate">Start Day:</label>
    <input type="date" id="startDate">
    <label for="endDate">End Day:</label>
    <input type="date" id="endDate">
    <button onclick="updateTimetable()">View Timetable</button>

    <div class="view_timetable" id="view_timetable">
        <div class="timetable_container">
            <table id="timetable">
            
            </table>
        </div>
    </div>

    <footer>
        <p>Mọi góp ý, thắc mắc xin liên hệ: Phòng dịch vụ sinh viên: Email: <a href="mailto:dichvusinhvien@fe.edu.vn">dichvusinhvien@fe.edu.vn</a>. Điện thoại: (024)7308.13.13</p>
        <p>© Powered by FPT University |  CMS |  library |  books24x7</p>
    </footer>

    <script src="js/timetable.js">
    </script>
</body>
</html>

