:begin
set Path=%~dp0
@echo on

cd C:\Program Files\MySQL\MySQL Server 8.0\bin
mysql --defaults-extra-file=%Path%my.cnf --default-character-set=utf8  <  %Path%test.sql

:end