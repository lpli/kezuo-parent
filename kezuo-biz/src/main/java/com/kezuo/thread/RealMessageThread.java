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
import com.kezuo.entity.dto.CommMessage;
import com.kezuo.entity.dto.UseWaterRecordMsg;
import com.kezuo.exception.ClientException;
import com.kezuo.util.CommUtils;
import com.kezuo.util.Crc8Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

/**
 * 实时数据发送线程
 *
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
                            //发送用水记录数据
                            if (1001 == Integer.valueOf(CommSend.getStcdFromDevice(device))) {
                                this.sendUseWaterRecodMsg(device,client);
                            }

                            //发送实时数据
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
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                log.error(null, e);
                break;
            } catch (Exception e) {
                log.error(null, e);
            }
        }
    }

    /**
     * 发送用水记录
     *
     * @param device
     */
    private void sendUseWaterRecodMsg(Device device, Client client) {
        try {
            UseWaterRecordMsg uw = new UseWaterRecordMsg();
            uw.setRecodeType(1);
            uw.setRecodeTm(new Date());

            uw.setOpenUserNo("420420100101");
            uw.setOpenTm(new Date());
            uw.setOpenWay(4);

            uw.setCloseUserNo("420420100101");
            uw.setCloseTm(new Date());
            uw.setCloseWay(4);

            uw.setMeterMode(0);

            uw.setUserElecNum(100.1f);
            uw.setUserWaterNum(101.1f);
            uw.setUserTmNum(102.2f);
            uw.setUserMoneyNum(103.3f);

            uw.setBeginWaterNum(104.4f);
            uw.setEndWaterNum(105.5f);
            uw.setBeginElecNum(106.6f);
            uw.setEndElecNum(107.7f);

            uw.setTotalElecNum(108.8f);
            uw.setTotalWaterNum(109.9f);
            uw.setTotalTmNum(110.0f);
            uw.setTotalMoneyNum(111.1f);

            CommMessage cm = new CommMessage();
            CommSend.setDeviceUpMsgCommInfo(cm);
            cm.setStation(Integer.valueOf(CommSend.getStcdFromDevice(device)));
            cm.setAfn(0x6a);
            cm.setContent(CommSend.getHexStringFromObject(uw));


            Message msg = client.sendMessage(cm);
            log.info(String.format("client[%s]收到用水过程数据确认：%s", cm.getStation(), Crc8Util.formatHexString(msg.toHexString())));

//            for (Message msg : cm.toMessage()) {
//                log.info(String.format("device[%s]发送用水过程数据：%s", cm.getStation(), Crc8Util.formatHexString(msg.toHexString())));
//            }
        } catch (Exception e) {
            log.error(null, e);
        }
    }
}
