@echo off
del /s /q *.class
javac Controller\*.java model\*.java View\*.java SolarMerge.java
java SolarMerge
del /s /q *.class
