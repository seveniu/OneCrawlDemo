#!/usr/bin/env bash
#/bin/bash
profile=$1
echo "$profile"
if [ ! ${profile} ]; then
  echo "profile is null"
else
  ./gradlew clean -Pprofile=$profile
  ./gradlew assemble -Pprofile=$profile
  cd build/libs/
  jar=$(find . -type f -name '*.jar' | awk -F './' '{print $2}')
  echo ${jar}
  java -jar ${jar} --spring.profiles.active=${profile}
fi
