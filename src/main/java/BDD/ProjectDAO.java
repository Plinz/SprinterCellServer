package BDD;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import rest.Member;
import rest.Project;
import rest.Task;

public interface ProjectDAO {

	@SqlUpdate("create table projects (idp integer primary key autoincrement, namep varchar(20), descriptionp varchar(100), tasksp text, membersp text)")
	public void createProjectTable();
	
	@SqlUpdate("insert into projects(namep, descriptionp, tasksp, membersp) values(:namep, :descriptionp, :tasksp, :membersp)")
	@GetGeneratedKeys
	public int insert(@Bind("namep") String namep, @Bind("descriptionp") String descriptionp, @Bind("tasksp") String tasks, @Bind("membersp") String membersp);
	
	@SqlUpdate("update projects set namep = :namep, descriptionp = :descriptionp, tasksp = :tasksp, membersp = :membersp")
	public void update(@BindBean Project p);
	
	@SqlQuery("select count(*) from projects")
	public int countP();
	
	@SqlQuery("select * from members, projectmembers where members.pseudo = projectmembers.pseudo and projectmembers.idp = :idp")
    @RegisterMapperFactory(BeanMapperFactory.class)
	public List<Member> findMembersByIdp(@Bind("idp") int idp);
	
	@SqlQuery("select * from tasks, projecttasks where tasks.idt = projecttasks.idt and projecttasks.idp = :idp")
    @RegisterMapperFactory(BeanMapperFactory.class)
	public List<Task> findTasksByIdp(@Bind("idp") int idp);
	
	@SqlQuery("select * from projects where idp = :idp")
    @RegisterMapperFactory(BeanMapperFactory.class)
	public Project findProjectByIdp(@Bind("idp") int idp);
	
	@SqlUpdate("delete from projects where idp = :idp")
	public int deleteProject(@Bind("idp") int idp);
	
	@SqlQuery("select * from projects")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Project> all();
	
	@SqlUpdate("drop table if exists projects")
	public void dropProject();

	public void close();
}
