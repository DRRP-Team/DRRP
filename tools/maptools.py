#!/usr/bin/python3
# coding: utf-8

import sys

ACTIONS = ("pack", "depack")

print("DRRP Git Map Tools v0.1.0 [Copyright (c) PROPHESSOR 2019]")

if len(sys.argv) < 3:
    print("usage: %s <action> <filename.wad>" % sys.argv[0])
    print("Available actions:")
    for action in ACTIONS:
        print("\t%s" % action)
    exit()

# Useful functions

def parseInt(stream): return int.from_bytes(stream.read(4), byteorder='little')

# Main

action  = sys.argv[1]
name    = sys.argv[2]
mapname = name[:-4] if name[-4:].lower() == '.wad' else name

if not action in ACTIONS:
    print("Unknown action!")
    print("Available actions:")
    for action in ACTIONS:
        print("\t%s" % action)
    exit()

if action == 'depack':

    with open(name, 'rb') as _in:
        if _in.read(4) != b'PWAD':
            print("%s isn't a DRRP PWAD file!" % name)

        lumpno = parseInt(_in)

        print("Found %s lumps!" % lumpno)
        
        lumplistoffset = parseInt(_in)

        _in.seek(lumplistoffset, 0)

        for _lump in range(lumpno):
            #print("Lump #%d" % (_lump+1))
            filepos = parseInt(_in)
            size = parseInt(_in)
            lumpname = _in.read(8).decode('ascii').strip('\x00')
            #print("\tFile position: %d" % filepos)
            #print("\tFile size: %d" % size)
            #print("\tFile name: %s" % lumpname)
            outname = None

            if size == 0 and _lump == 0:
                print("Found map %s" % lumpname)
                continue
            elif lumpname == "TEXTMAP":
                print("Depacking UDMF data...\t", end='')
                outname = '%s.map' % mapname
            elif lumpname == "SCRIPTS":
                print("Depacking ACS data...\t", end='')
                outname = '%s.acs' % mapname
            else:
                #print("Skipping %s" % lumpname)
                continue

            curpos = _in.tell()

            _in.seek(filepos, 0)
            
            with open(outname, 'wb') as _out:
                _out.write(_in.read(size))

            _in.seek(curpos, 0)

            print("[OK!]")
