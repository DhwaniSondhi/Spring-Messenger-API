package org.prac.MessengerAPI.message;

import java.util.List;

import javax.transaction.Transactional;

import org.prac.MessengerAPI.comment.Comment;
import org.prac.MessengerAPI.comment.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

	@Autowired
	MessageDao messageDao;

	@Autowired
	CommentDao commentDao;

	public List<Message> getMessages() {
		return messageDao.getMessages();
	}

	public Message getMessageById(long messageId) {
		return messageDao.getMessageById(messageId);
	}

	public List<Message> getMessagesByAuthor(String author) {
		return messageDao.getMessagesByAuthor(author);
	}

	public Message addMessage(Message message) {
		return messageDao.addMessage(message);
	}

	public Message updateMessage(long messageId, Message message) {
		return messageDao.updateMessage(messageId, message);
	}

	/*
	 * Delete comments related to the message
	 */
	@Transactional
	public void deleteMessage(long messageId) {
		List<Comment> comments = commentDao.getCommentsByMessageId(messageId);
		messageDao.deleteMessage(messageId);

	}
}
