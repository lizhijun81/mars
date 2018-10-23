

import java.util.List;

/**
 * 分页工具（含页码）
 * Created by cxq on 2017/11/22.
 */
public class PageUtil<T> {
 
    /**显示页码数量*/
    public static final int SHOW_PAGE_NUM = 10;
 
    /**记录集合*/
    private List<T> pageList  =  null;
    /**当前页数*/
    private int currPage = 1;
    /**每页显示大小*/
    private int pageSize  = 10;
    /**总页数*/
    private int totalPage  = 0;
    /**总记录数*/
    private long totalCount = 0;
    /**开始页码*/
    private int startPageNum;
    /**结束页码*/
    private int endPageNum;
 
    public PageUtil(List<T> pageList, int currPage, long totalCount) {
        this(pageList, currPage, 0, totalCount);
    }
 
    public PageUtil(List<T> pageList, int currPage, int pageSize, long totalCount) {
        this.pageList = pageList;
        this.currPage = currPage;
        if (pageSize>0)
            this.pageSize = pageSize;
        this.totalCount = totalCount;
        initPageNumber();
    }
 
    public List<T> getPageList() {
        return pageList;
    }
 
    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }
 
    public int getCurrPage() {
        return currPage;
    }
 
    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }
 
    public int getPageSize() {
        return pageSize;
    }
 
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
 
    public int getTotalPage() {
        this.totalPage = this.pageSize==0 ? 0 : (int)Math.ceil((double)this.totalCount / (double)this.pageSize);
        return this.totalPage;
    }
 
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
 
    public long getTotalCount() {
        return totalCount;
    }
 
    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
 
    public int getStartPageNum() {
        return startPageNum;
    }
 
    public void setStartPageNum(int startPageNum) {
        this.startPageNum = startPageNum;
    }
 
    public int getEndPageNum() {
        return endPageNum;
    }
 
    public void setEndPageNum(int endPageNum) {
        this.endPageNum = endPageNum;
    }
 
    public boolean hasPreviousPage() {
        return this.currPage > 1;
    }
 
    public boolean isHasPreviousPage() {
        return this.hasPreviousPage();
    }
 
    public boolean hasNextPage() {
        return this.currPage < this.getTotalPage();
    }
 
    public boolean isHasNextPage() {
        return this.hasNextPage();
    }
 
    /**
     * 初始化页码（仿百度）
     * startPageNum 开始页码
     * endPageNum 结束页码
     * 通过开始页码和结束页码，可以循环得到分页页码列表
     */
    public void initPageNumber(){
        if (this.getTotalPage() < SHOW_PAGE_NUM) {
            this.startPageNum = 1;
            this.endPageNum = this.getTotalPage();
        } else {
            if (this.getCurrPage() <= SHOW_PAGE_NUM / 2 + 1) {
                this.startPageNum = 1;
                this.endPageNum = SHOW_PAGE_NUM;
            } else {
                this.startPageNum = this.getCurrPage() - (SHOW_PAGE_NUM / 2);
                this.endPageNum = this.getCurrPage() + (SHOW_PAGE_NUM / 2 - 1);
                if (this.endPageNum >= this.getTotalPage()) {
                    this.endPageNum = this.getTotalPage();
                    this.startPageNum = this.getTotalPage() - SHOW_PAGE_NUM + 1;
                }
            }
        }
    }
}