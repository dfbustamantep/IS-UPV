package logicaNegocio;

public class Login1 implements Login {
	public boolean hacerLogin(String login, String password,String tipo){
		return login.compareTo(password)==0;
	}
	
}
