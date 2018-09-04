package com.kezuo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.kezuo.common.CommSend;
import com.kezuo.common.ConstsKezuo;
import com.kezuo.entity.Device;
import com.kezuo.service.IAsyncService;
import com.xx.Client;

@Service("asyncService")
public class IAsyncServiceImpl implements IAsyncService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync(Device device, int index) throws Exception {
        String stcd = CommSend.getStcdFromDevice(device);
        //Client client = new Client("tripnet.unilogger.cn", 10260, "test", 120, 6, 18, 7, 1, 60,"00000000000000000018621818050001");
        //Client client = new Client(ConstsKezuo.HOST, ConstsKezuo.PORT);
        Client client = new Client(ConstsKezuo.HOST, ConstsKezuo.PORT, stcd, ConstsKezuo.PRODUCTNO, ConstsKezuo.PRODUCTPWD, ConstsKezuo.YEAR, ConstsKezuo.MONTH, Integer.valueOf(stcd), ConstsKezuo.INTERVAL, CommSend.getSerialFromDevice(device));
        client.connect();

        //等待客户端启动
//        Thread.sleep(5000);
        ConstsKezuo.CLIENT_MAP.put(stcd, client);

        log.info("device.toString() :" + device.toString());

        //链路信息
//        Message linkResp = client.sendMessage(CommSend.getLinkCheckMsgFromDevice(device), 6);

        //注册信息
//        Message regResp = client.sendMessage(CommSend.getRegMsgFromDevice(device), 6);
//        log.info(String.format("i[%s]stcd[%s]收到注册信息确认：%s", index, stcd, regResp.toHexString()));

    }
}
