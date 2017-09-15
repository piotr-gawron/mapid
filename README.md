# Build

After cloning type `ant` in the console. The output should look similar to:

```
Buildfile: C:\Users\piotr.gawron\workspace\Martyna\build.xml

clean:
      [delete] Deleting directory C:\Users\piotr.gawron\workspace\Martyna\build

compile:
       [mkdir] Created dir: C:\Users\piotr.gawron\workspace\Martyna\build\classes
       [javac] C:\Users\piotr.gawron\workspace\Martyna\build.xml:9: warning: 'includeantruntime' was not set, defaulting to build.sysclasspath=last; set to false for repeatable builds
       [javac] Compiling 11 source files to C:\Users\piotr.gawron\workspace\Martyna\build\classes

jar:
       [mkdir] Created dir: C:\Users\piotr.gawron\workspace\Martyna\build\jar
         [jar] Building jar: C:\Users\piotr.gawron\workspace\Martyna\build\jar\mapid.jar

main:
BUILD SUCCESSFUL
Total time: 2 seconds
```

# FastQ ID change

Type in the console:

```
java -cp build/jar/mapid.jar list.fastq.FastqNameChange inputDirectory outputDirectory partsForId prefix
```

* `inputDirectory` - directory with raw fastq files where ids should be changed
* `outputDirectory` - directory where output fastq files should be put *MUST be different then `inputDirectory`*
* `partsForId` - numeric value defining how many parts of the filename (separated by "_") should be used as part of the id
* `prefix` - optional prefix for fastq sequence name

Example:
 
```
java -cp build/jar/mapid.jar list.fastq.FastqNameChange testFiles out 6 ".;barcodelabel="
```

# Merge tab separated files

Type in the console:

```
java -cp build/jar/mapid.jar list.mapid.Main
```

to get usage information about the tool:

```
 -a,--add-missing                   when rows in second file doesn't match
                                    the first add them to the output
    --column1 <arg>                 column with id in the first file
                                    (starting from 0)
    --column2 <arg>                 column with id in the second file
                                    (starting from 0)
 -d,--add-duplicates-column         when rows in second file contain
                                    duplicates attach duplicates columns
                                    to the row
    --file1 <arg>                   first file to merge
    --file2 <arg>                   second file to merge
    --identifier-substring1 <arg>   definition of identifier substring
                                    used to merge first file in the
                                    format: begin,end. For example if our
                                    identifier look like 'abc123.12', but
                                    we want to use only '123.1' part we
                                    should use '3,8'. If no value is
                                    provided then the whole string will be
                                    used as identifier..
    --identifier-substring2 <arg>   definition of identifier substring
                                    used to merge second file in the
                                    format: begin,end. For example if our
                                    identifier look like 'abc123.12', but
                                    we want to use only '123.1' part we
                                    should use '3,8'. If no value is
                                    provided then the whole string will be
                                    used as identifier..
 -m,--merge-duplicates              when rows in second file contain
                                    duplicates merge data in the rows
    --output <arg>                  output file
```

Here is an example that merges two example files with some id:  
```
java -cp build/jar/mapid.jar list.mapid.Main --file1 testFiles/merge/cog.txt --file2 testFiles/merge/cog_names.txt \
     --column1 1 --column2 0 --output out.txt
```

When id that should be match is a substring you can also specify which part of the id should be used. Here is an example  
```
java -cp build/jar/mapid.jar list.mapid.Main --file1 testFiles/merge/cog.txt --file2 testFiles/merge/map.txt \
     --column1 0 --column2 1 --identifier-substring1 0,17 --output out.txt
```
