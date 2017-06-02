package servlets;

import static constants.ConstantMessage.NEW_LINE;
import static constants.ConstantMessage.NOT_SUCCESS_RECORD_TASK_MSG;
import static constants.ConstantMessage.READ_MSG;
import static constants.ConstantMessage.RECORD_MSG;
import static constants.ConstantMessage.SUCCESS_READ_TASK_MSG;
import static constants.ConstantMessage.SUCCESS_RECORD_TASK_MSG;
import static constants.Constants.HELP_FILE;
import static constants.Constants.NONE;
import static constants.Exception.FILE_NOT_FOUND;
import static constants.Exception.IO;
import static constants.Exception.JAXB_READ;
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
@WebServlet("/Read")
public class Read extends HttpServlet {
	private Journalable<Task> journal;
	private XMLJournalible<Task> xml = new XMLJournal();

	public String readJournal(String fileName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message;
		try {
			System.out.println(READ_MSG);
			journal.replaceTasks(xml.readJournal(journal,getServletContext().getRealPath("storage/"+fileName+".xml")).getTasks());
			message = SUCCESS_READ_TASK_MSG;
			
		} catch (JAXBException e) {
			getServletContext().getRequestDispatcher("/view/ReadJournal.jsp").forward(request, response);
			message = JAXB_READ.toString();
		}
		return message+NEW_LINE;
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		journal = new Journal();
		String message = readJournal(request.getParameter("fileName"), request, response);
		System.out.println(message);
		request.setAttribute("journal", journal);
//		request.setAttribute("command", "read");
		getServletContext().getRequestDispatcher("/MainServlet").forward(request, response);
	}

}
