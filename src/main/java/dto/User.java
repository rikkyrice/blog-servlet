package dto;

import java.time.OffsetDateTime;

public class User {
	private String id;
	private String email;
	private String password;
	private OffsetDateTime createdAt;
	
	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public User(String id, String email, String password, OffsetDateTime createdAt) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.createdAt = createdAt;
	}
	
	public boolean isLoginUser(String id) {
		return this.id.equals(id);
	}
}
