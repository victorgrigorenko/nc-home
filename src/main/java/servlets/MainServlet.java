package servlets;

import static constants.ConstantMessage.*;
import static constants.Constants.DATE_FORMAT;
import static constants.Constants.HELP_FILE;
import static constants.Constants.NONE;
import static constants.Exception.IO;
import static constants.Command.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.Command;
import model.Journal;
import model.Journalable;
import model.Task;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet(urlPatterns="/MainServlet", loadOnStartup=0)
public class MainServlet extends HttpServlet {
	private Journalable<Task> journal;


    private void initJournal(){
    	journal = new Journal();
    	// добавим задач для теста
    	journal.addTask(journal.createTask("First Task", "description ...", new Date()));
    	journal.addTask(journal.createTask("Задача №2", "desc desc des", new Date()));
    	journal.addTask(journal.createTask("Задача №3", "test task", new Date()));
    }
    
    public MainServlet() {
    	initJournal();
	}
    
    
    @Override
    public void init() throws ServletException{
//    	Для начальной инициализации сервлета

    }
	private Date setupDate(String dateString) throws ParseException{ 
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		//if (!validateField(dateString,DATE_RG)) notifyObservers(NOT_VERIFY_DATE_MSG+NEW_LINE);

		return format.parse(dateString);
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
				String s = String.valueOf(request.getSession().getAttribute("add"));
				boolean added = (s!=null)?
						Boolean.parseBoolean(s):
						Boolean.parseBoolean(request.getParameter("add"));
					if (added){
						task = (Task) request.getAttribute("task");
						journal.addTask(task);
						//request.getSession().setAttribute("add", false);
				    	request.getSession().removeAttribute("add");
					}
					else{
						getServletContext().getRequestDispatcher("/view/AddTask.jsp").forward(request, response);
					}

				break;
			case DEL:	journal.deleteTask(request.getParameter("del"));
				break;
			case SEARCH: 
						task = journal.searchTask(request.getParameter("search"));
						String path = (task !=null)? "DisplayTask.jsp": "NotFoundTask.jsp";
						request.setAttribute("task", task);
						getServletContext().getRequestDispatcher("/view/"+path).forward(request, response);
				break;
			case EDIT:
					String status = String.valueOf(request.getSession().getAttribute("edit"));
					if ((status!=null)?Boolean.parseBoolean(status):false){
//						journal.searchTaskOnID(request.getAttribute("id"));// тут поиск по ID
//						//journal.replaceTask(title, task);
						status = "false";
					}
					String title = request.getParameter("edit");
					task = journal.searchTask(title);
					request.setAttribute("task", task);
					getServletContext().getRequestDispatcher("/view/EditTask.jsp").forward(request, response);
				break;
			case SHOW_ALL: 
				break;
			case CLEAR_ALL: journal.clearTasks();
				break;
			case RECORD: 
				break;
			case READ: 
				break;
			case HELP: 
				String help = request.getParameter("help");
				if(help.isEmpty()){
					getServletContext().getRequestDispatcher("/HelpServlet").forward(request, response);
				}
				break;
			case STOP: 
				break;
			case OTHER:
			default:
				System.out.println(NONE);
				getServletContext().getRequestDispatcher("/view/MainContainer.jsp").forward(request, response);
		}
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println(request.getSession().getMaxInactiveInterval());

    	String command = "";
    	request.setAttribute("journal", journal);

//    	request.getSession().removeAttribute("add"); 
    	for (Enumeration<String> cmd = request.getSession().getAttributeNames();cmd.hasMoreElements();) {
    		command = cmd.nextElement();
    		System.out.println(command);
    		Command commandOfEnum = Command.valueParse(command);
    		commandParse(commandOfEnum,request,response);
		}
    	for (Enumeration<String> cmd = request.getParameterNames();cmd.hasMoreElements();) {
    		command = cmd.nextElement();
    		System.out.println(command);
    		Command commandOfEnum = Command.valueParse(command);
    		commandParse(commandOfEnum,request,response);
		}

//*******

    	getServletContext().getRequestDispatcher("/view/MainContainer.jsp").forward(request, response);
    	
    	//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
