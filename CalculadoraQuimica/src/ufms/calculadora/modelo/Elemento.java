package ufms.calculadora.modelo;

public class Elemento {

	private Integer numeroAtomico;
	private String nome;
	private Double massaAtomica;
	private EnumSiglaElemento sigla;
	
	private Integer indice = 1;
	private Integer coeficiente = 1;

	public Elemento() {
	}

	public Integer getNumeroAtomico() {
		return numeroAtomico;
	}

	public void setNumeroAtomico(Integer numeroAtomico) {
		this.numeroAtomico = numeroAtomico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getMassaAtomica() {
		return massaAtomica;
	}

	public void setMassaAtomica(Double massaAtomica) {
		this.massaAtomica = massaAtomica;
	}

	public Elemento(EnumSiglaElemento siglaElemento) {
		this.sigla = siglaElemento;
	}

	public EnumSiglaElemento getSigla() {
		return sigla;
	}

	public void setSigla(EnumSiglaElemento sigla) {
		this.sigla = sigla;
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

	public boolean equals(Object obj) {
		if (obj instanceof Elemento) {
			Elemento elemento = (Elemento) obj;
			return (this.sigla.equals(elemento.getSigla())
					&& this.indice == elemento.getIndice() && this.coeficiente == elemento
						.getCoeficiente());
		}
		return false;

	}
}
