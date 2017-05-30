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

import model.Task;
import model.Taskable;

/**
 * Servlet implementation class Add
 */
@WebServlet("/Add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add() {
        super();
        // TODO Auto-generated constructor stub
    }


	private Date setupDate(String dateString) throws ParseException{ 
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		//if (!validateField(dateString,DATE_RG)) notifyObservers(NOT_VERIFY_DATE_MSG+NEW_LINE);

		return format.parse(dateString);
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// сохраняем задачу в журнал..
		request.setCharacterEncoding("UTF-8");
		String title = (request.getParameter("title")!=null&&!request.getParameter("title").isEmpty())?
				request.getParameter("title"):
				"default title";
		String description = (request.getParameter("description")!=null&&!request.getParameter("description").isEmpty())?
				request.getParameter("description"):
				"default description";
		Date date = new Date();
		try {
			date = setupDate(request.getParameter("date"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		request.getParameter("add");
		Taskable task = new Task(title, description, date);
		request.setAttribute("task", task);

		request.getSession().setAttribute("add", true);
		
		getServletContext().getRequestDispatcher("/MainServlet").forward(request, response);
		doGet(request, response);
	}

}
