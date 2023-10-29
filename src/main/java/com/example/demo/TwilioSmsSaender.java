package com.example.demo;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("twilio")
public class TwilioSmsSaender implements SMSSender{
    private final TwilioConfig twilioConfig;
    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSaender.class);

    public TwilioSmsSaender(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }


    @Override
    public void sendSms(SmsRequest smsRequest) {
        if(isPhoneNumberValid(smsRequest.getPhoneNumber())){
            PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
            String message = smsRequest.getMessage();
            MessageCreator creator = Message.creator(to,from,message);
            creator.create();
            LOGGER.info("Enviar sms" + smsRequest);

        }else{
            throw new IllegalArgumentException("Numero de telefone [" + smsRequest.getPhoneNumber() + "] nao eh valido");
        }

    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        return true;

    }
}
