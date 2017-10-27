package org.demo.entity;

/**
 * @auther HuJiaqi
 * @createTime 2017年10月26日
 * @discription
 */
public class TaskEntity extends UserEntity{

    private String taskId;
    private String nextUserId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getNextUserId() {
        return nextUserId;
    }

    public void setNextUserId(String nextUserId) {
        this.nextUserId = nextUserId;
    }
}
