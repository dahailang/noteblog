package com.pursuit.noteblog.alert.impl;

import java.util.List;
import com.pursuit.noteblog.alert.AppAlertService;
import com.pursuit.noteblog.dao.InstanceFaultDao;
import com.pursuit.noteblog.entity.InstanceFault;

/**
 * 应用报警实现
 */
public class AppAlertServiceImpl extends BaseAlertService implements AppAlertService {

    private InstanceFaultDao instanceFaultDao;

    @Override
    public List<InstanceFault> getListByAppId(long appId) {
        return instanceFaultDao.getListByAppId(appId);
    }

    public void setInstanceFaultDao(InstanceFaultDao instanceFaultDao) {
        this.instanceFaultDao = instanceFaultDao;
    }
}
