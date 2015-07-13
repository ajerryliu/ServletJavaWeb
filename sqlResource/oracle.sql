--如果用户存在 则删除级联删除整个用户


--建用户
create user scott identified by 123456 default tablespace System;

--授权
grant connect,resource to scott;
grant create any sequence to scott;
grant create any table to scott;
grant delete any table to scott;
grant insert any table to scott;
grant select any table to scott;
grant unlimited tablespace to scott;
grant execute any procedure to scott;
grant update  any table to scott;
grant create any view to scott;

--建表雇员表
CREATE TABLE EMP
(
EMPNO CHAR(4),
ENAME VARCHAR2(20),
JOB VARCHAR2(20),
MGR VARCHAR2(20),
HIREDATE DATE,
SAL NUMBER(10,2),
COMM NUMBER(10,2),
DEPARTMENT CHAR(2)
);

--插入一条数据
INSERT INTO EMP VALUES('7369','smith','clerk','7902','1980/12/17',800,0,20);

--修改oracle默认的日期格式
alter session set nls_date_format = 'yyyy-mm-dd hh24:mi:ss';

--查询数据
select * from emp;
--修改列名
alter table emp rename column department to deptno;
--建立部门表
CREATE TABLE DEPT
(
DEPTNO CHAR(2),
DNAME VARCHAR2(30),
LOC VARCHAR2(20)
);

--向部门表插入数据
insert into DEPT values('10','ACCOUNTING','NEW YORK');
insert into DEPT values('20','RESEARCH','DALLAS');
insert into DEPT values('30','SALES','CHICAGO');
insert into DEPT values('40','OPERATIONS','BOSTON');

--查询部门表中的数据
select * from dept;

--添加工资级别表
CREATE TABLE SALGRADE
(
  GRADE VARCHAR2(5),
  LOSAL NUMBER(10,2),
  HISAL NUMBER (10,2)
);

--添加数据
insert into salgrade values('1',700,1200);
insert into salgrade values('2',1201,1400);
insert into salgrade values('3',1401,2000);
insert into salgrade values('4',2001,3000);
insert into salgrade values('5',3001,9999);

--查询数据
select * from salgrade;


--把重复的数据合并 使用distinct
select distinct deptno,job from emp;

--oracle数据库中列名不区分大小写，数据区分大小写
--使用算术表达式 注意双引号
select sal*12 "年薪" ,ename from emp;
select sal*12 + comm*12 "all money" ,ename,comm from emp;

--使用nvl来操控null对象 如果comm为空，则使用0代替它
select sal*12 + nvl(comm,0)*12 from emp;

--显示月薪大于1800￥的记录
select * from emp where sal>1800;

--在1982之前被雇佣的记录
select ename ,hiredate from emp where hiredate < '1982-1-1';

--首字母为s的员工 %表示任意的字符  _表示单个字符
select ename,sal from emp where ename like 's%';

--第三个字符为l的记录
select ename sal from emp where ename like '__l%';

--多个或者查询 批处理更快
select * from emp where empno in(7369,7499,7566);

--没有上级的雇员
select * from emp where mgr is null;

--多条件查询
select * from emp where (sal>500 or job='salesman') and ename like 'j%';

--order by 排序
select * from emp order by sal asc;
select * from emp order by sal desc;

--多级 排序部门升序  工资降序
select * from emp order by deptno asc,sal desc;

--依据别名 升降序
select nvl(comm,0)*12 "all" ,ename from emp order by "all" asc;

--查询最高工资 同为分组函数
select max(sal),min(sal) from emp;

--查询拥有最高工资员工的信息  利用子查询  相当于返回的一个结果
select * from emp where sal =(select max(sal) from emp);

--查询比平均工资高的人的信息
select count(*) from emp;
select avg(sal) from emp;
select * from emp where sal > (select avg(sal) from emp);

--把低于平均工资的人且在1982年前入职的人的工资上涨10%

update emp set sal=sal*1.1  where sal<(select avg(sal) from emp);

--group by 和 having 显示每个部门的平均工资和最高工资
select avg(sal),max(sal),deptno from emp group by deptno; 
 
--显示每个部门中每个岗位的平均工资和最高工资  财务部有经理 会计 出纳
--先后顺序是 group by , having , order by   
select avg(sal),max(sal),deptno,job from emp group by deptno,job order by deptno;

--对分组的结果的进行再次筛选 使用having 关键字  分组函数在where中显示
select avg(sal),deptno from emp group by deptno having avg(sal)<2100;

--多表查询 原理是笛卡尔积
select ename,sal,dname from emp,dept where emp.deptno = dept.deptno and emp.deptno='10';








--查询处于10部门  部门的名称，员工编号，工资  应该是emp和dept连接
select dept.dname,emp.ename ,emp.sal, emp.deptno from emp ,dept  where emp.deptno = dept.deptno order by emp.deptno;

select dept.dname , emp.ename,emp.sal from emp,dept where  dept.deptno= emp.deptno ;



--显示员工的名字 工资 工资级别 也可以依据区间来作为条件来连接两张表
select emp.ename , emp.sal ,salgrade.grade from emp,salgrade where emp.sal between salgrade.losal and salgrade.hisal;

--自连接查询  一张表当做两张表看  查询员工对应的上司名称
select boss.ename a1 , worker.ename a2  from  emp boss ,emp worker where boss.empno = worker.mgr;

--单行子查询（只返回一行数据的查询） 显示和smith 处于同一部门的所有员工
select * from emp where deptno = (select deptno from emp where ename = 'smith');

--多行子查询（查询出的结果是一个集合） 具有和20号部门中所有职位的一样的员工的信息
select * from emp where job in (select distinct job from emp where deptno = '20' );

--查询比20号部门所有工资都高的员工的信息
select * from emp where sal > (select max(sal) from emp where deptno='20');

--查询比20号部门中任意一个员工工资高的员工信息 使用any 关键字

select * from emp where sal > any (select sal from emp where deptno ='20');

--多列子查询 结果是两个列  查询和smith部门和工资一样的员工的信息
select * from emp where (job,deptno) =(select job,deptno from emp where ename ='smith');

--查询出工资高于自己部门的平均工资的员工信息 内嵌视图 别名不用加双引号
select a1.ename,a1.sal,a2.mysal from emp a1 ,(select deptno,avg(sal) mysal from emp group by deptno) a2 where a1.deptno = a2.deptno and a1.sal > a2.mysal;


select deptno,avg(sal) as "avg2" from emp group by deptno;

--oracle数据库分页 使用rownum分页  效率其次  使用rowid分页效率最高
select a1.*,rownum rn from emp a1 where rownum < 6;

select * from (select a1.*,rownum rn from emp a1 where rownum <=6 ) a2 where a2.rn >=3; 

--升序排序
select * from (select a1.*,rownum rn from emp a1 where rownum <=6 order by sal) a2 where a2.rn >=3; 



--将查询的结果用新表存储起来  连同数据一起存储
create table son_of_emp (ename,enumber,money) as (select ename,deptno,sal from emp); 

select * from son_of_emp;

drop table son_of_emp;

--多个查询结果的结合的操作，并集 交集  差集

select * from emp where sal>2200 union select * from emp where emp.job ='manager'; 

--创建一个数据库
