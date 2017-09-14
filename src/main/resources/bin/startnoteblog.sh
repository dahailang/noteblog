echo -e "Starting the noteblog ...\n"
nohup java -jar /home/tengine/noteblog/noteblog-0.0.1-SNAPSHOT.war > /home/tengine/noteblog/logs/nohup.log 2>&1 &
