package org.prac.MessengerAPI.Profile;

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
		Session session = sessionFactory.openSession();
		List<Profile> out = session.createQuery("from Profile", Profile.class).list();
		session.close();
		return out;
	}

	public Profile getProfileByProfileName(String profileName) {
		Session session = sessionFactory.openSession();
		Profile out= null;
		try {
			out = session.createQuery("from Profile where profileName=:profileName", Profile.class)
					.setParameter("profileName", profileName).getSingleResult();
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}
		return out;
	}

	public Profile addProfile(Profile profile) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(profile);
		session.getTransaction().commit();
		session.close();
		return profile;
	}

	public Profile updateProfile(String profileName, Profile profile) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Profile profiledb = getProfileByProfileName(profileName);
		if (profiledb != null) {
			profiledb.setfName(profile.getfName());
			profiledb.setlName(profile.getlName());
			profiledb.setProfileName(profile.getProfileName());
		} else {
			profiledb = profile;
		}
		session.saveOrUpdate(profiledb);
		session.getTransaction().commit();
		session.close();
		return profiledb;
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
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Profile profile = getProfileByProfileName(profileName);
		session.remove(session.contains(profile) ? profile : session.merge(profile));
		session.getTransaction().commit();
		session.close();
	}
}
