package br.com.projetos.servico.exemplo.repositories;

import br.com.projetos.servico.exemplo.models.entities.DbTabelaPadrao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DbTabelaPadraoRepository extends JpaRepository<DbTabelaPadrao, Long>, QuerydslPredicateExecutor<DbTabelaPadrao> {
}
