<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Blog</title>
</head>
<body>
    <h2>Edit Blog</h2>
    <form action="/updateBlog" method="post">
        <input type="hidden" name="id" value="${blog.id}" />
        <label for="title">Blog Title:</label>
        <input type="text" id="title" name="title" value="${blog.title}" required/><br/><br/>
        <label for="content">Blog Content:</label><br/>
        <textarea id="content" name="content" rows="10" cols="30" required>${blog.content}</textarea><br/><br/>
        <button type="submit">Update Blog</button>
    </form>
</body>
</html>
