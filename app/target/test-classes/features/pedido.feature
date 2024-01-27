# language: pt
Funcionalidade: Criar pedido

  Cenário: Criar pedido com sucesso
    Dado que a api de carrinho retornou um carrinho fechado
    Então o pedido será criado

  Cenário: Atualizar status de pagamento
    Dado que o status do pagamento esteja aprovado
    Então status de pagamento do pedido será atualizado para aprovado
    Quando o status do pagamento for reprovado
    Então status de pagamento do pedido será atualizado para reprovado

  Cenário: Atualização do status do pedido
    Dado que status de pagamento esta aprovado
    Então o status do pedido será atualizado em produção
    Quando o status do pagamento for reprovado
    Então o status do pedido será atualizado para cancelado

  Cenário: Atualização do status do pedido em preparação
    Dado que status do pedido estiver preparação
    Então o status do pedido será atualizado em pronto

  Cenário: Atualização do status do pedido em pronto
   Dado que status do pedido estiver pronto
   Então o status do pedido será atualizado em finalizado








