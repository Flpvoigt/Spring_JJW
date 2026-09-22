@echo off
setlocal

cd /d "%~dp0"

set "JAVA_HOME=%~dp0.tools\jdk-25\jdk-25.0.2"
set "PATH=%JAVA_HOME%\bin;%PATH%"

if not exist "%JAVA_HOME%\bin\java.exe" (
    echo.
    echo ERRO: JDK 25 local nao encontrado.
    echo Caminho esperado:
    echo %JAVA_HOME%
    echo.
    pause
    exit /b 1
)

echo.
echo Java utilizado neste projeto:
"%JAVA_HOME%\bin\java.exe" -version
echo.
echo Iniciando Spring JJW...
echo.

call "%~dp0mvnw.cmd" spring-boot:run

endlocal
pause