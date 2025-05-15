package br.fiap.controle;
import br.fiap.cliente.Cliente;
import br.fiap.item.ItemProduto;
import br.fiap.notafiscal.NotaFiscal;
import br.fiap.produto.Produto;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Long.parseLong;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;
import static javax.swing.JOptionPane.*;

public class Controle {

    private static List<Cliente> listaCliente = new ArrayList<>();
    private static List<Produto> listaProduto = new ArrayList<>();
    private NotaFiscal nf;
    
    static {
        // lista de clientes
        listaCliente.add(new Cliente(123, "Ana Maria"));
        listaCliente.add(new Cliente(456, "Roberto Carlos"));
        listaCliente.add(new Cliente(789, "Didi Pereira"));
        
        // lista de produtos
        listaProduto.add(new Produto(1, "tênis", 5000, 100));
        listaProduto.add(new Produto(2, "calça", 2000, 1000));
        listaProduto.add(new Produto(3, "camiseta", 400, 500));
    }
    
    public Controle() {
        Random rd = new Random();
        nf = new NotaFiscal(listaCliente.get(rd.nextInt(listaCliente.size())));
    }
    
    public void menu() {
        int opcao;
        while(true) {
            try {
                opcao = parseInt(showInputDialog(gerarMenu()));
                switch(opcao) {
                    case 1:
                        adicionarProduto();
                        break;
                    case 2:
                        removerProduto();
                        break;
                    case 3:
                        fecharCompra();
                        break;
                    case 4:
                        return;
                    default:
                        showMessageDialog(null, "Opção inválida");
                }
            }
            catch(NumberFormatException e) {
                showMessageDialog(null, "Você deve digitar um número\n" + e);
            }
        }
    }

    private void fecharCompra() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        double total = nf.calcularTotal();
        String dado = nf.getCliente().toString();
        showMessageDialog(null, dado + "\nTotal da compra R$ " + df.format(total));
    }

    private void removerProduto() {
        String nome = showInputDialog("Qual produto será removido? ");
        nf.removerProduto(new Produto(nome));
    }

    private void adicionarProduto() {
        String nome;
        int quantidade;

        if(nf.getStatus()) {
            nome = showInputDialog("Qual produto será comprado? ");
            for(Produto produto : listaProduto) {
                if(produto.getNome().equalsIgnoreCase(nome)) {
                    quantidade = parseInt(showInputDialog("Qual a quantidade?"));
                    if(produto.getQuantidadeEmEstoque() >= quantidade) {
                        nf.adicionarItem(new ItemProduto(produto, quantidade));
                        produto.debitarEstoque(quantidade);
                    }
                }
            }
        }
        else {
            showMessageDialog(null, "Compra finalizada");
        }

    }

    private String gerarMenu() {
        String aux = "SISTEMA DE COMPRAS ONLINE\n";
        aux += "1. Adicionar produto\n";
        aux += "2. Remover produto\n";
        aux += "3. Fechar compra\n";
        aux += "4. Finalizar\n";
        return aux;
    }

}
