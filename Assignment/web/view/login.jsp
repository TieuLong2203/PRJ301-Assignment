<%-- 
    Document   : login
    Created on : Oct 18, 2023, 3:37:49 PM
    Author     : luulo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <title>FPT University</title>
        <link rel="stylesheet" href="css/login.css"/>
    </head>

    <body>
        <div class="header">
            <div class="part_header">
                <h2>FPT University Academic Portal</h2>
            </div>

            <div class="part_header">
                <div class="text">
                    <p>FAP mobile app (myFAP) is ready at</p>
                </div>
                <div class="imagine">
                    <a href="https://apps.apple.com/us/app/myfap/id1527723314">
                        <img src="imagines/login/app-store.png" alt="View app on App-store">
                    </a>
                    <a href="https://play.google.com/store/apps/details?id=com.fuct&hl=en-VN">
                        <img src="imagines/login/play-store.png" alt="View app on Google Play">
                    </a>
                </div>
            </div>

        </div>
        <div class="container">
            <h2>Login</h2>
            <form action="login" method="POST">
                <label for="campus">Select Campus:</label>
                <select name="campus" id="campus" class="campus">
                    <c:forEach items="${requestScope.campuses}" var="campus">
                        <option value="${campus.campusId}">${campus.campusAddress}</option>
                    </c:forEach>
                </select>
                <label for="email">Email</label>
                <input type="text" id="email" name="email" placeholder="Enter your email (@fpt//@fe)" required>

                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" required>
                <div class="remember">
                    <input type="checkbox" name="remember" id="rememer"> Remember me
                </div>
                <input type="submit" value="Login">
            </form>
        </div>
        <footer>
            <p>Mọi góp ý, thắc mắc xin liên hệ: Phòng dịch vụ sinh viên: Email: <a
                    href="mailto:dichvusinhvien@fe.edu.vn">dichvusinhvien@fe.edu.vn</a>. Điện thoại: (024)7308.13.13</p>
            <p>© Powered by FPT University | CMS | library | books24x7</p>
        </footer>
    </body>

</html>
