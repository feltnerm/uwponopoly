.PHONY: all clean doc q dev 

all: 	clean doc
	javac -sourcepath ./src -classpath .:lib/gson/gson-2.1.jar -d ./build src/UWPonopoly2.java
	mkdir -p ./build/com/google/gson
	cp ./lib/gson/gson-2.1.jar ./build/com/google/gson
    
clean: 
	rm -rf ./bin/*
	rm -rf ./build/*
	rm -rf ./doc/*
	
doc:
	rm -rf ./doc/*
	javadoc -classpath ./lib/gson/gson-2.1.jar -sourcepath ./src/ -author -d ./doc/javadoc -doctitle UWPonopoly -author -version -subpackages board,gui,player,game,config,dice -source 1.6 src/UWPonopoly2.java

q: 	clean
	javac -sourcepath ./src -classpath .:lib/gson/gson-2.1.jar -d ./build src/UWPonopoly2.java
	mkdir -p ./build/com/google/gson
	cp ./lib/gson/gson-2.1.jar ./build/com/google/gson 

qd: 	clean
	javac -verbose -sourcepath ./src -classpath .:lib/gson/gson-2.1.jar -d ./build src/UWPonopoly2.java
	mkdir -p ./build/com/google/gson
	cp ./lib/gson/gson-2.1.jar ./build/com/google/gson
	

dev: 	clean doc	
	javac -verbose -sourcepath ./src -classpath .:lib/gson/gson-2.1.jar -d ./build src/UWPonopoly2.java
	mkdir -p ./build/com/google/gson
	cp ./lib/gson/gson-2.1.jar ./build/com/google/gson

