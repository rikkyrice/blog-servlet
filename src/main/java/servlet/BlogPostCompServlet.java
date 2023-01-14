package servlet;

import java.io.IOException;
import java.time.OffsetDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BlogDAO;
import dto.Blog;

/**
 * Servlet implementation class BlogPostCompServlet
 */
@WebServlet("/postComplete")
public class BlogPostCompServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogPostCompServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		Blog blog = new Blog(0, title, body, OffsetDateTime.now());
		Blog postedBlog = BlogDAO.post(blog);
		if (postedBlog == null) {
			request.getRequestDispatcher("blogPost.jsp").forward(request, response);
		}
		request.getRequestDispatcher("/").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
