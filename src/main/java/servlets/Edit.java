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
@WebServlet("/Edit")
public class Edit extends HttpServlet {

	public Edit() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
//		Task task = (Task) request.getAttribute("task");
		String id = request.getParameter("id");
//		String command = request.getParameter("command");

		request.setAttribute("id", id);
		request.setAttribute("title", request.getParameter("title"));
		request.setAttribute("description", request.getParameter("description"));
		request.setAttribute("date", request.getParameter("date"));
//		request.setAttribute("command", command);
//		request.setAttribute("task", task);
		
		getServletContext().getRequestDispatcher("/view/EditTask.jsp").forward(request, response);
	}

}
