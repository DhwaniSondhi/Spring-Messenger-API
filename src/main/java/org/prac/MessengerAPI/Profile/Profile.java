package org.prac.MessengerAPI.Profile;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class Profile {

	@Id
	@GeneratedValue
	private long id;
	private String fName;
	private String lName;
	private String profileName;
	private Date created;

	public Profile() {
		super();
	}

	public Profile(String fName, String lName, String profileName, Date created) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.profileName = profileName;
		this.created = created;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", fName=" + fName + ", lName=" + lName + ", profileName=" + profileName
				+ ", created=" + created + "]";
	}


	public static class ProfileBuilder {
		private String fName;
		private String lName;
		private String profileName;
		private Date created;

		public ProfileBuilder() {
			super();
		}

		public ProfileBuilder setfName(String fName) {
			this.fName = fName;
			return this;
		}

		public ProfileBuilder setlName(String lName) {
			this.lName = lName;
			return this;
		}

		public ProfileBuilder setProfileName(String profileName) {
			this.profileName = profileName;
			return this;
		}

		public ProfileBuilder setCreated(Date created) {
			this.created = created;
			return this;
		}

		public Profile build() {
			return new Profile(fName, lName, profileName, created);
		}
	}
}
