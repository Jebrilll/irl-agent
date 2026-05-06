#!/usr/bin/env sh
if [ -z "$JAVA_HOME" ]; then
  JAVA_EXEC=java
else
  JAVA_EXEC="$JAVA_HOME/bin/java"
fi
if [ ! -x "$JAVA_EXEC" ]; then
  JAVA_EXEC=java
fi
PRG="$0"
while [ -h "$PRG" ]; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`"/$link"
  fi
done
SAVED="`pwd`"
cd "`dirname "$PRG"`"
SCRIPT_DIR="`pwd`"
cd "$SAVED"
EXECUTABLE=gradle/wrapper/gradle-wrapper.jar
if [ ! -f "$EXECUTABLE" ]; then
  echo "Gradle wrapper JAR not found: $EXECUTABLE"
  exit 1
fi
CLASSPATH="$SCRIPT_DIR/$EXECUTABLE"
eval "exec \"$JAVA_EXEC\" -classpath \"$CLASSPATH\" org.gradle.wrapper.GradleWrapperMain \"$@\""
