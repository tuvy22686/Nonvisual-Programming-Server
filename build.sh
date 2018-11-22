#!/usr/bin/env bash

if [ ! -e out/ ]; then mkdir out/ ; fi
if [ ! -e src/out/ ]; then mkdir src/out/ ; fi
if [ ! -e src/out/log/ ]; then mkdir src/out/log/ ; fi
if [ ! -e src/out/pid/ ]; then mkdir src/out/pid/ ; fi
kotlinc-jvm -classpath lib/gson-2.8.5.jar -include-runtime src/ -d out/app.jar