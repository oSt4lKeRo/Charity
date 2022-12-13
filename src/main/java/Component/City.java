package Component;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "city", schema = "public", catalog = "Charity")
@Getter @Setter
public class City {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int m_id;

	@Column(name = "title")
	private String m_title;

	public City(String m_title){
		this.m_title = m_title;
	}

	public City() {

	}
}
