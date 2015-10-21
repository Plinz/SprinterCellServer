package BDD;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import rest.Member;

public interface MemberDAO {

	@SqlUpdate("create members tasks (idm integer primary key autoincrement, mdp varchar(30), email varchar(50), projects text, pseudo varchar(50))")
	public void createMemberTable();
	
	@SqlUpdate("insert into tasks (mdp, email, projects, pseudo)")
	@GetGeneratedKeys
	public int insert(@BindBean Integer id);
	
	@SqlUpdate("update tasks set mdp = :mdp, email = :email, projects = :projects, pseudo = :pseudo")
	public void update(@BindBean Member m);
	
	@SqlQuery("select count(*) from members")
	public int countM();
	
	@SqlQuery("select * from members where idm = :idm")
    @RegisterMapperFactory(BeanMapperFactory.class)
	public Member findByIdm(@Bind("idm") int idm);
	
	@SqlUpdate("delete from members where idm = :idm")
	public int deleteMember(@Bind("idm") int idm);
	
	@SqlUpdate("drop table if exists members")
	public void dropMember();

	public void close();
	
}
