package org.demo.controller;

import org.demo.entity.TaskEntity;
import org.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auther HuJiaqi
 * @createTime 2017年10月26日
 * @discription
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * @auther HuJiaqi
     * @createTime 2017年10月26日
     * @discription 开启任务
     */
    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public String commitTask(@PathVariable String userId) {
        TaskEntity task = taskService.createTask(userId);
        if (task == null) {
            return "只有userId为3的员工可以开启任务";
        }
        return "userId为" + userId + "的" + task.getLevel() + "开启taskId为" + task.getTaskId() + "的任务，下一步的处理人的userId应为" + task.getNextUserId();
    }

    /**
     * @auther HuJiaqi
     * @createTime 2017年10月26日
     * @discription 完成任务
     */
    @RequestMapping(value = "/{userId}/{taskId}", method = RequestMethod.POST)
    public String commitTask(@PathVariable String userId, @PathVariable String taskId) {
        TaskEntity task = taskService.commitTask(userId, taskId);
        if ("0".equals(task.getNextUserId())) {
            return "userId为" + userId + "的" + task.getLevel() + "完成taskId为" + task.getTaskId() + "的任务，此任务终结";
        }
        return "userId为" + userId + "的" + task.getLevel() + "完成taskId为" + task.getTaskId() + "的任务，下一步的处理人的userId应为" + task.getNextUserId();
    }

    /**
     * @auther HuJiaqi
     * @createTime 2017年10月26日
     * @discription 查询任务
     */
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public String getTask(@PathVariable String userId) {
        List<TaskEntity> taskList = taskService.getTaskList(userId);
        if (CollectionUtils.isEmpty(taskList)) {
            return "userId为" + userId + "暂时没有任务";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("userId为" + userId + "现在有" + taskList.size() + "条任务，taskId分别为：|");
        String nextUserId = null;
        for (TaskEntity taskEntity : taskList) {
            sb.append(taskEntity.getTaskId() + "|");
            if (nextUserId == null) {
                nextUserId = taskEntity.getNextUserId();
            }
        }
        sb.append("，下一步的处理人的userId应为" + nextUserId);
        return sb.toString();
    }

}
