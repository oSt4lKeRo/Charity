package Component;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "company", schema = "public", catalog = "Charity")
@Getter @Setter
public class Company {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	private double price;

	@ManyToOne
	@JoinColumn(name = "organization_id", nullable = false)
	private Organization organization_id;


	@OneToOne
	@JoinColumn(name = "id")
	private Status status_id;

}
