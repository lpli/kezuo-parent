/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kezuo.init;

import com.kezuo.common.ConstsKezuo;
import com.kezuo.entity.Device;
import com.kezuo.service.IAsyncService;
import com.kezuo.service.IDeviceService;
import com.kezuo.thread.RealMessageThread;
import com.kezuo.util.mytool.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * spring 启动事件
 *
 * @author zlzuo
 */
@Component
public class SystemInit implements ApplicationListener<ContextStartedEvent> {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    /**
     * 初始化登录和注册
     */
    @Override
    public void onApplicationEvent(ContextStartedEvent event) {
        try {
            ApplicationContext context = event.getApplicationContext();
            IDeviceService deviceService = (IDeviceService) context.getBean("deviceService");
            IAsyncService  asyncService = (IAsyncService) context.getBean("asyncService");
            Map<String, Object> queryMap = new HashMap<>();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, ConstsKezuo.DAYS_PRE);
            queryMap.put("beginTime", TimeUtil.convertDateToString(cal.getTime(), TimeUtil.YMDHMS));
            List<Device> list = deviceService.selectDeviceList(queryMap);
            Device device;
            for (int i = 0, size = ConstsKezuo.calcClentNum(list.size()); i < size; i++) {
                device = list.get(i);
                try {
                    asyncService.executeAsync(device, i);
                } catch (Exception e) {
                    log.error(null, e);
                }
            }

            Thread realMessageThread = new Thread(new RealMessageThread());
            realMessageThread.start();

            log.info("systerm init success(系統初始化成功！)");
        } catch (Exception e) {
            log.error(null, e);
        }

    }
}
