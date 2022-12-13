package Component;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "organization", schema = "public", catalog = "Charity")
@Getter @Setter
public class Organization {

	public Organization(String title, String description){
		this.title = title;
		this.description = description;
		this.date_create = LocalDate.now();
		this.creator_id = null;
	}

	public Organization() {

	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "date_create")
	private LocalDate date_create;

	@OneToOne
	@JoinColumn(name = "creator_id")
	private Admin creator_id;

}
