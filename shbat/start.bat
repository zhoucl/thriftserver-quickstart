@echo off

call setenv.bat debug

set _RUNJAVA=%JRE_HOME%\bin\java.exe

set TITLE="thriftserver-quickstart"

set CMD_LINE_ARGS=--transport=framed --protocol=compact --server-type=threaded-selector --port=9091

set ACTION=start

echo %_RUNJAVA% -jar thriftserver-quickstart-0.0.1-SNAPSHOT.jar %CMD_LINE_ARGS% %ACTION%

%_RUNJAVA% -jar thriftserver-quickstart-0.0.1-SNAPSHOT.jar %CMD_LINE_ARGS% %ACTION%