package cn.bonuli.controllers;

import cn.bonuli.mappers.ExceptionLogMapper;
import cn.bonuli.values.ExceptionDTO;
import cn.bonuli.values.ExceptionLog;
import cn.bonuli.values.ExceptionValue;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * ExceptionController
 *
 * @author D.jin
 * @date 2018/7/2
 */
@RestController
@RequestMapping("/exception")
public class ExceptionController {
    final static Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @Autowired
    ExceptionLogMapper exceptionLogMapper;

    @GetMapping("/info")
    @ResponseBody
    public ExceptionValue info(HttpServletRequest request, @RequestParam("pageSize") int pageSize, @RequestParam("currentPage") int currentPage) {
        try {
            logger.info("pageSize:" + pageSize);
            logger.info("currentPage:" + currentPage);
            int total = exceptionLogMapper.count(null);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<ExceptionLog> exceptionLogs = exceptionLogMapper.query(null, pageSize, (currentPage - 1) * pageSize);
            List<ExceptionDTO> exceptionLogList = Lists.newArrayList();
            exceptionLogs.forEach(i -> {
                ExceptionDTO exceptionDTO = new ExceptionDTO(i.getCartonNumber(), i.getException(), sdf.format(i.getSortingAt()));
                exceptionLogList.add(exceptionDTO);
            });
            ExceptionValue exceptionValue = new ExceptionValue(true, total, exceptionLogList);
            return exceptionValue;
        } catch (Exception e) {
            e.printStackTrace();
            return new ExceptionValue(false, 0, null);

        }
    }

    @GetMapping("/search")
    @ResponseBody
    public ExceptionValue info(HttpServletRequest request, @RequestParam("cartonNumber") String cartonNumber, @RequestParam("pageSize") int pageSize, @RequestParam("currentPage") int currentPage) {
        try {
            logger.info("cartonNumber:" + cartonNumber);
            int total = exceptionLogMapper.count(cartonNumber);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<ExceptionLog> exceptionLogs = exceptionLogMapper.query(cartonNumber, pageSize, (currentPage - 1) * pageSize);
            List<ExceptionDTO> exceptionLogList = Lists.newArrayList();
            exceptionLogs.forEach(i -> {
                ExceptionDTO exceptionDTO = new ExceptionDTO(i.getCartonNumber(), i.getException(), sdf.format(i.getSortingAt()));
                exceptionLogList.add(exceptionDTO);
            });
            ExceptionValue exceptionValue = new ExceptionValue(true, total, exceptionLogList);
            return exceptionValue;
        } catch (Exception e) {
            e.printStackTrace();

            return new ExceptionValue(false, 0, null);

        }
    }
}
