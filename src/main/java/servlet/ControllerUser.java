package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import Component.Company;
import Component.User;
import Component.DataManager;

import javax.xml.crypto.Data;

@WebServlet(name = "controller_user", value = "/controller_user")
public class ControllerUser extends HttpServlet {

	Writer wr;


	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		String choose = req.getParameter("choose");
		wr = resp.getWriter();

		switch (choose){
			case "seeCompany":
				List<Company> companyList = DataManager.getCompanyList();
				for(Company i : companyList){
					wr.write("<hr>");
					wr.write("Название компании по сбору средств :" + i.getTitle() + "<br>Описание: " + i.getDescription() + "<br>Цена: " + i.getPrice() + "<br>");
					wr.write("<form action = \"\"" +
							"<input type=\"hidden\" name = \"choose\" value=\"" + i.getId() +"\">" +
							"<input type = \"submit\" value = \"donate\"" +
							"</form>");
					wr.write("<hr>");
				}
				break;

			case "seeHistory":
				break;

			case "deleteAccount":
				break;

			case "changePassword":

				String new_password = req.getParameter("password");

				Cookie[] cookies = req.getCookies();
				int userId = -1;
				if(cookies != null){
					for(Cookie i : cookies){
						if(i.getName().equals("userId")){
							userId = Integer.parseInt(i.getValue());
							break;
						}
					}
				}

				if(userId == -1){
					wr.write("Не удалось получить данные, проверьте авторизацию");
				} else {
					User user = null;
					List<User> userList = DataManager.getUserList();

					for(User i : userList){
						if(i.getId() == userId){
							user = i;
							break;
						}
					}

					user.setPassword(new_password);
					DataManager.updateToDateBase(user, wr);

					wr.write("Элемент успешно обновлен");
					wr.write("<form action = \"http://localhost:8080/Charity-1.0-SNAPSHOT/HTML/menu/User/menu_User.html\">" +
							"<input type = \"submit\" value = \"back\">" +
							"</form>");
				}


		}
	}
}
