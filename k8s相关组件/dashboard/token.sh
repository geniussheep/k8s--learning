#!/bin/sh

kubectl -n kube-system describe secret $(kubectl -n kube-system get secret | grep dashboard-token | awk '{print $1}')
