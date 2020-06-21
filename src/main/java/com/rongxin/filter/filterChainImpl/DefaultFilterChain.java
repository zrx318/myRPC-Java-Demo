package com.rongxin.filter.filterChainImpl;

import com.rongxin.filter.Filter;
import com.rongxin.filter.FilterChain;
import com.rongxin.model.WmRequest;
import com.rongxin.model.WmResponse;

import java.util.List;

/**
 * @author com.rongxin
 */
public class DefaultFilterChain implements FilterChain {

    private List<Filter> filterList;
    private int currentFilterIndex;

    public DefaultFilterChain(List<Filter> filterList) {
        this.filterList = filterList;
        currentFilterIndex = 0;
    }

    @Override
    public void doFilter(WmRequest wmRequest, WmResponse wmResponse) {
        filterList.get(currentFilterIndex++).doFilter(wmRequest, wmResponse, this);
    }

    public List<Filter> getFilterList() {
        return filterList;
    }

    public void setFilterList(List<Filter> filterList) {
        this.filterList = filterList;
    }

    public int getCurrentFilterIndex() {
        return currentFilterIndex;
    }

    public void setCurrentFilterIndex(int currentFilterIndex) {
        this.currentFilterIndex = currentFilterIndex;
    }
}
