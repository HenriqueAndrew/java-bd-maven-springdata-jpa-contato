package com.curso.entity;

// @author Henrique Andrew da Silva

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.NamedStoredProcedureQuery;

@Entity
@NamedStoredProcedureQuery( 
   name = "Usuario.soma",
   procedureName = "procedure_soma",
   parameters = { 
      @StoredProcedureParameter( 
         mode = ParameterMode.IN, 
         name = "arg",
         type = Integer.class),
      @StoredProcedureParameter(
         mode = ParameterMode.OUT, 
         name = "res",
         type = Integer.class)
   })
public class Usuario {

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
