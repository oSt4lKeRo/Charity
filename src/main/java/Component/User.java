package Component;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "user", schema = "public", catalog = "Charity")
@Getter @Setter
public class User extends Person{

	public User(String username, String password, String email, Organization organization_id){
		super(username, password);
		this.email = email;
		this.organization_id = organization_id;
	}

    public User(String username, String password, String email){
		super(username, password);
		this.email = email;
		this.organization_id = null;
	}

	public User(){super();}
	@Column(name = "email")
	private String email;

	@OneToOne
	@JoinColumn(name = "organization_id")
	private Organization organization_id;

}
