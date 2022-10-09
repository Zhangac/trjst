//package com.trjst.util;
//
//import com.baomidou.mybatisplus.core.metadata.IPage;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
///**
// * 分页工具类
// *
// */
//public class PageUtils<T> implements Serializable {
//	private static final long serialVersionUID = 1L;
//	/**
//	 * 总记录数
//	 */
//	private int totalCount;
//	/**
//	 * 每页记录数
//	 */
//	private int pageSize;
//	/**
//	 * 总页数
//	 */
//	private int totalPage;
//	/**
//	 * 当前页数
//	 */
//	private int currPage;
//	/**
//	 * 列表数据
//	 */
//	private List<T> list;
//
//	/**
//	 * 分页
//	 * @param list        列表数据
//	 * @param totalCount  总记录数
//	 * @param pageSize    每页记录数
//	 * @param currPage    当前页数
//	 */
//	public PageUtils(List<T> list, int totalCount, int pageSize, int currPage) {
//		this.list = list;
//		this.totalCount = totalCount;
//		this.pageSize = pageSize;
//		this.currPage = currPage;
//		this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
//	}
//
//	/**
//	 * 分页
//	 */
//	public PageUtils(IPage<T> page) {
//		this.list = Optional.ofNullable(page).map(p->p.getRecords()).orElse(new ArrayList<>());
//		this.totalCount = Math.toIntExact(Optional.ofNullable(page).map(p -> p.getTotal()).orElse(0L));
//		this.pageSize = Math.toIntExact(Optional.ofNullable(page).map(p -> p.getSize()).orElse(0L));
//		this.currPage = Math.toIntExact(Optional.ofNullable(page).map(p -> p.getCurrent()).orElse(0L));
//		this.totalPage = Math.toIntExact(Optional.ofNullable(page).map(p -> p.getPages()).orElse(0L));
//	}
//
//	public int getTotalCount() {
//		return totalCount;
//	}
//
//	public void setTotalCount(int totalCount) {
//		this.totalCount = totalCount;
//	}
//
//	public int getPageSize() {
//		return pageSize;
//	}
//
//	public void setPageSize(int pageSize) {
//		this.pageSize = pageSize;
//	}
//
//	public int getTotalPage() {
//		return totalPage;
//	}
//
//	public void setTotalPage(int totalPage) {
//		this.totalPage = totalPage;
//	}
//
//	public int getCurrPage() {
//		return currPage;
//	}
//
//	public void setCurrPage(int currPage) {
//		this.currPage = currPage;
//	}
//
//	public List<T> getList() {
//		return list;
//	}
//
//	public void setList(List<T> list) {
//		this.list = list;
//	}
//
//}
