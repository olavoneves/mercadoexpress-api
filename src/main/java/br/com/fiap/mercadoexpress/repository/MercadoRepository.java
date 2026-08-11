package br.com.fiap.mercadoexpress.repository;

import br.com.fiap.mercadoexpress.model.Mercado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio Spring Data JPA para a entidade {@link Mercado}.
 * Fornece as operacoes de CRUD sem necessidade de implementacao manual.
 */
@Repository
public interface MercadoRepository extends JpaRepository<Mercado, Long> {
}
