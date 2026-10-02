@echo off
setlocal

cd /d "%~dp0"

where java >nul 2>nul
if errorlevel 1 (
    echo.
    echo ERRO: Java 21 ou superior nao foi encontrado no PATH.
    echo.
    pause
    exit /b 1
)

echo.
echo Java utilizado neste projeto:
java -version

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
