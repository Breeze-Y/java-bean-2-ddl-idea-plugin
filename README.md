# Java Bean Convert Mysql DDL Script

#### 1. 介绍
把Java实体类转换成Mysql数据库建表DDL语句。

#### 2. 软件架构
软件开发说明：
插件是在以下环境中开发。

![](https://img.shields.io/badge/JDK-11%20+-green.svg)
![](https://img.shields.io/badge/IDEA%20VERSION-2020.3%20+-yellow.svg)

插件适用版本：     

![](https://img.shields.io/badge/IDEA%20VERSION-2020.3%20+-blue.svg)


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
`command + N -> CreateTableDDL`

#### 5. 界面预览

![输入图片说明](https://images.gitee.com/uploads/images/2021/0131/030430_f19b08e1_5081865.png "QQ20210131-030413@2x.png")
