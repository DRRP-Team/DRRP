#!/bin/bash

for i in ../maps/*.wad
do
    echo
    echo Depacking $i
    ./maptools.py depack $i
    echo
    echo
done
