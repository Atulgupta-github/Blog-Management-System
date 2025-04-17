<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create New Blog</title>
</head>
<body>
    <h2>Create New Blog</h2>
    <form action="/saveBlog" method="post">
        <label for="title">Blog Title:</label>
        <input type="text" id="title" name="title" required/><br/><br/>
        <label for="content">Blog Content:</label><br/>
        <textarea id="content" name="content" rows="10" cols="30" required></textarea><br/><br/>
        <button type="submit">Save Blog</button>
    </form>
</body>
</html>
