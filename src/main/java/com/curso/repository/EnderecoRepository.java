package com.curso.repository;

// @author Henrique Andrew da Silva
import com.curso.entity.Endereco;
import com.curso.entity.projection.CidadeTotal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.transaction.annotation.Transactional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("select e.cidade as cidade count(e.cidade) as total"
            + " from Enderece e"
            + " group by e.cidade")

    List<CidadeTotal> findByCidadeTotal();

    @Procedure("procedure_soma")
    Integer procedureSoma(Integer arg);

    @Modifying
    @Query("update Endereco e set e.cidade = ?1 where e.id = ?2")
    int updateCidadeByld(String cidade, Long id);

    @Transactional(readOnly=false)
    @Modifying
    @Query("delete from Endereco e where e.id =  ?1")
    int deleteEndereco(Long id);

}
