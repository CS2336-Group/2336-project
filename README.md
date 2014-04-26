#CS 2336 Project: Text File Compression

### To build and run
#### With ant
If you have ant installed, just run:

	ant

in the root directory. The program should compile and run.

If you would like to simply build the program without running it, run:

	ant build


If you would like to simple run the program without rebuilding it, run:
	ant run

#### Windows without ant
Build the project with:

	dir /s /B src/*.java > sources.txt
	javac -d target @sources.txt

Then, to run, change directories to the target directory. Then run:

	java compression.CompressionGUI

#### Linux without ant
Build the project with:

	find -name "*.java" >sources.txt
	javac -d target @sources.txt

Then, to run, change directories to the target directory. Then run:

	java compression.CompressionGUI

to run it.
