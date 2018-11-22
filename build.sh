#!/usr/bin/env bash

if [ ! -e out/ ]; then mkdir out/ ; fi
if [ ! -e src/out/ ]; then mkdir src/out/ ; fi
kotlinc-jvm -classpath lib/*.jar -include-runtime src/ -d out/app.jar