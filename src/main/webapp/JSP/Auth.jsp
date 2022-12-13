<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 10.12.2022
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%  Cookie[] cookies = request.getCookies();
    boolean check = false;

    for(Cookie i : cookies){
        if((i.getName().equals("adminId")) || (i.getName().equals("organizationId")) || (i.getName().equals("userId"))){
            check = true;
            break;
        }
    }

	if(check){
		out.write("Вы уже зарегистрированы");
    } else {
		out.write("\n" +
                "<div align=\"center\">\n" +
                " <h1>Charity catalog</h1>\n" +
                "\n" +
                "  <br><br><br><br>\n" +
                "  <form action=\"http://localhost:8080/Charity-1.0-SNAPSHOT/auth\" method = \"POST\">\n" +
                "      <label>login</label><br>\n" +
                "      <input type=\"text\" name = \"login\">\n" +
                "      <br>\n" +
                "      <label>password</label><br>\n" +
                "      <input type=\"text\" name = \"password\">\n" +
                "      <br><br>\n" +
                "      <input type=\"submit\" value=\"Войти\">\n" +
                "  </form>\n" +
                "    <br><br><br><br><br><br><br><br>\n" +
                "    <p>если у вас нет аккаунта то пройдите <a href=\"http://localhost:8080/Charity-1.0-SNAPSHOT/HTML/Register_User.html\">регистрацию</a></p>\n" +
                "\n" +
                "</div>");
    }
%>

</body>
</html>
