package servlets;

import static constants.ConstantMessage.*;
import static constants.Constants.DATE_FORMAT;
import static constants.Constants.HELP_FILE;
import static constants.Constants.NONE;
import static constants.Exception.IO;
import static constants.Patterns.FILE_RG;
import static constants.Command.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.Command;
import model.Journal;
import model.Journalable;
import model.Task;
import model.XMLJournal;
import model.XMLJournalible;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")//urlPatterns="/MainServlet", loadOnStartup=0)
public class MainServlet extends HttpServlet {
	private Journalable<Task> journal;
	private XMLJournalible<Task> xml = new XMLJournal();
    
    public MainServlet() {
    	journal = null;
//    	(Journalable<Task>) request.getSession().getAttribute("journal");
//    	System.out.println((journal!=null)?"Успех":"Неудача");
	}
    
    
    @Override
    public void init() throws ServletException{
//    	Для начальной инициализации сервлета
    	journal = null;//new Journal();
    	//здесь можно было бы добавить первоначальную загрузку заданий
    }
    
    
	public String getHelp() {
		StringBuilder sb = new StringBuilder();
		try (
				BufferedReader reader = new BufferedReader(new FileReader(getServletContext().getRealPath(HELP_FILE)))
			){
				String s;
			do {
				s = reader.readLine();
				if (s == null)
					break;
				sb.append(s+NEW_LINE);

			} while (true);

		} catch (FileNotFoundException e) {
			sb = new StringBuilder(e.getMessage());//FILE_NOT_FOUND.toString());

		} catch (IOException e) {
			sb = new StringBuilder(IO.toString());
		}
		
		return sb.toString()+NEW_LINE;
	}

	public void commandParse(Command command, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Task task; // tmp task
		System.out.println(command.toString());
		
		switch (command) {
			case ADD: 

				task = (Task) request.getAttribute("task");
				if(task!=null){
					journal.addTask(task);
				}

				break;
				
			case DEL: journal.deleteTask(request.getParameter("task"));
				break;
				
			case SEARCH: 
						task = journal.searchTask(request.getParameter("task"));
						String path = (task !=null)? "DisplayTask.jsp": "NotFoundTask.jsp";
						request.setAttribute("task", task);
						getServletContext().getRequestDispatcher("/view/"+path).forward(request, response);
				break;
			case EDIT:
					Date date = null;
					try {
						date = new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("date"));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					journal.editTask(
							request.getParameter("oldTitle"), 
							request.getParameter("title"),
							request.getParameter("description"), 
							date);
				break;
			case CLEAR_ALL: journal.clearTasks();
				break;
			case RECORD: 

//				if	(validateField(fileName,FILE_RG)){
//					 xml.recordJournal(journal, fileName);
//					message = SUCCESS_RECORD_TASK_MSG;
//				}
//				else
//					message = NOT_SUCCESS_RECORD_TASK_MSG;
				break;
			case READ: 
					journal = (Journalable<Task>) request.getAttribute("journal");
					request.removeAttribute("journal");
				break;
			case HELP: 
				getServletContext().getRequestDispatcher("/HelpServlet").forward(request, response);
				break;
			case OTHER:
			default:
				System.out.println(NONE);
		}
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println(request.getSession().getMaxInactiveInterval());

    	String command = "";
    	
    	if(journal == null){
    		journal = (Journal) request.getAttribute("journal");
//    		journal = (Journal) request.getSession().getAttribute("journal");
//    		request.getSession().removeAttribute("journal"); // если оставляем, то траблосы: Cannot forward after response has been committed
    	}

		command = request.getParameter("command");
		System.out.println(command);
		Command cmd = Command.valueParse(command);
		commandParse(cmd,request,response);

//*******
    	//request.getSession().setAttribute("journal", journal);
    	request.setAttribute("journal", journal);
    	
    	getServletContext().getRequestDispatcher("/view/MainContainer.jsp").forward(request, response);
    	
    	//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
