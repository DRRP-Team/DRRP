#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
TMP=$DIR/Credits.png
OUTPUT=$DIR/../graphics/CREDITS.png
HEIGHT=2700

chromium-browser --headless --hide-scrollbars --window-size=1008,$HEIGHT --screenshot="$TMP" "$DIR/Credits.html"

convert "$TMP" -fuzz 50% -transparent cyan "$OUTPUT" && rm "$TMP"
