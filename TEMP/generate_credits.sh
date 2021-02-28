#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
TMP=$DIR/Credits.png
OUTPUT=$DIR/../graphics/CREDITS.png
HEIGHT=3400

chromium-browser --headless --hide-scrollbars --window-size=1008,$HEIGHT --screenshot="$TMP" "$DIR/Credits.html"

convert "$TMP" -fuzz 25% -transparent black -channel A -blur 1x1 "$OUTPUT" && rm "$TMP"
