package org.prac.MessengerAPI.Message;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message {

	@Id
	@GeneratedValue
	private long id;
	private String message;
	private Date created;
	private String author;

	@Override
	public String toString() {
		return "Message [id=" + id + ", message=" + message + ", created=" + created + ", author=" + author + "]";
	}

	public Message() {
		super();
	}

	public Message(String message, Date created, String author) {
		super();
		this.message = message;
		this.created = created;
		this.author = author;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public static class MessageBuilder {
		private String message;
		private Date created;
		private String author;

		public MessageBuilder() {
			super();
		}

		public MessageBuilder setMessage(String message) {
			this.message = message;
			return this;
		}

		public MessageBuilder setCreated(Date created) {
			this.created = created;
			return this;
		}

		public MessageBuilder setAuthor(String author) {
			this.author = author;
			return this;
		}

		public Message build() {
			return new Message(message, created, author);
		}
	}
}
