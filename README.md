项目结构：

- src
  - Constant.java    包含此项目的主要常量
  - MyServer.java    项目主类，启动类
  - MyThread.java   线程实现
  - Response.java    服务器响应主类
  - Util.java               封装的一些多次用到的方法，静态调用
  - content(存放html界面)
    - index.html(主页面，必有)
    - 404.html(必有)
    - 其他，可自行添加

运行方法，进入bin目录，在命令行中使用 java MyServer启动，在浏览器输入localhost:8080/index.html即可看到效果

测试git