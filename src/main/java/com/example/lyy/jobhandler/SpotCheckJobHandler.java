/*
package com.example.lyy.jobhandler;

import com.longfor.wydt.commons.redis.BaseRedisDAO;
import com.longfor.wydt.som.enums.online.CheckStateEnum;
import com.longfor.wydt.som.online.dao.WorkAreaItemsDAO;
import com.longfor.wydt.som.online.services.ProjectService;
import com.longfor.wydt.som.online.services.SpotCheckService;
import com.longfor.wydt.som.online.utils.DateUtil;
import com.longfor.wydt.som.vo.mdm.ProjectResultVO;
import com.longfor.wydt.som.vo.online.CheckListVO;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * @title SpoCheckScheduleTask
 * @description 定时生成抽查工单
 * @date 2019/6/3 10:58
 * @author yindq
 *//*

@JobHandler(value="spotCheckJobHandler")
@Service
public class SpotCheckJobHandler extends IJobHandler {
    */
/**
     * 抽查工单标题
     *//*

    private static final String CHECK_NAME = "日常项目保洁-抽查";
    */
/**
     * 抽查类别
     *//*

    private static final String CHECK_TYPE = "日常项目保洁";
    */
/**
     * redis 得到流水号类型
     *//*

    private static final String REDIS_TYPE = "CC";

    @Resource(name = ProjectService.SERVICE_ID)
    private ProjectService projectService;

    @Resource(name = SpotCheckService.SERVICE_ID)
    private SpotCheckService spotCheckService;

    @Resource(name = BaseRedisDAO.SERVICE_ID)
    private BaseRedisDAO redisDAO;

    @Resource(name = WorkAreaItemsDAO.DAO_ID)
    private WorkAreaItemsDAO workAreaItemsDAO;

    */
/**
     * 每月1日/16日 0点-1点间 (cron = "0 0 0 1,16 * ? ")
     * 将上一个周期生成的工单中待完成的工单置为未完成,生成本周期新的抽查工单
     * 以项目维度分发给项目内有抽查权限的人员
     *//*

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        // 将上一个周期生成的工单中待完成的工单置为未完成
        LocalDateTime modifyTime = LocalDateTime.now();
        XxlJobLogger.log("将上一个周期生成的工单中待完成的工单置为未完成,任务执行时间: {}", modifyTime);
        spotCheckService.changeStatus();

        // 生成新的工单
        LocalDateTime createTime = LocalDateTime.now();
        XxlJobLogger.log("开始生成抽查工单,任务执行时间: {}", createTime);
        List<ProjectResultVO> projectTree = projectService.getAllProject();
        List<ProjectResultVO> allProject = new ArrayList<>();
        for (ProjectResultVO projectResultVO : projectTree) {
            List<Long> workAreas = workAreaItemsDAO.countWorkAreaByProject(projectResultVO.getPkProject());
            if(workAreas.size() != 0){
                allProject.add(projectResultVO);
            }
        }
        XxlJobLogger.log("一个项目生成一个抽查工单，项目数量: {}", allProject.size());
        for (ProjectResultVO projectVO : allProject) {
            CheckListVO vo = new CheckListVO();
            // 抽查工单编码
            String formatDate = DateUtil.formatDate(createTime);
            vo.setCheckCode(redisDAO.getIncrementNum(formatDate, REDIS_TYPE));
            // 抽查工单标题
            vo.setCheckName(CHECK_NAME);
            // 状态
            vo.setCheckState((byte)CheckStateEnum.TO_COMPLETED.getCode());
            // 抽查类别
            vo.setCheckType(CHECK_TYPE);
            // 所属项目
            vo.setRelationProject(projectVO.getPkProject());
            // 时限
            vo.setPlanStartDate(DateUtil.getStartDate(createTime));
            vo.setPlanEndDate(DateUtil.getEndDate(createTime));
            spotCheckService.save(vo);
        }
        XxlJobLogger.log("任务执行完毕");
        return ReturnT.SUCCESS;
    }

}
*/
