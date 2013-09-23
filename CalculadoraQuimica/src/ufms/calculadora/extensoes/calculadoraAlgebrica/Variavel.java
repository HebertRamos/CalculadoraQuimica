package ufms.calculadora.extensoes.calculadoraAlgebrica;

public class Variavel {

	private String id;
	private Integer valor = 0;
	private Integer valorCompanheiro = 1;
	
	
	public Variavel(){}
	
	public Variavel(String variavel){
		
		this.id = OperacaoMatematica.retiraNumero(variavel);
		String valorCompanheiro = OperacaoMatematica.retiraLetra(variavel);
		
		if(!valorCompanheiro.isEmpty()){
			this.setValorCompanheiro(Integer.valueOf(valorCompanheiro));
		}
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	public Integer getValorCompanheiro() {
		return valorCompanheiro;
	}
	public void setValorCompanheiro(Integer valorCompanheiro) {
		this.valorCompanheiro = valorCompanheiro;
	}
	
	public String toString(){
		return (valorCompanheiro != null && valorCompanheiro != 1   ? valorCompanheiro : "")+id+"="+(valor != null ? valor : "?");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Variavel other = (Variavel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
