export JAVA_HOME=/usr/java/jdk1.8.0_144
export PATH=$JAVA_HOME/bin:$PATH
##stop
/bin/sh /home/tengine/noteblog/bin/stopnoteblog.sh 
##start noteblog
/bin/sh /home/tengine/noteblog/bin/startnoteblog.sh
