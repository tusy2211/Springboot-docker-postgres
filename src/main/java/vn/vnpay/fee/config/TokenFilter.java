/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.vnpay.fee.config;
import vn.vnpay.fee.utils.SequenceGenerator;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.util.Strings;

/**
 *
 * @author hungtq
 */
public class TokenFilter implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        String token = httpRequest.getHeader("token");
        if (Strings.isEmpty(token)) {
            token = SequenceGenerator.getInstance().nextIdString();
        }
        ThreadContext.put("token", token);
        chain.doFilter(req, res);
        ThreadContext.clearAll();
    }

    @Override
    public void destroy() {
    }

}
