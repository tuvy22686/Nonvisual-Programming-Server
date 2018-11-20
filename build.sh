#!/usr/bin/env bash

kotlinc-jvm -classpath lib/gson-2.8.5.jar -include-runtime src/ -d app.jar
mkdir src/out/