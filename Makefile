clean: 
	rm -rf build/*.class
all:
	clean
	javac -sourcepath src -classpath .:lib/ src/UWPonopoly.java -d build/
dev:
	clean
	javac -verbose -sourcepath src -classpath .:lib src/UWPonopoly.java -d build
