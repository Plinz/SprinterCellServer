package BDD;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface TaskToMemberDAO {

	@SqlUpdate("create table tasksmembers (idt integer, pseudo varchar(50))")
	public void createTasksMember();

	@SqlUpdate("insert into tasksmembers (idt, pseudo) values (:idt, :pseudo)")
	@GetGeneratedKeys
	public int insert(@Bind("idt") int idt, @Bind("pseudo") String pseudo);

	@SqlUpdate("delete from tasksmembers where idt = :idt and pseudo = :pseudo")
	public int deleteProjectMember(@Bind("idt") int idt, @Bind("pseudo") String pseudo);

	@SqlUpdate("drop table if exists tasksmembers")
	public void dropTasksMember();

	public void close();
	
}
