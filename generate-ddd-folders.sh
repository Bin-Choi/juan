#!/bin/bash

BASE_PATH=src/main/java/com/juanbuild/juan
DOMAINS=("user" "unitprice" "worker" "instruction")

for DOMAIN in "${DOMAINS[@]}"
do
  mkdir -p "$BASE_PATH/$DOMAIN/domain"
  mkdir -p "$BASE_PATH/$DOMAIN/repository"
  mkdir -p "$BASE_PATH/$DOMAIN/application"
  mkdir -p "$BASE_PATH/$DOMAIN/controller"
  mkdir -p "$BASE_PATH/$DOMAIN/dto"
done

mkdir -p "$BASE_PATH/common"
mkdir -p "$BASE_PATH/config"

echo "✅ DDD 스타일 폴더 구조가 생성되었습니다!"
