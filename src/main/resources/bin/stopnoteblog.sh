PIDS=`ps -ef | grep noteblog |grep java | grep -v grep |awk '{print $2}'`

if [ -z "$PIDS" ]; then
    echo " WARN : The noteblog does not started!"
fi
echo -e "Stopping the noteblog ...\n"
for PID in $PIDS ;do
    echo "will kill $PID"
    kill -9 $PID
done
