Developer Loop
=============

Using Developer Loop, 

You can continiously run SML files and get their output from the REPL.

It's also extensible - you can easily tune it to work with other languages.

## Binary download:

    https://dl.dropboxusercontent.com/u/49249062/DevLoop.jar

## Running:

    java -jar DevLoop.jar -dir path file1 file2 ...


Running arguments: '-dir path file1 file2'

You don't need to put the .sml extension.

To run in local directory, you can omit the --dir path parameter.

Example: 'week1 hw1test' will run week1.sml and then hw1test.sml in the local directory.

Example: '-dir "C:/Programming Languages" week1 hw1test'

will run week1.sml and then hw1test.sml in the C:/Programming Languages directory.
