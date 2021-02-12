# Java Bean Convert Mysql DDL Script

#### 1. 介绍
 - 概述：
    - 把Java实体类转换成Mysql数据库建表DDL语句。
    - [插件地址](https://plugins.jetbrains.com/plugin/15980-javabean2ddl)
- 翻译功能说明 && 免责声明：
    1. 插件使用翻译功能均属于官方（腾讯翻译，百度翻译）支持的正规API接口。
    2. 开启翻译功能需要网络支持，需自行注册相应的翻译公司账户，申请接口调用的AppId和秘钥。（使用百度标准版和腾讯免费账户即可）
    3. 使用翻译功能时，只需要申请百度提供的标准版和腾讯翻译的免费账户即可。如果想要提高插件的体验，使用了收费的百度**高级版**或腾讯**付费账户**，产生的费用与本插件开发者无关。
    4. 百度翻译申请：[百度通用翻译API](http://api.fanyi.baidu.com/product/11) , Appid和秘钥查看 [秘钥](http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer)
    5. 腾讯翻译申请：[腾讯机器翻译](https://cloud.tencent.com/product/tmt), 秘钥管理 [秘钥](https://console.cloud.tencent.com/cam/capi)
#### 2. 软件架构
软件开发说明：  
- 插件开发环境：

![](https://img.shields.io/badge/JDK-11-green.svg)
![](https://img.shields.io/badge/IDEA%20VERSION-2020.3%20+-yellow.svg)
![](https://img.shields.io/badge/GRADLE%20VERSION-6.5-red.svg)

- 插件适用版本：     

![](https://img.shields.io/badge/IDEA%20VERSION-2019.2%20+-blue.svg)


#### 3. 目录层级
```text
java-bean-2-ddl-idea-plugin
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── breezes
    │   │           └── javabean2ddl
    │   │               ├── constant // 常量
    │   │               ├── enums    // 枚举
    │   │               ├── main     // 核心入口
    │   │               ├── model    // 实体
    │   │               ├── service  // 业务处理(ddl语句构建)
    │   │               ├── ui       // 界面
    │   │               └── utils    // 工具类
    │   └── resources
    │       └── META-INF
    └── test
        ├── java
        └── resources

```

#### 4. 快速使用
- 插件入口: `command + N -> CreateTableDDL`
- 配置面板入口: `Preferences -> Tools -> Java Bean To DDL`

#### 5. 界面预览

![输入图片说明](https://images.gitee.com/uploads/images/2021/0212/142006_9d2d92c8_5081865.png "QQ20210211-175445@2x.png")

![输入图片说明](https://images.gitee.com/uploads/images/2021/0212/142019_f6f45b7a_5081865.png "QQ20210211-175738@2x.png")

![输入图片说明](https://images.gitee.com/uploads/images/2021/0212/142102_69539040_5081865.png "QQ20210211-175314@2x.png")

![输入图片说明](https://images.gitee.com/uploads/images/2021/0212/142045_a82cf70c_5081865.png "QQ20210211-175639@2x.png")