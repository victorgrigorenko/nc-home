package servlets;

import static constants.ConstantMessage.NEW_LINE;
import static constants.ConstantMessage.NOT_SUCCESS_RECORD_TASK_MSG;
import static constants.ConstantMessage.RECORD_MSG;
import static constants.ConstantMessage.SUCCESS_RECORD_TASK_MSG;
import static constants.Constants.HELP_FILE;
import static constants.Constants.NONE;
import static constants.Exception.FILE_NOT_FOUND;
import static constants.Exception.IO;
import static constants.Exception.JAXB_RECORD;
import static constants.Patterns.FILE_RG;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import model.Journal;
import model.Journalable;
import model.Task;
import model.XMLJournal;
import model.XMLJournalible;


/**
 * Servlet implementation class TestServlet
 */
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
