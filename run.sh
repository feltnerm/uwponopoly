#!/usr/bin/env sh
#
# Usage:
#   ./run.sh

if [ ! -d ./build ]
then
    make q
fi

echo 'Starting... '
cd ./build

if [ "$1" == "g" ]
then
    java -cp .:com/google/gson/gson-2.1.jar UWPonopoly gui
else
    java -cp .:com/google/gson/gson-2.1.jar UWPonopoly debug
fi


