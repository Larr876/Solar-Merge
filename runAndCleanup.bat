@echo off
setlocal

echo Cleaning
del /s /q *.class >nul 2>&1

javac Controller\*.java model\*.java View\*.java SolarMerge.java
if errorlevel 1 goto :error

echo Running...
java SolarMerge
goto :end
:error
echo Error occured.
exit /b 1

:end
pause
