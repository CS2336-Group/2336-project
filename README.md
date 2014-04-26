#CS 2336 Project: Text File Compression

### To build and run
If you have ant installed, just run:

	ant

in the root directory. The program should compile and run.

If you would like to simply build the program without running it, run:

	ant build


If you would like to simple run the program without rebuilding it, run:
	ant run

If you do *not* have ant installed, you can compile it all with javac. If you're on Windows, build it with:

	dir /s /B src/*.java > sources.txt
	javac -d target @sources.txt

If you're on Linux, build it with:

	find -name "*.java" >sources.txt
	javac -d target @sources.txt

Then, to run, change directories to the target directory. Then run:

	java compression.CompressionGUI

to run it.
