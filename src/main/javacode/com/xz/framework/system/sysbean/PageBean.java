package com.xz.framework.system.sysbean;

import java.util.List;

/**
 * @author  wuhy on 2017/11/18.
 */
public class PageBean {
    private List<?> pageList;
    private int totalCount;
    private int page;
    private int pageSize;

    public List<?> getPageList() {
        return pageList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageList(List<?> pageList) {
        this.pageList = pageList;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
