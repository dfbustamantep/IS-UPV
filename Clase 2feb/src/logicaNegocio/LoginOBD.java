package logicaNegocio;

import javax.persistence.*;

import domain.Cuenta;

public class LoginOBD implements Login{
	String fileName="passwords.odb";
	private EntityManager db;
	private EntityManagerFactory emf;
	
	public LoginOBD() {
		emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
		db = emf.createEntityManager();
		System.out.println("Base de datos abierta");
		inicializarBD();
	}
	
	public void inicializarBD () {
		add("Kepa","hola","Profesor");
		add("Nerea","kaixo","Estudiante");
	}
	
	public boolean add(String login, String password, String tus){
		if (db.find(Cuenta.class, login)!=null) return false;
		db.getTransaction().begin();
		Cuenta c = new Cuenta(login,password,tus);
		db.persist(c);
		db.getTransaction().commit();
		return true;
		}
	
	@Override
	public boolean hacerLogin(String login, String password, String tusuario){
		Cuenta c= db.find(Cuenta.class,login);
		if (c==null) return false;
		return (c.getPassword().equals(password)
		 && (c.getTusuario().equals(tusuario)));
		}
		}


