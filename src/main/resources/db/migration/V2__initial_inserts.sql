INSERT INTO gproj.pessoa (nome, datanascimento, cpf, funcionario, gerente)
VALUES('Joe Carter', '1991-01-23', '97281588070', false, true);

INSERT INTO gproj.pessoa (nome, datanascimento, cpf, funcionario, gerente)
VALUES('Steve Teff', '1995-10-11', '26806120008', true, false);

INSERT INTO gproj.projeto (nome, data_inicio, data_previsao_fim, data_fim, descricao, status, orcamento, risco, idgerente)
VALUES('DSGPROJ', '2024-11-12', '2024-11-12', '2024-11-13', 'Desafio software gestão de projetos', 'EM_ANALISE', 1000.0, 'BAIXO', 1);

INSERT INTO gproj.membros (id_projeto, id_pessoa)
VALUES(1, 2);