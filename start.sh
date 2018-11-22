#!/usr/bin/env bash

kotlin /Nonvisual-Programming-Server/out/app.jar > /Nonvisual-Programming-Server/out/nvisual.log 2> /Nonvisual-Programming-Server/out/nvisual.error_log & echo $! > /Nonvisual-Programming-Server/out/nvisual.pid