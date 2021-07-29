<%@ page import="Models.User" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">


        <title>JSP - Hello World</title>
    </head>
    <body>

    <div class="container">
        <div class="row">
            <div class="col-8 mx-auto">
                <div class="card">
                    <div class="card-body">
                        <div class="col text-center">
                            <img src="Content/Images/generaluser.png"  width="150px" />
                            <h3>User Registration</h3>
                        </div>
                    </div>
                    <form id="myForm" action="Register" method="post">
                        <div class="form-group">
                            <input name="FirstName" class="form-control" style = "max-width:none" placeholder="First Name" required />
                        </div>
                        <div class="form-group">
                            <input name="LastName" class="form-control" style = "max-width:none" placeholder="Last Name" required />
                        </div>
                        <div class="form-group">
                            <input name="Address" class="form-control" style = "max-width:none" placeholder="Address" required />
                        </div>
                        <div class="form-group">
                            <input name="City" class="form-control" style = "max-width:none" placeholder="City" required />
                        </div>
                        <div class="form-group">
                            <label for="exampleFormControlSelect1">Select Country</label>
                            <select name="Country" class="form-control" id="exampleFormControlSelect1">
                                <option>---</option>
                                <option>China</option>
                                <option>Canada</option>
                                <option>Japan</option>
                                <option>Germany</option>
                                <option>France</option>
                                <option>United States</option>
                                <option>United Kingdom</option>
                            </select>
                        </div>
                        <div>
                            <input name="Email" class="form-control" style = "max-width:none" placeholder="Email" required />
                        </div>
                        <div>
                            <input name="Password" class="form-control" style = "max-width:none" placeholder="Password" required />

                            <div class="form-cotrol">
                                    <input value="Register" type="submit" class="btn btn-primary btn-lg btn-block mt-5 mx-auto" style="width: 80% " />
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-8 mx-auto">
    <table class="table table-hover mt-2">
        <thead>
        <tr>
            <th scope="col">Full Name</th>
            <th scope="col">Address</th>
            <th scope="col">City</th>
            <th scope="col">Country</th>
            <th scope="col">Email</th>
            <th scope="col">Action</th>

        </tr>
        </thead>
        <tbody>

            <% for (User user : (List<User>) request.getAttribute("userList")) { %>

            <tr>
                <td><%= user.getFirstName() + " " + user.getLastName()  %></td>
                <td><%= user.getAddress() %></td>
                <td><%= user.getCity() %></td>
                <td><%= user.getCountry() %></td>
                <td><%= user.getEmail() %></td>
                <td><a href="Delete?Email=<%= user.getEmail() %>">Delete</a></td>

        </tr>
        <% } %>
        </tbody>

            </table>
            </div>
        </div>
    </div>


    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>

    </body>
</html>