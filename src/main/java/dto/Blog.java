package dto;

import java.time.OffsetDateTime;

public class Blog {
	private int id;
	private String title;
	private String body;
	private OffsetDateTime postedAt;
	
	public Blog(int id, String title, String body, OffsetDateTime postedAt) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.postedAt = postedAt;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public OffsetDateTime getPostedAt() {
		return postedAt;
	}

	public void setPostedAt(OffsetDateTime postedAt) {
		this.postedAt = postedAt;
	}
}
