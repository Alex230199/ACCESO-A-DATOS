package act1_texto;

import java.io.Serializable;

public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private transient String sessionToken;
	
	public Usuario (int id, String username, String sessionToken) {
		this.id = id;
		this.username = username;
		this.sessionToken = sessionToken;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getSessionToken() {
		return sessionToken;
	}
	
	@Override
	public String toString() {
		return "Usuario {" +
				"id=" + id +
				", username='" + username + '\'' +
				", sessionToken='" + sessionToken + '\'' +
				'}';
			
	}
		
}

