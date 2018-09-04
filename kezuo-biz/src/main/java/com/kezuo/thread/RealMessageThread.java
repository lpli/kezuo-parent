/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kezuo.thread;

import com.kezuo.common.CommSend;
import com.kezuo.common.ConstsKezuo;
import com.kezuo.common.SpringContextUtil;
import com.kezuo.entity.Device;
import com.kezuo.init.SystemInit;
import com.kezuo.service.IAsyncService;
import com.kezuo.service.IDeviceService;
import com.kezuo.util.mytool.CommUtils;
import com.kezuo.util.mytool.TimeUtil;
import com.xx.Client;
import com.xx.core.dto.Message;
import com.xx.core.dto.RealtimeMessage;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author zlzuo
 */
public class RealMessageThread implements Runnable {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private IDeviceService deviceService;
    private IAsyncService asyncService;

    public RealMessageThread() {
        deviceService = (IDeviceService) SpringContextUtil.getBean("deviceService");
        asyncService = (IAsyncService) SpringContextUtil.getBean("asyncService");
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(60000);
            } catch (Exception e) {
                log.error(null, e);
            }

            try {
                Map<String, Object> queryMap = new HashMap<String, Object>();
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_MONTH, ConstsKezuo.DAYS_PRE);
                queryMap.put("beginTime", TimeUtil.convertDateToString(cal.getTime(), TimeUtil.YMDHMS));
                List<Device> list = deviceService.selectDeviceList(queryMap);
                Client client;
                Device device;
                String stcd = "";
                for (int i = 0, size = ConstsKezuo.calcClentNum(list.size()); i < size; i++) {
                    device = list.get(i);
                    //for (Device device : list) {//定时发送实时数据
                    try {
                        stcd = CommSend.getStcdFromDevice(device);
                        client = ConstsKezuo.CLIENT_MAP.get(stcd);
                        if (CommUtils.notNull(client) && client.isRunning()) {
                            Message msg = client.sendMessage(CommSend.getRealMsgFromDevice(device), 6);
                            //log.info("收到实时数据确认：" + msg.toHexString());
                            log.info(String.format("i[%s]client[%s]收到实时数据确认：%s", i, stcd, msg.toHexString()));
                        }else  {
                            try {
                                ConstsKezuo.CLIENT_MAP.remove(stcd);
                                asyncService.executeAsync(device, i);
                            } catch (Exception e) {
                                log.error(null, e);
                            }
                        }
                    } catch (Exception e) {
                        log.error(stcd + "-client发送实时数据异常：", e);
                    }
                }

            } catch (Exception e) {
                log.error(null, e);
            }
        }
    }

}
