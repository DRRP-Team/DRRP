#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

for i in $DIR/../maps/*.wad
do
    echo
    echo Depacking $i
    $DIR/maptools.py depack $i
    echo
    echo
done
