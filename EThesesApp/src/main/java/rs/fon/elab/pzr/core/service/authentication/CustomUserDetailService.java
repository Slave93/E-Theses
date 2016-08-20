package rs.fon.elab.pzr.core.service.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import rs.fon.elab.pzr.core.model.User;
import rs.fon.elab.pzr.core.repository.UserRepository;

public class CustomUserDetailService implements UserDetailsService{

	
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
	
		User user = userRepository.findByEmail(email);
		if(user==null){
			throw new UsernameNotFoundException("There is no user with userName: "+email);
		}

		List authList = new ArrayList();
		if(user.isAdmin()){
			authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			authList.add(new SimpleGrantedAuthority("ROLE_USER")); 
		}else{
			authList.add(new SimpleGrantedAuthority("ROLE_USER")); 
		}     
	    
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true, true, true, true, authList);
		return userDetails;
		
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	

}
