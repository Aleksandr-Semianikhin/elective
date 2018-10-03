<%--
  
  User: Aleksandr Semianikhin
  Document: header
  Date: 24.09.2018
  Time: 12:34
  
--%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/view/jsp/directive/page.jsp" %>
<%@ include file="/WEB-INF/view/jsp/directive/taglib.jsp" %>
<fmt:setBundle basename="resources"/>
<head>
    <title>
        ${param.title}
    </title>
</head>
<body>
<header>
    <nav class="navbar navbar-default navbar-inverse">
        <div class="container-fluid">
            <ul class="text-info navbar-left">
                <h1><fmt:message key="header.greeting"/> </h1>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <c:if test="${not empty user}">
                    <li>
                        <a href="index">
                            <c:out value="${user.firstName}"/>
                            <c:out value=" "/>
                            <c:out value="${user.lastName}"/>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/index?command=logout">
                            <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${empty user}">
                    <li>
                        <a href="${pageContext.request.contextPath}/index?command=login">
                            <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/index?command=registerPage">
                            <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                        </a>
                    </li>
                </c:if>
                <li><a href="${pageContext.request.contextPath}/index?command=setLang&lang=en">EN</a></li>
                <li><a href="${pageContext.request.contextPath}/index?command=setLang&lang=ru">RU</a></li>
            </ul>
        </div>
    </nav>
</header>


