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


@WebServlet("/DeleteTask")
public class DeleteTask extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 

		Journalable<Task> journal = (Journal) request.getSession().getAttribute("journal"); 
		if(journal!=null){
			Task task = journal.searchTask(request.getParameter("task"));
			task.clearSubTasks();
			journal.deleteTask(request.getParameter("task"));
			request.getSession().setAttribute("journal", journal);
			response.sendRedirect(getServletContext().getContextPath()+"/MainServlet");
		} else
			getServletContext().getRequestDispatcher("/view/NotFoundTask.jsp").forward(request, response);
	}

}
