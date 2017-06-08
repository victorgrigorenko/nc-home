package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Journal;
import model.Journalable;
import model.Task;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")//urlPatterns="/MainServlet", loadOnStartup=0)
public class MainServlet extends HttpServlet {
	private Journalable<Task> journal = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request,response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getSession().getMaxInactiveInterval());
		request.setCharacterEncoding("UTF-8");

		journal = (Journal) request.getSession().getAttribute("journal");

//    	request.getSession().setAttribute("journal", journal);
    	
    	response.sendRedirect(getServletContext().getContextPath()+"/view/MainContainer.jsp");
    	//getServletContext().getRequestDispatcher("/view/MainContainer.jsp").forward(request, response);
    	return;
	}

}
