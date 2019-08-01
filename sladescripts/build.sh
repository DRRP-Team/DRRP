#!/bin/bash

echo "Building production build of DRRP..."

cd ../

mkdir build
mkdir build/maps

cp -R maps/*.wad build/maps
cp materials build/
cp music build/
cp patches build/
cp shaders build/
cp sounds build/
cp sprites build/
cp MapInfos build/
cp graphics build/
cp actors build/
cp acs build/
cp textures build/
cp zscript build/
cp ANIMDEFS build/
cp * build/
