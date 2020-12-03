/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.vnpay.fee.security;

import java.util.List;

import lombok.*;

/**
 *
 * @author khanhbn
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PartnerConfig {
    private String name;
    private List<String> ips;
    private String accessKey;
}
