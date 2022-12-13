package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

@WebServlet(name = "organization_control", value = "/organization_control")
public class ControllerOrganization extends HttpServlet {

	Writer wr;

	public static void seeCompany(HttpServletRequest req, HttpServletResponse resp){

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		String choose = req.getParameter("choose");
		wr = resp.getWriter();

		switch (choose){
			case "seeCompany":
				break;
			case "addCompany":
				break;
			case "deleteCompany":
				break;
		}

	}
}
