#!/usr/bin/env sh
#
# Usage:
#   switch_boards BOARD_NAME

cd ./etc

if [ "$1" == "" ]
then
   echo "Usage: switch_boards BOARD_NAME"
   echo "Board name or corresponding number is permissible as a BOARD_NAME"
   echo ""
   echo "Available Boards"
   echo "==============="
   echo "1) Monopoly"
   echo "2) UW-Platteville"
fi

if [ "$1" == "Monopoly" -o "$1" == "1" ]
then
   cp monopoly_board.json board.json
   echo "Monopoly board loaded."
fi

if [ "$1" == "UW-Platteville" -o "$1" == "2" ]
then
   cp uwp_board.json board.json
   echo "UW-Platteville board loaded."
fi

