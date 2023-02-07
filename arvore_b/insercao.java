Inserçao(ponteiroRaiz, key, chavePromovida)
{
     se(ponteiroRaiz == -1) //se ponteiroRaiz nao aponta para nenhuma pagina
     {
         chavePromovida = key
         return(flag que indica que houve promoção de chave)
     }
     senao
     {
         carregue a página P apontada por ponteiroRaiz em memoria primária
         busque por key nessa página P
         posicao = página no qual key poderia estar
     }
    
     se(key foi encontrada)
     {
         //chave ja esta na arvore, retorne uma flag de erro
         return(flag de erro)
     }
    
     flagRetorno = Inserçao(posicao, key, chavePromovida)//procedimento recursivo
    
     se(flagRetorno indica que nao houve promoçao de chave ou que ocorreu um erro)
     {
         return(conteudo de flagRetorno)
     }
     senao se(há espaço na página P para chavePromovida)
     {
         insere chavePromovida na página P
         escreve página P em arquivo
         return(flag que indica que nao houve promocao de chave)
     }
     senao //nao ha espaço em P para key
     {
         realize operação de split em P
         escreva em arquivo  a nova página e a página P
         return(flag que indica que houve promoçao de chave)
     }
}
