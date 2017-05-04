package br.univel.meusTestes;

import java.math.BigDecimal;
import java.util.List;

import br.univel.minhaArvove.UniArvore;
import br.univel.minhaArvove.UniArvoreImpl;
import br.univel.minhaArvove.UniNode;
import br.univel.minhaArvove.UniNodeImpl;

public class Principal {
	
	public void mostrarTodosConsole(UniNode<Conta> node, String t) {
		List<UniNode<Conta>> f = node.getFilhos();

		if (!node.isLeaf()) {
			System.out.println(t.split(",")[0] + t.split(",")[1] + String.format("%02d", node.getConteudo().getId())
					+ "\t" + t.split(",")[0] + node.getConteudo().getNome());

			t = " " + t + String.format("%02d", node.getConteudo().getId()) + ".";

			for (int i = 0; i < f.size(); i++) {
				mostrarTodosConsole(f.get(i), t);
			}
		} else {
			System.out.println(
					t.split(",")[0] + t.split(",")[1] + String.format("%02d", node.getConteudo().getId()) + "\t\t"
							+ t.split(",")[0] + node.getConteudo().getNome() + ": R$" + node.getConteudo().getValor());
		}
	}
	
	public Principal() {

		Conta contaAgua = new Conta(1, "Água", new BigDecimal("101.28"));
		UniNode<Conta> noAgua = new UniNodeImpl<>(contaAgua);

		Conta contaAluguel = new Conta(2, "Aluguel", new BigDecimal("1541.97"));
		UniNode<Conta> noAluguel = new UniNodeImpl<>(contaAluguel);

		Conta contaIntTel = new Conta(3, "Internet e Telefone", new BigDecimal("212.44"));
		UniNode<Conta> noIntTel = new UniNodeImpl<>(contaIntTel);

		Conta contaEnElet = new Conta(4, "Energia Elétrica", new BigDecimal("308.91"));
		UniNode<Conta> noEnElet = new UniNodeImpl<>(contaEnElet);

		Conta despesaAdm = new Conta(1, "Despesas Administrativas", new BigDecimal(0));
		UniNode<Conta> noDespesaAdm = new UniNodeImpl<>(despesaAdm);
		noDespesaAdm.addFilho(noAgua);
		noDespesaAdm.addFilho(noAluguel);
		noDespesaAdm.addFilho(noIntTel);
		noDespesaAdm.addFilho(noEnElet);

		
		Conta despesasOper = new Conta(1, "Despesas Operacionais", new BigDecimal(0));
		UniNode<Conta> noDespesaOper = new UniNodeImpl<>(despesasOper);
		noDespesaOper.addFilho(noDespesaAdm);

		
		Conta contaBeneficios = new Conta(1, "Benefícios", new BigDecimal("2180.99"));
		UniNode<Conta> noBeneficios = new UniNodeImpl<>(contaBeneficios);

		Conta contaEncargos = new Conta(2, "Encargos", new BigDecimal("64.9"));
		UniNode<Conta> noEncargos = new UniNodeImpl<>(contaEncargos);

		Conta contaSalarios = new Conta(3, "Salários", new BigDecimal("8317.51"));
		UniNode<Conta> noSalarios = new UniNodeImpl<>(contaSalarios);

		Conta gastoComPessoal = new Conta(2, "Gasto com Pessoal", new BigDecimal(0));
		UniNode<Conta> noGastoComPessoal = new UniNodeImpl<>(gastoComPessoal);
		noGastoComPessoal.addFilho(noBeneficios);
		noGastoComPessoal.addFilho(noEncargos);
		noGastoComPessoal.addFilho(noSalarios);

		noDespesaOper.addFilho(noGastoComPessoal);

		
		Conta contaLimpeza = new Conta(1, "Serviços de Limpeza", new BigDecimal("2150.8"));
		UniNode<Conta> noLimpeza = new UniNodeImpl<>(contaLimpeza);

		Conta contaManutencao = new Conta(2, "Serviços de Manutenção", new BigDecimal("370.19"));
		UniNode<Conta> noManutencao = new UniNodeImpl<>(contaManutencao);

		Conta ManutencaoLimpeza = new Conta(3, "Manutenção e Limpeza", new BigDecimal(0));
		UniNode<Conta> noManutencaoLimpeza = new UniNodeImpl<>(ManutencaoLimpeza);
		noManutencaoLimpeza.addFilho(noLimpeza);
		noManutencaoLimpeza.addFilho(noManutencao);

		noDespesaOper.addFilho(noManutencaoLimpeza);

		
		Conta contaMaterialEscritorio = new Conta(1, "Material de Escritorio", new BigDecimal("201.6"));
		UniNode<Conta> noMaterialEscritorio = new UniNodeImpl<>(contaMaterialEscritorio);

		Conta contaMaterialLimpeza = new Conta(2, "Material de Limpeza", new BigDecimal("280.45"));
		UniNode<Conta> noMaterialLimpeza = new UniNodeImpl<>(contaMaterialLimpeza);

		Conta materiais = new Conta(4, "Materiais", new BigDecimal(0));
		UniNode<Conta> noMateriais = new UniNodeImpl<>(materiais);
		noMateriais.addFilho(noMaterialEscritorio);
		noMateriais.addFilho(noMaterialLimpeza);

		noDespesaOper.addFilho(noMateriais);

		
		UniArvore<UniNode<Conta>> planoContas = new UniArvoreImpl(noDespesaOper);
		mostrarTodosConsole(planoContas.getRaiz(), " , ");
		somarFilhos(planoContas.getRaiz()); 
		System.out.println("\nTotal de despesas: R$" + planoContas.getRaiz().getConteudo().getValor());
	}

	private void somarFilhos(UniNode<Conta> node) {
		List<UniNode<Conta>> f = node.getFilhos();

		for (int i = 0; i < f.size(); i++) {
			if (!f.get(i).isLeaf()) {
				somarFilhos(f.get(i));
				node.getConteudo().setValor(node.getConteudo().getValor().add(f.get(i).getConteudo().getValor()));
			} 
			else {
				node.getConteudo().setValor(node.getConteudo().getValor().add(f.get(i).getConteudo().getValor()));
			}
		}
	}

	public static void main(String[] args) {
		new Principal();
	}
}
