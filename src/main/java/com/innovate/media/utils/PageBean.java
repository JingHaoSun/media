package com.innovate.media.utils;

import java.util.List;
import java.util.Map;
/**
 * 
 * @author sjh
 *
 * @param
 */
public class PageBean {
    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 每页显示的条数
     */
    private int total;
    /**
     * 总条数
     */
    private Integer totalNum;
    /**
     * 是否有下一页
     */
    private Integer isMore;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 开始索引
     */
    private Integer startIndex;
    /**
     * 分页中的对象集合
     */
    private List<Map> rows;

    public PageBean() {
        super();
    }

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		if(total<0) {
			this.total = 0;
		}else {
		this.total = total;
		}
	}

	public List<Map> getRows() {
		return rows;
	}

	public void setRows(List<Map> listReturn) {
		this.rows = listReturn;
	}
//    public PageBean(Integer currentPage, Integer pageSize, Integer totalNum) {
//        super();
//        this.currentPage = currentPage;
//        System.out.println(currentPage);
//        this.total = pageSize;
//        System.out.println(pageSize);
//        this.totalNum = totalNum;
//        System.out.println(totalNum);
//        this.totalPage = (this.totalNum+this.total-1)/this.total;
//        System.out.println(totalPage);
//        this.startIndex = (this.currentPage-1)*this.total<=0?1:startIndex;
//        System.out.println(startIndex);
//        this.isMore = this.currentPage >= this.totalPage?0:1;
//        System.out.println(isMore);
//    }
	
}