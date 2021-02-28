#!/bin/bash

echo "Building production build of DRRP..."

cd ../

rm -rf build
mkdir -p build/maps

cp -R maps/*.wad build/maps
cp -R materials build/
cp -R music build/
cp -R patches build/
cp -R shaders build/
cp -R sounds build/
cp -R sprites build/
cp -R MapInfos build/
cp -R graphics build/
cp -R actors build/
cp -R acs build/
cp -R textures build/
cp -R zscript build/
cp -R ANIMDEFS build/
cp * build/
