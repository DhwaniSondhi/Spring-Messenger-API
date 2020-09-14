package org.prac.MessengerAPI.comment;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDao {

	private SessionFactory sessionFactory;

	@Autowired
	public CommentDao(EntityManagerFactory entityManagerFactory) {
		this.sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
	}

	public List<Comment> getComments() {
		try (Session session = this.sessionFactory.openSession()) {
			return session.createQuery("from Comment", Comment.class).list();
		}
	}

	public Comment getCommentById(long commentId) {
		try (Session session = this.sessionFactory.openSession()) {
			Comment comment = session.find(Comment.class, commentId);
			if (comment == null) {
				throw new NoResultException("No entity found for query");
			} else {
				return comment;
			}
		}
	}

	public List<Comment> getCommentsByMessageId(long messageId) {
		try (Session session = this.sessionFactory.openSession()) {
			return session.createQuery("from Comment where messageId=:messageId", Comment.class)
							.setParameter("messageId", messageId)
							.list();
		}
	}
	
	public Comment addComment(Comment comment) {
		try (Session session = this.sessionFactory.openSession()) {
			session.beginTransaction();
			session.save(comment);
			session.getTransaction().commit();
			return comment;
		}
	}

	public Comment updateComment(long commentId, Comment comment) {
		try (Session session = this.sessionFactory.openSession()) {
			session.beginTransaction();
			Comment commentDb;
			try {
				commentDb = getCommentById(commentId);
				commentDb.setCommentString(comment.getCommentString());
				commentDb.setMessageId(comment.getMessageId());
			} catch (Exception e) {
				commentDb = comment;
			}
			session.saveOrUpdate(commentDb);
			session.getTransaction().commit();
			return commentDb;
		}
	}

	public void deleteComment(long commentId) {
		try (Session session = this.sessionFactory.openSession()) {
			session.beginTransaction();
			Comment commentDb = getCommentById(commentId);
			session.remove(session.contains(commentDb) ? commentDb : session.merge(commentDb));
			session.getTransaction().commit();
		}
	}
}
