package Component;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "donats", schema = "public", catalog = "Charity")
@Getter @Setter
public class donats {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "summa")
	private double summa;

	@OneToOne
	@JoinColumn(name = "id", nullable = false)
	private User user_id;


	@OneToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company_id;

	@OneToOne
	@JoinColumn(name = "status_id", nullable = false)
	private Status status_id;
}
