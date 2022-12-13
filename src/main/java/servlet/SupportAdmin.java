package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;

@WebServlet(name = "delete", value = "/delete")
public class SupportAdmin extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String chooser = req.getParameter("choose");

		switch (chooser){
			case "User":
				String user_id = req.getParameter("param");

//				DataManager.deleteById(User.class, Integer.parseInt(user_id));
				break;
			case "Organization":
				String organization_id = req.getParameter("param");

//				DataManager.deleteById(Organization.class, Integer.parseInt(organization_id));
				break;
			case "Company":
				String company_id = req.getParameter("param");

//				DataManager.deleteById(Company.class, Integer.parseInt(company_id));
				break;
		}
		Writer wr = resp.getWriter();
		wr.write("Выполнено!");

	}
}
