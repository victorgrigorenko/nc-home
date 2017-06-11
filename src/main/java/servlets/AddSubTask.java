package servlets;

import static constants.ConstantMessage.NOT_VERIFY_DATE_MSG;
import static constants.Constants.DATE_FORMAT;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Journal;
import model.Journalable;
import model.Task;

@WebServlet("/AddSubTask")
public class AddSubTask extends HttpServlet {
	Journalable<Task> journal;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		journal = (Journal) request.getSession().getAttribute("journal");
		if(journal!=null){
	    	Task task = (Task) request.getSession().getAttribute("task");
			Date date = null;
	    	try {
				date = new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("date"));
				
			} catch (ParseException e) {

				request.setAttribute("errorMsg", NOT_VERIFY_DATE_MSG);
			}
	    	Task subTask = task.createSubTask(
				request.getParameter("title"),
				request.getParameter("description"), 
				date);
			if (subTask == null){
				getServletContext().getRequestDispatcher("/view/ErrorPage.jsp").forward(request, response);
			}
			task.addSubTask(subTask);
		
			request.getSession().setAttribute("journal", journal);
			response.sendRedirect(getServletContext().getContextPath()+"/MainServlet");
		} else
			getServletContext().getRequestDispatcher("/view/NotFoundTask.jsp").forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		journal = (Journal) request.getSession().getAttribute("journal");

		if(journal!=null){
			request.setAttribute("id", request.getParameter("id"));
			request.setAttribute("title", request.getParameter("title"));
			
	    	Task task = journal.searchTask(request.getParameter("title"));
			request.getSession().setAttribute("task", task);
			
			getServletContext().getRequestDispatcher("/view/AddSubTask.jsp").forward(request, response);
		} else
			getServletContext().getRequestDispatcher("/view/ErrorPage.jsp").forward(request, response);		
	}

}
