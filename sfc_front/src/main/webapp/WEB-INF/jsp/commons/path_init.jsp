<%--
  Created by IntelliJ IDEA.
  User: viphu
  Date: 2017-11-30
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String staticPath = application.getInitParameter("staticPath");
    String localPath = request.getContextPath();
    String imagePath = application.getInitParameter("imagePath");
    request.setAttribute("staticPath", staticPath);
    request.setAttribute("localPath", localPath);
    request.setAttribute("imagePath", imagePath);
%>
