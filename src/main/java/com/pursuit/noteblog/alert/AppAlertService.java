package com.pursuit.noteblog.alert;

import java.util.List;

import com.pursuit.noteblog.entity.InstanceFault;

/**
 * 应用报警
 */
public interface AppAlertService {
	
    /**
     * 应用故障列表
     *
     * @param appId
     * @return
     */
    List<InstanceFault> getListByAppId(long appId);
}
