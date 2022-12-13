package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import Component.User;
import Component.Company;
import Component.DataManager;

@WebServlet(name = "controller_admin", value = "/controller_admin")
public class ControllerAdmin extends HttpServlet {

	Writer wr;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		String choose = req.getParameter("choose");
		wr = resp.getWriter();

		switch (choose){

			case "seeUserList":
				List<User> userList = DataManager.getUserList();
				for(User i : userList){
					wr.write("<hr>");
					wr.write("Имя пользователя: " + i.getUsername() +
							"<br>Почта: " + i.getEmail());
					wr.write("<br><form action = \"\"" +
							"<input type=\"hidden\" name = \"choose\" value=\"User\">" +
							"<input type=\"hidden\" name = \"param\" value=\"" + i +"\">" +
							"<input type = \"submit\" value = \"Delete elem\"" +
							"</form>");
					wr.write("<hr>");
				}
				break;

			case "seeOrganizationList":

				List<User> organizationList = DataManager.getOrganizationUserList();

				for(User i : organizationList){
					wr.write("<hr>");
					wr.write("Имя пользователя: " + i.getUsername() +
							"<br>Почта: " + i.getEmail());
					wr.write("<hr>");
				}
				break;

			case "seeCompanyList":
				List<Company> companyList = DataManager.getCompanyList();
				for(Company i : companyList) {
					wr.write("<hr>");
					wr.write("Название компании по сбору средств: " + i.getTitle() + "<br>Описание: " + i.getDescription() + "<br>Цена: " + i.getPrice() + "<br>");
				}
				break;
			case "seeActionCompanyList":
				List<Company> actionCompanyList = DataManager.getActionCompanyList();
				for(Company i : actionCompanyList) {
					wr.write("<hr>");
					wr.write("Название компании по сбору средств: " + i.getTitle() + "<br>Описание: " + i.getDescription() + "<br>Цена: " + i.getPrice() + "<br>");
				}
				break;
			case "seeInactionCompanyList":
				List<Company> inactionCompanyList = DataManager.getInactionCompanyList();
				for(Company i : inactionCompanyList) {
					wr.write("<hr>");
					wr.write("Название компании по сбору средств: " + i.getTitle() + "<br>Описание: " + i.getDescription() + "<br>Цена: " + i.getPrice() + "<br>");
				}
				break;
		}
	}
}
