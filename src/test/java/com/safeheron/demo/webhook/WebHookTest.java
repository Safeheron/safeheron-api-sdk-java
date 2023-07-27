package com.safeheron.demo.webhook;

import com.safeheron.client.webhook.WebHook;
import com.safeheron.client.webhook.WebHookResponse;
import com.safeheron.client.webhook.WebhookConverter;
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
 * @date 2022/10/12 19:27
 */
@Slf4j
public class WebHookTest {

    static Map<String, String> config;

    static WebhookConverter webhookConverter;

    @BeforeClass
    public static void beforeClass() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        File file = new File("src/test/resources/demo/webhook/config.yaml");
        InputStream inputStream = new FileInputStream(file);
        config = yaml.load(inputStream);
        webhookConverter = new WebhookConverter(config.get("safeheronWebHookRsaPublicKey"), config.get("webHookRsaPrivateKey"));

    }

    @Test
    public void testWebHook() throws Exception {
        //The webHook received by the controller
        WebHook webHook = new WebHook();
        String convert = webhookConverter.convert(webHook);
        System.out.println(String.format("Decrypt bizContent: %s", convert));

        WebHookResponse webHookResponse = new WebHookResponse();
        webHookResponse.setMessage("SUCCESS");
        webHookResponse.setCode("200");
        //The customer returns WebHookResponse after processing the business logic.
    }
}
