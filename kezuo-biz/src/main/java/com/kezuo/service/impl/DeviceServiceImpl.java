package com.kezuo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import com.kezuo.entity.Device;
import com.kezuo.mapper.DeviceMapper;
import com.kezuo.service.IDeviceService;

/**
 * <p>
 * 机井设备 服务实现类
 * </p>
 *
 * @author zlzuo
 * @since 2018-08-27
 */
@Service("deviceService")
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {

    @Resource
    DeviceMapper deviceMapper;

    @Override
    public List<Device> selectDeviceList(Map<String, Object> queryMap) {
        return deviceMapper.selectDeviceList(queryMap);
    }

}
