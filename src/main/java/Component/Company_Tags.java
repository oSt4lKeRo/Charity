package Component;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "company_tags", schema = "public", catalog = "Charity")
@Getter @Setter
public class Company_Tags {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@OneToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company_id;

	@ManyToOne
	@JoinColumn(name = "tags_id", nullable = false)
	private Tags tags_id;
}
