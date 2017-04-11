package com.superproject.service.myservice;

import java.util.List;

import com.superproject.vo.UserVo;

/**
 * 
 * @author leejean
 *
 */
public interface SuperService {
	/**
	 * 保存
	 * @param userVo
	 * @return
	 * @throws Exception
	 */
	boolean save(UserVo userVo) throws Exception;
	/**
	 * 根据id获取用户
	 * @param id
	 * @return
	 * @throws Exception
	 */
	UserVo get(Long id) throws Exception;
	/**
	 * 获取所有用户
	 * @return
	 * @throws Exception
	 */
	List<UserVo> getAll() throws Exception;
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean delete(Long id) throws Exception;
	/**
	 * 修改
	 * @param userVo
	 * @return
	 * @throws Exception
	 */
	boolean update(UserVo userVo) throws Exception;
}
