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
 * Servlet implementation class TestServlet
 */
@WebServlet("/ClearAll")
public class ClearAll extends HttpServlet {
	private Journalable<Task> journal;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		journal = (Journal) request.getSession().getAttribute("journal"); 
		if(journal != null){
			journal.clearTasks();
			request.getSession().setAttribute("journal", journal);
		}
		response.sendRedirect(getServletContext().getContextPath()+"/MainServlet");
//		getServletContext().getRequestDispatcher("/MainServlet").forward(request, response);
	}

}
