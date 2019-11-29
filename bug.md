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



2019.10.16


数据库表设计时为考虑到关键字问题，有一张表的表名为group为关键字，插入数据时一直出问题。
解决：更换表名

mybatis注入mapper时重复error while adding the mapper
解决：
在mapper逆向工程生成时有重复生成问题，就是有重复的id，所以在生成时不能注入两次，在mapper.xml中将重复生成的删除


AJAX跨域请求问题：
在控制器中加入HttpServletResponse response
//设置响应头
response.setHeader("Access-Control-Allow-Origin", "*");

2019.11.24
有些签到任务一定要判断是否已经在数据库中存在，要先进行查找，如果有的话就更新，没有才插入，不能一个任务参加了多次。

