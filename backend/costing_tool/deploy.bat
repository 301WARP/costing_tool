#! /bin/bash

:; function SET() { eval $1; }
:; function REM() { eval $1 $2; }

:<<":BATCH"
@ECHO off
SETLOCAL ENABLEDELAYEDEXPANSION
:BATCH

SET SERVER=10.36.241.204
SET PORT=8080
:; # default Windows key
SET KEY_FILE="E:\keys\warp_andrew.key"
:; # default Linux key
REM SET KEY_FILE="$HOME/.ssh/warp_andrew.key"

:<<":BATCH"
REM use key file if provided
if not "%1"=="" SET KEY_FILE=%1

REM repository directory
SET REPOS_DIR=%~dp0

REM repository name
PUSHD %REPOS_DIR%
for %%I in (.) do SET REPOS=%%~nxI
POPD

REM exclude .git
SET files=
for /d %%d in (*) do (if not "%%d" == ".git" SET files=!files! %%d)
for %%f in (*) do SET files=!files! %%f

REM copy files
scp -i %KEY_FILE% -r %files% remote@%SERVER%:\srv\maven

REM execute this script on server
ssh -i %KEY_FILE% remote@%SERVER% "/srv/maven/%REPOS%/deploy.bat"

exit /b
:BATCH

# running locally
if [ "$(ps h -o comm -p "$PPID")" != "sshd" ]; then 

	# use key file if provided
	if [ ! -z $1 ]; then
		KEY_FILE=$1
	fi

	# get directory running script
	# this is the root directory of the reposirotry
	REPOS_DIR=$(cd -- "$(dirname -- "$0")" &> /dev/null && pwd)
	if [ $REPOS_DIR == "" ]; then
		>&2 echo Error: Improper script directory.
		exit 1
	fi

	#  get repository name
	REPOS=${REPOS_DIR##*/}

	# copy repository to remote server
	rsync -av -e "ssh -i $KEY_FILE" --exclude ".git" $REPOS_DIR remote@$SERVER:/srv/maven

	# run this script on remote server
	ssh -i "$KEY_FILE" remote@$SERVER "/srv/maven/$REPOS/deploy.bat"

# running remotely
else

	# get pid of any process listenting on desired port...
	PID=$(ss -lptn 'sport = :'$PORT | grep pid | awk -F',' '{ sub(/pid=/, "", $2); print $2}')

	# ...and kill it
	kill -9 $PID

	# change to repository directory
	REPOS_DIR=$(cd -- "$(dirname -- "$0")" &> /dev/null && pwd)
	cd $REPOS_DIR

	# run spring application
	mvn clean spring-boot:run

	exit 0
fi
