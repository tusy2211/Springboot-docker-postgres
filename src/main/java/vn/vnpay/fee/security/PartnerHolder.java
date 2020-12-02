/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.vnpay.fee.security;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 *
 * @author truongnq
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "")
public class PartnerHolder {
    private Set<PartnerConfig> partners;
}
