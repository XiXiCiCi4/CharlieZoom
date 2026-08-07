@rem
@rem Gradle wrapper startup script for Windows (minimal).
@rem Requires a Java 17+ runtime (JAVA_HOME or java on PATH).
@rem
@echo off
setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.

set CLASSPATH=%DIRNAME%gradle\wrapper\gradle-wrapper.jar

if not "%JAVA_HOME%"=="" (
  set JAVACMD=%JAVA_HOME%\bin\java.exe
) else (
  set JAVACMD=java.exe
)

"%JAVACMD%" %DEFAULT_JVM_OPTS% -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
if %ERRORLEVEL% neq 0 exit /b %ERRORLEVEL%

endlocal
