package com.rongxin.filter;

import com.rongxin.model.WmRequest;
import com.rongxin.model.WmResponse;

/**
 * @author com.rongxin
 */
public interface FilterChain {
    void doFilter(WmRequest wmRequest, WmResponse wmResponse);
}
