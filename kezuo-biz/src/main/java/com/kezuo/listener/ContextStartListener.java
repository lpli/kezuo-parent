package com.kezuo.listener;

import com.kezuo.Client;
import com.kezuo.common.CommSend;
import com.kezuo.common.ConstsKezuo;
import com.kezuo.entity.Device;
import com.kezuo.service.IDeviceService;
import com.kezuo.thread.RealMessageThread;
import com.kezuo.thread.SendMessageThread;
import com.kezuo.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * 容器启动事件
 */
public class ContextStartListener implements ApplicationListener<ContextRefreshedEvent> {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    /**
     * 初始化登录和注册
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            ApplicationContext context = event.getApplicationContext();
            IDeviceService deviceService = (IDeviceService) context.getBean("deviceService");
            List<Device> list = deviceService.selectEffectiveDeviceList();
            Device device;
            for (int i = 0, size = ConstsKezuo.calcClentNum(list.size()); i < size; i++) {
                device = list.get(i);
                deviceService.creatClient(device);

                try {
                    Thread.sleep(100);
                }catch (InterruptedException ie){
                    log.error(null, ie);
                }
//                String stcd = CommSend.getStcdFromDevice(device);
//                Client client = new Client(ConstsKezuo.HOST, ConstsKezuo.PORT, stcd, ConstsKezuo.PRODUCTNO, ConstsKezuo.PRODUCTPWD, ConstsKezuo.YEAR, ConstsKezuo.MONTH, Integer.valueOf(stcd), ConstsKezuo.INTERVAL, CommSend.getSerialFromDevice(device));
//                client.connect();
//                ConstsKezuo.CLIENT_MAP.put(stcd, client);
//                ConstsKezuo.DEVICE_MAP.put(stcd, device);
//                log.info("start client ====" + device.toString());
            }

            Executor executor = (Executor) context.getBean("executor");
            //executor.execute(new RealMessageThread());
            executor.execute(new SendMessageThread());
            log.info("systerm init success(系統初始化成功！)");
        } catch (Exception e) {
            log.error(null, e);
        }

    }
}