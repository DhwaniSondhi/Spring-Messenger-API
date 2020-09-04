package org.prac.MessengerAPI.Message;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDao {

	private SessionFactory sessionFactory;

	@Autowired
	public MessageDao(EntityManagerFactory entityManagerFactory) {
		this.sessionFactory=entityManagerFactory.unwrap(SessionFactory.class);
	}

	public List<Message> getMessages() {
		Session session = sessionFactory.openSession();
		return session.createQuery("from Message", Message.class).list();
	}

	public List<Message> getMessagesByAuthor(String author) {
		Session session = sessionFactory.openSession();
		return session.createQuery("from Message where author=:author", Message.class).setParameter("author", author)
				.list();
	}

	public Message getMessageById(long id) {
		Session session = sessionFactory.openSession();
		return session.find(Message.class, id);
	}

	public Message addMessage(Message message) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(message);
		session.getTransaction().commit();
		return message;
	}

	public Message updateMessage(Message message) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(message);
		session.getTransaction().commit();
		return message;
	}

	public void deleteMessage(Message message) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(message);
		session.getTransaction().commit();
	}
}
