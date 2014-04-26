#CS 2336 Project: Text File Compression

## Project Summary
Our group implemented three text file compression algorithms and made a GUI
wrapper for them. **Arithmetic Encoding**, **Huffman Coding**, and **Run Length
Encoding** were all used in this project. In addition, the **Burrows-Wheeler
Transform** (while not explicitly a compression algorithm) was implemented behind
the scenes to assist in Run Length Encoding. Text files will be compressed to
binary files with the extension .emi.

## Usage
Type in the name of the file you want to process, either to encode or to decode.
The filename can be either relative to the current directory or an absolute
path. Select the desired encoding algorithm from the dropdown menu and then
click "Encode/Decode". The program will next ask you whether you want to encode
or decode the file. Once you select, it will process the file and display a
summary of the process.

## To build and run from source
### With ant
If you have ant installed, just run:

	ant

in the root directory. The program should compile and run.

If you would like to simply build the program without running it, run:

	ant build

If you would like to simple run the program without rebuilding it, run:
	ant run

### Windows without ant
Build the project with:

	dir /s /B src/*.java > sources.txt
	javac -d target @sources.txt

Then, to run, change directories to the target directory. Then run:

	java compression.CompressionGUI

### Linux without ant
Build the project with:

	find -name "src/*.java" >sources.txt
	javac -d target @sources.txt

Then, to run, change directories to the target directory. Then run:

	java compression.CompressionGUI

to run it.
