<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
</head>
<body>
    <h2>Welcome to the Blog Management System</h2>
    <p>Hello, ${sessionScope.username}!</p>
    <h3>Blog Management</h3>
    <ul>
        <li><a href="/createBlog">Create New Blog</a></li>
        <li><a href="/viewBlogs">View Blogs</a></li>
    </ul>
    <form action="/logout" method="post">
        <button type="submit">Logout</button>
    </form>
</body>
</html>
