#!/usr/bin/env bash

base_dir=$(dirname $0)/..

pid_file=$base_dir/app.pid

status_stopped='stopped'
status_running='running'

if [ ! -f "$pid_file" ]; then
  echo $status_stopped
  exit 0
fi

pid=`cat $pid_file`

if ps -p $pid > /dev/null
then
    echo $status_running
else
    echo $status_stopped
fi