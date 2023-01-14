package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import dto.Blog;

public class BlogDAO {
	
	public static Blog selectById(int id) {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
				"JDBCドライバのロードに失敗しました");
		}

		Connection con = null;
		Blog blog = null;
		try {
			con = DriverManager.getConnection(
					"jdbc:h2:tcp://localhost:9092/txx-db", "sa", "sa");
			String sql = "SELECT * FROM Blogs WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String title = rs.getString("title");	
				String body = rs.getString("body");
				OffsetDateTime postedAt = 
						OffsetDateTime.ofInstant(
								Instant.ofEpochMilli(rs.getTimestamp("posted_at").getTime()),
								ZoneId.of("Asia/Tokyo"));
				blog = new Blog(id, title, body, postedAt);
			}
			pstmt.close();
			return blog;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return blog;
	}
	
	public static List<Blog> query(String keyword) {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
				"JDBCドライバのロードに失敗しました");
		}

		Connection con = null;
		List<Blog> blogs = new ArrayList<>();
		try {
			con = DriverManager.getConnection(
					"jdbc:h2:tcp://localhost:9092/txx-db", "sa", "sa");
			String sql = "SELECT * FROM Blogs ";
			if (keyword != "") {
				sql += "WHERE title like ? or body like ? ";
			}
			sql += "order by posted_at desc";
			PreparedStatement pstmt = con.prepareStatement(sql);
			if (keyword != "") {
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");
			}
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");	
				String body = rs.getString("body");
				OffsetDateTime postedAt = 
						OffsetDateTime.ofInstant(
								Instant.ofEpochMilli(rs.getTimestamp("posted_at").getTime()),
								ZoneId.of("Asia/Tokyo"));
				Blog blog = new Blog(id, title, body, postedAt);
				blogs.add(blog);
			}
			pstmt.close();
			return blogs;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return blogs;
	}
	
	public static List<Blog> selectAll() {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
				"JDBCドライバのロードに失敗しました");
		}

		Connection con = null;
		List<Blog> blogs = new ArrayList<>();
		try {
			con = DriverManager.getConnection(
					"jdbc:h2:tcp://localhost:9092/txx-db", "sa", "sa");
			String sql = "SELECT * FROM Blogs order by posted_at desc";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");	
				String body = rs.getString("body");
				OffsetDateTime postedAt = 
						OffsetDateTime.ofInstant(
								Instant.ofEpochMilli(rs.getTimestamp("posted_at").getTime()),
								ZoneId.of("Asia/Tokyo"));
				Blog blog = new Blog(id, title, body, postedAt);
				blogs.add(blog);
			}
			pstmt.close();
			return blogs;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return blogs;
	}
	
	public static Blog post(Blog blog) {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
				"JDBCドライバのロードに失敗しました");
		}

		Connection con = null;
		try {
			con = DriverManager.getConnection(
					"jdbc:h2:tcp://localhost:9092/txx-db", "sa", "sa");
			String sql = "INSERT INTO blogs(title, body, posted_at) VALUES(?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, blog.getTitle());
			pstmt.setString(2, blog.getBody());
			pstmt.setTimestamp(3, Timestamp.valueOf(blog.getPostedAt().atZoneSameInstant(ZoneId.of("Asia/Tokyo")).toLocalDateTime()));
			int num = pstmt.executeUpdate();
			if (num == 0) {
				return null;
			}
			return blog;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
 	}
	
	public static Blog update(Blog blog) {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
				"JDBCドライバのロードに失敗しました");
		}

		Connection con = null;
		try {
			con = DriverManager.getConnection(
					"jdbc:h2:tcp://localhost:9092/txx-db", "sa", "sa");
			String sql = "UPDATE blogs SET "
					+ "title = ?,"
					+ "body = ?,"
					+ "posted_at = ? "
					+ "WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, blog.getTitle());
			pstmt.setString(2, blog.getBody());
			pstmt.setTimestamp(3, Timestamp.valueOf(blog.getPostedAt().atZoneSameInstant(ZoneId.of("Asia/Tokyo")).toLocalDateTime()));
			pstmt.setInt(4, blog.getId());
			int num = pstmt.executeUpdate();
			if (num == 0) {
				return null;
			}
			return blog;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
 	}
	
	public static void delete(int id) {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
				"JDBCドライバのロードに失敗しました");
		}

		Connection con = null;
		try {
			con = DriverManager.getConnection(
					"jdbc:h2:tcp://localhost:9092/txx-db", "sa", "sa");
			String sql = "DELETE FROM blogs WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			int num = pstmt.executeUpdate();
			if (num == 0) {
				throw new IllegalArgumentException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
