package servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xml.internal.security.Init;

import model.Journal;
import model.Journalable;
import model.Task;

/**
 * Servlet implementation class InitServlet
 */
@WebServlet(urlPatterns="/InitServlet",loadOnStartup=1)
public class InitServlet extends HttpServlet {
	
	
    private void initJournal(Journal journal){
    	// добавим задач для теста
    	journal.addTask(journal.createTask("First Task", "description ...", new Date()));
    	journal.addTask(journal.createTask("Задача №2", "desc desc des", new Date()));
    	journal.addTask(journal.createTask("Задача №3", "test task", new Date()));
    	journal.addTask(journal.createTask("Задача №4", "Lorem ipsum dolor sit amet, "
    			+ "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
    			+ "labore et dolore magna aliqua. ", new Date()));
    }

        
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Journalable<Task> journal = (Journal) request.getSession().getAttribute("journal");
		if(journal==null||journal.getTasks().isEmpty()){  //! тут один момент
			journal = new Journal();
			initJournal((Journal) journal);
			request.setAttribute("journal", journal);
//			request.getSession().setAttribute("journal", journal);
		}
		
		getServletContext().getRequestDispatcher("/MainServlet").forward(request, response);
	}

}
