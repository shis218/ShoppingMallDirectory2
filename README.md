Para executar esse projeto: A opção mais facil é colocar na Heorku, na entrega esta hospeda em https://shoppingmalldirectory2.herokuapp.com/

Os endpoints atuais são: 
[Não funcionais] /rota ; /mapa; /criamapa; /dij ; /dijExemplo[Funciona, mas não é relacionado a esse ep]; /criaPontosDeInteresse
[Implementados 100%] /AdicionaLoja, /listarLojas, /listarLojasPorAtividade, /listarAtividades, /ListarProdutosPorLoja
[Não implementados] /RemoveProduto, /CadastraVenda, /vetMapa


Para fazer o upload em uma hospedagem propria de Heroku: Em deploy method, escolha do GitHub e adicione a devida conexão do github junto com o repositório a ser usado

Se der certo, o botão connect ira aparecer. É possivel adicionar o automatic deploys e o deploy manual na parte mais abaixo

Caso tudo esteja certo, o app já estará pronto pra uso e qualquer novo deploy no github ele será recompilado[a aba activity mostra como está os deploys]


Para compilar, basta utilizar uma IDE que aceite Spring e Maven, rodar um maven clean, maven build e executar



