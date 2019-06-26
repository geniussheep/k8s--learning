#!/usr/bin/env bash
base_dir=$(dirname $0)/..
cd $base_dir

pid_file=app.pid

if [ -f "$pid_file" ]; then
  pid=`cat $pid_file`
  if ps -p $pid > /dev/null; then
    echo "[pid: $pid] is running"
    exit 1
  fi
  rm -f $pid_file
fi

if [ -z "$JAVA_HOME" ]; then
  JAVA="java"
else
  JAVA="$JAVA_HOME/bin/java"
fi

log_dir=logs
log_file=$log_dir/app.out

if [ ! -d "$log_dir" ]; then
  mkdir -p "$log_dir"
fi

config_dir=conf
CLASSPATH=.:libs/*
main_class=com.benlai.bdk.data.examples.Application

java_options="-Dlog4j.configuration=$config_dir/log4j.properties"

nohup $JAVA $java_options -cp $CLASSPATH ${main_class}> "$log_file" 2>&1 </dev/null &

pid=$!
echo $pid > $pid_file
echo "$pid is started"