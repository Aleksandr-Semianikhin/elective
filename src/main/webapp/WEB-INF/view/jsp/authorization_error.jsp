<%--
  
  User: Aleksandr Semianikhin
  Document: authorization_error
  Date: 27.09.2018
  Time: 14:18
  
--%>
<%@ include file="/WEB-INF/view/jsp/directive/page.jsp" %>
<%@ include file="/WEB-INF/view/jsp/directive/taglib.jsp" %>
<!DOCTYPE html>
<html>
<fmt:setBundle basename="strings"/>
<fmt:message key="auth.error" var="pageTitle"/>
<jsp:include flush="true" page="header.jsp">
    <jsp:param name="title" value="${pageTitle}"/>
</jsp:include>
<a href="index"><fmt:message key="auth.go_to_menu"/></a>
</body>
</html>