package com.kezuo.service;

import com.kezuo.entity.Device;

public interface IAsyncService {
    /**
     * 执行异步任务
     */
    void executeAsync(Device device, int index) throws Exception;
}
