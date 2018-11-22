#!/usr/bin/env bash

[ -f out/nvisual.pid ] && kill -9 $(cat out/nvisual.pid)
[ -f out/nvisual.pid ] && rm out/nvisual.pid