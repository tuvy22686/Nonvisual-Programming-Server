#!/usr/bin/env bash

kotlin out/app.jar > out/nvisual.log 2> out/nvisual.error_log & echo $! > out/nvisual.pid