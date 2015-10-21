package BDD;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface ProjectToMemberDao {

	@SqlUpdate("create table projectmembers (idp integer, idm integer)")
	public void createProjectMember();
	
	@SqlUpdate("insert into projectmembers (idp, idm)")
	@GetGeneratedKeys
	public int insert(@Bind("idp") int idp, @Bind("idm") int idm);
	
	@SqlUpdate("delete from projectmembers where idp = :idp and idm = :idm")
	public int deleteProjectMember(@Bind("idp") int idp, @Bind("idm") int idm);
	
	@SqlUpdate("drop table if exists projectmembers")
	public void dropProjectMember();

	public void close();
	
}
