#### The elements of pattern layout
###### Suppose current package name is "a.b.c", current class name is "a.b.c.Test"
+ %c: Show the whole package name, use {<depth>} to define depth of package name, eg:  

         "%c"    is "a.b.c"  
         "%c{2}" is "b.c"  

+ %nc: If length of package name is less than 'n', then fill the space with whitespace at left side.
+ %-nc: If length of package name is less than 'n', then fill the space with whitespace at right side.
+ %.nc: If length of package name is great than 'n', then remove the redundant character.
+ %m.nc: If length of package name is less than 'm', then fill the space with whitespace at left side; If length is great than 'n', then remove redundant character.
+ %-m.nc: If length of package name is less than 'm', then fill the space with whitespace at right side; If length is great than 'n', then remove redundant character.
+ %C: Show the class name(include the package name), eg:  
 
         "%C"    is "a.b.c.Test"
         "%C{2}" is "c.Test"

+ %d: Show the date time of each log, use {<pattern>} to define date time format(ISO8601), eg:

          "%d{ABSOLUTE}"    is    "22:23:30,117"
          "%d{DATE}"        is    "12 Oct 2005 22:23:30,117"
          "%d{ISO8601}"     is    "2005-10-12 22:23:30,117"
          "%d{yyyy-MM-dd HH:mm:ss SSS}" is "2005-10-22 22:23:30 119"  

+ %F: Show the source file name  
+ %l: Show the code position when the log record, include class name, thread id and line number of code. eg:  

          "%l"    is  "a.b.c.Test.main(Test.java:129)"

+ %L: Show line number of code when log is record  

          "%L"    is  "129"

+ %m: Show the log message. eg:

          "%m"    is  "This is a message for debug."

+ %M: Show the method name when log is record. eg:

          "%M"    is  "main"

+ %n: Show a line break character on current platform. "\r\n" on windows，"\n" on UNIX/Linux/MacOS
+ %p: Show the priority level of each log, eg:

          "%p"    is  "INFO"

+ %r: Show the time through from system started till log is recorded

          "%r"    is  "1215"

+ %t: Show the thread ID of each log. eg:

          "%t"    is  "Test"

+ %x: Show log stack information order by NDC (Nested Diagnostic Context)
+ %X: Show log stack information order by MDC (Mapped Diagnostic Context). Can record each client information on server. eg:

          "%X{5}" is record the log of no.5 client

+ %%: Show a percent

          "%%"    is "%"
