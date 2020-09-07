package org.prac.MessengerAPI.Comment;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Comment {

	@Id
	@GeneratedValue
	private long id;
	private String commentString;
	private long messageId;
	@CreationTimestamp
	private Timestamp created;

	@Override
	public String toString() {
		return "Comment [id=" + id + ", commentString=" + commentString + ", created=" + created + "]";
	}

	public Comment() {
		super();
	}

	public Comment(String commentString, long messageId) {
		super();
		this.commentString = commentString;
		this.messageId = messageId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCommentString() {
		return commentString;
	}

	public void setCommentString(String commentString) {
		this.commentString = commentString;
	}

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public static class CommentBuilder {

		private String commentString;
		private long messageId;

		public CommentBuilder setCommentString(String commentString) {
			this.commentString = commentString;
			return this;
		}

		public void setMessageId(long messageId) {
			this.messageId = messageId;
		}

		public Comment build() {
			return new Comment(commentString, messageId);
		}

	}
}
