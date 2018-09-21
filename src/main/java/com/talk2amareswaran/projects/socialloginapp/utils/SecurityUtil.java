package com.talk2amareswaran.projects.socialloginapp.utils;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.security.SocialUserDetails;

import com.talk2amareswaran.projects.socialloginapp.entity.AppUser;
import com.talk2amareswaran.projects.socialloginapp.service.SocialUserDetailsImpl;

public class SecurityUtil {

	public static void logInUser(AppUser user, List<String> roleNames) {
		SocialUserDetails userDetails = new SocialUserDetailsImpl(user, roleNames);
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
