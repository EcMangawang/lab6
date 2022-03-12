<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    </head>
    <body>
        <h1 class="text-center">Emmari's User Management System</h1>
        <div class="container">
            <div class="row">
                <div class="col">
                    
<!-- Users list table-->
                <form action="user" method="POST" >
                  <table class="table table-striped table-dark">
                    <thead>
                        <tr>
                            <th>Email</th>
                            <th>First name</th>
                            <th>Last name</th>
                            <th>Active</th>
                            <th>Edit</th>
                            <th>Delete</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.email}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>${user.active ? "Y" : "N"}</td>
                                <td>
                                    <button type="submit" class="" name="action"  value="edit?${user.email}">Edit</button>
                                </td>
                                <td>
                                    <button type="submit" class="" name="action" value="delete?${user.email}">Delete</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                  </table>
                </form>

<!-- Add new user form -->
                <form action="user" method="POST">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Email</th>
                                <th>First name</th>
                                <th>Last name</th>
                                <th>Password</th>
                                <th>Role</th>
                                <th>Active</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <input type="email" name="email">
                                </td>
                                <td>
                                    <input type="text" name="firstName">
                                </td>
                                <td>
                                    <input type="text" name="lastName">
                                </td>
                                <td>
                                    <input type="password" name="password">
                                </td>
                                <td>
                                    <select name="role">
                                        <option value="">Please select a role</option>
                                        <c:forEach var="role" items="${roles}">
                                            <option value="${role.name}">${role.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <input type="checkbox" name="active">
                                </td>
                                <td>
                                    <button type="submit" class="" name="action" value="add">Add</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
                </div>
            </div>
        </div>
        
    </body>
</html>