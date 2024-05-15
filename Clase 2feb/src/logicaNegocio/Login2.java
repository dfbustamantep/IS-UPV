package logicaNegocio;

public class Login2 implements Login {

	@Override
	public boolean hacerLogin(String l, String p, String u) {
		if (u.equals("Estudiante")) return true;
		else return l.length()==p.length()	;
	}

}
