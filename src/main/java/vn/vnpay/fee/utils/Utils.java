/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.vnpay.fee.utils;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import vn.vnpay.fee.beans.BaseResponse;
import vn.vnpay.fee.constants.Rescode;

/**
 *
 * @author truongnq
 */
public class Utils {

    public static String createSoapRequestAccVnmart(String terminalID, String email, String vnmartName, String createUser) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:vnm=\"http://vnmart.vnpay.vn/\">\n"
                + "   <soapenv:Header/>\n"
                + "   <soapenv:Body>\n"
                + "      <vnm:CreateMerchantAccount>\n"
                + "         <!--Optional:-->\n"
                + "         <vnm:tmnCode>" + terminalID + "</vnm:tmnCode>\n"
                + "         <!--Optional:-->\n"
                + "         <vnm:email>" + email + "</vnm:email>\n"
                + "         <!--Optional:-->\n"
                + "         <vnm:merchantName>" + vnmartName + "</vnm:merchantName>\n"
                + "         <!--Optional:-->\n"
                + "         <vnm:createUser>" + createUser + "</vnm:createUser>\n"
                + "      </vnm:CreateMerchantAccount>\n"
                + "   </soapenv:Body>\n"
                + "</soapenv:Envelope>";
    }

    public static String genRanDomPass() {

        String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String NUMCHARS = "0123456789";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        int index;
        while (salt.length() < 5) {
            index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        index = (int) (rnd.nextFloat() * NUMCHARS.length());
        salt.append(NUMCHARS.charAt(index));
        String saltStr = salt.toString();
        return saltStr;
    }

    public static String phoneFormat(String phone) {
        if (phone.startsWith("+84")) {
            return "0" + phone.substring(3);
        }
        if (phone.startsWith("84")) {
            return "0" + phone.substring(2);
        }
        return phone;
    }

    public static String convertWalletType(String walletType) {
        if ("2".equals(walletType)) {
            return "1";
        } 
        return "0";
    }

    public static String md5(String value) {
        if (StringUtils.isEmpty(value)) {
            return "";
        }
        return DigestUtils.md5DigestAsHex(value.getBytes(StandardCharsets.UTF_8));

    }

    public static BaseResponse checkResponseCode(String code) {
        if (Rescode.CREATE_VNMART_FAILED.code().equals(code)) {
            return BaseResponse.builder()
                    .code(Rescode.CREATE_VNMART_FAILED.code())
                    .message(Rescode.CREATE_VNMART_FAILED.description())
                    .build();
        }

        if (Rescode.MAINTENANCE.code().equals(code)) {
            return BaseResponse.builder()
                    .code(Rescode.MAINTENANCE.code())
                    .message(Rescode.MAINTENANCE.description())
                    .build();
        }

        if (Rescode.TIMEOUT.code().equals(code)) {
            return BaseResponse.builder()
                    .code(Rescode.TIMEOUT.code())
                    .message(Rescode.TIMEOUT.description())
                    .build();
        }
        return BaseResponse.builder()
                .code(Rescode.SUCCESS.code())
                .build();
    }
}
