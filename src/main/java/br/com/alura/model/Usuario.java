package br.com.alura.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

@Entity
@UserDefinition
public class Usuario extends PanacheEntityBase {

	@Id
	@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
	private Long id;

	private String nome;

	private String cpf;

	@Username
	private String username;

	@Password
	private String password;

	@Roles
	private String role;

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNome() {
		return nome;
	}

	public String getUsername() {
		return username;
	}

	@JsonbTransient
	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public static void adicionar(Usuario usuario) {
		usuario.password = BcryptUtil.bcryptHash(usuario.password);
		usuario.role = validarUsername(usuario.username);
		usuario.persist();
	}

	private static String validarUsername(String username) {
		return username.equals("alura") ? "admin" : "user";
	}

}
