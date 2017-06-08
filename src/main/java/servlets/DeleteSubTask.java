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
@WebServlet("/DeleteSubTask")
public class DeleteSubTask extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Journalable<Task> journal = (Journal) request.getSession().getAttribute("journal"); /// ! роверять наличие журнала в сессии перед записью!!!
		if(journal != null){
			Task task = journal.getTask(Integer.parseInt(request.getParameter("owner")));
			task.deleteSubTask(Integer.parseInt(request.getParameter("id")));
			
			request.getSession().setAttribute("journal", journal);
			response.sendRedirect(getServletContext().getContextPath()+"/MainServlet");
		} else
			getServletContext().getRequestDispatcher("/view/NotFoundTask.jsp").forward(request, response);		
	}

}
