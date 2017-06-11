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


@WebServlet("/Search")
public class Search extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Journalable<Task> journal = (Journal) request.getSession().getAttribute("journal");
		if(journal != null){
			Task task = journal.searchTask(request.getParameter("task"));
			String path = (task !=null)? "DisplayTask.jsp": "NotFoundTask.jsp";
			request.setAttribute("task", task);
			getServletContext().getRequestDispatcher("/view/"+path).forward(request, response);
		}
		System.out.println("Журнал пуст");
		getServletContext().getRequestDispatcher("/view/NotFoundTask.jsp").forward(request, response);
	}

}
