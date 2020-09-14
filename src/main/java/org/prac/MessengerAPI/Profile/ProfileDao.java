package org.prac.MessengerAPI.profile;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileDao {

	private SessionFactory sessionFactory;

	@Autowired
	public ProfileDao(EntityManagerFactory entityManagerFactory) {
		sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
	}

	public List<Profile> getProfiles() {
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery("from Profile", Profile.class).list();
		}
	}

	public Profile getProfileByProfileName(String profileName) {
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery("from Profile where profileName=:profileName", Profile.class)
							.setParameter("profileName", profileName)
							.getSingleResult();
		}
	}

	public Profile addProfile(Profile profile) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.save(profile);
			session.getTransaction().commit();
			return profile;
		}
	}

	public Profile updateProfile(String profileName, Profile profile) {
		try(Session session = sessionFactory.openSession()){
			session.beginTransaction();
			Profile profiledb;
			try {
				profiledb = getProfileByProfileName(profileName);
				profiledb.setfName(profile.getfName());
				profiledb.setlName(profile.getlName());
				profiledb.setProfileName(profile.getProfileName());
			} catch (Exception e) {
				profiledb = profile;
			}
			session.saveOrUpdate(profiledb);
			session.getTransaction().commit();
			return profiledb;
		}
	}

	/*
	 * 
	 * #remove() works only on entities which are managed in the current
	 * transaction/context. In this case, we were retrieving the entity in an
	 * earlier transaction, storing it in the HTTP session and then attempting to
	 * remove it in a different transaction/context. This won't work.
	 * 
	 * We need to check if the entity is managed by #contains() and if not, then
	 * make it managed it #merge().
	 */
	public void deleteProfile(String profileName) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Profile profile = getProfileByProfileName(profileName);
			session.remove(session.contains(profile) ? profile : session.merge(profile));
			session.getTransaction().commit();
		}
	}
}
