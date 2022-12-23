package gpcoder.jpa.hibernate.hibernate;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "account")
public class Account implements Serializable {
	private static final long serialVersionUID = -1743019284264764854L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long id;

	@NonNull
	@Column(name = "username", unique = true)
	private String username;

	@NonNull
	@Column(name = "password")
	private String password;

	@NonNull
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	@NonNull
	@Column(name = "modified_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
}
