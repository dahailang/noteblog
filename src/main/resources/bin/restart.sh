###java env
export JAVA_HOME=/usr/java/jdk1.8.0_144
export JRE_HOME=$JAVA_HOME/jre

##restart noteblog
/home/tengine/noteblog/bin/stop.sh
sleep 5
/home/tengine/noteblog/bin/start.sh
