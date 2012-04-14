all:
	rm -rf build/*.class
	javac -sourcepath src -classpath lib/ src/UWPonopoly2.java -d build/
clean: 
	rm -rf build/*.class
dev:
	clean
	javac -verbose -sourcepath src -classpath lib/ src/UWPonopoly.java -d build
