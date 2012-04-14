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

if [ "$1" == "p" ]
then
    echo 'Making...'
    make 
else
    echo 'Making...'
    make verbose
fi

echo '$ java UWPonopoly'
java UWPonopoly