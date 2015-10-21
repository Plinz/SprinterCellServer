package BDD;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import rest.Project;

public interface ProjectToTaskDao {
	
	@SqlUpdate("create table projecttasks (idp integer, idt integer)")
	public void createProjectTask();
	
	@SqlUpdate("insert into projecttasks (idp, idt)")
	@GetGeneratedKeys
	public int insert(@Bind("idp") int idp, @Bind("idt") int idt);
	
	@SqlUpdate("delete from projecttasks where idp = :idp and idt = :idt")
	public int deleteProjectTask(@Bind("idp") int idp, @Bind("idt") int idt);
	
	@SqlUpdate("drop table if exists projecttasks")
	public void dropProjectTask();

	public void close();
	
}
