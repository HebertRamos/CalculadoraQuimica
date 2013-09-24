package ufms.calculadora.modelo;

import java.util.ArrayList;
import java.util.List;

public class EquacaoQuimica {

	private List<Solucao> reagentes;
	private List<Solucao> produtos;

	public List<Solucao> getReagentes() {
		return reagentes;
	}

	public void setReagentes(List<Solucao> reagentes) {
		this.reagentes = reagentes;
	}

	public void adicionarReagente(Solucao reagente) {
		if (this.reagentes == null) {
			this.reagentes = new ArrayList<Solucao>();
		}
		this.reagentes.add(reagente);
	}

	public List<Solucao> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Solucao> produtos) {
		this.produtos = produtos;
	}

	public void adicionarProduto(Solucao produto) {
		if (this.produtos == null) {
			this.produtos = new ArrayList<Solucao>();
		}
		this.produtos.add(produto);
	}

	public String toString() {
		String saida = "";
		Integer indice = 0;
		for (Solucao reagente : this.reagentes) {
			saida += reagente.getIndice();
			for (Elemento elemento : reagente.getElementos()) {
				saida += "("+elemento.getSigla().name()+elemento.getCoeficiente()+")";
			}

			if (indice < this.reagentes.size() - 1) {
				saida += " + ";
			}
			indice++;
		}

		saida += " => ";

		indice = 0;
		for (Solucao reagente : this.produtos) {
			saida += reagente.getIndice();
			for (Elemento elemento : reagente.getElementos()) {
				saida += "("+elemento.getSigla().name()+elemento.getCoeficiente()+")";
			}

			if (indice < this.produtos.size() - 1) {
				saida += " + ";
			}
			indice++;
		}

		return saida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produtos == null) ? 0 : produtos.hashCode());
		result = prime * result + ((reagentes == null) ? 0 : reagentes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EquacaoQuimica other = (EquacaoQuimica) obj;
		if (produtos == null) {
			if (other.produtos != null)
				return false;
		} else if (!this.produtosIguais(other))
			return false;
		if (reagentes == null) {
			if (other.reagentes != null)
				return false;
		} else if (!this.reagentesIguais(other))
			return false;
		return true;
	}

	private boolean reagentesIguais(EquacaoQuimica equacao) {
		for (int i = 0; i < this.getReagentes().size(); i++) {
			if (i >= equacao.getReagentes().size() || !this.getReagentes().get(i).equals(equacao.getReagentes().get(i))) {
				return false;
			}
		}
		return true;
	}

	private boolean produtosIguais(EquacaoQuimica equacao) {
		for (int i = 0; i < this.getProdutos().size(); i++) {
			if (i >= equacao.getProdutos().size() || !this.getProdutos().get(i).equals(equacao.getProdutos().get(i))) {
				return false;
			}
		}
		return true;
	}

}
