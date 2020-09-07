package org.prac.MessengerAPI.Message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

	@Autowired
	MessageDao messageDao;

	@GetMapping("/messages")
	public List<Message> getMessages() {
		return messageDao.getMessages();
	}

	@GetMapping("/messages/{id}")
	public Message getMessageById(@PathVariable long id) {
		return messageDao.getMessageById(id);
	}

	@GetMapping("/messages/author/{author}")
	public List<Message> getMessagesByAuthor(@PathVariable String author) {
		return messageDao.getMessagesByAuthor(author);
	}

	@PostMapping("/message")
	public Message addMessage(@RequestBody @Validated Message message) {
		return messageDao.addMessage(message);
	}

	@PutMapping("/message/{id}")
	public Message updateMessage(@PathVariable long id, @RequestBody @Validated Message message) {
		message.setId(id);
		return messageDao.updateMessage(id, message);
	}

	@DeleteMapping("/message/{id}")
	public void deleteMessage(@PathVariable long id) {
		messageDao.deleteMessage(id);
	}
}
