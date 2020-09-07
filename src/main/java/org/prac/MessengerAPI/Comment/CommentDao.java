package org.prac.MessengerAPI.Comment;

import java.util.List;

import javax.persistence.EntityManagerFactory;

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

	public List<Comment> getComments(long messageId) {
		try (Session session = this.sessionFactory.openSession()) {
			return session.createQuery("from Comment where messageId=:messageId", Comment.class)
							.setParameter("messageId", messageId)
							.list();
		}
	}

	public Comment getComment(long messageId, long commentId) {
		try (Session session = this.sessionFactory.openSession()) {
			return session.createQuery("from Comment where messageId=:messageId and id=:commentId", Comment.class)
							.setParameter("messageId", messageId)
							.setParameter("commentId", commentId)
							.uniqueResult();
		}
	}
	
	public Comment addComment(long messageId, Comment comment) {
		try (Session session = this.sessionFactory.openSession()) {
			comment.setMessageId(messageId);
			session.beginTransaction();
			session.save(comment);
			session.getTransaction().commit();
			return comment;
		}
	}

	public Comment updateComment(long messageId, long commentId, Comment comment) {
		try (Session session = this.sessionFactory.openSession()) {
			session.beginTransaction();
			Comment commentDb;
			try {
				commentDb = getComment(messageId, commentId);
				commentDb.setCommentString(comment.getCommentString());
			} catch (Exception e) {
				commentDb = comment;
			}
			session.saveOrUpdate(session.contains(commentDb) ? commentDb : session.merge(commentDb));
			session.getTransaction().commit();
			return commentDb;
		}
	}

	public void deleteComment(long messageId, long commentId) {
		try (Session session = this.sessionFactory.openSession()) {
			session.beginTransaction();
			Comment commentDb = getComment(messageId, commentId);
			session.remove(session.contains(commentDb) ? commentDb : session.merge(commentDb));
			session.getTransaction().commit();
		}
	}
}
