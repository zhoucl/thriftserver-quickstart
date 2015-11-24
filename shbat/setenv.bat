@echo off
rem ---------------------------------------------------------------------------
rem Set JAVA_HOME or JRE_HOME if not already set, ensure any provided settings
rem are valid and consistent with the selected start-up options and set up the
rem endorsed directory.
rem ---------------------------------------------------------------------------
if ""%1"" == ""debug"" goto needJavaHome

rem Otherwise either JRE or JDK are fine
if not "%JRE_HOME%" == "" goto gotJreHome
if not "%JAVA_HOME%" == "" goto gotJavaHome
echo Neither the JAVA_HOME nor the JRE_HOME environment variable is defined
echo At least one of these environment variable is needed to run this program
goto exit

:gotJreHome
set _RUNJAVA="%JRE_HOME%\bin\java.exe"

:gotJavaHome
set _RUNJAVA="%JAVA_HOME%\bin\java.exe"


:needJavaHome
rem Check if we have a usable JDK
if "%JAVA_HOME%" == "" goto noJavaHome
if not exist "%JAVA_HOME%\bin\java.exe" goto noJavaHome
if not exist "%JAVA_HOME%\bin\javaw.exe" goto noJavaHome
if not exist "%JAVA_HOME%\bin\jdb.exe" goto noJavaHome
if not exist "%JAVA_HOME%\bin\javac.exe" goto noJavaHome
set "JRE_HOME=%JAVA_HOME%"
goto okJava

:okJava
set _RUNJAVA="%JAVA_HOME%\bin\java.exe"
goto exit

:noJavaHome
echo The JAVA_HOME environment variable is not defined correctly.
echo It is needed to run this program in debug mode.
echo NB: JAVA_HOME should point to a JDK not a JRE.
goto exit

:exit
exit /b 1