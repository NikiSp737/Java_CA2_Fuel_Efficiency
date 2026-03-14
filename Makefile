# Makefile for CA2 car mileage

# Usage:
# make all: compiles java and runs on all txt files
# make compile: makes bin dir and "javacs" all ca2 java files
# make test-compile: compiles Junit test files into test_bin dir
# make test: Compiles test files and runs tests
# make test0: runs program on test0.txt
# make run-all:  Copies all data txt files specified 
# make clean: removes all .class files in bin directory

# Directories
SRC = src/main
BIN = bin
DATA = data
MAIN = Mileage
TEST = src/test
TESTBIN = testbin
JUNIT = src/lib/junit-platform-console-standalone-6.0.3.jar

# make all 
all: compile
	make run-all > results.txt

# Compile CA2 Java files, automatically creates bin folder if it doesn't exist
compile:
	mkdir -p $(BIN)
	javac -d $(BIN) \
	$(SRC)/EfficiencyStats.java \
	$(SRC)/EfficiencyStatsTask.java \
	$(SRC)/Mileage.java \
	$(SRC)/MileageProcessor.java \
	$(SRC)/MileageRecord.java \
	$(SRC)/MileageTestHarness.java
	echo "Compiled successfully!"
# If want to compile any java file then uncomment:
# javac -d $(BIN) $(wildcard $(SRC)/*.java)

test:
	mkdir -p $(TESTBIN)
	javac -cp "$(BIN):$(JUNIT)" -d $(TESTBIN) $(TEST)/*.java
	echo "Tests built!"
	java -jar $(JUNIT) execute --classpath "$(BIN):$(TESTBIN)" --scan-classpath
	echo "Running Tests!"

# Had to read: 
# https://docs.junit.org/6.0.3/running-tests/console-launcher.html
# To use Junit from command line

# To run program with small test file
test0: compile
	java -cp $(BIN) $(MAIN) $(DATA)/test0.txt
	echo "Testing test0.txt"

# To run program with all files
run-all: compile
	echo "Running mileage_tiny.txt"
	java -cp $(BIN) $(MAIN) $(DATA)/mileage_tiny.txt
	echo ""

	echo "Running mileage_4months.txt"
	java -cp $(BIN) $(MAIN) $(DATA)/mileage_4months.txt
	echo ""

	echo "Running mileage_15months.txt"
	java -cp $(BIN) $(MAIN) $(DATA)/mileage_15months.txt
	echo ""

	echo "Running mileage_10years.txt"
	java -cp $(BIN) $(MAIN) $(DATA)/mileage_10years.txt
	echo ""
	
# Later if any text file testing is needs testing use for loop in shell
# run-all: compile
# for file in $(DATA)/*.txt; do \
# echo "Running $$file"; \
# java -cp $(BIN) $(MAIN) $$file; \
# echo ""; \
# done
	
# remove compiled class files
clean:
	rm -f $(BIN)/*.class
