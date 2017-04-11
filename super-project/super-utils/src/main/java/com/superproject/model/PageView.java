package com.superproject.model;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
/**
 * 分页视图
 * <li>@author Leejean
 * <li>@create 2014-6-27下午03:16:55
 */
public class PageView<T> {
	/** 分页数据 **/
	private List<T> rows;
	private int total;
	private int pagesize;
	private int curpage;
	private int allpage;
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getAllpage() {
		if(total!=0&&pagesize!=0){			
			return (total-1)/pagesize+1;
		}else{
			return this.allpage;
		}
	}
	public void setAllpage(int allpage) {
		this.allpage = allpage;
	}
	
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getCurpage() {
		return curpage;
	}
	public void setCurpage(int curpage) {
		this.curpage = curpage;
	}
	public PageView(List<T> rows, int total) {
		super();
		this.rows = rows;
		this.total = total;
	}	
	
	public PageView(List<T> rows, int total, int curpage, int pagesize) {
		super();
		this.rows = rows;
		this.total = total;
		this.curpage = curpage;
		this.pagesize = pagesize;
		caclAllPage();
	}
	private void caclAllPage() {
		if(total!=0&&pagesize!=0){			
			this.allpage =  (total-1)/pagesize+1;
		}
	}
	public PageView() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "PageView [rows=" + rows + ", total=" + total + ", pagesize="
				+ pagesize + ", curpage=" + curpage + ", allpage=" + allpage
				+ "]";
	}
	public String toJSON(){
		JSONObject obj = new JSONObject();
		obj.put("rows", this.getRows());
		obj.put("total",this.getTotal());
		obj.put("pagesize", this.getPagesize());
		obj.put("curpage", this.getCurpage());
		obj.put("allpage", this.getAllpage());
		return obj.toString();
	}
	public static void main(String[] args) {
		int total =1002;
		int pagesize = 10;
		System.out.println((total-1)/pagesize+1);
	}
}
