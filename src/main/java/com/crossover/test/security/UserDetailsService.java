package com.crossover.test.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 * Get user name and roles, add as a model to be shown by a View
 * 
 * @author vinicius
 *
 */
@Service
public class UserDetailsService {

	/**
	 * Get user name and roles
	 * 
	 * @param mav
	 */
	public void getUserDetails(ModelAndView mav) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// Spring security returns the 'anonymousUser' as authenticated. TODO:
		// study better way to handle it.
		if (authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getName())) {
			List<String> roles = new ArrayList<>();

			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			// Had to transform GrantAuthority to String because of contains()
			// method of Velocity template
			authorities.iterator().forEachRemaining(authority -> roles.add(authority.getAuthority()));

			mav.addObject("roles", roles);
			mav.addObject("username", authentication.getName());
		}
	}

}
