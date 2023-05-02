# Crypto SYS

## Índices:
- Para acessar as animações do projeto: <a href="https://github.com/nunes1909/crypto-sys/wiki">Clique aqui</a>.

## Sobre este projeto:
A ideia deste aplicativo é:
- Cadastrar e Logar usuários.
- Consumir a API <a href="https://www.mercadobitcoin.com.br/api-doc/">Mercado Bitcoin</a>.
- Listar Cripto moedas.
  - Obter os preços das ultimas 24 hrs.
- Realizar transações locais com as Cripto.
- Realizar depositos e saques em uma carteira local.

## Observação:
<b>Esse projeto foi um desafio proposto por um amigo, que se deu em desenvolver em 5 dias um aplicativo que consumisse a API o Mercado Bitcoin. Esse App deveria ser capaz de exibir uma listagem de Cripto Moedas, e realizar transações locais.

Então aproveitei os meus estudos de Firebase, e implementei um Crud de usuários com o Firebase Authentication. Neste App cada usuário remoto possui uma carteira local, e nela é possível depositar, sacar e realizar transações locais com as Cripto moedas disponíveis.

Como eu tive pouco tempo pra fazer, e desenvolver um projeto de compra e venda com uma conta atrelada envolve muita regra de negócio, com certeza eu posso ter deixado pontos de melhoria passarem. Mas de qual quer forma, fiquei satisfeito com o resultado.
</b>

## Motivação:
Este aplicativo faz parte do meu portfólio pessoal e eu o desenvolvi visando os meus estudos e a prática dos meus conhecimentos. Caso você faça o download deste repositório e observe pontos de melhoria, seja no código, estrutura, UI/UX, etc... Ou queira apenas dar um simples feedback eu ficarei extremamente feliz. Estou aqui para aprender e me tornar um desenvolvedor cada vez melhor. 😁

Você pode entrar em contato comigo através do <a href="https://www.linkedin.com/in/nunes1909/">Linkedin</a> e também através do e-mail: <a href="mailto:gnunes1909@gmail.com">gnunes1909@gmail.com</a>

## Funcionalidades:
- Feature de Login.
- Feature de Cadastro.
- Feature de Listagem de Criptos.
- Feature de Detalhes.
  - Maior e menor preço das ultimas 24hrs e o preço atual.
- Feature de Negociação: 
  - Compra e Venda de Criptos.
- Feature de Carteira.
  - Deposito, Saque e Histórico de Transações.

## Componentes:
- Architecture Components:
  - ViewModel, Fragments, LiveData, Navigation, Flow, Room, etc...
- Injeção de dependência com Koin.
- Consumo de API Rest com Retrofit.
- Armazenamento de dados com DataStore.
- Cadastro e Login de usuários com Firebase Authentication.

# Conceitos:
- Architecture MVVM.

## Formas de acessar o app:
- Download do Apk na release do projeto.
  - <a href="https://github.com/nunes1909/crypto-sys/releases/tag/v1.0">Clique aqui</a>.
- Clonando o repositório:
  - ``` git clone https://github.com/nunes1909/crypto-sys ```

