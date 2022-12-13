<%@ page import="Component.Company" %>
<%@ page import="Component" %>
<%@ page import="java.awt.*" %>
<%@ page import="java.util.List" %>
<%@ page import="Component.DataManager" %><%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 02.12.2022
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Charity</title>
</head>
<body>
    <% List<Company> companyList = DataManager.getCompanyList();
	for(Company i : companyList){
		out.println("<hr>");
		out.println(i.getTitle() + " " + i.getDescription() + " " + i.getPrice());
		out.println("<form action = \"\"" +
                "<input type=\"hidden\" name = \"choose\" value=\"" + i.getId() +"\">" +
                "<input type = \"submit\" value = \"donate\"" +
                "</form>");
		out.println("<hr>");
    }
    %>
</body>
</html>
