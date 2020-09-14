package org.prac.MessengerAPI.message;

import java.util.List;

import javax.validation.Valid;

import org.prac.MessengerAPI.customannotations.profilepresent.ProfilePresent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
@Validated
public class MessageController {

	@Autowired
	MessageService messageService;

	@GetMapping
	public List<Message> getMessages() {
		return messageService.getMessages();
	}

	@GetMapping("/{messageId}")
	public Message getMessageById(@PathVariable long messageId) {
		return messageService.getMessageById(messageId);
	}

	@GetMapping("/author/{author}")
	public List<Message> getMessagesByAuthor(@PathVariable @ProfilePresent String author) {
		return messageService.getMessagesByAuthor(author);
	}

	@PostMapping
	public Message addMessage(@RequestBody @Valid Message message) {
		return messageService.addMessage(message);
	}

	@PutMapping("/{messageId}")
	public Message updateMessage(@PathVariable long messageId, @RequestBody @Valid Message message) {
		return messageService.updateMessage(messageId, message);
	}

	@DeleteMapping("/{messageId}")
	public void deleteMessage(@PathVariable long messageId) {
		messageService.deleteMessage(messageId);
	}
}
