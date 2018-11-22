#!/usr/bin/env bash

fuser -kvn tcp 12345
rm out/nvisual.pid