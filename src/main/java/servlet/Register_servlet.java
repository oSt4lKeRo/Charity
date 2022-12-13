package servlet;

import jakarta.persistence.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.Statement;

import Component.User;
import Component.Organization;
import Component.DataManager;

@WebServlet(name = "register", value = "/register")
public class Register_servlet extends HttpServlet {

	Writer wr;

	private static final EntityManagerFactory m_factory = Persistence.createEntityManagerFactory("Charity");

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		wr = resp.getWriter();

		String choose = req.getParameter("chooser");
		String m_login = req.getParameter("log");
		String m_password = req.getParameter("pas");
		String m_email = req.getParameter("mail");

		switch (choose){

			case "user":
				if (!DataManager.checkMail(m_email)) {
					if (!DataManager.checkNick(m_login)) {

						User user_new = new User(m_login, m_password, m_email);

						DataManager.addToDatabase(user_new, wr);
						wr.write("Аккаунт успешно создан");

					} else {
						wr.write("Пользователь с таким логином/почтой уже зарегистрирован");
					}
				} else {
					wr.write("Пользователь с таким логином/почтой уже зарегистрирован");
				}
				break;

			case "organization":
				String title = req.getParameter("title");
				String description = req.getParameter("description");

				if (!DataManager.checkOrganization(title)) {
					if (!DataManager.checkMail(m_email)) {
						if (!DataManager.checkNick(m_login)) {

							Organization organization_new = new Organization(title, description);
							DataManager.addToDatabase(organization_new, wr);
							User user_new = new User(m_login, m_password, m_email, organization_new);

//							wr.write(+ organization_new.getDescription()+ " " + organization_new.getDate_create().toString());

							DataManager.addToDatabase(user_new, wr);
							wr.write("Аккаунт успешно создан");

						} else {
							wr.write("Организация с таким логином/почтой уже зарегистрирован");
						}
					} else {
						wr.write("Организация с таким логином/почтой уже зарегистрирован");
					}
				} else {
					wr.write("Организация с таким именем уже зарегистрирована");
				}
				break;
		}

	}
}

