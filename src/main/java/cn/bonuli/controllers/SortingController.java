package cn.bonuli.controllers;

import cn.bonuli.mappers.ExceptionLogMapper;
import cn.bonuli.mappers.PortMapper;
import cn.bonuli.mappers.SortingHistoryMapper;
import cn.bonuli.mappers.SortingMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SortingController
 *
 * @author D.jin
 * @date 2018/6/26
 */
@RestController
@RequestMapping("/api")
public class SortingController {
    final static Logger logger = LoggerFactory.getLogger(SortingController.class);

    final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    PortMapper portMapper;

    @Autowired
    SortingHistoryMapper sortingHistoryMapper;

    @Autowired
    SortingMapper sortingMapper;

  /*  @Autowired
    RabbitMQEventBusFactory rabbitMQEventBusFactory;*/

    @Autowired
    ExceptionLogMapper exceptionLogMapper;


}
