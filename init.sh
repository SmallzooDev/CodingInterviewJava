#!/bin/bash
url=$1
if [ -z "$url" ]; then
    echo "사용법: ./init.sh <문제_URL>"
    exit 1
fi

# 문자열을 카멜 케이스로 변환하고 패키지 이름으로 적합하게 만드는 함수
to_camel_case() {
    # 숫자로 시작하는 경우 'p' 접두사 추가
    local input=$1
    if [[ $input =~ ^[0-9] ]]; then
        input="p$input"
    fi

    # 특수문자를 제거하고 카멜케이스로 변환
    echo "$input" | sed 's/[^a-zA-Z0-9-]//g' | awk -F'-' '{
        result=$1
        for(i=2; i<=NF; i++) {
            split($i,chars,"")
            upper=toupper(chars[1])
            rest=substr($i,2)
            result=result upper rest
        }
        print result
    }'
}

# URL에서 사이트 타입 확인
if [[ $url == *"leetcode.com"* ]]; then
    site="leetcode"
    # URL에서 problems/ 이후의 문제 이름만 추출
    raw_name=$(echo $url | sed -E 's/.*problems\/([^/?]+).*/\1/')
    problem_name=$(to_camel_case "$raw_name")
elif [[ $url == *"acmicpc.net"* ]]; then
    site="baekjoon"
    raw_name=$(echo $url | sed -E 's/.*\/problem\/([0-9]+).*/\1/')
    problem_name=$(to_camel_case "$raw_name")
else
    echo "지원하지 않는 사이트입니다. (지원: leetcode, acmicpc.net)"
    exit 1
fi

# 기본 디렉토리 경로 (자바 프로젝트 구조에 맞게 변경)
base_dir="src/problems/$site/$problem_name"

# 디렉토리가 이미 존재하는지 확인
if [ -d "$base_dir" ]; then
    echo "경고: 이미 존재하는 문제입니다: $base_dir"
    exit 1
fi

# 디렉토리 생성
mkdir -p "$base_dir"

if [[ $site == "leetcode" ]]; then
    cat > "$base_dir/Solution.java" << EOF
package problems.$site.$problem_name;

public class Solution {

}
EOF
else
    cat > "$base_dir/Main.java" << EOF
package problems.$site.$problem_name;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.close();
    }
}
EOF
fi

# README.md 파일 생성 (동일하게 유지)
cat > "$base_dir/README.md" << EOF
# ${site} ${problem_name}
---
> url : ${url}
EOF

echo "문제 생성 완료: $base_dir"