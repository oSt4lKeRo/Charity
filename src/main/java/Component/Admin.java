package Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "admin", schema = "public", catalog = "Charity")
@Getter @Setter
public class Admin extends Person{

	public Admin() {

	}
}
