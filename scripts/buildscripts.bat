@echo off
pushd ..
if not exist acs mkdir acs
if not exist ir mkdir ir

if not exist acs\libc.bin (
   echo Building libc . . . 
   gdcc-makelib --bc-target=ZDoom --bc-zdacs-init-delay --alloc-min Sta "" 1000000000 libGDCC libc -o acs/libc.bin
)

echo Building game lib . . . 
gdcc-cc --bc-target=ZDoom -c source/*.c ir/game.ir
gdcc-ld --bc-target=ZDoom --bc-zdacs-init-delay -llibc ir/game.ir acs/game.bin

echo Press any key to exit . . . 
pause >> nul
popd