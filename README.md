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
