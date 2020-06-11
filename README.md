# Aula-57 - Lançando exceções do tipo ResponseStatusException 8.5, 8.6 e 8.7

- Aqui vamos alterar novamente.
- Vamos refatorar.
- PQ ? Da maneira que a classe EntidadeNaoEncontradaException foi implementada para que possa devolver
uma única expcetion podendo devolver diversos tipos de status de erro http e suas mensagens, 
Mas aqui não é o caso da nossa implementação.
- Quem esta lançando esta exception é nossa classe de serviço, e não vamos deixar um código de status HTTP dentro 
da classe de serviço. 
- Olhe como ficou agora a classe `EntidadeNaoEncontradaException`

- Alteramos o buscar da cozinha controller, para chamar um metodo de SV chamado buscarOuFalhar, deixando a classe
cozinhaController ainda mais limpa.

`CozinhaController`
```
@GetMapping("/{cozinhaId}")
public Cozinha buscar(@PathVariable Long cozinhaId) {
    return cadastroCozinha.buscarOuFalhar(cozinhaId);
}
``` 

`CadastroCozinhaService.java` 
```
public Cozinha buscarOuFalhar(Long cozinhaId) {
    return cozinhaRepository.findById(cozinhaId).orElseThrow(
            () -> new EntidadeNaoEncontradaException(String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId)));
}
``` 

Com isso foi refatorado todos os controllers e service para criar o metodo buscarOuFalhar;
