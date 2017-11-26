##获取dump的方法：
1. 在oom时dump：JVM参数：-XX:+HeapDumpOnOutOfMemoryError
2. 交互式环境下dump：
1. JVM参数：-XX:+HeapDumpOnCtrlBreak
2. 用外部tools：jmap -dump:format=b,file=<filename.hprof> <pid>
3. 用外部tools：jconsole
4. 用外部工具：MAT
5. kill -3 <pid>
6. jstack -l <pid> > <dumpfile>

##查看GC情况
1. jstat  -gcutil 10118 2000


1.配置方法
在JAVA_OPTIONS变量中增加
-XX:+HeapDumpOnOutOfMemoryError  -XX:HeapDumpPath=${目录}

2.参数说明
-XX:+HeapDumpOnOutOfMemoryError参数表示当JVM发生OOM时，自动生成DUMP文件。
-XX:HeapDumpPath=${目录}参数表示生成DUMP文件的路径，也可以指定文件名称，
例如：-XX:HeapDumpPath=${目录}/java_heapdump.hprof。如果不指定文件名，
默认为：java_<pid>_<date>_<time>_heapDump.hprof。