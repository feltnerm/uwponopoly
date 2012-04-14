.PHONY: all clean dev

all:
	clean
	javac -sourcepath src -classpath lib/ src/UWPonopoly2.java -d build/
clean: 
	rm -rf ./bin/*
	rm -rf ./build/*
dev:
	clean
	javac -verbose -sourcepath src -classpath lib/ src/UWPonopoly2.java -d build
