<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .header {
            background: #343a40;
            color: white;
            padding: 20px;
            text-align: center;
        }
        .blogs {
            margin-top: 30px;
        }
        .blog {
            background: white;
            margin-bottom: 20px;
            padding: 15px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .blog h2 {
            margin: 0;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>Welcome to the Blog</h1>
    </div>

    <div class="blogs">
        <c:forEach var="blog" items="${blogs}">
            <div class="blog">
                <h2>${blog.title}</h2>
                <p>${blog.content}</p>
            </div>
        </c:forEach>
    </div>
</body>
</html>
