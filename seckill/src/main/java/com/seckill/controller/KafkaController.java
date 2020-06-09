package com.seckill.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * kafka usage
 * @author kai
 * @date 2020-06-07 9:35
 */
@RestController
@Slf4j
public class KafkaController {

    private KafkaTemplate<String, String > kafkaTempalte;

    @Autowired
    public KafkaController(KafkaTemplate<String, String > kafkaTempalte) {
        this.kafkaTempalte = kafkaTempalte;
    }

    @PostMapping("/kafka")
    public JSONObject kafkaProvider(@RequestBody JSONObject req){
        log.info("request param -> msg:{}", req);

        String msg = req.getString("msg");
        kafkaTempalte.send("springTopic", msg);

        return new JSONObject().fluentPut("code", "success").fluentPut("msg", msg);

    }
}
