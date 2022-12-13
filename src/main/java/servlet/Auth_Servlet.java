package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Component.User;
import Component.Admin;
import Component.DataManager;

@WebServlet(name = "Auth", value = "/auth")
public class Auth_Servlet extends HttpServlet {

	String login;
	String pas;
	PrintWriter out;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		Cookie[] cookies = req.getCookies();
		boolean check = false;

		for(Cookie i : cookies){
			if((i.getName() == "adminId") || (i.getName() == "organizationId") || (i.getName() == "userId")){
				check = true;
				break;
			}
		}


	if(!check) {
		login = req.getParameter("login");
		pas = req.getParameter("password");

		out = resp.getWriter();

		User user = DataManager.findUser(login, pas, out);
		if (user == null) {

			Admin admin = DataManager.findAdmin(login, pas);
			if (admin == null) {

				out.write("Пользователь не обнаружен,");
				out.write("но вы можете пройти <a href=\"\">регистрацию</a>");

			} else {
				String adminId = String.valueOf(admin.getId());
				Cookie cookie = new Cookie("adminId", adminId);

				resp.setHeader("adminId", adminId);
				resp.addCookie(cookie);
				resp.sendRedirect("http://localhost:8080/Charity-1.0-SNAPSHOT/HTML/menu/Admin/menu_Admin.html");
			}

		} else {
			if (user.getOrganization_id() == null) {

				String userId = String.valueOf(user.getId());
				Cookie cookie = new Cookie("userId", userId);
				cookie.setMaxAge(-1);

				resp.addCookie(cookie);
				resp.sendRedirect("http://localhost:8080/Charity-1.0-SNAPSHOT/HTML/menu/User/menu_User.html");

			} else {
				if (user.getOrganization_id().getCreator_id() == null) {
					out.write("Ваш аккаунт еще не одобрен администрацией");
				} else {

					String organizationID = String.valueOf(user.getId());
					Cookie cookie = new Cookie("organizationId", organizationID);

					resp.addCookie(cookie);
					resp.sendRedirect("http://localhost:8080/Charity-1.0-SNAPSHOT/HTML/menu/Organization/menu_Organization.html");
				}
			}
			}
		} else {
		out.write("Вы уже авторизированны");
	}
	}
}
