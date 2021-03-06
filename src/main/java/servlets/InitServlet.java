package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Journal;
import model.Journalable;
import model.Task;


@WebServlet(urlPatterns="/InitServlet",loadOnStartup=0)
public class InitServlet extends HttpServlet {	

        
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Journalable<Task> journal = (Journal) request.getSession().getAttribute("journal");
		if(journal == null)
			journal = new Journal();
		request.getSession().setAttribute("journal", journal);
		getServletContext().getRequestDispatcher("/view/ReadJournal.jsp").forward(request, response);
		
	}

}
