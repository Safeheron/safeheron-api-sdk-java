package com.safeheron.demo.webhook;

import com.safeheron.client.webhook.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;


/**
 * @author xigaoku
 */
@Slf4j
public class WebHookTest {

    static Map<String, String> config;

    static WebhookConverter webhookConverter;

    @BeforeClass
    public static void beforeClass() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        File file = new File("src/test/resources/demo/api/webhook/config.yaml");
        InputStream inputStream = new FileInputStream(file);
        config = yaml.load(inputStream);
        webhookConverter = new WebhookConverter(config.get("safeheronWebHookRsaPublicKey"), config.get("webHookRsaPrivateKey"));
    }

    @Test
    public void testWebHook() throws Exception {
        //The webHook received by the controller
        WebHook webHook = new WebHook();
        WebHookBizContent webHookBizContent = webhookConverter.convert(webHook);
        System.out.println(String.format("Decrypt webHookBizContent: %s", webHookBizContent));

        //According to different types of WebHook, the customer handles the corresponding type of business logic.
        if (webHookBizContent.getEventDetail() instanceof TransactionParam) {
            //todo the customer handles the business logic whose callback type is the transaction
        } else if (webHookBizContent.getEventDetail() instanceof MPCSignParam) {
            //todo the customer handles the business logic whose callback type is the MPCSign
        } else if (webHookBizContent.getEventDetail() instanceof Web3SignParam) {
            //todo the customer handles the business logic whose callback type is the Web3Sign
        }

        WebHookResponse webHookResponse = new WebHookResponse();
        webHookResponse.setMessage("SUCCESS");
        webHookResponse.setCode("200");
        //The customer returns WebHookResponse after processing the business logic.
    }
}
