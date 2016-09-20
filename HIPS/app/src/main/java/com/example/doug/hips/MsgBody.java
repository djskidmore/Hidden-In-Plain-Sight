package com.example.doug.hips;

/**
 * Created by Doug on 9/20/2016.
 */
public class MsgBody {
    public static volatile String msgBody = "";

    public void updateMsgBody(String newMsgBody) {
        msgBody = newMsgBody;
    }

    public String getMsgBody() { return msgBody; }
}
