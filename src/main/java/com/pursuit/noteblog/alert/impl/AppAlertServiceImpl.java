package com.pursuit.noteblog.alert.impl;

import java.util.List;
import com.pursuit.noteblog.alert.AppAlertService;
import com.pursuit.noteblog.entity.InstanceFault;

/**
 * 应用报警实现
 */
public class AppAlertServiceImpl extends BaseAlertService implements AppAlertService {


    @Override
    public List<InstanceFault> getListByAppId(long appId) {
        return null;
    }
}
