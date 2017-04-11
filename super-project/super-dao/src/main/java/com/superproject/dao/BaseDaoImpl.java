package com.superproject.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.superproject.model.PageParams;
import com.superproject.utils.tools.BeanConvertor;
import com.superproject.utils.tools.DateUtil;
import com.superproject.utils.tools.StringUtil;


/**
 * 通用dao实现类
 * <li>@author Leejean
 * <li>@create 2014-6-24 下午04:14:54
 * @param <T> 目标类
 */
@SuppressWarnings("unchecked")
@Repository
public class BaseDaoImpl<T> implements BaseDaoI<T> {
	private Logger log = Logger.getLogger(this.getClass()); 
	private String message="数据库操作异常.";
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 获得当前事物的session
	 * 
	 * @return org.hibernate.Session
	 */
	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	/**
	 * 保存
	 * @author Leejean
	 * @create 2014-7-5下午12:06:02
	 * @param t 持久化对象
	 * @return 执行结果
	 */
	public boolean save(T o){
		try {
			Serializable serializable=this.getCurrentSession().save(o);
			return serializable==null?false:true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(message, e);
			return false;
		}
	}
	
	/**
	 * 保存或者修改
	 * @author liuligong
	 * @create 2014-11-10 8:58
	 * @param t 持久化对象
	 * @return 执行结果
	 */
	public boolean saveOrUpdate(T o){
		try {
			this.getCurrentSession().saveOrUpdate(o);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(message, e);
			return false;
		}
	}
	/**
	 * 根据ID查找对象
	 * @author Leejean
	 * @create 2014-7-5下午12:06:17
	 * @param clazz 目标对象
	 * @param id ID
	 * @return 对象实例
	 */
	public T findById(Class<T> c,String id) {
		try {
			return (T) this.getCurrentSession().get(c, id);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(message, e);
			return null;
		}
	}
	/**
	 * 普通分页查询
	 * @author Leejean
	 * @create 2014-7-5下午12:08:49
	 * @param hql HQL
	 * @param pageIndex 当前页
	 * @param pageSize 页大小
	 * @param params 参数
	 * @return 结果集
	 */
	public List<T> queryByPage(String hql, int pageIndex,int pageSize, Object...params) {
		try {
			Query query = getQuery(hql);
			query.setFirstResult((pageIndex-1)*pageSize);
			query.setMaxResults(pageSize);
			bandParameter(query, params);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(message, e);
			return null;
		}
	}
	/**
	 * 分页查询(适用于Easyui分页)
	 * 局限:排序仅支持单表
	 * @author Leejean
	 * @create 2014-6-27下午03:39:31
	 * @param hql HQL 目标表取别名为:o 无需后接空格   如:'from MyTable o'
	 * @param pageParams 分页参数
	 * @param params 用户输入参数，一般用来自于自定义Bean
	 * @return 结果集
	 */
	public List<T> queryByPage(String hql, PageParams pageParams, Object...params) {
		try {
			if(StringUtil.notNull(pageParams.getOrder())&&StringUtil.notNull(pageParams.getSort())){			
				hql += " order by o." + pageParams.getSort() + " " + pageParams.getOrder();
			}
			Query query = getQuery(hql);
			query.setFirstResult((pageParams.getPage()-1)*pageParams.getRows());
			query.setMaxResults(pageParams.getRows());
			bandParameter(query, params);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(message, e);
			return null;
		}
	}
	
	/**
	 * 分页查询带排序功能
	 * @author Leejean
	 * @create 2014-6-27下午03:39:31
	 * @param hql HQL语句
	 * @param pageIndex 页码
	 * @param pageSize 页大小
	 * @param sort 排序列
	 * @param order 升序asc&降序desc
	 * @param params 参数列表
	 * @return 结果集
	 */
	public List<T> queryByPage(String hql, int pageIndex,int pageSize,String sort,String order,
			Object... params) {
		try {
			if(sort!=null&&!sort.equals("")&&order!=null&&!order.equals("")){			
				hql += " order by o." + sort + " " + order;
			}
			Query query = getQuery(hql);
			query.setFirstResult((pageIndex - 1) * pageSize);
			query.setMaxResults(pageSize);
			bandParameter(query, params);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(message, e);
			return null;
		}
	}
	/**
	 * 普通查询
	 * @param hql HQL语句
	 * @param params 参数列表
	 * @return 对象集合
	 */
	public List<T> query(String hql,Object...params) {
		try {
			Query query=getQuery(hql);
			bandParameter(query, params);
			return (List<T>)query.list();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(message, e);
			return null;
		}
	}
	/**
	 * 普通查询
	 * @param hql HQL语句
	 * @param params 参数列表
	 * @return 对象集合
	 */
	public List<T> queryTop(String hql,Integer top,Object...params) {
		try {
			Query query=getQuery(hql);
			bandParameter(query, params);
			query.setMaxResults(top);
			return (List<T>)query.list();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(message, e);
			return null;
		}
	}
	/**
	 * 
	 */
	public <T> T query(Class<T> clazz, String hql, Object... params) {
		try {
			Query query=getQuery(hql);
			bandParameter(query, params);
			return (T)query.list();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(message, e);
			return null;
		}
	}
	public List<Object[]> queryObjectArray(String hql, Object... params) {
		try {
			Query query=getQuery(hql);
			bandParameter(query, params);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(message, e);
			return null;
		}
	}
	public List<Object[]> queryPageObjectArray(String hql, int pageIndex,
			int pageSize) {
		try {
			Query query = getQuery(hql);
			query.setFirstResult((pageIndex-1)*pageSize);
			query.setMaxResults(pageSize);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(message, e);
			return null;
		}
	}

	public List<Object[]> queryObjectArray(String hql, PageParams pageParams,
			Object... params) {
		try {
			if(StringUtil.notNull(pageParams.getOrder())&&StringUtil.notNull(pageParams.getSort())){			
				hql += " order by o." + pageParams.getSort() + " " + pageParams.getOrder();
			}
			Query query = getQuery(hql);
			query.setFirstResult((pageParams.getPage()-1)*pageParams.getRows());
			query.setMaxResults(pageParams.getRows());
			bandParameter(query, params);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(message, e);
			return null;
		}
	}
	/**
	 * 单值查询
	 * @author Leejean
	 * @create 2014-6-27下午03:39:31
	 * @param hql 
	 * @param params 
	 * @return
	 */

	public Object unique(String hql,Object...params) {
		try {
			Query query=getQuery(hql);
			bandParameter(query, params);
			return query.setMaxResults(1).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(message, e);
			return null;
		}
	}
	/**
	 * 对象查询
	 */

	public T unique(Class<T> c, String hql, Object... params) {
		try {
			Query query=getQuery(hql);
			bandParameter(query, params);
			return (T) query.setMaxResults(1).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(message, e);
			return null;
		}
	}
	/**
	 * 获得Query对象
	 * @author Leejean
	 * @create 2014-6-27下午03:39:31
	 * @param hql HQL语句
	 * @return Query Query对象
	 */
	private Query getQuery(String hql) {
		
		Query query=this.getCurrentSession().createQuery(hql);
		
		return query;
	}
	/**
	 * 绑定参数
	 * @author Leejean
	 * @create 2014-6-27下午03:39:31
	 * @param query Query 
	 * @param params 如果传入的是 list,中间如果有date类型的需要写成 ：list.add("值"+";Date");
	 */
	private void bandParameter(Query query, Object... params) {
		if(params.length>0){
			if(params[0].getClass().getName().equals("java.util.ArrayList") ){
				List<Object> objParams = (List<Object>) params[0];
				for(int i=0;i<objParams.size();i++){
					Object value = objParams.get(i);
					String[] args = String.valueOf(value).split("\\;");
					if(null!=args&&args.length>1&&"Date".equals(args[1])){
						query.setParameter(i,DateUtil.strToDate(String.valueOf(args[0]), "yyyy-MM-dd HH:mm:ss"));
					}else{
						query.setParameter(i,value);
					}
				}
			}else{
				for(int i=0;i<params.length;i++){
					query.setParameter(i, params[i]);
				}
			}
			
		}
	}
	/**
	 * 获得记录数(通常用于分页查询)
	 * @author Leejean
	 * @create 2014-6-27下午03:39:31 
	 * @param hql HQL语句
	 * @param params 参数列表
	 * @return 记录数
	 */

	public long queryTotal(String hql, Object... params) {
		try {
			Query query = getQuery(hql);
			bandParameter(query, params);
			return Long.parseLong(query.setMaxResults(1).uniqueResult().toString());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(message, e);
			return 0;
		}
	}
	/**
	 * 按条件删除或修改
     * @author Leejean
	 * @create 2014-6-27下午03:39:31 
	 * @param hql
	 * @param params
	 * @return
	 */

	public int updateByParams(String hql, Object... params) {
		try {
			Query query =  getQuery(hql);
			bandParameter(query, params);
			return query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(message, e);
			return 0;
		}
	}
	/**
	 * 按id删除对象
	 * @author Leejean
	 * @create 2014-7-5下午12:05:14
	 * @param id id
	 * @param clazz 目标对象
	 * @return
	 */

	public int deleteById(String id,Class<T> clazz){
		Query query =  getQuery("delete from "+clazz.getName()+" o where o.id='"+id+"'");
		return query.executeUpdate();
	}
	/**
	 * 按条件删除或修改
	 * @author Leejean
	 * @create 2014-7-5下午12:05:51
	 * @param hql
	 * @param params
	 * @return
	 */

	public int deleteByParams(String hql, Object... params) {
		try {
			Query query =  getQuery(hql);
			bandParameter(query, params);
			return query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(message, e);
			return 0;
		}
	}
	/**
	 * SQL拓展查询
	 * @author Leejean
	 * @create 2014-6-27下午03:39:31
	 * @param sql SQL语句
	 * @param params 参数列表
	 * @return 对象集合
	 */

	public List<?> queryBySql(String sql, Object... params) {
		try {
			Query query =  this.getCurrentSession().createSQLQuery(sql);
			bandParameter(query, params);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(message, e);
			return null;
		}
	}
	@SuppressWarnings("rawtypes")

	public void batchInsert(List list,Class clazz) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			int i=0;
			for (Object t : list) {
				Object po=BeanConvertor.copyProperties(t, clazz);
				session.save(po);
				if ( i % 20 == 0 ) { //单次批量操作的数目为20
					session.flush(); //清理缓存，执行批量插入20条记录的SQL insert语句
					session.clear(); //清空缓存中
				}
				i++;
			}
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.flush(); //清理缓存，执行批量插入20条记录的SQL insert语句
			session.clear(); //清空缓存中
			tx.rollback();
			session.close();
			e.printStackTrace();
		}
	}
}
