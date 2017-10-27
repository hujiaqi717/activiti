package org.demo.service;

import org.demo.entity.TaskEntity;

import java.util.List;

/**
 * @auther HuJiaqi
 * @createTime 2017年10月26日
 * @discription
 */
public interface TaskService {

    /**
     * @auther HuJiaqi
     * @createTime 2017年10月26日
     * @discription 开启任务
     */
    TaskEntity createTask(String userId);

    /**
     * @auther HuJiaqi
     * @createTime 2017年10月26日
     * @discription 完成任务
     */
    TaskEntity commitTask(String userId, String taskId);

    /**
     * @auther HuJiaqi
     * @createTime 2017年10月26日
     * @discription 获取待处理的任务列表
     */
    List<TaskEntity> getTaskList(String userId);

}
