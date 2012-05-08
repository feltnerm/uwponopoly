#!/usr/bin/env sh
#
# Usage:
#   ./run.sh [type]
#
#   ./run.sh
#       - Compiles with the verbose flag
#
#   ./run.sh p
#       - Compiles with no verbosity


if [ -e UWPonopoly.class ]
then
    echo 'Cleaning .class files.'
    make clean
fi

#if [ "$1" == "p" ]
#then
#    echo 'Making...'
#    make 
#else
#    echo 'Making...'
#    make verbose
#fi
echo 'Making...'
if [ ! -d ./build ]
then
   mkdir ./build
fi

make q
echo 'Starting... '
cd ./build

if [ "$1" == "g" ]
then
    java -cp .:com/google/gson/gson-2.1.jar UWPonopoly gui
else
    java -cp .:com/google/gson/gson-2.1.jar UWPonopoly debug
fi


