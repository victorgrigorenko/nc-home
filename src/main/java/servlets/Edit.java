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

import model.Journal;
import model.Journalable;
import model.Task;
import model.Taskable;

/**
 * Servlet implementation class Add
 */
@WebServlet("/Edit")
public class Edit extends HttpServlet {

    // После редактирования
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date date = null;
    	try {
			date = new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("date"));
			
		} catch (ParseException e) {
			System.out.println(NOT_VERIFY_DATE_MSG);
		}

    	Journal journal = (Journal) request.getSession().getAttribute("journal");
    	journal.editTask(
				request.getParameter("oldTitle"), 
				request.getParameter("title"),
				request.getParameter("description"), 
				date);
   
    	request.getSession().setAttribute("journal", journal);
    	
    	response.sendRedirect(getServletContext().getContextPath()+"/MainServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    // До редактирования
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		request.setAttribute("id", request.getParameter("id"));
		request.setAttribute("title", request.getParameter("title"));
		request.setAttribute("description", request.getParameter("description"));
		request.setAttribute("date", request.getParameter("date"));
		
		getServletContext().getRequestDispatcher("/view/EditTask.jsp").forward(request, response);
	}

}
