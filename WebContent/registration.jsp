<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="include/header.jsp" />
<c:if test="${sessionScope.studentSession != null}">
  <c:redirect url="DashBoardServlet" />
</c:if>

<body class="text-center pt-5" style="max-width: 500px;">
  <div class="container-fluid border shadow col-11 mt-5">

    <h1 class="display-1 mt-5 mb-3 ">Sherpool</h1>
    <h1 class="display-5 mb-3">Registration</h1>

    <form method="post" action="StudentRegistrationServlet">

      <div class="form-group text-left row border-top pt-3">
        <div class="col-12">
          <label>Student ID and Password</label>
          <div class="input-group">
            <input class="form-control" name="studentID" type="text" placeholder="Student ID" required>
            <input class="form-control" name="password" type="password" placeholder="Password" required>
          </div>
        </div>
      </div>

      <div class="form-group text-left row">
        <div class="col-12">
          <label>Student Name</label>
          <div class="input-group">
            <input class="form-control" name="fName" type="text" placeholder="First Name" required>
            <input class="form-control" name="lName" type="text" placeholder="Last Name" required>
          </div>
        </div>
      </div>

      <div class="form-group text-left row">
        <div class="col-7">
          <label>Email Address</label>
          <input class="form-control" name="email" type="email" placeholder="Email" required>
        </div>
        <div class="col-5">
          <label>Phone Number</label>
          <input class="form-control" name="phone" type="text" placeholder="Phone Number" required>
        </div>
      </div>

      <div class="form-group row border-top pt-3 justify-content-center">
        <div class="col-6">
          <button class="form-control btn btn-primary" type="submit">Register</button>
        </div>
      </div>
      
      <div class="form-group row justify-content-center">
        <div class="col-6"><a class="btn-light" href="index.jsp">Click Here to Login</a>
        </div>
      </div>

    </form>
  </div>

  <script src="JavaScript/jquery.min.js"></script>
  <script src="JavaScript/bootstrap.bundle.min.js"></script>
  <script src="JavaScript/bootstrap.min.js"></script>
</body>

</html>