package com.pursuit.noteblog.alert.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pursuit.noteblog.component.EmailComponent;
import com.pursuit.noteblog.component.MobileAlertComponent;

/**
 * 报警基类
 */
public class BaseAlertService {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 邮箱报警
     */
    protected EmailComponent emailComponent;

    /**
     * 手机短信报警
     */
    protected MobileAlertComponent mobileAlertComponent;

    public void setEmailComponent(EmailComponent emailComponent) {
        this.emailComponent = emailComponent;
    }

    public void setMobileAlertComponent(MobileAlertComponent mobileAlertComponent) {
        this.mobileAlertComponent = mobileAlertComponent;
    }

}
