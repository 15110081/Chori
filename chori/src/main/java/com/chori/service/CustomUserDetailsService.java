package com.chori.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chori.entity.User;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserService ser;

	private static final Log log = LogFactory
			.getLog(CustomUserDetailsService.class);

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userId)
			throws UsernameNotFoundException {
		User user = ser.findById(userId);

		System.out.println("User : " + user);

		if (user == null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("Username not found");
		}

		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), true, true, true, user
						.getStatus().equals("Active"),
				getGrantedAuthorities(user));
	}

	/**
	 * This method is used to get all Role of 1 user.
	 * 
	 * @param user
	 * @return List<GrantedAuthority>
	 */
	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		log.info(String.format(
				"getGrantedAuthorities with param 'user' in class: %s",
				getClass()));
		try {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_"
					+ user.getRole().getRoleid()));
			System.out.print("authorities :" + authorities);

			log.debug("getGrantedAuthorities successfully");
			return authorities;
		} catch (Exception e) {
			log.error(String
					.format("getGrantedAuthorities with param 'user' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}

	}
}
