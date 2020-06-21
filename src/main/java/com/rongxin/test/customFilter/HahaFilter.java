package com.rongxin.test.customFilter;

import com.rongxin.filter.Filter;
import com.rongxin.filter.FilterChain;
import com.rongxin.model.WmRequest;
import com.rongxin.model.WmResponse;

/**
 * @author com.rongxin
 */
public class HahaFilter implements Filter {

    @Override
    public void doFilter(WmRequest wmRequest, WmResponse wmResponse, FilterChain filterChain) {
        System.out.println("********haha before********");
        filterChain.doFilter(wmRequest, wmResponse);
        System.out.println("********haha finish********");
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
