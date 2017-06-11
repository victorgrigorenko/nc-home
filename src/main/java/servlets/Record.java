package servlets;

import static constants.ConstantMessage.NEW_LINE;
import static constants.ConstantMessage.NOT_SUCCESS_RECORD_TASK_MSG;
import static constants.ConstantMessage.NOT_VERIFY_DATE_MSG;
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


@WebServlet("/Record")
public class Record extends HttpServlet {
	private Journalable<Task> journal;
	private XMLJournalible<Task> xml = new XMLJournal();

	private boolean validateField(String field, String pattern){
		Pattern dataRg 	= Pattern.compile(pattern); 
		Matcher matcher	= dataRg.matcher(field);
		return (matcher.matches());
	}

	public String recordJournal(String fileName, HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String message; 
		try {
			if	(validateField(fileName,FILE_RG)){
				 xml.recordJournal(journal, getServletContext().getRealPath("storage/"+fileName+".xml"));
				message = SUCCESS_RECORD_TASK_MSG;
			}
			else {
				message = NOT_SUCCESS_RECORD_TASK_MSG;
			}
			
		} catch (FileNotFoundException e) {
			message = FILE_NOT_FOUND.toString();

		} catch (IOException e) {
			message = IO.toString();

		} catch (JAXBException e) {
			message = JAXB_RECORD.toString();
		}
		return message;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		journal = (Journal) request.getSession().getAttribute("journal"); 
		String message;

		message = recordJournal(request.getParameter("fileName"), request, response);
		request.setAttribute("errorMsg", message);
		if(SUCCESS_RECORD_TASK_MSG.equals(message))
			getServletContext().getRequestDispatcher("/MainServlet").forward(request, response);
		else
			getServletContext().getRequestDispatcher("/view/RecordJournal.jsp").forward(request, response);
	}

}
