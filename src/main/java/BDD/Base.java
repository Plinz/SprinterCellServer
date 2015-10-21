package BDD;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.developeverywhere.noyau.Membre;

public class Base {
	protected String url, mdp, nom;
	protected Connection con;

	/**
	 * Base de données
	 * 
	 * @param file
	 *            nom de la BDD en .db
	 */
	public Base() {
		con = null;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		File monFichier = new File(System.getProperty("java.io.tmpdir")
				+ System.getProperty("file.separator") + "Database.db");
		if (!monFichier.exists()) {
			try {
				monFichier.createNewFile();
			} catch (Exception e) {
				System.out.println("Impossible de créer le fichier : " + e);
			}
		}

		url = "jdbc:sqlite:" + System.getProperty("java.io.tmpdir")
				+ System.getProperty("file.separator") + "Database.db";
		nom = null;
		mdp = null;
		try {
			con = DriverManager.getConnection(this.url, this.nom, this.mdp);
			Statement stmt = con.createStatement();
			String query = "create table if not exists users (pseudo text, mdp text, email text, primary key (pseudo))";
			stmt.executeUpdate(query);

			query = "create table if not exists doing (id integer, pseudo text, nom text, root boolean)";
			stmt.executeUpdate(query);

			query = "create table if not exists projects (id integer, nom text, root text)";
			stmt.executeUpdate(query);

			query = "create table if not exists source (id integer, nomProjet text, code text, primary key (id))";
			stmt.executeUpdate(query);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * crée un utilisateur dans la bdd
	 * 
	 * @param pseudo
	 *            pseudo du user
	 * @param mdp
	 *            mdp du user
	 * @param email
	 *            mail du user
	 */
	public void createUser(String pseudo, String mdp, String email) {
		try {
			con = DriverManager.getConnection(this.url, this.nom, this.mdp);

			PreparedStatement prep = con
					.prepareStatement("insert into users values (?, ?, ?)");
			prep.setString(1, pseudo);
			prep.setString(2, mdp);
			prep.setString(3, email);
			prep.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * fait les entrées necessaire dans la bdd à la creation d'un projet
	 * 
	 * @param nomProj
	 *            nom du projet crée
	 * @param root
	 *            nom du user qui a créer le projet
	 */
	// attention conflits avec les noms de projets non traités
	public void createProject(int id, String nomProj, String root) {
		try {
			con = DriverManager.getConnection(this.url, this.nom, this.mdp);

			PreparedStatement prep = con
					.prepareStatement("insert into projects values (?, ?, ?)");
			prep.setInt(1, id);
			prep.setString(2, nomProj);
			prep.setString(3, root);
			prep.executeUpdate();

			prep = con
					.prepareStatement("insert into doing values (?, ?, ?, 1)");
			prep.setInt(1, id);
			prep.setString(2, root);
			prep.setString(3, nomProj);
			prep.executeUpdate();

			prep = con
					.prepareStatement("create table if not exists ? (pseudo text, read boolean, write boolean)");
			prep.setString(1, nomProj);
			prep.executeUpdate();

			prep = con.prepareStatement("insert into ? values (?, 1, 1)");
			prep.setString(1, nomProj);
			prep.setString(2, root);
			prep.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * avoir tout les projet suivi du user
	 * 
	 * @param pseudo
	 *            user
	 * @return
	 */
	@SuppressWarnings("finally")
	public ArrayList<Integer> getProject(String pseudo) {

		ArrayList<Integer> userProjects = new ArrayList<Integer>();

		try {
			con = DriverManager.getConnection(this.url, this.nom, this.mdp);

			PreparedStatement prep = con
					.prepareStatement("Select id from doing where pseudo=?");
			prep.setString(1, pseudo);

			ResultSet rs = prep.executeQuery();

			while (rs.next()) {

				userProjects.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return userProjects;
		}
	}

	/**
	 * verifie si un user existe
	 * 
	 * @param pseudo
	 *            pseudo a verifier
	 * @return vrai s'il existe faux sinon
	 */
	public boolean existUser(String pseudo) {

		try {
			con = DriverManager.getConnection(this.url, this.nom, this.mdp);

			PreparedStatement prep = con
					.prepareStatement("Select * from users where pseudo=?");
			prep.setString(1, pseudo);

			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return false;
	}

	/**
	 * verifie si une adresse mail existe
	 * 
	 * @param mail
	 *            mail a verifier
	 * @return vrai si elle existe faux sinon
	 */
	public boolean existMail(String mail) {

		try {
			con = DriverManager.getConnection(this.url, this.nom, this.mdp);

			PreparedStatement prep = con
					.prepareStatement("Select * from users where email=?");
			prep.setString(1, mail);

			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return false;
	}

	/**
	 * Verifie si l'utilisateur existe dans la bdd
	 * 
	 * @param pseudo
	 *            pseudo a verifier
	 * @param mdp
	 *            mot de passe à verifier
	 * @return true s'il existe bien avec cette combinaison pseudo/mdp
	 */
	public boolean userValide(String pseudo, String mdp) {
		try {
			con = DriverManager.getConnection(this.url, this.nom, this.mdp);

			PreparedStatement prep = con
					.prepareStatement("Select * from users where pseudo=? and mdp=?");
			prep.setString(1, pseudo);
			prep.setString(2, mdp);
			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return false;
	}

	/**
	 * permet d'obtenir le mdp du user en passant par la bdd
	 * 
	 * @param pseudo
	 *            user du mot de passe voulu
	 * @return le mdp
	 */
	public String getMdp(String pseudo) {

		try {
			con = DriverManager.getConnection(this.url, this.nom, this.mdp);

			PreparedStatement prep = con
					.prepareStatement("Select mdp from users where pseudo=?");
			prep.setString(1, pseudo);

			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				return rs.getString(1);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;

	}

	/**
	 * permet d'obtenir le mail d'un user
	 * 
	 * @param pseudo
	 *            user du mail voulu
	 * @return le mail du user
	 */
	public String getMail(String pseudo) {

		try {
			con = DriverManager.getConnection(this.url, this.nom, this.mdp);

			PreparedStatement prep = con
					.prepareStatement("Select email from users where pseudo=?");
			prep.setString(1, pseudo);

			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				return rs.getString(1);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;

	}

	/**
	 * Permet d'ajouter un user à un projet (root uniquement si possible)
	 * 
	 * @param pseudo
	 *            pseudo du user à ajouter
	 * @param projet
	 *            nom du projet
	 */
	public void ajouterUserAunProjet(String pseudo, String projet) {
		try {
			con = DriverManager.getConnection(this.url, this.nom, this.mdp);

			PreparedStatement prep = con
					.prepareStatement("insert into ? values (?, 1, 1)");
			prep.setString(1, projet);
			prep.setString(2, pseudo);

			prep.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Permet de supprimer un user à un projet (root uniquement si possible)
	 * 
	 * @param pseudo
	 *            pseudo du user à supprimer
	 * @param projet
	 *            nom du projet
	 */
	public void supprimerUserAunProjet(String pseudo, String projet) {
		try {
			con = DriverManager.getConnection(this.url, this.nom, this.mdp);

			PreparedStatement prep = con
					.prepareStatement("delete from ? where pseudo=?");
			prep.setString(1, projet);
			prep.setString(2, pseudo);

			prep.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * permet d'obtenir les collaborateurs d'un projet
	 * 
	 * @param projet
	 *            affiche les collaborateurs du projet
	 * @return collaborateurs (write only);
	 */

	@SuppressWarnings("finally")
	public ArrayList<String> getCollaborateurs(String projet) {
		ArrayList<String> collaboProjects = new ArrayList<String>();
		String[] found;
		try {
			con = DriverManager.getConnection(this.url, this.nom, this.mdp);

			PreparedStatement prep = con
					.prepareStatement("Select pseudo from ? where write=1");
			prep.setString(1, projet);

			ResultSet rs = prep.executeQuery();

			while (rs.next()) {

				collaboProjects.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			final int size = collaboProjects.size();
			found = new String[size];
			collaboProjects.toArray(found);
			return collaboProjects;
		}
	}

	/**
	 * recher les projets ou users correspondant au parametre (+ ou -)
	 * 
	 * @param recherche
	 *            recherche
	 * @return liste des resultats
	 */
	@SuppressWarnings("finally")
	public ArrayList<String> rechercher(String recherche) {
		ArrayList<String> search = new ArrayList<String>();
		String[] found;
		try {
			con = DriverManager.getConnection(this.url, this.nom, this.mdp);

			PreparedStatement prep = con
					.prepareStatement("Select pseudo from users where pseudo like ?");
			prep.setString(1, "%" + recherche + "%");

			ResultSet rs = prep.executeQuery();

			while (rs.next()) {

				search.add(rs.getString(1));
			}

			PreparedStatement prep2 = con
					.prepareStatement("Select nom from projects where nom like %?%");
			prep2.setString(1, recherche);

			ResultSet rs2 = prep2.executeQuery();

			while (rs2.next()) {

				search.add(rs.getString(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			final int size = search.size();
			found = new String[size];
			search.toArray(found);
			return search;
		}
	}

	public String getProjectName(int id) {
		try {
			con = DriverManager.getConnection(this.url, this.nom, this.mdp);

			PreparedStatement prep = con
					.prepareStatement("Select nom from projects where id=?");
			prep.setInt(1, id);

			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				return rs.getString(1);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;

	}

	public void ajouterSourceFile(int id, String nom) {
		try {
			con = DriverManager.getConnection(this.url, this.nom, this.mdp);

			PreparedStatement prep = con
					.prepareStatement("insert into source (id, nomProjet)  values (?, ?)");
			prep.setInt(1, id);
			prep.setString(2, nom);

			prep.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setCode(int id, String code) {
		try {
			con = DriverManager.getConnection(this.url, this.nom, this.mdp);

			PreparedStatement prep = con
					.prepareStatement("update source set code=? where id=?");
			prep.setString(1, code);
			prep.setInt(2, id);

			prep.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getSourceFileName(Integer idSourceFile) {
		try {
			con = DriverManager.getConnection(this.url, this.nom, this.mdp);

			PreparedStatement prep = con
					.prepareStatement("Select nom from source where id=?");
			prep.setInt(1, idSourceFile);

			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				return rs.getString(1);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;

	}
/*
	public String getProjectName(Integer idProject) {
		try {
			con = DriverManager.getConnection(this.url, this.nom, this.mdp);

			PreparedStatement prep = con
					.prepareStatement("Select nom from projects where id=?");
			prep.setInt(1, idProject);

			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				return rs.getString(1);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;

	}
*/

	public Membre getProjectOwner(Integer idProject) {

		Membre m = null;
		try {
			con = DriverManager.getConnection(this.url, this.nom, this.mdp);

			PreparedStatement prep = con
					.prepareStatement("Select root from projects where id=?");
			prep.setInt(1, idProject);

			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				return m= new Membre(rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return m;
	}
	
	/**
	 * @param name le nom du projet
	 * @return l'id du projet 
	 */
	public Integer getProjectid(String name) {
		try {
			con = DriverManager.getConnection(this.url, this.nom, this.mdp);

			PreparedStatement prep = con
					.prepareStatement("Select id from projects where nom=?");
			prep.setString(1, name);

			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				return Integer.valueOf(rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	//projects(id integer, nom text, root text)
	//source (id text, nomProjet text, code text);
	
	public ArrayList<Integer> getSourceFilesFromProject(Integer idProject) {
		ArrayList<String> nomProjets= new ArrayList<String>();
		ArrayList<Integer> idProjets= new ArrayList<Integer>();
		try {
			con = DriverManager.getConnection(this.url, this.nom, this.mdp);

			PreparedStatement prep = con
					.prepareStatement("Select nom from projects where id=?");
			prep.setInt(1, idProject);

			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				nomProjets.add(rs.getString(1));
			}
			
			for(int i=0; i<nomProjets.size();i++){
				prep = con.prepareStatement("Select id from source where nomProjet=?");
				prep.setString(1, nomProjets.get(i));

				ResultSet rs2 = prep.executeQuery();
				
				while (rs.next()) {
					idProjets.add(rs2.getInt(1));
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return idProjets;

	}
}
