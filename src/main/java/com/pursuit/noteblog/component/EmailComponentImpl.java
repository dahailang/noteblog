package com.pursuit.noteblog.component;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 发送邮件服务
 */
public class EmailComponentImpl implements EmailComponent {
    private final Logger logger = LoggerFactory.getLogger(EmailComponentImpl.class);

    /**
     * 管理员邮件列表
     */
    private String adminEmail;

    @Override
    public boolean sendMailToAdmin(String title, String content) {
    	logger.info("发送邮件到管理员");
        return sendMail(title, content, Arrays.asList(adminEmail));
    }

    @Override
    public boolean sendMail(String title, String content, List<String> emailList) {
        return sendMail(title, content, emailList, null);
    }
    
    @Override
	public boolean sendMail(String title, String content, List<String> emailList, List<String> ccList) {
        /**
         * your company send short message codes
         */
        return true;
	}

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

	@Override
	public String getAdminEmail() {
		return adminEmail;
	}
	
}
