package dto;

import java.time.OffsetDateTime;

public class Blog {
	private int id;
	private String userId;
	private String title;
	private String body;
	private OffsetDateTime postedAt;
	
	public Blog(int id, String userId, String title, String body, OffsetDateTime postedAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.body = body;
		this.postedAt = postedAt;
	}
	
	public int getId() {
		return id;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	public OffsetDateTime getPostedAt() {
		return postedAt;
	}
}
