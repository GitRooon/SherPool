<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="include/header.jsp" />
<c:if test="${sessionScope.studentSession == null}">
  <c:redirect url="index.jsp" />
</c:if>

<body style="min-width: 700px;">
  <jsp:include page="include/navbar.jsp" />
  <div class="container-fluid">

    <div class="container-fluid border shadow col-11 mt-5 p-3 justify-content-start text-left" style="height:180px" >
    <div class="float-left mr-3">
 <img src="Images/thumbnailD.svg" alt="default" class="img-thumbnail rounded-circle" style="height: 150px;width:150px">
    </div>
    <div class="float-left">
    <h1 class="font-weight-bold display-5">${checkSchedule.driverName}</h1><br>
      <h3 class="display-6">
      ${checkSchedule.phone } <br>
      ${checkSchedule.email } 
      </h3>
      </div>
      </div>

    <div class="container-fluid border shadow col-11 mt-3">
      <h1 class="display-5 mt-3 mb-3">
        Trip Information
        <c:if test="${fn:length(riders) >= checkSchedule.seats}">
          <span class="badge badge-danger ml-3" style="font-size: 20px;">Full</span>
        </c:if>
        <c:if test="${fn:length(riders) < checkSchedule.seats}">
          <span class="badge badge-success ml-3" style="font-size: 20px;">${checkSchedule.seats - fn:length(riders)} seats Available</span>
        </c:if>
      </h1>

      <div class="form-group text-left row border-top pt-3">
        <div class="col-12">
          <table id="schedulesList" class="table table-sm table-striped table-borderless table-hover">
            <thead>
              <tr>
                <th style="width: 25%" scope="col">Name</th>
                <th scope="col">Number</th>
                <th scope="col">Email</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${riders}" var="row">
                <tr>
                  <th class="align-middle">${row.riderName}</th>
                  <td class="align-middle">${row.phone }</td>
                  <td class="align-middle">${row.email }</td>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>

      <div class="form-group row border-top pt-3 justify-content-end">
        <div class="mr-3">

          <a class="btn btn-outline-secondary" href="DashBoardServlet">Go Back</a>

          <c:set value="false" var="alreadyRider" />
          <c:choose>
            <c:when test="${studentSession.studentID == checkSchedule.studentID }">
              <a class="btn btn-secondary" href="CancelSchedule?scheduleNO=${checkSchedule.scheduleNO }">Cancel Pick Up</a>
            </c:when>
            <c:when test="${fn:length(riders) != 0}">

              <c:forEach items="${riders}" var="row">
                <c:if test="${row.studentID == studentSession.studentID}">
                  <a class="btn btn-secondary" href="CancelRide?scheduleNO=${checkSchedule.scheduleNO }">Cancel Ride</a>
                  <c:set value="true" var="alreadyRider" />
                </c:if>
              </c:forEach>
            </c:when>
          </c:choose>
          <c:if test="${!alreadyRider && studentSession.studentID != checkSchedule.studentID}}">
            <a class="btn btn-primary" href="ReserveSpot?scheduleNO=${checkSchedule.scheduleNO }">Reserve Seat</a>
          </c:if>
        </div>
      </div>
    </div>
  </div>

  <script src="JavaScript/jquery.min.js"></script>
  <script src="JavaScript/bootstrap.bundle.min.js"></script>
  <script src="JavaScript/bootstrap.min.js"></script>
</body>

</html>
