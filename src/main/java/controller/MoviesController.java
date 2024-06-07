package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.MoviesDAO;
import dto.MoviesDTO;

@WebServlet("/MoviesController")
public class MoviesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getRequestURI();
		MoviesDAO dao = MoviesDAO.getInstance();
		
		String title = request.getParameter("title");
		String genre = request.getParameter("genre");
		try {
			if(cmd.equals("/input.movies")) {
				dao.insert(new MoviesDTO(0,title,genre,null));
				response.sendRedirect("/output.jsp");
			}else if(cmd.equals("/output.movies")) {
				List<MoviesDTO>list = dao.selectAll();
				request.setAttribute("list", list);
				request.getRequestDispatcher("/output.jsp").forward(request, response);
			}else if(cmd.equals("/update.movies")) {
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
