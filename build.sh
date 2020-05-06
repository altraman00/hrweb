#!/usr/bin/env bash

set -eo pipefail

# 单模块方式 如果是多模块，需要添加${module}，
# 总之，始终保持需要build的对象跟该模块的Dockerfile在同一个层级下
module="resume-parse"
docker build -t "fec/zhaopin/${module}:latest" .


# 多模块方式
#modules=( resume-parse )
#for module in "${modules[@]}"; do
#    docker build -t "fec/zhaopin/${module}:latest" ${module}
#done



