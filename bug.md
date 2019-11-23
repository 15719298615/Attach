# Attach

在attach开发中遇到的bug，以及后期优化时遇到的问题

2019.10.14

使用postman插入数据库时中文乱码
解决：在postman中加入Content-Type   application/json;charset=utf-8

并且把tomcat的默认编码格式改为utf-8
400错误，服务器发送的时间格式不对。
