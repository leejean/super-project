package com.superproject.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.superproject.dao.BaseDaoI;
import com.superproject.po.User;
import com.superproject.user.UserDao;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	BaseDaoI<User> baseDao;
	public boolean save(User user) throws Exception {
		return baseDao.save(user);
	}

	public User get(Long id) throws Exception {
		return baseDao.findById(User.class, String.valueOf(id));
	}

	public List<User> getAll() throws Exception {
		return baseDao.query("from User");
	}

	public boolean delete(Long id) throws Exception {
		baseDao.deleteById(String.valueOf(id), User.class);
		return true;
	}

	public boolean update(User user) throws Exception {
		baseDao.saveOrUpdate(user);
		return true;
	}

}
