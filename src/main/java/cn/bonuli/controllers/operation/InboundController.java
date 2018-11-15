package cn.bonuli.controllers.operation;

import cn.bonuli.values.result.LayuiTableResult;
import cn.bonuli.values.result.ResultData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * InboundController
 *
 * @author D.jin
 * @date 2018/10/29
 */

@RestController
@RequestMapping("/operation")
public class InboundController {
    final static Logger logger = LoggerFactory.getLogger(InboundController.class);

    @GetMapping("/inbound")
    @ResponseBody
    public LayuiTableResult inboundQuery() {
        try {
            logger.info("inbound>>>:{}", new ObjectMapper().valueToTree(""));
            return LayuiTableResult.failed("");
        } catch (Exception e) {
            e.printStackTrace();
            return LayuiTableResult.failed(e.getMessage());
        }
    }
}
