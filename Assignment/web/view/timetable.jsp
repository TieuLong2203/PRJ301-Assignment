<%-- 
    Document   : WeeklyTimetable
    Created on : Oct 14, 2023, 10:03:54 PM
    Author     : luulo
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Weekly Timetable for Student Name</title>
        <style>
            .table-container {
                max-width: 100%; /* Maximum width to make it responsive */
                overflow-x: auto; /* Add a horizontal scrollbar when content overflows */
            }
            table {
                min-width: 900px; /* Minimum width of the table */
                border-collapse: collapse;
                width: 100%; /* Make the table responsive within the container */
            }
            th,
            td {
                border: 1px solid #000;
                padding: 10px;
                text-align: center;
                min-width: 200px; /* Minimum width of each cell */
                max-width: 200px;
            }
            th {
                color: white;
                background-color: #e09500;
            }
        </style>
    </head>
    <body>
        <h1>Weekly Timetable for <span>${requestScope.email}</span></h1>

        <p>Note: These activities do not include extra-curricular activities, such as club activities...</p>

        <p><strong>Chú thích (Note in Vietnamese):</strong> Các hoạt động trong bảng dưới không bao gồm hoạt động ngoại khóa, ví dụ như hoạt động câu lạc bộ...</p>

        <p><strong>Room Codes (Mã phòng):</strong></p>
        <ul>
            <li>Các phòng bắt đầu bằng AL thuộc tòa nhà Alpha. VD: AL,..</li>
            <li>Các phòng bắt đầu bằng BE thuộc tòa nhà Beta. VD: BE,..</li>
            <li>Các phòng bắt đầu bằng G thuộc tòa nhà Gamma. VD: G201,..</li>
            <li>Các phòng tập bằng đầu bằng R thuộc khu vực sân tập Vovinam.</li>
            <li>Các phòng bắt đầu bằng DE thuộc tòa nhà Delta. VD: DE,..</li>
            <li>Little UK (LUK) thuộc tầng 5 tòa nhà Delta</li>
        </ul>

        <p><strong>Select Timetable Period:</strong></p>
        <form action="timetable" method="post">
            <label for="startDate">Start Date: </label>
            <input type="date" id="startDate" name="startDate">

            <label for="endDate">End Date: </label>
            <input type="date" id="endDate" name="endDate">

            <input type="submit" value="View Timetable">
        </form>

        <div class="table-container">
            <table>
                <tr>
                    <td>Day/Time</td>
                    <c:forEach items="${requestScope.dates}" var="date">
                        <td>
                            <fmt:formatDate value="${date}" pattern="dd-MM-yyyy" var="formattedDate" />
                            ${formattedDate}
                        </td>
                    </c:forEach>
                    <!-- Add more day headers as needed -->
                </tr>
                <c:forEach items="${requestScope.slots}" var="slot">
                    <tr>
                        <th>
                            Slot: ${slot.slotId} <br>
                            ${slot.startTime} -> ${slot.endTime}
                        </th>
                        <c:forEach items="${requestScope.dates}" var="date">
                            <td>
                                <c:forEach items="${requestScope.sessions}" var="ses">
                                    <c:if test="${ses.sessionDate eq date and ses.slot.slotId eq slot.slotId}">
                                        ${ses.course.courseId} - ${ses.lecture.lectureId} <br> 
                                        ${ses.room.roomId} - ${ses.group.groupName}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
                <!-- Add more time slots and classes for each day -->
            </table>
        </div>
    </body>
</html>


