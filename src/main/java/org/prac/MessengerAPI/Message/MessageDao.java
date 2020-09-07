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
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery("from Message", Message.class).list();
		}
	}

	public List<Message> getMessagesByAuthor(String author) {
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery("from Message where author=:author", Message.class)
							.setParameter("author", author)
							.list();
		}
	}

	public Message getMessageById(long id) {
		try (Session session = sessionFactory.openSession()) {
			return session.find(Message.class, id);
		}
	}

	public Message addMessage(Message message) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.save(message);
			session.getTransaction().commit();
			return message;
		}
	}

	public Message updateMessage(long id, Message message) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Message msg = null;
			try {
				msg = getMessageById(id);
				msg.setMessage(message.getMessage());
			} catch (Exception e) {
				msg = message;
			}
			session.saveOrUpdate(msg);
			session.getTransaction().commit();
			return msg;
		}
	}

	public void deleteMessage(long id) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Message message = getMessageById(id);
			session.remove(session.contains(message) ? message : session.merge(message));
			session.getTransaction().commit();
		}
	}
}
