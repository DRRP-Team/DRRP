#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

CSVURL="https://docs.google.com/spreadsheets/d/1Z5jJ63d2EkMvEPGwbZDk22OUsZPxP4owD8NTLSIG4fA/export?format=csv&id=1Z5jJ63d2EkMvEPGwbZDk22OUsZPxP4owD8NTLSIG4fA&gid=0"

wget -qO $DIR/../LANGUAGE.CSV "$CSVURL" && python3 $DIR/updateOldLocalization.py
echo "OK!"
