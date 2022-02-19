//Henrique Andrew da Silva
package com.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.curso.entity.Contato;
import com.curso.entity.projection.NomeCidade;
import com.curso.entity.projection.SemEndereco;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    /*@Query - recurso que proporciona trabalhar com diferentes formas de consultas ao Banco de dados. 
    Exemplo: JPQL, SQL e consultas nomeadas.
    */
    
    /*@Modifying - recurso que tem a função de indicar que o objetivo original da @Query será modificado.
    Desta forma é possível adicionar na @Query uma JPQL com instrução de UPDATE ou DELETE.
    */
                
    //Consulta contato (nome e idade da tabela Contatos - A)
    Contato findByNomeAndIdade(String nome, Integer idade);

    //Consulta contato (nome e idade da tabela Contatos - B consulta JPQL com parâmetro baseado em posições)
    @Query("select c from Contato c "
            + "where idade>= ?1 or nome like ?2")
    List<Contato> findByIdadeOuNome(Integer idade, String nome);

    //Consulta contato (nome e idade da tabela Contatos - B consulta JPQL com parâmetros nomeados)    
    @Query("select c from Contato c "
            + "where idade >= : idade or nome like :nome")
    List<Contato> findByIdadeOuNome2(@Param("idade") Integer idade,@Param("nome") String nome);

    //Consulta contato (apenas primeiro registro da tabela Contatos)
    Contato findFirstBy();

    //Consulta contato (apenas primeiro registro da tabela contatos após ordenação por idade)
    Contato findTopByOrderByIdadesAc();

    //Consulta lista de contatos (trazendo os tres primeiros após ordenação por idade)
    List<Contato> findFirst3ByOrderByIdadeAsc();

    //Consulta lista de contatos (trazendo contatos que tenha idade maior que 18 anos e ordenar por nome)
    @Query(" select c from Contato c  where idade >= 18 order by nome asc")
    List<Contato> findByContatoMaiolrdade();

    //Consulta lista de contatos (onde data de cadastro é maior que ???)
    @Query("select c from Contato c where c.dtCadastro > ?1")
    List<Contato> findByDtCadastroAfter(Date dataCadastro);

    //Consulta lista de contatos (onde data de cadastro é igual a ???)
    @Query(value = "select * from contatos where data_cadastro = ?1", nativeQuery = true)
    List<Contato> findByDataCadastro(Date dataCadastro);

    //Consulta lista de contatos por idade (utilizando o recurso NamedQuery na classe Contato)
    @Query(name = "Contato.byIdade")
    List<Contato> findByIdade(Integer idade);

    //Consulta lista de contatos por idade (utilizando o recurso NamedNativeQuery na classe Contato)
    @Query(name = "Contato.byNome")
    Contato findByNome(String nome);

    //
    List<Contato> findByEnderecoCidade(String cidade);

    SemEndereco findContatoByNome(String nome);

    NomeCidade findContatoCidadeByNome(String nome);

}
