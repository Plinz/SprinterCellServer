package BDD;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface ProjectToMemberDao {

	@SqlUpdate("create table projectmembers (idp integer, pseudo varchar(50))")
	public void createProjectMember();
	
	@SqlUpdate("insert into projectmembers (idp, pseudo) values(:idp, :pseudo)")
	@GetGeneratedKeys
	public int insert(@Bind("idp") int idp, @Bind("pseudo") String pseudo);
	
	@SqlUpdate("delete from projectmembers where idp = :idp and pseudo = :pseudo")
	public int deleteProjectMember(@Bind("idp") int idp, @Bind("pseudo") String pseudo);
	
	@SqlUpdate("drop table if exists projectmembers")
	public void dropProjectMember();

	public void close();
	
}
