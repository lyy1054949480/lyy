/*
package com.example.lyy.jobhandler;

import com.alibaba.fastjson.JSON;
import com.longfor.wydt.som.online.services.TaskListService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
*/
/**//*

*/
/**
 * @Title: 设置每日工单中未完成工单
 * @Description: 设置每日工单中未完成工单
 * @Date: 2019/7/4 10:29
 * @Auther: zhaoxuezhao
 *//*

@JobHandler(value="incompleteTaskJobHandler")
@Component
public class IncompleteTaskJobHandler extends IJobHandler {

    @Resource
    private TaskListService taskListService;

    */
/**
     * 每日 0点0分0秒 (cron = "0 0 0 * * ? ")执行一次
     * <p>
     * 计划结束时间在前日，但是未分派，未接单，未完成的工单置为未完成状态
     *//*

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        XxlJobLogger.log("扫描未完成工单-开始-> " + currentDate);
        List<Long> longs = taskListService.selectByPlanTime(date);
        XxlJobLogger.log("扫描未完成工单-结果-> " + JSON.toJSONString(longs));
        XxlJobLogger.log("扫描未完成工单-结束-> " + currentDate);
        return ReturnT.SUCCESS;
    }
}
*/
