<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "javax.servlet.RequestDispatcher" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow">
  <h2>
    <a class="navbar-brand" href="DashBoardServlet">
      Sherpool
    </a>
  </h2>

  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
    <ul class="navbar-nav">
      <c:if test="${!studentSession.driver}">
        <li class="nav-item active ml-3 mr-3">
          <a class="btn btn-success" href="carRegistration.jsp">Register as Driver</a>
        </li>
      </c:if>
      <li>
        <a class="nav-link active ml-3 mr-3" href="DashBoardServlet">Home</a>
      </li>
      <li class="border-left">
        <a class="nav-link active ml-3 mr-3" href="LogoutServlet">Logout</a>
      </li>
    </ul>
  </div>
</nav>
