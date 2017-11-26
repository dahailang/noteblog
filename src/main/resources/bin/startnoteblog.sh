echo -e "Starting the noteblog ...\n"
JAVA_OPTS="-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${DEPLOY_DIR}/logs/java.hprof -Dfile.encoding=UTF-8"
nohup java $JAVA_OPTS -jar /home/tengine/noteblog/noteblog-0.0.1-SNAPSHOT.war > /home/tengine/noteblog/logs/nohup.log 2>&1 &
/home/tengine/nginx/sbin/nginx -s reload
