package BDD;

import java.util.List;

import rest.Task;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface TaskDao {
	
	@SqlUpdate("create table tasks (idt integer primary key autoincrement, title varchar(20), description varchar(100), value Integer, state varchar(20))")
	public void createTaskTable();
	
	@SqlUpdate("insert into tasks (title, description, value) values (:title, :description, :value)")
	@GetGeneratedKeys
	public int insert(@Bind("title") String title, @Bind("description") String description, @Bind("value") int value);
	
	@SqlUpdate("update tasks set title = :title, description = :description, state = :state, value = :value")
	public void update(@BindBean Task t);
	
	@SqlQuery("select count(*) from tasks")
	public int count();
	
	@SqlQuery("select * from tasks where idt = :idt")
    @RegisterMapperFactory(BeanMapperFactory.class)
	public Task findByIdt(@Bind("idt") int idt);
	
	@SqlQuery("select * from tasks where title = :title")
    @RegisterMapperFactory(BeanMapperFactory.class)
	public Task findByTitle(@Bind("title") String title);
	
	@SqlUpdate("delete from tasks where idt = :idt")
	public int deleteTask(@Bind("idt") int idt);
	
	@SqlUpdate("drop table if exists tasks")
	public void dropTask();
	
	@SqlQuery("select * from tasks order by idt")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Task> all();
	
	

	public void close();
	
}