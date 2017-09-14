export JAVA_HOME=/usr/java/jdk1.8.0_144
export PATH=$JAVA_HOME/bin:$PATH
##stop
PIDS=`ps -ef | grep noteblog | grep -v grep |awk '{print $2}'`
if [ -z "$PIDS" ]; then
    echo "ERROR: The noteblog does not started!"
    exit 1
fi

echo -e "Stopping the noteblog ...\c"
for PID in $PIDS ; do
    kill -9 $PID > /dev/null 2>&1
done
##start
nohup java -jar /home/tengine/noteblog/noteblog-0.0.1-SNAPSHOT.war > /home/tengine/noteblog/logs/nohup.log 2>&1 &