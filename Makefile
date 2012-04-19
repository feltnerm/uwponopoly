.PHONY : all clean dev

all:    clean	
	javac -sourcepath ./src -classpath .:lib/gson/gson-2.1.jar -d ./build src/UWPonopoly2.java
    
clean: 
	rm -rf ./bin/*
	rm -rf ./build/*

dev: 	clean
	javac -verbose -sourcepath src -classpath lib/ src/UWPonopoly2.java -d build
