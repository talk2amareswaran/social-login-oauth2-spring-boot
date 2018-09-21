package com.talk2amareswaran.projects.socialloginapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SocialUserDetailsServiceImpl implements SocialUserDetailsService {

	@Autowired
	private UserDetailsService userDetailService;

	@Override
	public SocialUserDetails loadUserByUserId(String userName) throws UsernameNotFoundException, DataAccessException {
		System.out.println("SocialUserDetailsServiceImpl.loadUserByUserId=" + userName);
		UserDetails userDetails = ((UserDetailsServiceImpl) userDetailService).loadUserByUsername(userName);
		return (SocialUserDetailsImpl) userDetails;
	}

}
