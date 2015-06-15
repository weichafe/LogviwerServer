package com.larrainvial.logviwer.event;

import com.larrainvial.logviwer.vo.ClientVO;
import com.larrainvial.trading.emp.Event;
import java.net.Socket;

public class SendDatatoClientEvent extends Event {

    public ClientVO clientVO;

    public SendDatatoClientEvent(Object source, ClientVO clientVO) {
        super(source);
        this.clientVO = clientVO;
    }
}
