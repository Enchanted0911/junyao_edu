package icu.junyao.msmService.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import icu.junyao.msmService.service.MsmService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author wu
 */
@Service
public class MsmServiceImpl implements MsmService {



    /**
     * 发送短信的方法
     * @param param
     * @param phone
     * @return
     */
    @Override
    public boolean send(Map<String, Object> param, String phone) throws Exception {
        if(StringUtils.isEmpty(phone)){
            return false;
        }
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId("LTAI5tDv9y5SMuXmJFm86S4v")
                // 您的AccessKey Secret
                .setAccessKeySecret("EYtrPskQ3gNScwvQRLocCUgQWbD76K");
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        Client client = new Client(config);
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phone)
                // 签名名称
                .setSignName("签名名称")
                // 模板code
                .setTemplateCode("SMS_7125****")
                .setTemplateParam(JSONObject.toJSONString(param));
        // 复制代码运行请自行打印 API 的返回值
        try {
            SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
            return "ok".equals(sendSmsResponse.body.code);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
