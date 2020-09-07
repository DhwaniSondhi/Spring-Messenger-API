package org.prac.MessengerAPI.Comment;

import java.util.List;

import org.prac.MessengerAPI.Message.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message/{messageId}/comments")
public class CommentController {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private MessageDao messageDao;

	@GetMapping("")
	public List<Comment> getCommments(@PathVariable long messageId) {
		messageDao.getMessageById(messageId);
		return commentDao.getComments(messageId);
	}

	@GetMapping("/{commentId}")
	public Comment getCommment(@PathVariable long messageId, @PathVariable long commentId) {
		messageDao.getMessageById(messageId);
		return commentDao.getComment(messageId, commentId);
	}
	
	@PostMapping("")
	public Comment addComment(@PathVariable long messageId, @RequestBody Comment comment) {
		messageDao.getMessageById(messageId);
		return commentDao.addComment(messageId, comment);
	}
	
	@PutMapping("/{commentId}")
	public Comment updateComment(@PathVariable long messageId, @PathVariable long commentId,
			@RequestBody Comment comment) {
		messageDao.getMessageById(messageId);
		return commentDao.updateComment(messageId, commentId, comment);
	}
	
	@DeleteMapping("/{commentId}")
	public void deleteComment(@PathVariable long messageId, @PathVariable long commentId) {
		messageDao.getMessageById(messageId);
		commentDao.deleteComment(messageId, commentId);
	}
}
