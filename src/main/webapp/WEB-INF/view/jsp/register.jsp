<%--
  
  User: Aleksandr Semianikhin
  Document: register
  Date: 29.09.2018
  Time: 21:30
  
--%>
<%@ include file="/WEB-INF/view/jsp/directive/page.jsp" %>
<%@ include file="/WEB-INF/view/jsp/directive/taglib.jsp" %>
<fmt:setBundle basename="resources"/>
<fmt:message key="register.title" var="pageTitle"/>
<jsp:include flush="true" page="/WEB-INF/view/jsp/header.jsp">
    <jsp:param name="title" value="${pageTitle}"/>
</jsp:include>
<div class="container-fluid">
    <div class="card card-container">
        <form action="index" method="POST" class="form-signin">
            <input hidden="true" name="command" value="register"/>
            <label for="login"><c:out value="${error}"/> </label>
            <input type="text" id="firstName" name="firstName" class="form-control" placeholder="<fmt:message key="register.firstName"/>" required="true" autofocus>
            <input type="text" id="lastName" name="lastName" class="form-control" placeholder="<fmt:message key="register.lastName"/>" required="true">
            <input type="text" id="login" name="login" class="form-control" placeholder="<fmt:message key="register.login"/>" required="true">
            <input type="password" id="password" name="password" class="form-control" placeholder="<fmt:message key="register.password"/>" required="true">
            <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">
                <fmt:message key="register.button"/>
            </button>
        </form>
    </div>
</div>
<jsp:include page="footer.jsp"/>
