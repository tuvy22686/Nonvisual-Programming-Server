#!/usr/bin/env bash

[ -f /Nonvisual-Programming-Server/out/pid/nvisual.pid ] && kill -9 $(cat /Nonvisual-Programming-Server/out/pid/nvisual.pid)
[ -f /Nonvisual-Programming-Server/out/pid/nvisual.pid ] && rm /Nonvisual-Programming-Server/out/pid/nvisual.pid