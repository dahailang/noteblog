PIDS=`ps -ef | grep noteblog | grep -v grep |awk '{print $2}'`
if [ -z "$PIDS" ]; then
    echo "ERROR: The noteblog does not started!"
    exit 1
fi

echo -e "Stopping the noteblog ...\c"
for PID in $PIDS ; do
    kill -9 $PID > /dev/null 2>&1
done

