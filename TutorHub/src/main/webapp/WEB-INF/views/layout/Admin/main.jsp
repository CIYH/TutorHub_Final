<%-- 
    Document   : main
    Created on : Jun 9, 2025, 2:07:57 PM
    Author     : qnhat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/layout/Admin/header.jsp"/> 


<c:if test="${not empty body}">
    <jsp:include page="${body}"/>
</c:if>


