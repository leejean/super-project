package com.superproject.user;

import java.util.List;

import com.superproject.po.User;

public interface UserDao {
	/**
	 * 
	 * @param userVo
	 * @return
	 * @throws Exception
	 */
	boolean save(User user) throws Exception;
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	User get(Long id) throws Exception;
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	List<User> getAll() throws Exception;
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean delete(Long id) throws Exception;
	/**
	 * 
	 * @param userVo
	 * @return
	 * @throws Exception
	 */
	boolean update(User user) throws Exception;
}
