<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="include/header.jsp" />
<c:if test="${sessionScope.studentSession == null}">
  <c:redirect url="index.jsp" />
</c:if>

<body style="min-width: 700px;">
  <jsp:include page="include/navbar.jsp" />
  <div class="container-fluid">

    <c:if test="${studentSession.driver || studentSession.studentID == currentSchedule.studentID || rider.scheduleNO != 0}">
      <div class="container-fluid border shadow col-11 mt-5">
        <h1 class="display-5 mt-3 mb-3">Current Trip</h1>

        <div class="form-group text-left row border-top pt-3">
          <div class="col-12">
            <c:choose>

              <c:when test="${studentSession.studentID == currentSchedule.studentID }">
                <table id="schedulesList" class="table table-sm table-striped table-borderless table-hover text-nowrap">
                  <thead>
                    <tr>
                      <th style="width: 25%" scope="col">Driver</th>
                      <th scope="col">Place</th>
                      <th scope="col">Destination</th>
                      <th style="width: 30%" scope="col">Expiry Date</th>
                      <th style="width: 5%" scope="col">Seats</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <th class="align-middle">${currentSchedule.driverName}</th>
                      <td class="align-middle" class="align-middle">${currentSchedule.source }</td>
                      <td class="align-middle">${currentSchedule.destination }</td>
                      <c:set value="${currentSchedule.expiryDate }" var="dateString" />
                      <fmt:parseDate value="${dateString}" var="dateObject" pattern="yyyy-MM-dd HH:mm:ss" />
                      <td class="align-middle">
                        <fmt:formatDate value="${dateObject }" pattern="EEEE, MMM d, yyyy '@' h:mm a" />
                      </td>
                      <td class="text-center align-middle">${currentSchedule.seats }</td>
                    </tr>
                  </tbody>
                </table>
              </c:when>

              <c:when test="${rider.scheduleNO != 0}">
                <table id="schedulesList" class="table table-sm table-striped table-borderless table-hover text-nowrap">
                  <thead>
                    <tr>
                      <th style="width: 25%" scope="col">Driver</th>
                      <th scope="col">Place</th>
                      <th scope="col">Destination</th>
                      <th style="width: 30%" scope="col">Expiry Date</th>
                      <th style="width: 5%" scope="col">Seats</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <th class="align-middle">${rider.driverName}</th>
                      <td class="align-middle" class="align-middle">${rider.source }</td>
                      <td class="align-middle">${rider.destination }</td>
                      <c:set value="${rider.expiryDate }" var="dateString2" />
                      <fmt:parseDate value="${dateString2}" var="dateObject2" pattern="yyyy-MM-dd HH:mm:ss" />
                      <td class="align-middle">
                        <fmt:formatDate value="${dateObject2 }" pattern="EEEE, MMM d, yyyy '@' h:mm a" />
                      </td>
                      <td class="text-center align-middle">${rider.seats }</td>
                    </tr>
                  </tbody>
                </table>
              </c:when>

              <c:when test="${studentSession.driver}">

                <c:if test="${param.showPickUpForm != 'show'}">
                  <form action="#">
                    <button class="btn btn-outline-primary m-3" type="submit" name="showPickUpForm" value="show">Add Pick up</button>
                  </form>
                </c:if>

                <c:if test="${param.showPickUpForm == 'show'}">
                  <form class="m-3" style="width: 200px;" method="post" action="AddPickUp">

                    <div class="form-group text-left row">
                      <div class="col-12">
                        <label>Pick up time in Minutes</label>
                        <input class="form-control" name="minutes" type="number" min="1" max="60" placeholder="Pick up Time" required>
                      </div>
                    </div>

                    <div class="form-group text-left row">
                      <div class="col-12">
                        <label for="pickTime"> Leaving from: </label>
                        <div class="input-group">
                          <div class="input-group-prepend">
                            <label class="input-group-text" for="inputGroupSelect01">Campus</label>
                          </div>
                          <select class="custom-select" id="inputGroupSelect01" name="campusSource">
                            <option value="HMC">HMC</option>
                            <option value="Davis">Davis</option>
                            <option value="Trafalgar">Trafalgar</option>
                          </select>
                        </div>
                      </div>
                    </div>

                    <div class="form-group text-left row">
                      <div class="col-12">
                        <label for="pickTime"> Headed to: </label>
                        <div class="input-group">
                          <div class="input-group-prepend">
                            <label class="input-group-text" for="inputGroupSelect01">Campus</label>
                          </div>
                          <select class="custom-select" id="inputGroupSelect01" name="campusDestination">
                            <option value="HMC">HMC</option>
                            <option value="Davis">Davis</option>
                            <option value="Trafalgar">Trafalgar</option>
                          </select>
                        </div>
                      </div>
                    </div>

                    <button class="btn btn-outline-primary" type="submit">Add Pick Up</button>
                  </form>
                </c:if>

              </c:when>

            </c:choose>
          </div>
        </div>

        <c:if test="${currentSchedule.scheduleNO != 0 || rider.scheduleNO != 0}">
          <div class="form-group row border-top pt-3 justify-content-end">
            <div class="mr-3">
              <c:choose>
                <c:when test="${studentSession.studentID == currentSchedule.studentID }">
                  <a class="btn btn-outline-secondary" href="CancelSchedule?scheduleNO=${currentSchedule.scheduleNO }">Cancel Pick Up</a>
                  <a class="btn btn-primary" href="CheckSchedule?scheduleNO=${currentSchedule.scheduleNO }">Check Pick Up</a>
                </c:when>
                <c:when test="${rider.scheduleNO != 0}">
                  <a class="btn btn-outline-secondary" href="CancelRide?scheduleNO=${rider.scheduleNO }">Cancel Ride</a>
                  <a class="btn btn-primary" href="CheckSchedule?scheduleNO=${rider.scheduleNO }">Check Ride</a>
                </c:when>
              </c:choose>
            </div>
          </div>
        </c:if>
      </div>
    </c:if>

    <c:if test="${currentSchedule.scheduleNO == 0 && rider.scheduleNO == 0}">
      <div class="container-fluid border shadow col-11 mt-3">
        <h1 class="display-5 mt-3 mb-3">Available Schedules</h1>

        <div class="form-group text-left row border-top pt-3">
          <div class="col-12">

            <table id="schedulesList" class="table table-sm table-striped table-borderless table-hover">
              <thead>
                <tr class="font-weight-bold">
                  <th style="width: 25%" scope="col">Driver</th>
                  <th scope="col">Place</th>
                  <th scope="col">Destination</th>
                  <th style="width: 30%" scope="col">Expiry Date</th>
                  <th style="width: 5%" scope="col">Seats</th>
                  <th style="width: 10%" scope="col"></th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${schedules}" var="row">
                  <c:if test="${currentSchedule.scheduleNO != row.scheduleNO }">
                    <tr>
                      <th class="align-middle">${row.driverName}</th>
                      <td class="align-middle">${row.source }</td>
                      <td class="align-middle">${row.destination }</td>
                      <c:set value="${row.expiryDate }" var="dateString" />
                      <fmt:parseDate value="${dateString}" var="dateObject" pattern="yyyy-MM-dd HH:mm:ss" />
                      <td class="align-middle">
                        <fmt:formatDate value="${dateObject }" pattern="EEEE, MMM d, yyyy '@' h:mm a" />
                      </td>
                      <td class="text-center align-middle">${row.seats }</td>
                      <td class="text-center align-middle"><a class="btn btn-primary" href="CheckSchedule?scheduleNO=${row.scheduleNO }">Check</a></td>
                    </tr>
                  </c:if>
                </c:forEach>
              </tbody>
            </table>

          </div>
        </div>

      </div>
    </c:if>
  </div>

  <script src="JavaScript/jquery.min.js"></script>
  <script src="JavaScript/bootstrap.bundle.min.js"></script>
  <script src="JavaScript/bootstrap.min.js"></script>
</body>

</html>