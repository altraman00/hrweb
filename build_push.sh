#!/usr/bin/env bash

env=${ZP_ENV}
if [[ ! $env ]]; then
  env="test"
fi
echo $env

set -eo pipefail

# 单模块，注意docker build 后面那个点，表示当前跟Dockerfile在同一路径下，
# 如果是多模块，需要添加${module}，总之，始终保持需要build的对象跟该模块的Dockerfile在同一个层级下
module="resume-parse"
docker build -t "fec/zhaopin/${module}:${env}" .
docker tag fec/zhaopin/${module}:${env} 192.168.0.5:5000/fec/zhaopin/${module}:${env}
# 推送到私有的Regeistry上
docker push 192.168.0.5:5000/fec/zhaopin/${module}:${env}


# 如果是多模块的结构，采用以下方式，注意docker build最后面是模块名${module}，
# 跟上面单模块的不一样，但模块的是一个点，表示当前目录，
# docker build的模块的路径要跟Dockerfile在同一个层级
#modules=( resume-parse )
#for module in "${modules[@]}"; do
#    docker build -t "fec/zhaopin/${module}:${env}" ${module}
#    docker tag fec/zhaopin/${module}:${env} 192.168.0.5:5000/fec/zhaopin/${module}:${env}
#    docker push 192.168.0.5:5000/fec/zhaopin/${module}:${env}
#done

