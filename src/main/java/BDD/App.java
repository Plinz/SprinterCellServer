package BDD;

import javassist.compiler.ast.Member;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.skife.jdbi.v2.DBI;
import org.sqlite.SQLiteDataSource;

import rest.MemberResource;
import rest.TaskDBResource;

@ApplicationPath("/v1/")
public class App extends ResourceConfig {
    
    public App() {
    	packages("rest");
    	register(MemberResource.class);
    	register(TaskDBResource.class);
    }
    
    public static DBI getDbi() {
		return dbi;
	}
	public static void setDbi(DBI dbi) {
		App.dbi = dbi;
	}

	private static DBI dbi;
	static {
		SQLiteDataSource ds = new SQLiteDataSource();
		ds.setUrl("jdbc:sqlite:" + System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "CP.db");
		setDbi(new DBI(ds));
    }
}