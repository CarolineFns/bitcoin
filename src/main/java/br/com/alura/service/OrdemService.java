package br.com.alura.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.SecurityContext;

import br.com.alura.model.Ordem;
import br.com.alura.model.Usuario;
import br.com.alura.repository.OrdemRepository;

@ApplicationScoped
public class OrdemService {

	@Inject
	OrdemRepository ordemRepository;

	public void inserir(SecurityContext securityContext, Ordem ordem) {
		Optional<Usuario> usuarioOptional = Usuario.findByIdOptional(ordem.getIdUsuario());
		Usuario usuario = usuarioOptional.orElseThrow();

		if (!usuario.getUsername().equals(securityContext.getUserPrincipal().getName()))
			throw new RuntimeException("Usuário logado diferente do usuário da ordem.");

		ordem.setData(new Date());
		ordem.setStatus("ENVIADA");
		ordemRepository.persist(ordem);
	}

	public List<Ordem> listar() {
		return ordemRepository.listAll();
	}

}
