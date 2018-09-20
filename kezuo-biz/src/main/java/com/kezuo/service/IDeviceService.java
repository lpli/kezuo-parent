package com.kezuo.service;

import com.baomidou.mybatisplus.service.IService;
import com.kezuo.entity.Device;
import com.kezuo.exception.ClientException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * <p>
 * 机井设备 服务类
 * </p>
 *
 * @author zlzuo
 * @since 2018-08-27
 */
public interface IDeviceService extends IService<Device> {

    public List<Device> selectDeviceList(Map<String, Object> queryMap);

    /**
     * 查询有效设备列表
     *
     * @return
     */
    public List<Device> selectEffectiveDeviceList();

    /**
     * 创建客户端
     *
     * @param device
     */
    public void creatClient(Device device) throws Exception;
}
