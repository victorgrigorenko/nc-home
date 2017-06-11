package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ErrorHandler")
public class ErrorHandler extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
      int code = (int) request.getAttribute("javax.servlet.error.status_code");
      
      request.setAttribute("throwable", throwable);
      request.setAttribute("code", code);

		getServletContext().getRequestDispatcher("/view/ErrorPage.jsp").forward(request, response);
	}

}
