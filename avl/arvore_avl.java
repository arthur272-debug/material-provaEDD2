import java.util.ArrayList;

public class arvore_avl {

    protected No raiz;

    public void inserirNo(int k) {
        No variavel_no = new No(k);
        inserirNo(this.raiz, variavel_no);
    }

    public void inserirNo(No compararNo, No inserir_variavelNo) {

        if (compararNo == null) {
            this.raiz = inserir_variavelNo;

        } else {

            if (inserir_variavelNo.getChave() < compararNo.getChave()) {

                if (compararNo.getEsquerda() == null) {
                    compararNo.setEsquerda(inserir_variavelNo);
                    inserir_variavelNo.setPai(compararNo);
                    verificarBalanceamento(compararNo);

                } else {
                    inserirNo(compararNo.getEsquerda(), inserir_variavelNo);
                }

            } else if (inserir_variavelNo.getChave() > compararNo.getChave()) {

                if (compararNo.getDireita() == null) {
                    compararNo.setDireita(inserir_variavelNo);
                    inserir_variavelNo.setPai(compararNo);
                    verificarBalanceamento(compararNo);

                } else {
                    inserirNo(compararNo.getDireita(), inserir_variavelNo);
                }

            } else {

            }
        }
    }

    public void verificarBalanceamento(No no_atual) {
        setBalanceamento(no_atual);
        int balanceamento = no_atual.getBalanceamento();

        if (balanceamento == -2) {

            if (calcularAltura(no_atual.getEsquerda().getEsquerda()) >= calcularAltura(no_atual.getEsquerda().getDireita())) {
                no_atual = rotacaoDireita(no_atual);

            } else {
                no_atual = duplaRotacaoEsquerdaDireita(no_atual);
            }

        } else if (balanceamento == 2) {

            if (calcularAltura(no_atual.getDireita().getDireita()) >= calcularAltura(no_atual.getDireita().getEsquerda())) {
                no_atual = FazerRotacaoEsquerda(no_atual);

            } else {
                no_atual = duplaRotacaoDireitaEsquerda(no_atual);
            }
        }

        if (no_atual.getPai() != null) {
            verificarBalanceamento(no_atual.getPai());
        } else {
            this.raiz = no_atual;
        }
    }

    public void removerNo(int k) {
        removerNo(this.raiz, k);
    }

    public void removerNo(No no_atual, int variavel_no) {
        if (no_atual == null) {
            return;

        } else {

            if (no_atual.getChave() > variavel_no) {
                removerNo(no_atual.getEsquerda(), variavel_no);

            } else if (no_atual.getChave() < variavel_no) {
                removerNo(no_atual.getDireita(), variavel_no);

            } else if (no_atual.getChave() == variavel_no) {
                removerNoEncontrado(no_atual);
            }
        }
    }

    public void removerNoEncontrado(No variavel_no) {
        No r;

        if (variavel_no.getEsquerda() == null || variavel_no.getDireita() == null) {

            if (variavel_no.getPai() == null) {
                this.raiz = null;
                variavel_no = null;
                return;
            }
            r = variavel_no;

        } else {
            r = sucessor(variavel_no);
            variavel_no.setChave(r.getChave());
        }

        No p;
        if (r.getEsquerda() != null) {
            p = r.getEsquerda();
        } else {
            p = r.getDireita();
        }

        if (p != null) {
            p.setPai(r.getPai());
        }

        if (r.getPai() == null) {
            this.raiz = p;
        } else {
            if (r == r.getPai().getEsquerda()) {
                r.getPai().setEsquerda(p);
            } else {
                r.getPai().setDireita(p);
            }
            verificarBalanceamento(r.getPai());
        }
        r = null;
    }

    public No FazerRotacaoEsquerda(No variavel_no) {

        No direita = variavel_no.getDireita();
        direita.setPai(variavel_no.getPai());

        variavel_no.setDireita(direita.getEsquerda());

        if (variavel_no.getDireita() != null) {
            variavel_no.getDireita().setPai(variavel_no);
        }

        direita.setEsquerda(variavel_no);
        variavel_no.setPai(direita);

        if (direita.getPai() != null) {

            if (direita.getPai().getDireita() == variavel_no) {
                direita.getPai().setDireita(direita);

            } else if (direita.getPai().getEsquerda() == variavel_no) {
                direita.getPai().setEsquerda(direita);
            }
        }

        setBalanceamento(variavel_no);
        setBalanceamento(direita);

        return direita;
    }

    public No rotacaoDireita(No variavel_no) {

        No esquerda = variavel_no.getEsquerda();
        esquerda.setPai(variavel_no.getPai());

        variavel_no.setEsquerda(esquerda.getDireita());

        if (variavel_no.getEsquerda() != null) {
            variavel_no.getEsquerda().setPai(variavel_no);
        }

        esquerda.setDireita(variavel_no);
        variavel_no.setPai(esquerda);

        if (esquerda.getPai() != null) {

            if (esquerda.getPai().getDireita() == variavel_no) {
                esquerda.getPai().setDireita(esquerda);

            } else if (esquerda.getPai().getEsquerda() == variavel_no) {
                esquerda.getPai().setEsquerda(esquerda);
            }
        }

        setBalanceamento(variavel_no);
        setBalanceamento(esquerda);

        return esquerda;
    }

    public No duplaRotacaoEsquerdaDireita(No variavel_no) {
        variavel_no.setEsquerda(FazerRotacaoEsquerda(variavel_no.getEsquerda()));
        return rotacaoDireita(variavel_no);
    }

    public No duplaRotacaoDireitaEsquerda(No variavel_no) {
        variavel_no.setDireita(rotacaoDireita(variavel_no.getDireita()));
        return FazerRotacaoEsquerda(variavel_no);
    }

    public No sucessor(No variavel_no) {
        if (variavel_no.getDireita() != null) {
            No r = variavel_no.getDireita();
            while (r.getEsquerda() != null) {
                r = r.getEsquerda();
            }
            return r;
        } else {
            No x = variavel_no.getPai();
            while (x != null && variavel_no == x.getDireita()) {
                variavel_no = x;
                x = variavel_no.getPai();
            }
            return x;
        }
    }

    private int calcularAltura(No no_atual) {
        if (no_atual == null) {
            return -1;
        }

        if (no_atual.getEsquerda() == null && no_atual.getDireita() == null) {
            return 0;

        } else if (no_atual.getEsquerda() == null) {
            return 1 + calcularAltura(no_atual.getDireita());

        } else if (no_atual.getDireita() == null) {
            return 1 + calcularAltura(no_atual.getEsquerda());

        } else {
            return 1 + Math.max(calcularAltura(no_atual.getEsquerda()), calcularAltura(no_atual.getDireita()));
        }
    }

    private void setBalanceamento(No no) {
        no.setBalanceamento(calcularAltura(no.getDireita()) - calcularAltura(no.getEsquerda()));
    }

    final protected ArrayList<No> inorder() {
        ArrayList<No> ret = new ArrayList<No>();
        inorder(raiz, ret);
        return ret;
    }

    final protected void inorder(No no, ArrayList<No> lista) {
        if (no == null) {
            return;
        }
        inorder(no.getEsquerda(), lista);
        lista.add(no);
        inorder(no.getDireita(), lista);
    }
}
