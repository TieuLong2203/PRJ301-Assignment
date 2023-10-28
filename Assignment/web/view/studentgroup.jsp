<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Student Group</title>
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
                margin: 20px 0;
            }

            table, th, td {
                border: 1px solid #ccc;
            }

            th, td {
                padding: 10px;
                text-align: left;
            }

            th {
                background-color: #333;
                color: #fff;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            tr:hover {
                background-color: #ddd;
            }

            select {
                width: 100%;
                padding: 5px;
            }

            /* Add additional styling as needed */
        </style>

    </head>
    <body>
        <h1>Student Group: <span></span></h1>

        <table>
            <tr>
                <th>Campus</th>
                <th>Term (Semester)</th>
                <th>Department</th>
                <th>Course</th>
                <th>Group</th>
            </tr>
            <tr>
                <td>FU hola</td>
                <td>
                </td>
                <td>
                    <c:forEach items="${requestScope.departments}" var="dept">
                        <a href="?department_id=${dept.departmentId}">${dept.departmentName}</a> <br>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach items="${requestScope.courses}" var="course">
                        <a href="?department_id=${requestScope.department_id}&course_id=${course.courseId}">${course.courseName}</a> <br>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach items="${requestScope.groups}" var="group">
                        <a href="?department_id=${requestScope.department_id}&course_id=${requestScope.course_id}&group_id=${group.groupId}">${group.groupName}</a>
                    </c:forEach>
                </td>
            </tr>
        </table>
        <h1>Student Information</h1>

        <table>
            <thead>
                <tr>
                    <th>Student ID</th>
                    <th>Student Name</th>
                    <th>Gender</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.students}" var="student">
                    <tr>
                        <td>${student.studentId}</td>
                        <td>${student.studentName}</td>
                        <td>
                            ${student.gender ? 'Male' : 'Female'}
                        </td>
                    </tr>
                </c:forEach>
                <!-- Add more rows for additional students -->
            </tbody>
        </table>

    </body>
</html>
