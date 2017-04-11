package com.superproject.service.myservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superproject.po.User;
import com.superproject.service.myservice.SuperService;
import com.superproject.user.UserDao;
import com.superproject.utils.tools.BeanConvertor;
import com.superproject.vo.UserVo;

/**
 * 
 * @author leejean
 *
 */
@Service
public class SuperServiceImpl implements SuperService{
	@Autowired	
	UserDao userDao;
	public boolean save(UserVo userVo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public UserVo get(Long id) throws Exception {
		User user = userDao.get(id);
		return BeanConvertor.copyProperties(user, UserVo.class);
	}

	public List<UserVo> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean delete(Long id) throws Exception {
		return userDao.delete(id);
	}

	public boolean update(UserVo userVo) throws Exception {
		return false;
	}
	
}
