
---
activiti
```
这个工程是初次学习springboot与activiti整合的代码
仅为初学demo，数据库操作为模拟操作
```
- activiti主要搭建流程
1. maven引入springboot、activiti、jdbc的关键包
2. 配置application.properties，主要是数据源
3. 初始化数据库配置（springboot完成）
4. idea使用actiBPM画图，图片不是必要引入的
5. 注入RuntimeService开启任务
6. 注入TaskService查询和处理任务
- restful风格接口
1. 使用协议动词标志动作
2. 接口名为名词，后面/内容中包括参数，用url直接标志业务
3. 使用状态码返回接口
- bpmn2.0规范
1. http://www.omg.org/spec/BPMN/2.0/
2. 暂未细看，但发现eclipse与idea中最新的插件均是遵循的bpmn2.0规范，可直接使用

---
