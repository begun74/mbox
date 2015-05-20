delete from cleanup;
delete from qmgr;
delete from smtp;
delete from smtpd;
delete from postfixid;
delete from virtual;

ALTER TABLE cleanup AUTO_INCREMENT=0;
ALTER TABLE qmgr AUTO_INCREMENT=0;
ALTER TABLE smtp AUTO_INCREMENT=0;
ALTER TABLE smtpd AUTO_INCREMENT=0;
ALTER TABLE postfixid AUTO_INCREMENT=0;
ALTER TABLE virtual AUTO_INCREMENT=0;