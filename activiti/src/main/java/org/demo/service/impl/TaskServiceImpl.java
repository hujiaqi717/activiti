package org.demo.service.impl;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.demo.dao.UserDao;
import org.demo.entity.TaskEntity;
import org.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther HuJiaqi
 * @createTime 2017年10月26日
 * @discription
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private org.activiti.engine.TaskService taskService;

    @Autowired
    private UserDao userDao;

    @Override
    public TaskEntity createTask(String userId) {
        TaskEntity taskEntity = new TaskEntity();
        String level = userDao.getLevelByUserId(userId);
        taskEntity.setUserId(userId);
        taskEntity.setLevel(level);
        // 只有员工能开启任务
        if (!("emp").equals(level)) {
            return null;
        }
        // 开启任务
        ProcessInstance myProcess_1 = runtimeService.startProcessInstanceByKey("myProcess_1");
        taskEntity.setTaskId(myProcess_1.getId());
        taskEntity.setNextUserId("3");
        return taskEntity;
    }

    @Override
    public TaskEntity commitTask(String userId, String taskId) {
        TaskEntity taskEntity = new TaskEntity();
        String level = userDao.getLevelByUserId(userId);
        taskEntity.setUserId(userId);
        taskEntity.setLevel(level);
        //完成任务
        taskService.complete(taskId);
        taskEntity.setTaskId(taskId);
        switch (userId) {
            case "3":
                taskEntity.setNextUserId("2");
                break;
            case "2":
                taskEntity.setNextUserId("1");
                break;
            case "1":
                taskEntity.setNextUserId("0");
        }
        return taskEntity;
    }

    @Override
    public List<TaskEntity> getTaskList(String userId) {
        String level = userDao.getLevelByUserId(userId);
        List<Task> list = taskService.createTaskQuery().taskAssignee(level).list();
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<TaskEntity> taskEntityList = new ArrayList<>();
        for (Task task : list) {
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setUserId(userId);
            taskEntity.setLevel(level);
            taskEntity.setNextUserId(userId);
            taskEntity.setTaskId(task.getId());
            taskEntityList.add(taskEntity);
        }
        return taskEntityList;
    }

}
