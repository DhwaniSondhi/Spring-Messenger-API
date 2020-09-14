package org.prac.MessengerAPI.comment;

import java.util.List;

import javax.validation.Valid;

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
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentDao commentDao;

	@GetMapping
	public List<Comment> getCommments() {
		return commentDao.getComments();
	}

	@GetMapping("/{commentId}")
	public Comment getCommentById(@PathVariable long commentId) {
		return commentDao.getCommentById(commentId);
	}

	@GetMapping("/message/{messageId}")
	public List<Comment> getCommentsByMessageId(@PathVariable long messageId) {
		return commentDao.getCommentsByMessageId(messageId);
	}
	
	@PostMapping
	public Comment addComment(@RequestBody @Valid Comment comment) {
		return commentDao.addComment(comment);
	}
	
	@PutMapping("/{commentId}")
	public Comment updateComment(@PathVariable long commentId, @RequestBody @Valid Comment comment) {
		return commentDao.updateComment(commentId, comment);
	}
	
	@DeleteMapping("/{commentId}")
	public void deleteComment(@PathVariable long commentId) {
		commentDao.deleteComment(commentId);
	}
}
