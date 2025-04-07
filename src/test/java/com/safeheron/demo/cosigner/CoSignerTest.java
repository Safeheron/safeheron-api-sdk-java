package com.safeheron.demo.cosigner;

import com.safeheron.client.config.ActionEnum;
import com.safeheron.client.cosigner.*;
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
 * @author pengzhaofeng
 * @date 2023/8/1 19:27
 */
@Slf4j
public class CoSignerTest {

    static Map<String, String> config;

    static CoSignerConverter coSignerConverter;

    @BeforeClass
    public static void beforeClass() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        File file = new File("src/test/resources/demo/api/cosigner/config.yaml");
        InputStream inputStream = new FileInputStream(file);
        config = yaml.load(inputStream);
        coSignerConverter = new CoSignerConverter(config.get("apiPubKey"), config.get("bizPrivKey"));
    }

    @Test
    public void testCoSigner() throws Exception {
        //The CoSignerCallBack received by the controller
        CoSignerCallBackV3 coSignerCallBack = new CoSignerCallBackV3();
        CoSignerBizContentV3 coSignerBizContent = coSignerConverter.requestV3Convert(coSignerCallBack);
        System.out.println(String.format("Decrypt coSignerBizContent: %s", coSignerBizContent));

        //According to different types of CoSignerCallBack, the customer handles the corresponding type of business logic.
        if (coSignerBizContent.getDetail() instanceof TransactionApproval) {
            //todo the customer handles the business logic whose callback type is the transaction
        } else if (coSignerBizContent.getDetail() instanceof MPCSignApproval) {
            //todo the customer handles the business logic whose callback type is the MPCSign
        } else if (coSignerBizContent.getDetail() instanceof Web3SignApproval) {
            //todo the customer handles the business logic whose callback type is the Web3Sign
        }

        CoSignerResponseV3 coSignerResponse = new CoSignerResponseV3();
        coSignerResponse.setAction(ActionEnum.APPROVE.getCode());
        coSignerResponse.setApprovalId(coSignerBizContent.getApprovalId());
        Map<String, String> encryptResponse = coSignerConverter.responseV3Converter(coSignerResponse);
        //The customer returns encryptResponse after processing the business logic.
    }
}
