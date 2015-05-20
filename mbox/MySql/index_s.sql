drop index n_indx  on smtp ;
drop index n_indx  on smtpd ;
drop index n_indx  on qmgr ;
drop index n_indx  on cleanup ;
drop index n_indx  on virtual ;
create index  n_indx  on smtp (n);
create index  n_indx  on smtpd (n);
create index  n_indx  on qmgr (n);
create index  n_indx  on cleanup (n);
create index  n_indx  on virtual (n);


drop index data_indx  on smtp ;
drop index data_indx  on smtpd ;
drop index data_indx  on qmgr ;
drop index data_indx  on cleanup ;
drop index data_indx  on virtual ;
create index  data_indx  on smtp (data);
create index  data_indx  on smtpd (data);
create index  data_indx  on qmgr (data);
create index  data_indx  on cleanup (data);
create index  data_indx  on virtual (data);

drop index  to_indx  on smtp ;
drop index  from_indx  on qmgr ;
create index  to_indx  on smtp (to_);
create index  from_indx  on qmgr (from_);

drop index id_indx  on smtp ;
drop index id_indx  on smtpd ;
drop index id_indx  on qmgr ;
drop index id_indx  on cleanup ;
drop index id_indx  on virtual ;
create index  id_indx  on smtp (id) USING BTREE;
create index  id_indx  on smtpd (id) USING BTREE;
create index  id_indx  on qmgr (id) USING BTREE;
create index  id_indx  on cleanup (id) USING BTREE;
create index  id_indx  on virtual (id) USING BTREE;




