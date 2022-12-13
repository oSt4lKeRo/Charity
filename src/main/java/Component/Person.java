package Component;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter @Setter
public abstract class Person {

	public Person(String username, String password){
		this.username = username;
		this.password = password;
		this.is_active = true;
	}
	public Person(){}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Basic
	@Column(name = "is_active")
	private boolean is_active;
}
