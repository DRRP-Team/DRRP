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

echo "Building DRRPvX.Y.Z.pk3..."

cd build
zip -r -9 ../DRRPvX.Y.Z.pk3 .

echo "Building DRRPvX.Y.Z_GZDoom3xPatch.pk3..."

mkdir GZDoom3xPatch
cp -R ../TEMP/GZDoom3xPatch/* GZDoom3xPatch
cd GZDoom3xPatch
zip -r -9 ../../DRRPvX.Y.Z_GZDoom3xPatch.pk3 .
cd ../

echo "Building DRRPvX.Y.Z_GZDoom440PlusPatch.pk3..."

mkdir GZDoom440PlusPatch
cp -R ../TEMP/GZDoom440PlusPatch/* GZDoom440PlusPatch
cd GZDoom440PlusPatch
zip -r -9 ../../DRRPvX.Y.Z_GZDoom440PlusPatch.pk3 .
cd ../

cd ../
