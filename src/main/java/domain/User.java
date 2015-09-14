package domain;

//import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.ToString;

@Entity
@Table(name = "users")
@ToString
public class User {

	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Size(max = 64)
	@Column(name = "id", nullable = false, updatable = false)
	private String id;

	@NotNull
	@Size(max = 64)
	@Column(name = "password", nullable = false)
	private String password;

	User() {
	}

	public User(final String id, final String password) {
		this.id = id;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}
}
