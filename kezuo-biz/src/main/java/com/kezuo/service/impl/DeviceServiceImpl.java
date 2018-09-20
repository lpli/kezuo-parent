package com.kezuo.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kezuo.Client;
import com.kezuo.common.CommSend;
import com.kezuo.common.ConstsKezuo;
import com.kezuo.exception.ClientException;
import com.kezuo.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource
    DeviceMapper deviceMapper;

    @Override
    public List<Device> selectDeviceList(Map<String, Object> queryMap) {
        return deviceMapper.selectDeviceList(queryMap);
    }

    @Override
    public List<Device> selectEffectiveDeviceList() {
        Map<String, Object> queryMap = new HashMap<>();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, ConstsKezuo.DAYS_PRE);
        queryMap.put("beginTime", TimeUtil.convertDateToString(cal.getTime(), TimeUtil.YMDHMS));
        List<Device> list = this.selectDeviceList(queryMap);
        return list;
    }

    @Override
    public void creatClient(Device device) throws Exception {
        String stcd = CommSend.getStcdFromDevice(device);
        Client client = new Client(ConstsKezuo.HOST, ConstsKezuo.PORT, stcd, ConstsKezuo.PRODUCTNO, ConstsKezuo.PRODUCTPWD, ConstsKezuo.YEAR, ConstsKezuo.MONTH, Integer.valueOf(stcd), ConstsKezuo.INTERVAL, CommSend.getSerialFromDevice(device));
        client.connect();
        ConstsKezuo.CLIENT_MAP.remove(stcd);
        ConstsKezuo.CLIENT_MAP.put(stcd, client);
        ConstsKezuo.DEVICE_MAP.put(stcd, device);
        log.info("creat client ====" + device.toString());
    }

}
