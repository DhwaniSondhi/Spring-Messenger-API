package org.prac.MessengerAPI.Profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

	@Autowired
	private ProfileDao profileDao;

	@GetMapping("/profiles")
	public List<Profile> getProfiles() {
		return profileDao.getProfiles();
	}

	@GetMapping("/profiles/{profileName}")
	public Profile getProfileByProfileName(@PathVariable String profileName) {
		return profileDao.getProfileByProfileName(profileName);
	}

	@PostMapping("/profile")
	public Profile addProfile(@RequestBody Profile profile) {
		return profileDao.addProfile(profile);
	}

	@PutMapping("/profile/{profileName}")
	public Profile updateProfile(@PathVariable String profileName, @RequestBody Profile profile) {
		return profileDao.updateProfile(profileName, profile);
	}

	@DeleteMapping("/profile/{profileName}")
	public void deleteProfile(@PathVariable String profileName) {
		profileDao.deleteProfile(profileName);
	}
}

