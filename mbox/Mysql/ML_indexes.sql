DROP Index n_indx ON Cleanup;
CREATE INDEX n_indx ON Cleanup (n);
DROP Index n_indx ON Qmgr;
CREATE INDEX n_indx ON Qmgr (n);
DROP Index n_indx ON smtp;
CREATE INDEX n_indx ON smtp (n);
DROP Index n_indx ON smtpD;
CREATE INDEX n_indx ON smtpD (n);
DROP Index n_indx ON virtual;
CREATE INDEX n_indx ON virtual (n);

DROP Index n_indx ON Cleanup;
CREATE INDEX n_indx ON Cleanup (data);
DROP Index n_indx ON Qmgr;
CREATE INDEX n_indx ON Qmgr (data);
DROP Index n_indx ON smtp;
CREATE INDEX n_indx ON smtp (data);
DROP Index n_indx ON smtpD;
CREATE INDEX n_indx ON smtpD (data);
DROP Index n_indx ON virtual;
CREATE INDEX n_indx ON virtual (data);