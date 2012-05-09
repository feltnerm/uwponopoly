#!/usr/bin/env sh
#
# Usage:
#   ./run.sh

if [ -e UWPonopoly.class ]
then
    echo 'Cleaning .class files.'
    make clean
fi

if [ ! -d ./build ]
then
    make
fi

echo 'Starting... '
cd ./build

if [ "$1" == "g" ]
then
    java -cp .:com/google/gson/gson-2.1.jar UWPonopoly gui
else
    java -cp .:com/google/gson/gson-2.1.jar UWPonopoly debug
fi


