# Makefile for assignment 3


BINDIR=./bin
SRCDIR=./src
DOCDIR=./doc
EXBIN =./exBin

#Build rule for java sources

.SUFFIXES: .java .class


${BINDIR}/%.class: ${SRCDIR}/%.java
	javac $< -cp ${BINDIR} -d ${BINDIR} 

${BINDIR}/Main.class : ${BINDIR}/Data.class ${BINDIR}/Hashing.class ${BINDIR}/SearchKeys.class


clean:
	rm -f ${BINDIR}/*.class
runBST:
	java -cp ./bin PowerBSTApp
runAVL:
	java -cp ./bin PowerAVLApp '123456789'
run:
	java -cp ./bin Main
docs:
	javadoc  -classpath ${BINDIR} -d ${DOCDIR} ${SRCDIR}/*.java
cleandocs:
	rm -rf ${DOCDIR}/*

