# MVVM Study 

    Model - View - View Model

facilita a criacao de testes para a sua
aplicação por ser uma arquitetura desacoplada.

## Um pequeno resumo

### Model

- contem os dados

NÃO pode se comunicar diretamente com a View.
Geralmente, é recomendado expor os dados ao ViewModel
por meio de Observables.

### View

- UI

Deve ser desprovida de qualquer regra de negócio.
Observa o ViewModel por meio de observables.

### View Model

- interface entre model e view

Algo semelhante à relação entre Service e Repository.
Responsável por:

* transoformar os dados do Model.
* fornecer fluxos de dados à View.
* atualizar a View por meio de Hooks
