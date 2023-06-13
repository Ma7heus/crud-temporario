create table NACIONALIDADE(
 IDNACIONALIDADE SERIAL PRIMARY KEY,
 DESCRICAO VARCHAR(100)
);

create sequence IDNACIONALIDADE
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO CYCLE;

  insert into nacionalidade (DESCRICAO) VALUES('Brasileiro nato');
  insert into nacionalidade (DESCRICAO) VALUES('Brasileiro naturalizado');
  insert into nacionalidade (DESCRICAO) VALUES('Dupla ou múltipla nacionalidade');
  insert into nacionalidade (DESCRICAO) VALUES('Perda da nacionalidade');
  insert into nacionalidade (DESCRICAO) VALUES('Reaquisição de nacionalidade');
  insert into nacionalidade (DESCRICAO) VALUES('Estrangeiro');