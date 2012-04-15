.PHONY : all clean dev

clean: 
	rm -rf ./bin/*
	rm -rf ./build/*

all: clean
	javac -sourcepath ./src -classpath .:lib/gson/gson-2.1.jar -d ./build src/UWPonopoly2.java

dev:
	clean
	javac -verbose -sourcepath src -classpath lib/ src/UWPonopoly2.java -d build
