#!/bin/bash

# Script for the Doom RPG Remake Project. Updates the year in all necessary
#files by regular expression.
# (c) DRRP team, 2022.

PREVYEARVALUE=$(( `date +%Y` - 1 ))

CHANGEEPREFIX="Copyright (c) "
NEWYEARVALUE=$(( $PREVYEARVALUE + 1 ))

echo "Updating from the $PREVYEARVALUE to the $NEWYEARVALUE year."

CHANGEDFILESAMOUNT=0

FILESWITHCOPYRIGHTS=`grep -sr '../' --exclude="update_copyright_year.sh" --exclude-dir=".git*" --exclude-dir=".vscode" -e "${CHANGEEPREFIX}[[:digit:]]\{4\}-$PREVYEARVALUE" | perl -pe "s/(.+):.+/\1/g"`

for i in ${FILESWITHCOPYRIGHTS}; do
	echo -n "Updating \"$i\"... "
	sed -i "s/${CHANGEEPREFIX}\([[:digit:]]\{4\}-\)$PREVYEARVALUE/${CHANGEEPREFIX}\1$NEWYEARVALUE/1" "$i"
	CHANGEDFILESAMOUNT=$(( CHANGEDFILESAMOUNT + 1 ))
	echo Done.
done

echo "Updated files: $CHANGEDFILESAMOUNT."
