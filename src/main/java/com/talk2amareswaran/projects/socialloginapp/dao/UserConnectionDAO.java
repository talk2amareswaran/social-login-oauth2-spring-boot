package com.talk2amareswaran.projects.socialloginapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.talk2amareswaran.projects.socialloginapp.entity.UserConnection;

@Repository
@Transactional
public class UserConnectionDAO {

	@Autowired
	private EntityManager entityManager;

	public UserConnection findUserConnectionByUserProviderId(String userProviderId) {
		try {
			String sql = "Select e from " + UserConnection.class.getName() + " e " //
					+ " Where e.userProviderId = :userProviderId ";

			Query query = entityManager.createQuery(sql, UserConnection.class);
			query.setParameter("userProviderId", userProviderId);

			@SuppressWarnings("unchecked")
			List<UserConnection> list = query.getResultList();

			return list.isEmpty() ? null : list.get(0);
		} catch (NoResultException e) {
			return null;
		}
	}
}
