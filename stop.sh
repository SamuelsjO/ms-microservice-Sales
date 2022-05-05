# !/bin/bash
NOCOLOR='\033[0m'
YELLOW='\033[1;33m'

docker container rm -f $(docker ps -aq) && docker rmi $(docker images -q) -f && docker volume prune -f && docker system prune -f && docker network prune -f