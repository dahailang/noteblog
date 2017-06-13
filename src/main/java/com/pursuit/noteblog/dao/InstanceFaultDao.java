package com.pursuit.noteblog.dao;


import java.util.List;

import com.pursuit.noteblog.entity.InstanceFault;

/**
 */
public interface InstanceFaultDao {

    /**
     * 添加InstanceFault实例
     *
     * @return
     */
    int insert(InstanceFault instanceFault);

    /**
     * 实例故障列表
     *
     * @param instId
     * @return
     */
    List<InstanceFault> getListByInstId(int instId);

    /**
     * 应用故障列表
     *
     * @param appId
     * @return
     */
    List<InstanceFault> getListByAppId(long appId);


}
