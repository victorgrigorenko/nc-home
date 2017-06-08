package servlets;

import static constants.ConstantMessage.DATE;
import static constants.ConstantMessage.NEW_LINE;
import static constants.ConstantMessage.NOT_VERIFY_DATE_MSG;
import static constants.Constants.DATE_FORMAT;
import static constants.Patterns.DATE_RG;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.Request;

import model.Journalable;
import model.Journal;
import model.Task;
import model.Taskable;

/**
 * Servlet implementation class Add
 */
@WebServlet("/EditSubTask")
public class EditSubTask extends HttpServlet {



	private Date setupDate(String dateString) throws ParseException{ 
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		//if (!validateField(dateString,DATE_RG)) notifyObservers(NOT_VERIFY_DATE_MSG+NEW_LINE);

		return format.parse(dateString);
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Journalable<Task> journal = (Journal) request.getSession().getAttribute("journal");
    	if (journal != null){
	    	Task task = journal.searchTask(request.getParameter("ownerTitle"));
			Date date = null;			
	    	try {
				date = new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("date"));
			} catch (ParseException e) {
				System.out.println(NOT_VERIFY_DATE_MSG);
			}
	    	
			task.editSubTask( 
					Integer.parseInt(request.getParameter("id")), 
					request.getParameter("title"),
					request.getParameter("description"), 
					date);
	
			request.getSession().setAttribute("journal", journal);
			response.sendRedirect(getServletContext().getContextPath()+"/MainServlet");
    	} else
			getServletContext().getRequestDispatcher("/view/NotFoundTask.jsp").forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		request.setAttribute("ownerTitle", request.getParameter("ownerTitle"));
		request.setAttribute("owner", request.getParameter("owner"));
		request.setAttribute("id", request.getParameter("id"));
		request.setAttribute("title", request.getParameter("title"));
		request.setAttribute("description", request.getParameter("description"));
		request.setAttribute("date", request.getParameter("date"));
		
		getServletContext().getRequestDispatcher("/view/EditSubTask.jsp").forward(request, response);
	}

}
