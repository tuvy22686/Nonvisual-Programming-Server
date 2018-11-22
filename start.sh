#!/usr/bin/env bash

kotlin /Nonvisual-Programming-Server/out/app.jar > /Nonvisual-Programming-Server/out/log/nvisual.log 2> /Nonvisual-Programming-Server/out/log/nvisual.error_log & echo $! > /Nonvisual-Programming-Server/out/pid/nvisual.pid