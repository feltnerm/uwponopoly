#!/usr/bin/env sh
#
# Usage:
#   ./tests.sh TEST_NAME

cd ./build

if [ "$1" == "" ]
then
   echo "Usage: ./tests.sh TEST_NAME"
   echo ""
   echo "Available Tests"
   echo "==============="
   echo "1) Board"
fi

if [ "$1" == "Board" -o "$1" == "1" ]
then
   java -cp .:com/google/gson/gson-2.1.jar board.Board
fi

