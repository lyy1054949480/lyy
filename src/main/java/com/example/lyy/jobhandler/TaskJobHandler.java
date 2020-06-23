/*
package com.example.lyy.jobhandler;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.longfor.wydt.commons.enums.IndicatorEnum;
import com.longfor.wydt.commons.redis.BaseRedisDAO;
import com.longfor.wydt.som.dto.online.ServicePersonalJobDTO;
import com.longfor.wydt.som.dto.online.WorkAreaItemsArrangeSchedulingDTO;
import com.longfor.wydt.som.enums.mdm.SchedulingTypeEnum;
import com.longfor.wydt.som.enums.mdm.StatusEnum;
import com.longfor.wydt.som.enums.mdm.TaskSheetStateEnum;
import com.longfor.wydt.som.enums.mdm.TaskTypeEnum;
import com.longfor.wydt.som.enums.online.RedisIncrementEnum;
import com.longfor.wydt.som.enums.online.RegularTypeEnum;
import com.longfor.wydt.som.enums.online.WorkListTypeEnum;
import com.longfor.wydt.som.online.services.*;
import com.longfor.wydt.som.online.utils.DateUtil;
import com.longfor.wydt.som.vo.mdm.*;
import com.longfor.wydt.som.vo.online.ServicePersonalVO;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

*/
/**
 * @Title: 任务工单生成
 * @Description: 任务工单生成
 * @Date: 2019/6/10 11:26
 * @Auther: zhaoxuezhao
 *//*

@JobHandler(value = "taskJobHandler")
@Component
public class TaskJobHandler extends IJobHandler {


    @Resource(name = ProjectService.SERVICE_ID)
    private ProjectService projectService;

    @Resource(name = WorkAreaService.SERVICE_ID)
    private WorkAreaService workAreaService;

    @Resource(name = StandardWorkService.SERVICE_ID)
    private StandardWorkService standardWorkService;

    @Resource(name = WorkAreaItemsService.SERVICE_ID)
    private WorkAreaItemsService workAreaItemsService;

    @Resource(name = WorkPostService.SERVICE_ID)
    private WorkPostService workPostService;

    @Resource(name = SchedulingService.SERVICE_ID)
    private SchedulingService schedulingService;



    @Resource(name = TaskListService.SERVICE_ID)
    private TaskListService taskListService;

    @Resource(name = TaskWorkItemsService.SERVICE_ID)
    private TaskWorkItemsService taskWorkItemsService;


    @Resource(name = ServicePersonalService.SERVICE_ID)
    private ServicePersonalService servicePersonalService;

    @Resource(name = BaseRedisDAO.SERVICE_ID)
    private BaseRedisDAO redisDAO;

    */
/**
     * 每日 0点-1点间 (cron = "0 0 0 * * ? ")执行一次
     * <p>
     * 以项目维度生成工单，并派发工单为服务人员
     *//*

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        String currentDate = new SimpleDateFormat("yyyyMMdd").format(DateUtil.getNextDate(Time.valueOf("00:00:00"), 1));
        XxlJobLogger.log("任务工单生成 -> " + currentDate);
        //项目工单
        Map<Long, List<TaskListVO>> taskInProject = Maps.newHashMap();
        List<ProjectResultVO> projectList = projectService.getAllProject();
        XxlJobLogger.log("任务工单生成 -> 获取项目列表：" + JSON.toJSONString(projectList));
        if (CollectionUtils.isNotEmpty(projectList)) {
            WorkAreaItemsVO areaItemsVO = new WorkAreaItemsVO();
            areaItemsVO.setState(StatusEnum.ENABLED.getCode());
            WorkAreaSearchVO workAreaSearchVO = new WorkAreaSearchVO();
            WorkPostVO workPostVO = new WorkPostVO();
            //遍历项目，按项目生成工单，以项目为单位
            for (ProjectResultVO projectResultVO : projectList) {
                XxlJobLogger.log("工单生成-所在项目开始：" + JSON.toJSONString(projectResultVO));
                taskInProject.put(projectResultVO.getPrimaryKey(), Lists.newArrayList());

                //获取项目内工作区
                workAreaSearchVO.setRelationProject(projectResultVO.getPkProject());
                List<WorkAreaSearchResultVO> workAreas = workAreaService.getWorkAreasBySearch(workAreaSearchVO);
                if (CollectionUtils.isEmpty(workAreas)) {
                    continue;
                }
                //获取项目内所有岗位
                workPostVO.setRelationProject(projectResultVO.getPkProject());
                List<WorkPostVO> workPosts = workPostService.getWorkPostByRelationProject(workPostVO);

                //设置工作项查询项目主键
                areaItemsVO.setRelationProject(projectResultVO.getPkProject());
                //工作区生成工单
                for (WorkAreaSearchResultVO workArea : workAreas) {
                    //设置工作项查询工作区主键
                    XxlJobLogger.log("工单生成-所在工作区：" + JSON.toJSONString(workArea));
                    areaItemsVO.setWorkArea(workArea.getPkWorkArea());
                    for (WorkPostVO workPost : workPosts) {
                        XxlJobLogger.log("工单生成-所在岗位：" + JSON.toJSONString(workPost));
                        StringBuilder key = new StringBuilder("taskJob-");
                        key.append(projectResultVO.getPkProject() + "-");
                        key.append(workArea.getPkWorkArea() + "-");
                        key.append(workPost.getPkWorkPost() + "-");
                        key.append(currentDate);
                        if (!redisDAO.exists(key.toString())) {
                            //设置工作项查询岗位主键，获取项目内工作区内岗位对应工作项
                            areaItemsVO.setWorkPost(workPost.getPkWorkPost());
                            List<WorkAreaItemsArrangeSchedulingDTO> workAreaItems = workAreaItemsService.getWorkAreaItemsByProjectId(areaItemsVO);
                            if (CollectionUtils.isEmpty(workAreaItems)) {
                                continue;
                            }
                            //获取岗位对应班次
                            SchedulingVO scheduling = schedulingService.getScheduling(workPost.getWorkScheduling());
                            if (null == scheduling) {
                                continue;
                            }
                            //工单生成
                            Map<Integer, TaskListVO> taskListVO = getTaskListVO(scheduling);
                            XxlJobLogger.log("工单生成-初步生成工单：" + JSON.toJSONString(taskListVO));
                            //工单的工作项
                            Map<Integer, List<WorkAreaItemsVO>> workItemMap = Maps.newHashMap();
                            workItemMap.put(1, Lists.newArrayList());
                            workItemMap.put(2, Lists.newArrayList());
                            workItemMap.put(3, Lists.newArrayList());


                            for (WorkAreaItemsVO workAreaItemsVO : workAreaItems) {
                                //判断工作项定时模式
                                boolean checkWorkAreaItem = false;
                                if(null==workAreaItemsVO.getRegularType()){
                                    continue;
                                }
                                if (RegularTypeEnum.EVERYDAY.getCode() == workAreaItemsVO.getRegularType()) {
                                    checkWorkAreaItem = true;
                                } else if (RegularTypeEnum.EVERYWEEK.getCode() == workAreaItemsVO.getRegularType()) {
                                    String weekTypeWorkDay = workAreaItemsVO.getWeekTypeWorkDay();
                                    int nextWeekDay = DateUtil.getNextWeekDay();
                                    if (weekTypeWorkDay.contains(String.valueOf(nextWeekDay))) {
                                        checkWorkAreaItem = true;
                                    }
                                } else if (RegularTypeEnum.EVERYMONTH.getCode() == workAreaItemsVO.getRegularType()) {
                                    Date nextDate = DateUtil.getNextDate(Time.valueOf("00:00:00"), 1);
                                    checkWorkAreaItem = DateUtil.checkDateRange(nextDate, workAreaItemsVO.getMonthStartDay(), workAreaItemsVO.getMonthDuration());
                                }

                                if (checkWorkAreaItem) {
                                    XxlJobLogger.log("工单生成-工作项：" + JSON.toJSONString(workAreaItemsVO));
                                    Time arrangeEndTime = workAreaItemsVO.getEndTime();
                                    Time arrangeStartTime = workAreaItemsVO.getStartTime();
                                    //排班时间不能为空
                                    if (null != arrangeEndTime && null != arrangeStartTime) {
                                        if (SchedulingTypeEnum.DURATIVE.getCode() == scheduling.getSchedulingType()) {
                                            //持续性工单生成
                                            Time schedulingStartTime = scheduling.getStartTime();
                                            Time schedulingEndTime = scheduling.getEndTime();
                                            if (DateUtil.checkTime(arrangeStartTime, arrangeEndTime, schedulingStartTime, schedulingEndTime)) {
                                                //持续工单工作项
                                                workItemMap.get(1).add(workAreaItemsVO);
                                            } else {
                                                //待分配工单工作项
                                                workItemMap.get(3).add(workAreaItemsVO);
                                            }
                                        } else {
                                            //阶段性工单工作项
                                            Time schedulingStartTime = scheduling.getStartTime();
                                            Time schedulingEndTime = scheduling.getEndTime();
                                            Time schedulingContinueStartTime = scheduling.getContinueStartTime();
                                            Time schedulingContinueEndTime = scheduling.getContinueEndTime();
                                            if (DateUtil.checkTime(arrangeStartTime, arrangeEndTime, schedulingStartTime, schedulingEndTime)) {
                                                //阶段性工单上午
                                                workItemMap.get(1).add(workAreaItemsVO);
                                            } else if (DateUtil.checkTime(arrangeStartTime, arrangeEndTime, schedulingContinueStartTime, schedulingContinueEndTime)) {
                                                //阶段性工单下午
                                                workItemMap.get(2).add(workAreaItemsVO);
                                            } else if (DateUtil.isOverlap(arrangeStartTime, arrangeEndTime, schedulingStartTime, schedulingEndTime) && DateUtil.isOverlap(arrangeStartTime, arrangeEndTime, schedulingContinueStartTime, schedulingContinueEndTime)) {
                                                //排班开始时间在上午班，排班结束时间在下午班
                                                if (arrangeStartTime.compareTo(schedulingEndTime) == 0 && arrangeEndTime.compareTo(schedulingContinueStartTime) == 0) {
                                                    workItemMap.get(3).add(workAreaItemsVO);
                                                } else {
                                                    workItemMap.get(1).add(workAreaItemsVO);
                                                    workItemMap.get(2).add(workAreaItemsVO);
                                                }
                                            } else {
                                                //待分配工单
                                                workItemMap.get(3).add(workAreaItemsVO);
                                            }
                                        }
                                    }
                                }

                            }
                            XxlJobLogger.log("工单生成-工单工作项：" + JSON.toJSONString(workItemMap));
                            //将工单和工单工作项关联并入库
                            for (Integer i : workItemMap.keySet()) {
                                //持续性工单（阶段性工单上午）
                                if (CollectionUtils.isNotEmpty(workItemMap.get(i))) {
                                    TaskListVO taskListVO1 = taskListVO.get(i);
                                    String taskCode = redisDAO.getIncrementNum(RedisIncrementEnum.TASK_PREFIX.getKey() + currentDate, RedisIncrementEnum.TASK_PREFIX.getKey(), currentDate);
                                    taskListVO1.setTaskCode(taskCode);
                                    taskListVO1.setTaskName(TaskTypeEnum.DAILY_CLEANING.getValue());
                                    taskListVO1.setTaskState(TaskSheetStateEnum.TO_DISPATCH.getCode());
                                    taskListVO1.setTaskType(TaskTypeEnum.DAILY_CLEANING.getCode());
                                    taskListVO1.setWorkListType(WorkListTypeEnum.NORMAL.getCode());
                                    taskListVO1.setRelationProject(workArea.getRelationProject());
                                    taskListVO1.setRelationWorkPost(workPost.getPkWorkPost());
                                    taskListVO1.setSupplier(workArea.getSupplier());
                                    //插入工单
                                    taskListService.insertJobTask(taskListVO1);
                                    XxlJobLogger.log("任务工单插入 -> " + JSON.toJSONString(taskListVO1));
                                    List<WorkAreaItemsVO> workAreaItemsVOS = workItemMap.get(i);
                                    List<TaskWorkItemsVO> taskItems = getTaskItems(taskListVO1, workAreaItemsVOS, i);
                                    //滞留工单无班次时间符合，选取工作项排班开始时间最小值，结束时间最大值
                                    if (i == 3) {
                                        Date planStartDate = null;
                                        Date planEndDate = null;
                                        if (CollectionUtils.isNotEmpty(taskItems)) {
                                            taskItems.sort(new Comparator<TaskWorkItemsVO>() {
                                                @Override
                                                public int compare(TaskWorkItemsVO o1, TaskWorkItemsVO o2) {
                                                    return o1.getWorkStartDate().compareTo(o2.getWorkStartDate());
                                                }
                                            });
                                            planStartDate = taskItems.get(0).getWorkStartDate();
                                            taskItems.sort(new Comparator<TaskWorkItemsVO>() {
                                                @Override
                                                public int compare(TaskWorkItemsVO o1, TaskWorkItemsVO o2) {
                                                    return o1.getWorkEndDate().compareTo(o2.getWorkEndDate());
                                                }
                                            });
                                            planEndDate = taskItems.get(taskItems.size() - 1).getWorkEndDate();
                                        }
                                        if (null != planStartDate && null != planEndDate) {
                                            taskListVO1.setPlanStartDate(planStartDate);
                                            taskListVO1.setPlanEndDate(planEndDate);
                                            taskListService.updateTaskPlaneTime(taskListVO1);
                                        }
                                    }
                                    taskWorkItemsService.insertBatchJob(taskItems);
                                    XxlJobLogger.log("任务工单工作项插入 -> " + JSON.toJSONString(taskItems));
                                    //正常工单进入后续分配，待分配工单直接滞留
                                    if (i != 3) {
                                        taskInProject.get(projectResultVO.getPrimaryKey()).add(taskListVO1);
                                    }
                                    redisDAO.set(key.toString(), key.toString(), 86400L);
                                }
                            }
                        }
                    }
                }

                XxlJobLogger.log("工单生成-所在项目工单生成结束：" + JSON.toJSONString(projectResultVO));
            }


        }
        XxlJobLogger.log("任务工单生成结束" + currentDate);
        //按项目进行工单分配
        if (!taskInProject.isEmpty()) {
            XxlJobLogger.log("任务工单分配开始 " + currentDate);
            for (Long pkProject : taskInProject.keySet()) {
                List<TaskListVO> taskListVOS = taskInProject.get(pkProject);
                if (CollectionUtils.isNotEmpty(taskListVOS)) {
                    ServicePersonalJobDTO servicePersonalVO = new ServicePersonalJobDTO();
                    for (TaskListVO taskList : taskListVOS) {
                        XxlJobLogger.log("工单分配 -> 工单：" + taskList.getPkTaskList());
                        Long workAreaId = taskWorkItemsService.getByTaskId(taskList.getPkTaskList()).get(0).getWorkAreaId();
                        servicePersonalVO.setRelationProject(taskList.getRelationProject());
                        servicePersonalVO.setSupplier(taskList.getSupplier());
                        servicePersonalVO.setWorkPost(taskList.getRelationWorkPost());
                        servicePersonalVO.setWorkArea(workAreaId);
                        servicePersonalVO.setServiceEndtDate(DateUtil.getNextDate(Time.valueOf("00:00:00"), 1));
                        List<ServicePersonalVO> personalVOS = servicePersonalService.getPersonalJob(servicePersonalVO);
                        if (CollectionUtils.isNotEmpty(personalVOS)) {
                            //筛选今日上班的人员
                            Iterator<ServicePersonalVO> iterator = personalVOS.iterator();
                            while (iterator.hasNext()) {
                                ServicePersonalVO next = iterator.next();
                                if (null == next.getWorkPostDay() || !next.getWorkPostDay().contains(String.valueOf(DateUtil.getNextWeekDay()))) {
                                    iterator.remove();
                                }
                            }
                        }
                        //无人符合条件时，工单滞留，还是待分配状态
                        if (CollectionUtils.isNotEmpty(personalVOS)) {
                            TaskListVO taskListVO = new TaskListVO();
                            //分发给工单数量最小的服务人员
                            Map<ServicePersonalVO, Integer> taskNum = Maps.newHashMap();
                            for (ServicePersonalVO personalVO : personalVOS) {
                                taskListVO.setServicePersonal(personalVO.getPkServicePersonal());
                                taskListVO.setTaskType(TaskTypeEnum.DAILY_CLEANING.getCode());
                                Integer integer = taskListService.countPersonalTask(taskListVO);
                                taskNum.put(personalVO, integer);
                            }
                            //筛选工单数量最少的人员进行分配
                            List<Map.Entry<ServicePersonalVO, Integer>> list = new ArrayList(taskNum.entrySet());
                            Collections.sort(list, new Comparator<Map.Entry<ServicePersonalVO, Integer>>() {
                                @Override
                                public int compare(Map.Entry<ServicePersonalVO, Integer> o1, Map.Entry<ServicePersonalVO, Integer> o2) {
                                    return o1.getValue() - o2.getValue();
                                }
                            });
                            ServicePersonalVO servicePersonal = list.get(0).getKey();
                            //将工单分配给指定人员
                            taskList.setServicePersonal(servicePersonal.getPkServicePersonal());
                            taskListService.saveServicePerson(taskList, IndicatorEnum.NO.getCode());
                            XxlJobLogger.log("工单分配成功 -> " + JSON.toJSONString(taskList));
                        }
                    }
                }
            }
        }
        return ReturnT.SUCCESS;
    }

    */
/**
     * 工单和工作区内工作项生成 -> 任务管理工作项
     *//*

    public List<TaskWorkItemsVO> getTaskItems(TaskListVO taskListVO, List<WorkAreaItemsVO> workAreaItemsVOList, Integer type) {
        List<TaskWorkItemsVO> taskListItem = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(workAreaItemsVOList)) {
            //工作区名称
            WorkAreaVO workAreaVO = workAreaService.selectByPrimaryKey(workAreaItemsVOList.get(0).getWorkArea());
            StandardWorkVO standardWorkVO = new StandardWorkVO();
            for (WorkAreaItemsVO workAreaItem : workAreaItemsVOList) {
                TaskWorkItemsVO taskWorkItemsVO = new TaskWorkItemsVO();
                //所属项目
                taskWorkItemsVO.setRelationProject(taskListVO.getRelationProject());
                //工作区
                taskWorkItemsVO.setWorkAreaId(workAreaItem.getWorkArea());
                //工作项名称
                taskWorkItemsVO.setWorkAreaName(workAreaVO.getWorkAreaName());
                //工作项
                taskWorkItemsVO.setWorkItemId(workAreaItem.getWorkItem());
                //工作项名称
                standardWorkVO.setPkStandardWork(workAreaItem.getWorkItem());
                standardWorkVO = standardWorkService.getStandardWorkById(standardWorkVO);
                taskWorkItemsVO.setWorkItemName(standardWorkVO.getWorkItem());
                //工作区工作项
                taskWorkItemsVO.setWorkAreaItem(workAreaItem.getPkWorkAreaItems());
                //优先级
                taskWorkItemsVO.setPriority(workAreaItem.getPriority());
                // 工作范围描述
                taskWorkItemsVO.setWorkRangeDescribe(workAreaItem.getWorkScope());
                if (DateUtil.getNextDate(workAreaItem.getEndTime(), 1).after(taskListVO.getPlanEndDate()) && type != 3) {
                    taskWorkItemsVO.setWorkEndDate(taskListVO.getPlanEndDate());
                } else {
                    if (!workAreaItem.getEndTime().after(workAreaItem.getStartTime()) && type == 3) {
                        taskWorkItemsVO.setWorkEndDate(DateUtil.getNextDate(workAreaItem.getEndTime(), 2));
                    } else {
                        taskWorkItemsVO.setWorkEndDate(DateUtil.getNextDate(workAreaItem.getEndTime(), 1));
                    }
                }

                if (!DateUtil.getNextDate(workAreaItem.getStartTime(), 1).after(taskListVO.getPlanStartDate()) && type != 3) {
                    taskWorkItemsVO.setWorkStartDate(taskListVO.getPlanStartDate());
                } else {
                    taskWorkItemsVO.setWorkStartDate(DateUtil.getNextDate(workAreaItem.getStartTime(), 1));
                }
                //设置状态
                taskWorkItemsVO.setState(TaskSheetStateEnum.TO_DISPATCH.getCode());
                //所属任务工单编码
                taskWorkItemsVO.setTaskCode(taskListVO.getTaskCode());
                //所属任务工单任务工单id
                taskWorkItemsVO.setWorkTask(taskListVO.getPkTaskList());
                taskListItem.add(taskWorkItemsVO);
            }
        }


        return taskListItem;
    }

    */
/**
     * 根据班次类型生成阶段性工单和持续性工单,并设置工单计划开始时间和计划结束时间
     * 1.持续性工单或阶段性工单-上午
     * 2.阶段性工单下午
     * 3.待分配工单
     *//*

    public Map<Integer, TaskListVO> getTaskListVO(SchedulingVO scheduling) {
        //设置计划开始时间、计划结束时间、任务类别(日常保洁)
        Map<Integer, TaskListVO> taskListVOMap = Maps.newHashMap();
        TaskListVO taskOne = new TaskListVO();
        TaskListVO taskTwo = new TaskListVO();
        TaskListVO taskThree = new TaskListVO();

        taskOne.setPlanStartDate(DateUtil.getNextDate(scheduling.getStartTime(), 1));
        //跨天
        if (!scheduling.getEndTime().after(scheduling.getStartTime())) {
            taskOne.setPlanEndDate(DateUtil.getNextDate(scheduling.getEndTime(), 2));
        } else {
            taskOne.setPlanEndDate(DateUtil.getNextDate(scheduling.getEndTime(), 1));
        }
        taskThree.setPlanStartDate(DateUtil.getNextDate(scheduling.getStartTime(), 1));
        taskThree.setPlanEndDate(DateUtil.getNextDate(scheduling.getEndTime(), 1));
        taskListVOMap.put(1, taskOne);
        if (SchedulingTypeEnum.PERIOD.getCode() == scheduling.getSchedulingType()) {
            //阶段性下午任务
            taskTwo.setPlanStartDate(DateUtil.getNextDate(scheduling.getContinueStartTime(), 1));
            if (!scheduling.getContinueEndTime().after(scheduling.getContinueStartTime())) {
                taskTwo.setPlanEndDate(DateUtil.getNextDate(scheduling.getContinueEndTime(), 2));
            } else {
                taskTwo.setPlanEndDate(DateUtil.getNextDate(scheduling.getContinueEndTime(), 1));
            }
            taskListVOMap.put(2, taskTwo);
            taskThree.setPlanStartDate(DateUtil.getNextDate(scheduling.getStartTime(), 1));
            taskThree.setPlanEndDate(DateUtil.getNextDate(scheduling.getContinueEndTime(), 1));
        }
        taskListVOMap.put(3, taskThree);
        return taskListVOMap;

    }


}
*/
