@rem ~~~~~~~ Batch File to Download DL4J-bin Jar dependency. File Size 120 MB ~~~~~~~
@echo off
REM Creating a Newline variable (the two blank lines are required!)
set NLM=^


set NL=^^^%NLM%%NLM%^%NLM%%NLM%
echo %NL%%NL%%NL%%NL% 1. Download the dependencies from the links.%NL%%NL% 2. Extract the .jar files from the jars.rar into '/jars' folder. %NL%%NL% 3. Similarly, extract lib.rar and bin.rar into 'WEB-INF/lib' and 'WEB-INF/bin'.
echo.
echo %NL%%NL% ~~~~ Download .jar dependencies from the link after pressing enter. ~~~~
echo.
pause
start "" "https://yadi.sk/d/wpvQ38e-3Tm3do"
echo %NL%%NL% ~~~~ Download and extract bin.rar from the link into WEB-INF/bin. ~~~~
echo.
pause
start "" "https://yadi.sk/d/OyBEcwSe3Tm3oj"
echo %NL%%NL% ~~~~ Download and extract lib.rar from the link into WEB-INF/lib. ~~~~
echo.
pause
start "" "https://yadi.sk/d/xQHxZV_U3Tm3rm"