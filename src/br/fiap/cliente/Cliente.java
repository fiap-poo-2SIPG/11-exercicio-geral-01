package br.fiap.cliente;

public class Cliente {
    private long cpf;
    private String nome;

    public Cliente(long cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    @Override
    public String toString() {
        String aux = "";
       aux += "CPF: " + cpf + "\n";
       aux += "Nome: " + nome + "\n";
       return aux;
    }
}
