package servlet;

import java.io.IOException;
import java.time.OffsetDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BlogDAO;
import dto.Blog;
import dto.User;

/**
 * Servlet implementation class BlogUpdateCompServlet
 */
@WebServlet("/updateComplete")
public class BlogUpdateCompServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogUpdateCompServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		User loginUser = (User) session.getAttribute("loginUser");
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		OffsetDateTime postedAt = OffsetDateTime.parse(request.getParameter("postedAt"));
		Blog blog = new Blog(id, loginUser.getId(), title, body, postedAt);
		Blog updatedBlog = BlogDAO.update(blog);
		if (updatedBlog == null) {
			request.setAttribute("blog", blog);
			request.getRequestDispatcher("blogUpdate.jsp").forward(request, response);
		}
		request.setAttribute("blog", updatedBlog);
		request.getRequestDispatcher("post/" + id).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
