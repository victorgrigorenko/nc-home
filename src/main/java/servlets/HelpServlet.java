package servlets;

import static constants.ConstantMessage.NEW_LINE;
import static constants.Constants.HELP_FILE;
import static constants.Exception.IO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/HelpServlet")
public class HelpServlet extends HttpServlet {
	
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
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("help", getHelp());
		response.sendRedirect(getServletContext().getContextPath()+"/view/Help.jsp");
	}

}
