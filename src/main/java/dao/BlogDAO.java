package dao;

import java.sql.Connection;
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
		Connection con = null;
		Blog blog = null;
		try {
			con = DBConn.getConn();
			String sql = "SELECT * FROM Blogs WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String userId = rs.getString("user_id");
				String title = rs.getString("title");	
				String body = rs.getString("body");
				OffsetDateTime postedAt = 
						OffsetDateTime.ofInstant(
								Instant.ofEpochMilli(rs.getTimestamp("posted_at").getTime()),
								ZoneId.of("Asia/Tokyo"));
				blog = new Blog(id, userId, title, body, postedAt);
			}
			pstmt.close();
			return blog;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				DBConn.close(con);
			}
		}
		return blog;
	}
	
	public static List<Blog> query(String keyword) {
		Connection con = null;
		List<Blog> blogs = new ArrayList<>();
		try {
			con = DBConn.getConn();
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
				String userId = rs.getString("user_id");
				String title = rs.getString("title");	
				String body = rs.getString("body");
				OffsetDateTime postedAt = 
						OffsetDateTime.ofInstant(
								Instant.ofEpochMilli(rs.getTimestamp("posted_at").getTime()),
								ZoneId.of("Asia/Tokyo"));
				Blog blog = new Blog(id, userId, title, body, postedAt);
				blogs.add(blog);
			}
			pstmt.close();
			return blogs;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				DBConn.close(con);
			}
		}
		return blogs;
	}
	
	public static List<Blog> selectAll() {
		Connection con = null;
		List<Blog> blogs = new ArrayList<>();
		try {
			con = DBConn.getConn();
			String sql = "SELECT * FROM Blogs order by posted_at desc";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("user_id");
				String title = rs.getString("title");	
				String body = rs.getString("body");
				OffsetDateTime postedAt = 
						OffsetDateTime.ofInstant(
								Instant.ofEpochMilli(rs.getTimestamp("posted_at").getTime()),
								ZoneId.of("Asia/Tokyo"));
				Blog blog = new Blog(id, userId, title, body, postedAt);
				blogs.add(blog);
			}
			pstmt.close();
			return blogs;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				DBConn.close(con);
			}
		}
		return blogs;
	}
	
	public static List<Blog> selectAllByUserId(String uId) {
		Connection con = null;
		List<Blog> blogs = new ArrayList<>();
		try {
			con = DBConn.getConn();
			String sql = "SELECT * FROM Blogs where user_id = ? order by posted_at desc";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uId);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("user_id");
				String title = rs.getString("title");	
				String body = rs.getString("body");
				OffsetDateTime postedAt = 
						OffsetDateTime.ofInstant(
								Instant.ofEpochMilli(rs.getTimestamp("posted_at").getTime()),
								ZoneId.of("Asia/Tokyo"));
				Blog blog = new Blog(id, userId, title, body, postedAt);
				blogs.add(blog);
			}
			pstmt.close();
			return blogs;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				DBConn.close(con);
			}
		}
		return blogs;
	}
	
	public static Blog post(Blog blog) {
		Connection con = null;
		try {
			con = DBConn.getConn();
			String sql = "INSERT INTO blogs(user_id, title, body, posted_at) VALUES(?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, blog.getUserId());
			pstmt.setString(2, blog.getTitle());
			pstmt.setString(3, blog.getBody());
			pstmt.setTimestamp(4, Timestamp.valueOf(blog.getPostedAt().atZoneSameInstant(ZoneId.of("Asia/Tokyo")).toLocalDateTime()));
			int num = pstmt.executeUpdate();
			if (num == 0) {
				return null;
			}
			return blog;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				DBConn.close(con);
			}
		}
		return null;
 	}
	
	public static Blog update(Blog blog) {
		Connection con = null;
		try {
			con = DBConn.getConn();
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
			if (con != null) {
				DBConn.close(con);
			}
		}
		return null;
 	}
	
	public static void delete(int id) {
		Connection con = null;
		try {
			con = DBConn.getConn();
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
			if (con != null) {
				DBConn.close(con);
			}
		}
	}
}
