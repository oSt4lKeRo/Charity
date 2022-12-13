package Component;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.extern.java.Log;
import org.hibernate.HibernateError;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

public class DataManager {

	private static EntityManagerFactory m_factory = Persistence.createEntityManagerFactory("Charity");


	public static void addToDatabase(Object obj, Writer wr) throws IOException {

		EntityManager manager = m_factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();

		try {

			transaction.begin();
			manager.persist(obj);
			transaction.commit();

		} catch (Exception e){
			wr.write(e.getMessage());
		}

		finally
		{
			if (transaction.isActive())
			{ transaction.rollback(); }

			manager.close();
		}
	}

	public static void updateToDateBase(Object obj, Writer wr) throws IOException {
		EntityManager manager = m_factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();

		try {

			transaction.begin();
			manager.merge(obj);
			transaction.commit();

		} catch (Exception e){
			wr.write(e.getMessage());
		}

		finally
		{
			if (transaction.isActive())
			{ transaction.rollback(); }

			manager.close();
		}
	}



	////////////////////////////////////////Find Block
	public static Admin findAdmin(String username, String password){
		EntityManager manager = m_factory.createEntityManager();
		Admin Admin = null;
		try {
			Query query = manager.createQuery("SELECT a FROM Admin a WHERE a.username LIKE '" + username + "' AND a.password LIKE '" + password + "'", User.class);
			Admin = (Admin) query.getSingleResult();
		} catch (NoResultException exep) {
			exep.printStackTrace();
		} finally {
			manager.close();
		}
		return Admin;
	}

	public static User findUser(String username, String password, Writer wr) throws IOException {

		EntityManager manager = m_factory.createEntityManager();
		User User = null;
		try {
			Query query = manager.createQuery("SELECT a FROM User a WHERE a.username LIKE '" + username + "' AND a.password LIKE '" + password + "'", User.class);
			User = (User) query.getSingleResult();
			wr.write("Все окей");
		} catch (NoResultException exep) {
			exep.printStackTrace();
		} finally {
			manager.close();
		}

		return User;
	}


	public static Organization findOrganization(int organization_id){

		EntityManager manager = m_factory.createEntityManager();
		Organization Organization = null;

		try {
			Query query = manager.createQuery("SELECT a FROM Organization a WHERE a.id = " + organization_id + "", Organization.class);
			Organization = (Organization) query.getSingleResult();
		} catch (NoResultException exep) {
			exep.printStackTrace();
		} finally {
			manager.close();
		}

		return Organization;
	}

	
	
	

	////////////////////////////////////////Check block
	public static boolean checkNick(String username){
		List<User> userList = null;
		boolean check = false;
		try {
			EntityManager manager = m_factory.createEntityManager();
			Query query = manager.createQuery("SELECT a FROM User a WHERE a.username = '" + username + "'", User.class);

			userList = query.getResultList();
			manager.close();
			if(userList.size() > 0){
				check = true;
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return check;
	}

	public static boolean checkMail(String email){
		List<User> userList = null;
		boolean check = false;
		try {
			EntityManager manager = m_factory.createEntityManager();
			Query query = manager.createQuery("SELECT a FROM User a WHERE a.email = '" + email + "'", User.class);

			userList = query.getResultList();
			manager.close();

			if(userList.size() > 0){
				check = true;
			}

		} catch (Exception e){
			e.printStackTrace();
		}
		return check;
	}

	public static boolean checkOrganization(String title){
		List<Organization> organizationList = null;
		boolean check = false;
		try {
			EntityManager manager = m_factory.createEntityManager();
			Query query = manager.createQuery("SELECT a FROM Organization a WHERE a.title = '" + title + "'", Organization.class);

			organizationList = query.getResultList();
			manager.close();

			if(organizationList.size() > 0){
				check = true;
			}

		} catch (Exception e){
			e.printStackTrace();
		}
		return check;
	}
	
	

	////////////////////////////////////////Get List Block

	public static List<User> getUserList(){
		List<User> userList = null;
		try {
			EntityManager manager = m_factory.createEntityManager();
			Query query = manager.createQuery("SELECT a FROM User a WHERE a.organization_id = " + null + "", User.class);

			userList = query.getResultList();
			manager.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		return userList;
	}

	public static List<User> getOrganizationUserList(){
		List<User> organizationList = null;
		try {
			EntityManager manager = m_factory.createEntityManager();
			Query query = manager.createQuery("SELECT a FROM User a WHERE a.organization_id != " + null + "");

			organizationList = query.getResultList();
			manager.close();
		} catch (HibernateError e){
			e.printStackTrace();
		}
		return organizationList;
	}

	public static List<Company> getCompanyList(){
		List<Company> companyList = null;

		try {
		EntityManager manager = m_factory.createEntityManager();
		Query query = manager.createQuery("SELECT a FROM Company a", Company.class);

			companyList = query.getResultList();
			manager.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		return companyList;
	}

	public static List<Company> getActionCompanyList(){
		List<Company> companyList = null;
		List<Status> statusList = null;

		try {
			EntityManager manager = m_factory.createEntityManager();
			Query queryCompany = manager.createQuery("SELECT a FROM Company a", Company.class);
			Query queryStatus = manager.createQuery("SELECT b FROM Status b", Status.class);

			companyList = queryCompany.getResultList();
			statusList = queryStatus.getResultList();

			for(Company i : companyList){
				if(i.getStatus_id().getTitle() == statusList.get(2).getTitle()){
					companyList.remove(i);
				}
			}

			manager.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		return companyList;
	}


	public static List<Company> getInactionCompanyList(){
		List<Company> companyList = null;
		List<Status> statusList = null;

		try {
			EntityManager manager = m_factory.createEntityManager();
			Query queryCompany = manager.createQuery("SELECT a FROM Company a", Company.class);
			Query queryStatus = manager.createQuery("SELECT b FROM Status b", Status.class);

			companyList = queryCompany.getResultList();
			statusList = queryStatus.getResultList();

			for(Company i : companyList){
				if(i.getStatus_id().getTitle() == statusList.get(0).getTitle()){
					companyList.remove(i);
				}
			}

			manager.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		return companyList;
	}



	////////////////////////////////////////Delete Elem

	public static boolean deleteById(Class<?> type, int id)
	{
		EntityManager manager = m_factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		boolean res = false;

		try
		{
			transaction.begin();

			Object persistentInstance = manager.find(type, id);
			if (persistentInstance != null)
			{
				manager.remove(persistentInstance);
				res = true;
			}

			transaction.commit();
		}
		finally
		{
			if (transaction.isActive())
			{ transaction.rollback(); }

			manager.close();
		}
		return res;
	}


	public static void deleteOrganization(){

	}

	public static void deleteCompany(){

	}




	////////////////////////////////////////Set List Block


}
