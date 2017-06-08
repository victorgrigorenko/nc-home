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

/**
 * Servlet implementation class Add
 */
@WebServlet("/Add")
public class Add extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// сохраняем задачу в журнал..
		request.setCharacterEncoding("UTF-8");
		Journalable<Task> journal = (Journal) request.getSession().getAttribute("journal"); 		
		
		if(journal != null){
			Date date = null;
			try {
				date = new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("date"));
			} catch (ParseException e) {
				System.out.println(NOT_VERIFY_DATE_MSG);
			}
	
			Task task = journal.createTask(
					request.getParameter("title"), 
					request.getParameter("description"), 
					date); 
			task.setID(journal.getFreeID());
			journal.addTask(task);
		}

		response.sendRedirect(getServletContext().getContextPath()+"/MainServlet");
		//getServletContext().getRequestDispatcher("/MainServlet").forward(request, response);
	}

}
