package Component;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "address", schema = "public", catalog = "Charity")
@Getter @Setter
public class Address {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@OneToOne()
	@JoinColumn(name = "city_id", nullable = false)
	private City city_id;

	@Column(name = "street")
	private String street;

	@Column(name = "house_number")
	private String house_number;

	@Column(name = "apartment_number")
	private String apartment_number;

	@Column(name = "index")
	private String index;

	@OneToOne()
	@JoinColumn(name = "organization_id", nullable = false)
	private Organization organization_id;

}
