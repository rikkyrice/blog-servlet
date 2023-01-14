package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import dto.User;

public class UserDAO {
	public static User selectById(String id) {
		User user = null;
		Connection con = null;
		try {
			con = DBConn.getConn();
			String sql = "SELECT * FROM Users WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String email = rs.getString("email");
				String password = rs.getString("password");
				OffsetDateTime createdAt = 
						OffsetDateTime.ofInstant(
								Instant.ofEpochMilli(rs.getTimestamp("created_at").getTime()),
								ZoneId.of("Asia/Tokyo"));
				user = new User(id, email, password, createdAt);
			}
			pstmt.close();
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				DBConn.close(con);
			}
		}
		return user;
	}
}
