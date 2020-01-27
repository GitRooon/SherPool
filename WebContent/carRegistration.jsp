<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="include/header.jsp" />
<c:if test="${sessionScope.studentSession == null || studentSession.driver}">
  <c:redirect url="index.jsp" />
</c:if>

<body>
  <jsp:include page="include/navbar.jsp" />
  <div class="container-fluid" style="width: 500px;">
    <div class="container-fluid border shadow col-11 mt-5">

      <h1 class="display-5 mt-3 mb-3">Car Registration</h1>

      <form method="post" action="CarRegistrationServlet">

        <div class="form-group text-left row border-top pt-3">
          <div class="col-6">
            <label>Plate Number</label>
            <input class="form-control" name="plateNumber" type="text" placeholder="Plate Number" required>
          </div>
          <div class="col-6">
            <label>Seats</label>
            <input class="form-control" name="seats" type="number" placeholder="Seats" required>
          </div>
        </div>
        
        <div class="form-group text-left row">
          <div class="col-6">
            <label for="Make">Make</label>
            <input class="form-control" name="make" type="text" placeholder="Make" required>
          </div>
          <div class="col-6">
            <label for="Model">Model</label>
            <input class="form-control" name="model" type="text" placeholder="Model" required>
          </div>
        </div>

        <div class="form-group row border-top pt-3">
          <div class="col-4">
            <a class="form-control btn btn-outline-secondary" href="DashBoardServlet">Go Back</a>
          </div>
          <div class="col-8">
            <button class="form-control btn btn-primary" type="submit">Register</button>
          </div>
        </div>

      </form>
    </div>
  </div>

  <script src="JavaScript/jquery.min.js"></script>
  <script src="JavaScript/bootstrap.bundle.min.js"></script>
  <script src="JavaScript/bootstrap.min.js"></script>
</body>

</html>
