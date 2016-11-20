--
-- remember to execute the correct env script before executing this script
--

define v_log_file=./run_all.log

spool &v_log_file
set echo on feedback on verify on
connect &v_xj_un/&v_xj_pw@&v_conn_str
show   user
prompt &v_conn_str
--
-- always enlarge sort area in case there are big index creation
--
alter session set sort_area_size = 2097152
/
@db_config.sql


@secu_company_user.sql


disconnect
