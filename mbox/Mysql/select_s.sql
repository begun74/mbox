SELECT distinct qmgr.n,date(qmgr.data), time(qmgr.data), qmgr.from_,  time(smtp.data), smtp.to_, smtp.status
FROM qmgr 
inner join smtp on smtp.n=qmgr.n and (date(qmgr.data)=date(smtp.data) and hour(qmgr.data)=hour(smtp.data))
where date(qmgr.data)>='2015-04-22' and date(qmgr.data)<='2015-04-22' 
order by qmgr.data desc, qmgr.from_;

SELECT distinct qmgr.n,date(qmgr.data), time(qmgr.data), qmgr.from_,  time(smtp.data), smtp.to_, smtpd.client,smtp.status
FROM qmgr 
inner join smtp on smtp.n=qmgr.n and (date(qmgr.data)=date(smtp.data) and hour(qmgr.data)=hour(smtp.data)) 
inner join smtpd on smtpd.n=smtp.n
where date(qmgr.data)>='2015-04-22' and date(qmgr.data)<='2015-04-22' 
order by qmgr.data desc, qmgr.from_;

select qwe.n,qmgr.data ,qmgr.from_ ,smtp.to_, smtp.status from 
	(select distinct qmgr.n ,qmgr.data
		from qmgr
		where qmgr.from_="removed" and date(qmgr.data)>='2015-04-22' and date(qmgr.data)<='2015-04-22' 
	) as qwe
inner join qmgr on qmgr.n=qwe.n and qmgr.from_ != "removed"
inner join smtp on smtp.n=qwe.n and (date(qwe.data)=date(smtp.data) and hour(qwe.data)=hour(smtp.data))
order by qmgr.data desc, qmgr.from_;

select * from smtp where smtp.procName like '%virtual%';	

show create table smtp;
SELECT distinct date(qmgr.data), time(qmgr.data), qmgr.from_, qmgr.n, smtpd.client
FROM qmgr
inner join smtpd on smtpd.n=qmgr.n and date(qmgr.data)=date(smtpd.data)
where date(qmgr.data)='2015-04-19' and time(qmgr.data)>='11:00:00'
order by qmgr.data desc, qmgr.from_;

select count(id),max(id) from postfixid;

select distinct  qmgr.data, qmgr.from_, qmgr.n
from qmgr
where date(qmgr.data)='2015-04-19' and time(qmgr.data)>='11:00:00'
order by qmgr.data desc;

select  distinct date(qmgr.data) from qmgr ;

select distinct qmgr.data, qmgr.from_, qmgr.n, smtp.data, smtp.to_ ,smtp.status 
from qmgr 
inner join smtp on smtp.n=qmgr.n and hour(qmgr.data)=hour(smtp.data)
inner join smtpd on smtpd.n=smtp.n
where qmgr.n='BDA831DCA';


begin;
update qmgr set qmgr.data =subdate(qmgr.data,interval 1 year) where month(qmgr.data)=12;
commit;
rollback;