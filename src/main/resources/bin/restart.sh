export JAVA_HOME=/usr/java/jdk1.8.0_144
export PATH=$JAVA_HOME/bin:$PATH
mkdir -p /home/tengine/noteblog/logs
##stop
/bin/sh /home/tengine/noteblog/bin/stopnoteblog.sh 
##start noteblog
/bin/sh /home/tengine/noteblog/bin/startnoteblog.sh
