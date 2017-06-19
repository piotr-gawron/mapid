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
java -cp build/jar/mapid.jar list.fastq.FastqNameChange testFiles out 6 .;barcodelabel=
```

 