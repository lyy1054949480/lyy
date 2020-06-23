/*
package com.example.lyy.jobhandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.longfor.wydt.commons.enums.IndicatorEnum;
import com.longfor.wydt.commons.redis.BaseRedisDAO;
import com.longfor.wydt.commons.wechat.dto.WeChatMessageResult;
import com.longfor.wydt.som.dto.wechat.TaskInfoDTO;
import com.longfor.wydt.som.enums.mdm.TaskTypeEnum;
import com.longfor.wydt.som.enums.online.TaskRedisPrefixEnum;
import com.longfor.wydt.som.online.services.ProjectService;
import com.longfor.wydt.som.online.services.TaskListService;
import com.longfor.wydt.som.vo.mdm.ProjectResultVO;
import com.longfor.wydt.som.vo.mdm.TaskListVO;
import com.longfor.wydt.som.wechat.service.WeChatMpService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

*/
/**
 * @Title: 工单状态刷新
 * @Description: 工单状态刷新
 * @Date: 2019/7/4 17:59
 * @Auther: zhaoxuezhao
 *//*

@JobHandler(value = "taskStateJobHandler")
@Component
public class TaskStateJobHandler extends IJobHandler {


    @Resource(name = ProjectService.SERVICE_ID)
    private ProjectService projectService;

    @Resource(name = TaskListService.SERVICE_ID)
    private TaskListService taskListService;

    @Resource(name = WeChatMpService.SERVICE_ID)
    private WeChatMpService weChatMpService;

    @Resource(name = BaseRedisDAO.SERVICE_ID)
    private BaseRedisDAO redisDAO;


    */
/**
     * 每30秒 (cron = "0,30 * * * * ? ")执行一次
     * <p>
     * 以项目维度刷新工单状态
     *//*

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        XxlJobLogger.log("更新工单超时状态 开始 -> " + currentDate);
        List<ProjectResultVO> projectList = projectService.getAllProject();
        if (CollectionUtils.isNotEmpty(projectList)) {
            List<Long> overTimes = new ArrayList<>();
            List<Long> rectifyOverTimes = new ArrayList<>();
            List<Long> workOvers = new ArrayList<>();
            List<Long> rectifyWorkOvers = new ArrayList<>();

            for (ProjectResultVO projectResultVO : projectList) {
                XxlJobLogger.log("更新工单状态 项目 -> " + JSON.toJSONString(projectResultVO));
                String overTimeKey = TaskRedisPrefixEnum.OVERTIME.getValue() + projectResultVO.getPkProject();
                if (redisDAO.exists(overTimeKey)) {
                    Map<String, JSONObject> map = redisDAO.getMap(overTimeKey);
                    Collection<JSONObject> values = map.values();
                    values.forEach(System.out::println);
                    for (JSONObject o : values) {
                        TaskInfoDTO taskInfo = JSON.parseObject(o.toJSONString(), TaskInfoDTO.class);
                        XxlJobLogger.log("更新工单签到状态 redis对象 -> " + JSON.toJSONString(taskInfo));
                        WeChatMessageResult weChatMessageResult = weChatMpService.sendMessage(TaskRedisPrefixEnum.OVERTIME.getValue(), taskInfo);
                        XxlJobLogger.log("更新工单签到状态 推送结果 -> " + JSON.toJSONString(weChatMessageResult));
                        if (null != weChatMessageResult.getPkTaskList()) {
                            if(taskInfo.getTaskListType().equalsIgnoreCase(TaskTypeEnum.RECTIFY_WORK.getValue())){
                                rectifyOverTimes.add(weChatMessageResult.getPkTaskList());
                            }else{
                                overTimes.add(weChatMessageResult.getPkTaskList());
                            }
                        }
                    }
                }
                String workOverKey = TaskRedisPrefixEnum.WORKOVER.getValue() + projectResultVO.getPkProject();
                if (redisDAO.exists(workOverKey)) {
                    Map<String, JSONObject> map = redisDAO.getMap(workOverKey);
                    Collection<JSONObject> values = map.values();
                    for (JSONObject object : values) {
                        TaskInfoDTO taskInfo = JSON.parseObject(object.toJSONString(), TaskInfoDTO.class);
                        XxlJobLogger.log("更新工单办结状态 redis对象 -> " + JSON.toJSONString(taskInfo));
                        WeChatMessageResult weChatMessageResult = weChatMpService.sendMessage(TaskRedisPrefixEnum.WORKOVER.getValue(), taskInfo);
                        XxlJobLogger.log("更新工单办结状态 推送结果 -> " + JSON.toJSONString(weChatMessageResult));
                        if (null != weChatMessageResult.getPkTaskList()) {
                            if(taskInfo.getTaskListType().equalsIgnoreCase(TaskTypeEnum.RECTIFY_WORK.getValue())){
                                rectifyWorkOvers.add(weChatMessageResult.getPkTaskList());
                            }else{
                                workOvers.add(weChatMessageResult.getPkTaskList());
                            }
                        }
                    }
                }
                //更新签到超时工单
                TaskListVO taskListVO = new TaskListVO();
                if (CollectionUtils.isNotEmpty(overTimes)){
                    taskListVO.setOvertimeState(IndicatorEnum.YES.getCode());
                    taskListService.updateTaskBatch(taskListVO,overTimes);
                    XxlJobLogger.log("更新工单签到超时状态  -> " + overTimes);
                }
                //更新办结结超时工单
                if (CollectionUtils.isNotEmpty(workOvers)){
                    taskListVO.setOvertimeState(null);
                    taskListVO.setWorkOverState(IndicatorEnum.YES.getCode());
                    taskListService.updateTaskBatch(taskListVO,workOvers);
                    taskListVO.setWorkOverState(null);
                    XxlJobLogger.log("更新工单办结超时状态  -> " + workOvers);
                }
                //整改类工单签到超时
                if(CollectionUtils.isNotEmpty(rectifyOverTimes)){
                    taskListVO.setOvertimeState(IndicatorEnum.YES.getCode());
                    taskListVO.setTaskType(TaskTypeEnum.RECTIFY_WORK.getCode());
                    taskListService.updateTaskBatch(taskListVO,rectifyOverTimes);
                    XxlJobLogger.log("更新工单整改签到超时状态  -> " + rectifyOverTimes);
                }
                //整改类工单办结超时
                if(CollectionUtils.isNotEmpty(rectifyWorkOvers)){
                    taskListVO.setOvertimeState(null);
                    taskListVO.setTaskType(TaskTypeEnum.RECTIFY_WORK.getCode());
                    taskListVO.setWorkOverState(IndicatorEnum.YES.getCode());
                    taskListService.updateTaskBatch(taskListVO,rectifyWorkOvers);
                    XxlJobLogger.log("更新工单整改办结超时状态  -> " + rectifyWorkOvers);
                }
            }
        }
        return ReturnT.SUCCESS;
    }
}
*/
