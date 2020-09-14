package org.prac.MessengerAPI.message;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.prac.MessengerAPI.customannotations.profilepresent.ProfilePresent;

@Entity
public class Message {

	@Id
	@GeneratedValue
	private long id;
	private String message;
	@ProfilePresent
	private String author;
	@CreationTimestamp
	private Timestamp created;

	@Override
	public String toString() {
		return "Message [id=" + id + ", message=" + message + ", created=" + created + ", author=" + author + "]";
	}

	public Message() {
		super();
	}

	public Message(String message, String author) {
		super();
		this.message = message;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public static class MessageBuilder {
		private String message;
		private String author;

		public MessageBuilder() {
			super();
		}

		public MessageBuilder setMessage(String message) {
			this.message = message;
			return this;
		}

		public MessageBuilder setAuthor(String author) {
			this.author = author;
			return this;
		}

		public Message build() {
			return new Message(message, author);
		}
	}
}
