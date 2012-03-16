#!/usr/bin/env sh

if [ -e UWPonopoly.class ]
then
    echo 'Cleaning .class files.'
    make clean
fi

if [ "$1" == "p" ]
then
    echo 'Making...'
    javac UWPonopoly.java
else
    echo 'Making...'
    javac UWPonopoly.java -verbose
fi

echo '$ java UWPonopoly'
java UWPonopoly
