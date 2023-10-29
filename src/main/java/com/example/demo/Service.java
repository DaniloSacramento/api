package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@org.springframework.stereotype.Service
public class Service {
    private final SMSSender smsSender;
    @Autowired
    public Service(@Qualifier("twilio") TwilioSmsSaender twilioSmsSaender, SMSSender smsSender) {
        this.smsSender = smsSender;
    }
    public void sendSms(SmsRequest smsRequest){
        smsSender.sendSms(smsRequest);
    }
}
