package ufms.calculadora.modelo;

import java.util.ArrayList;
import java.util.List;

public class Solucao {

	private List<Elemento> elementos;
	private Integer indice = 1;
	private Integer coeficiente = 1;

	public List<Elemento> getElementos() {
		return elementos;
	}

	public void setElementos(List<Elemento> elementos) {
		this.elementos = elementos;
	}

	public void adicionarElemento(Elemento elemento) {
		if (this.elementos == null) {
			this.elementos = new ArrayList<Elemento>();
		}
		this.elementos.add(elemento);
	}

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	public Integer getCoeficiente() {
		return coeficiente;
	}

	public void setCoeficiente(Integer coeficiente) {
		this.coeficiente = coeficiente;
	}

	public Elemento buscaElementoPelaSigla(EnumSiglaElemento sigla) {
		if (this.elementos == null || this.elementos.size() == 0) {
			return null;
		}

		Elemento elementoBuscado = new Elemento();
		elementoBuscado.setSigla(sigla);

		Integer indiceElementoBuscado = this.elementos.lastIndexOf(elementoBuscado);
		if (indiceElementoBuscado == -1) {
			return null;
		}

		return this.elementos.get(indiceElementoBuscado);
	}

	@Override
	public boolean equals(Object objeto) {

		if (objeto instanceof Solucao) {
			Solucao solucao = (Solucao) objeto;
			if (this.elementos == null || this.elementos.size() == 0) {
				if (solucao.elementos == null || solucao.elementos.size() == 0) {
					return true;
				}
			} else {
				if (this.coeficiente != solucao.getCoeficiente() || this.indice != solucao.getIndice()) {
					return false;
				}
				for (int i = 0; i < this.elementos.size(); i++) {
					if (!this.elementos.get(i).equals(solucao.elementos.get(i))) {
						return false;
					}
				}
				return true;
			}

		}

		return false;

	}
}
