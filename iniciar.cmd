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

set "CODIGO_SAIDA=%ERRORLEVEL%"

echo.
if %CODIGO_SAIDA% equ 0 (
    echo Aplicacao encerrada normalmente.
) else (
    echo Aplicacao encerrada com erro: %CODIGO_SAIDA%
)

endlocal
pause