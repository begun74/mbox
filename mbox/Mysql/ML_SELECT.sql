select * from Qmgr where n ='75819132B';
select * from smtp where n ='75819132B';
select * from virtual where n ='75819132B';

select distinct q.data,q.from_,v.to_ from Qmgr q 
left join virtual v on v.n=q.n
where q.from_ like '%minsk.gov.by';

select distinct q.data, q.n, s.n, q.from_, s.to_ from Qmgr q 
inner join smtp s on s.n=q.n
where q.from_ like 'noreply.mosk@%';

select distinct q.data, q.n, q.from_, sD.client from Qmgr q 
inner join smtpD sD on sD.n=q.n
where q.from_ like 'noreply.mosk@%';

select distinct q.data, q.n, q.from_,v.to_,v.n from Qmgr q 
left join virtual v  on v.n=q.n
where q.n ='75819132B';