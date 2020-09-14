package org.prac.MessengerAPI.profile;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

	@Autowired
	private ProfileDao profileDao;

	@GetMapping
	public List<Profile> getProfiles() {
		return profileDao.getProfiles();
	}

	@GetMapping("/{profileName}")
	public Profile getProfileByProfileName(@PathVariable String profileName) {
		return profileDao.getProfileByProfileName(profileName);
	}

	@PostMapping
	public Profile addProfile(@RequestBody @Valid Profile profile) {
		return profileDao.addProfile(profile);
	}

	@PutMapping("/{profileName}")
	public Profile updateProfile(@PathVariable String profileName, @RequestBody Profile profile) {
		return profileDao.updateProfile(profileName, profile);
	}

	@DeleteMapping("/{profileName}")
	public void deleteProfile(@PathVariable String profileName) {
		profileDao.deleteProfile(profileName);
	}
}

