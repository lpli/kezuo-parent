/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kezuo.thread;

import com.kezuo.Client;
import com.kezuo.common.CommSend;
import com.kezuo.common.ConstsKezuo;
import com.kezuo.core.dto.Message;
import com.kezuo.entity.Device;
import com.kezuo.exception.ClientException;
import com.kezuo.util.CommUtils;
import com.kezuo.util.Crc8Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 *
 * 实时数据发送线程
 * @author zlzuo
 */
public class RealMessageThread implements Runnable {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run() {
        while (true) {
            try {
                Device device;
                Client client;
                for (Map.Entry<String, Client> entry : ConstsKezuo.CLIENT_MAP.entrySet()) {
                    try {
                        client = entry.getValue();
                        String stcd = entry.getKey();
                        device = ConstsKezuo.DEVICE_MAP.get(entry.getKey());
                        if (CommUtils.notNull(client) && client.isRunning()) {
                            Message msg = client.sendMessage(CommSend.getRealMsgFromDevice(device));
                            log.info(String.format("client[%s]收到实时数据确认：%s", stcd, Crc8Util.formatHexString(msg.toHexString())));
                        } else {
                            //重连
                            client.connect();
                        }
                    } catch (ClientException e) {
                        log.error("客户端异常", e);
                    }
                }
                Thread.sleep(6000L);
            } catch (InterruptedException e) {
                log.error(null, e);
                break;
            } catch (Exception e) {
                log.error(null, e);
            }
        }
    }

}
