insert into cozinha (nome) values (1, 'Tailandesa');
insert into cozinha (nome) values (2, 'Indiana');

insert into restaurante (taxa_frete, cozinha_id, id, nome) values (10.00, 1, 1,'Thai Gourmet');
insert into restaurante (taxa_frete, cozinha_id, id, nome) values (9.50, 1, 2, 'Thai Delivery');
insert into restaurante (taxa_frete, cozinha_id, id, nome) values (15.00, 2, 3, 'Tuk Tuk Comida Indiana');

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'Sao Paulo');
insert into estado (id, nome) values (3, 'Ceara');

insert into cidade (id, nome, estado_id) values (1, 'Uberlandia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'Sao Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into forma_pagamento (id, descricao) values (1, 'Cartao de credito');
insert into forma_pagamento (id, descricao) values (2, 'Cartao de debito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');