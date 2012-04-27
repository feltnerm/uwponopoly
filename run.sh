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

make all

echo 'Starting... '
cd ./build
java -cp .:com/google/gson/gson-2.1.jar UWPonopoly2 gui
