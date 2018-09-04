package com.kezuo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kezuo.entity.Device;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 机井设备 Mapper 接口
 * </p>
 *
 * @author zlzuo
 * @since 2018-08-27
 */
public interface DeviceMapper extends BaseMapper<Device> {


    List<Device> selectDeviceList(Map<String, Object> queryMap);

}
