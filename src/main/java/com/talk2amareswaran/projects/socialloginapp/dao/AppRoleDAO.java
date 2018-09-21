package com.talk2amareswaran.projects.socialloginapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.talk2amareswaran.projects.socialloginapp.entity.AppRole;
import com.talk2amareswaran.projects.socialloginapp.entity.AppUser;
import com.talk2amareswaran.projects.socialloginapp.entity.UserRole;

@Repository
@Transactional
public class AppRoleDAO {

	@Autowired
    private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<String> getRoleNames(Long userId) {
        String sql = "Select ur.appRole.roleName from " + UserRole.class.getName() + " ur " 
                + " where ur.appUser.userId = :userId ";
        Query query = this.entityManager.createQuery(sql, String.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
	
	 public AppRole findAppRoleByName(String roleName) {
	        try {
	            String sql = "Select e from " + AppRole.class.getName() + " e " 
	                    + " where e.roleName = :roleName ";
	 
	            Query query = this.entityManager.createQuery(sql, AppRole.class);
	            query.setParameter("roleName", roleName);
	            return (AppRole) query.getSingleResult();
	        } catch (NoResultException e) {
	            return null;
	        }
	    }
	
	 public void createRoleFor(AppUser appUser, List<String> roleNames) {
	        //
	        for (String roleName : roleNames) {
	            AppRole role = this.findAppRoleByName(roleName);
	            if (role == null) {
	                role = new AppRole();
	                role.setRoleName(AppRole.ROLE_USER);
	                this.entityManager.persist(role);
	                this.entityManager.flush();
	            }
	            UserRole userRole = new UserRole();
	            userRole.setAppRole(role);
	            userRole.setAppUser(appUser);
	            this.entityManager.persist(userRole);
	            this.entityManager.flush();
	        }
	    }
	 
	 
	 
	
}
