# java-demo-springcloud




- > ### 打开设置
    > - Editor > File and Code Templates
    > - Scheme: "Default"
    >> - 选择："Includes"
    >>> - 选择："File Header"文件
    >>>> - 在这个文件中添加一个类注释什么的
    >>>>> ```
    >>>>>     /**
    >>>>>      * @author suyh
    >>>>>      * @since ${YEAR}-${MONTH}-${DAY}
    >>>>>      */
    >>>>> ```
    >>> - 新建文件"FileCopyright"
    >>>> ```
    >>>>     /**
    >>>>      * Copyright XXXXXXXX......
    >>>>      */
    >>>> ```
    >> - 选择："Files"
    >>> - 找到并选定："Class" "Interface" "Enum" "AnnotationType"
    >>>> - 解析指定文件并插入到此位置
    >>>>> ```
    >>>>>     #parse("FileCopyright.java")
    >>>>>     #if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
    >>>>>     #parse("File Header.java")
    >>>>>     public class ${NAME} {
    >>>>>     }
    >>>>> ```

> Hystrix 
> 参考博客:
> ```html
> https://blog.csdn.net/nb7474/article/details/84440822
> https://blog.csdn.net/weixin_34256074/article/details/86128105
> ``` 
> 属性常量:
> ```java
> import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
> ```

