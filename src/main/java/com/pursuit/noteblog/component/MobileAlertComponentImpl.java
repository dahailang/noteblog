package com.pursuit.noteblog.component;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pursuit.noteblog.util.StringUtil;


/**
 * 手机短信报警
 */
public class MobileAlertComponentImpl implements MobileAlertComponent {

    private final Logger logger = LoggerFactory.getLogger(MobileAlertComponentImpl.class);
    /**
     * 管理员电话
     */
    private String adminPhones;

    private final static String COMMA = ",";
    

    @Override
    public void sendPhoneToAdmin(String message) {
        if (StringUtil.isBlank(message) || StringUtil.isBlank(adminPhones)) {
            logger.error("message is {}, maybe empty or adminPhones is {}, maybe empty", message, adminPhones);
        }
        sendPhone(message, Arrays.asList(adminPhones.split(COMMA)));
    }

    @Override
    public void sendPhone(String message, List<String> phoneList) {
        /**
         * your company send short message codes
         */
    }

    public void setAdminPhones(String adminPhones) {
        this.adminPhones = adminPhones;
    }
    
}
