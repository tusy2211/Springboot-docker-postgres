/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.vnpay.fee.security;

import java.util.Collections;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 *
 * @author truongnq
 */
@Configuration
@EnableConfigurationProperties(PartnerHolder.class)
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final static Logger LOGGER = LogManager.getLogger(CustomAuthenticationProvider.class);
    
    @Autowired
    PartnerHolder partnerHolder;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String name = auth.getName();
        String accessKey = auth.getCredentials().toString();
        WebAuthenticationDetails details = (WebAuthenticationDetails) auth.getDetails();
        String userIp = details.getRemoteAddress();
        LOGGER.info("Begin authenticate request from ip: {}", userIp);
        Set<PartnerConfig> partners = partnerHolder.getPartners();
        if (null == partners) {
            return new UsernamePasswordAuthenticationToken(name, accessKey, Collections.EMPTY_LIST);
        }
        for (PartnerConfig partner : partners) {
            if (!partner.getName().equalsIgnoreCase(name)) {
                continue;
            }
            if (!partner.getAccessKey().equalsIgnoreCase(accessKey)) {
                LOGGER.info("Partner name: {} accesskey: {} not match config", name, accessKey);
                break;
            }
            if (!partner.getIps().contains(userIp) && !partner.getIps().contains("*")) {
                LOGGER.info("Partner name: {} ip: {} not accepts", name, userIp);
                break;
            }
            return new UsernamePasswordAuthenticationToken(name, accessKey, Collections.EMPTY_LIST);
        }
        throw new BadCredentialsException("BadCredentialsException");
    }

    @Override
    public boolean supports(Class<?> type) {
        return true;
    }

}
