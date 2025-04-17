<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Blogs</title>
</head>
<body>
    <h2>All Blogs</h2>
    <c:forEach var="blog" items="${blogs}">
        <div>
            <h3>${blog.title}</h3>
            <p>${blog.content}</p>
            <p><a href="/editBlog?id=${blog.id}">Edit</a> | <a href="/deleteBlog?id=${blog.id}">Delete</a></p>
        </div>
    </c:forEach>
    <br/>
    <a href="/dashboard">Back to Dashboard</a>
</body>
</html>
