package rs.fon.elab.pzr.rest.resources.old;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/profiles")
public class ProfileResource {
/*
	private ProfileDAO profileDAO = new ProfileDAOImpl();

	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Profile> getProfiles() {
		return profileDAO.getAllProfiles();
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Profile addProfile(@RequestBody Profile profile) {
		return profileDAO.addProfile(profile);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{profileName}")
	public @ResponseBody Profile getProfile(@PathVariable("profileName") String profileName) {
		return profileDAO.getProfile(profileName);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{profileName}")
	public Profile updateProfile(@PathVariable("profileName") String profileName, @RequestBody Profile profile) {
		profile.setProfileName(profileName);
		return profileDAO.updateProfile(profile);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{profileName}")
	public Profile deleteProfile(@PathVariable("profileName") String profileName) {
		return profileDAO.removeProfile(profileName);
	}
*/

}
