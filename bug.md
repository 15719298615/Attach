# Attach

在attach开发中遇到的bug，以及后期优化时遇到的问题

2019.10.14

使用postman插入数据库时中文乱码
解决：在postman中加入Content-Type   application/json;charset=utf-8

并且把tomcat的默认编码格式改为utf-8
400错误，服务器发送的时间格式不对。


2019.10.15
在写代码时，遇到不符合规范Operator '==' cannot be applied to 'long', 'null'
这个问题是因为那个对象是基本类型,不是包装类型,所以不能为空，把基本类型 改为包装类型,问题解决


后端返回前端代码时乱码
解决方法：在controller的requestmapping中加入：produces="application/json;charset=utf-8"
