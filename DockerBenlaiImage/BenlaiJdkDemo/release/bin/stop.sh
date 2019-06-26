#!/usr/bin/env bash
options=
while [[ $# -gt 0 ]]
do
  key="$1"
  case $key in
    -9|--force)
      options='-9'
      ;;
    *)
      ;;
  esac
  shift
done
base_dir=$(dirname $0)/..
pid_file=$base_dir/app.pid
if [ ! -f "$pid_file" ]; then
  echo 'no pid file'
  exit 1
fi
pid=`cat $pid_file`
max_attempt=5
for attempt in $(seq 1 $max_attempt); do
  echo "killing [pid: $pid] with [options: $options], attempt: $attempt"
  kill $options $pid
  sleep 3
  if ps -p $pid > /dev/null; then
    echo "[pid: $pid] is still running"
  else
    rm -f $pid_file
    echo "[pid: $pid] is killed"
    exit 0
  fi
done
echo "kill [pid: $pid] failed"
exit 1