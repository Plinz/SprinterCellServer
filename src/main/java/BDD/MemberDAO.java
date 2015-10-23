package BDD;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.persistence.sessions.Project;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import rest.Member;

public interface MemberDAO {

	@SqlUpdate("create table members (pseudo varchar(50) primary key, mdp varchar(30), email varchar(50), projects text)")
	public void createMemberTable();
	
	@SqlUpdate("insert into members (pseudo) values (:pseudo)")
	@GetGeneratedKeys
	public int insert(@Bind("pseudo") String pseudo );
	
	@SqlUpdate("update members set mdp = :password, email = :email where pseudo = :pseudo")
	public void update(@Bind("pseudo") String pseudo, @Bind("password") String password, @Bind("email") String email);
	
	
	@SqlQuery("select count(*) from members")
	public int countM();
	
	@SqlQuery("select mdp from members where pseudo = :pseudo")
	public String getMdp(@Bind("pseudo") String pseudo);
	
	@SqlQuery("select * from members where pseudo = :pseudo")
    @RegisterMapperFactory(BeanMapperFactory.class)
	public Member findByPseudo(@Bind("pseudo") String pseudo);
	
	@SqlQuery("select * from projects, projectmembers  where pseudo = :pseudo and projects.idp = projectmembers.idp")
    @RegisterMapperFactory(BeanMapperFactory.class)
	public List<rest.Project> getProjects(@Bind("idm") String pseudo);
	
	@SqlUpdate("delete from members where pseudo = :pseudo")
	public int deleteMember(@Bind("pseudo") String pseudo);
	
	@SqlUpdate("drop table if exists members")
	public void dropMember();
	
	@SqlQuery("select * from members order by pseudo")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Member> all();
	
	public void close();
	
}
