#!/bin/bash

pushd ..
mkdir -p acs ir

if [[ ! -e acs\libc.bin ]]; then
   echo Building libc . . . 
   gdcc-makelib --bc-target=ZDoom --bc-zdacs-init-delay --alloc-min Sta "" 1000000000 libGDCC libc -o acs/libc.bin
fi

echo Building game lib . . . 
gdcc-cc --bc-target=ZDoom -c source/*.c ir/game.ir
gdcc-ld --bc-target=ZDoom --bc-zdacs-init-delay -llibc ir/game.ir acs/game.bin
read -n1 -r -p "Press any key to continue..." key
popd