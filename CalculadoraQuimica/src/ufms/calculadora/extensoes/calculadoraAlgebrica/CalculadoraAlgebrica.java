package ufms.calculadora.extensoes.calculadoraAlgebrica;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe que realiza de calculos em cima de uma  Express�o Alg�brica. 
 * 
 * @author hramos
 *
 */
public class CalculadoraAlgebrica {

	/**
	 * Isola uma vari�vel de uma express�o alg�brica.
	 * 
	 * Passos:
	 * 1 - Caso as ocorrencias da variavel estejam somente do lado direito da express�o os lados s�o trocados, para manter as ocorrencias do lado esquerdo.
	 * 2 - Mantem do lado esquerdo somente as ocorrencias de vari�vel.
	 * 3 - Caso haja v�rias ocorrencias da vari�vel � realizada as opear��es para que reste apenas uma.
	 * 4 - Executa as opera��es do lado direito afim de sobrar apenas um n�mero.
	 * 5 - Se sobrou um operador do em uma posi��o a esquerda da variavel o mesmo � retirado.
	 * 6 - Passa divindo o valor companheiro da variavel.
	 
	 * @param expressaoAlgebrica Express�o Alg�brica.
	 * @param identificadorVariavel Indentificador da Vari�vel.
	 * @return Express�o alg�brica com a vari�vel j� isolada.
	 * @throws OperacaoNaoSuportadaException 
	 */
	public ExpressaoAlgebrica isolaVariavel(ExpressaoAlgebrica expressaoAlgebrica, String identificadorVariavel) throws OperacaoNaoSuportadaException{
		
		
		
		if(expressaoAlgebrica.ladoContemVariavel(expressaoAlgebrica.getLadoDireito(), identificadorVariavel) && !expressaoAlgebrica.ladoContemVariavel(expressaoAlgebrica.getLadoEsquerdo(), identificadorVariavel)){
			expressaoAlgebrica.trocaLado();
		}
		
		for (int i = 0; i < expressaoAlgebrica.getLadoEsquerdo().size(); i++) {
			
			//Que n�o seja um algarismo com a variavel e n�o seja uma operacao
			if(!expressaoAlgebrica.getLadoEsquerdo().get(i).contains(identificadorVariavel) && !EnumOperacaoesCalculadoraAlgebrica.getSimbolosOperacao().containsValue(expressaoAlgebrica.getLadoEsquerdo().get(i))){
				String operador = (i == 0 ? null : expressaoAlgebrica.getLadoEsquerdo().get(i - 1)); 
				String algarismo = expressaoAlgebrica.getLadoEsquerdo().get(i);
			
				passoParaOutroLado(operador, algarismo, expressaoAlgebrica.getLadoEsquerdo(), expressaoAlgebrica.getLadoDireito());
			}
		}
		
		
		if(expressaoAlgebrica.getLadoEsquerdo().size() > 1){
			
		}
		
		
		List<String> ladoComOperacoesExecutadas = executaOperacoesLado(expressaoAlgebrica.getLadoDireito()); 
		expressaoAlgebrica.setLadoDireito(ladoComOperacoesExecutadas);
		
		if(EnumOperacaoesCalculadoraAlgebrica.getSimboloOperacaoPeloString(expressaoAlgebrica.getLadoEsquerdo().get(0)) != null){
			expressaoAlgebrica.getLadoEsquerdo().remove(0);
		}
		
		try {
			Integer valorResultadoLadoDireito = Integer.valueOf(expressaoAlgebrica.getLadoDireito().get(0));
			Integer valorCompanheiro = Integer.valueOf(expressaoAlgebrica.getLadoEsquerdo().get(0).replace(identificadorVariavel, ""));
		
		
			if(valorCompanheiro != 1){
				Integer valorResultante = valorResultadoLadoDireito / valorCompanheiro;
				expressaoAlgebrica.getLadoDireito().set(0, String.valueOf(valorResultante));
			}
			
			String variavelSemValorCompanheiro = expressaoAlgebrica.getLadoEsquerdo().get(0).replace(String.valueOf(valorCompanheiro), "");
			expressaoAlgebrica.getLadoEsquerdo().set(0, variavelSemValorCompanheiro);
			
		}catch (NumberFormatException e) {
			throw new OperacaoNaoSuportadaException();
		}
		
		
		return expressaoAlgebrica;
	}
	
	
	/**
	 * Faz um parse da Express�o Algebrica para uma vari�vel se existe uma igualdade da variavel. Ex: A=10.
	 * 
	 * @param expressaoAlgebrica
	 * @return Variavel
	 */
	public Variavel parsearParaVariavel(ExpressaoAlgebrica expressaoAlgebrica){
		
		expressaoAlgebrica.getLadoEsquerdo().set(0, expressaoAlgebrica.getLadoEsquerdo().get(0).replace("1", ""));
		
		if(expressaoAlgebrica.getLadoEsquerdo().size() == 1 && expressaoAlgebrica.getLadoDireito().size() == 1 && !expressaoAlgebrica.getLadoEsquerdo().get(0).matches(".*\\d.*")){
			Variavel var = new Variavel();
			var.setId(expressaoAlgebrica.getLadoEsquerdo().get(0));
			var.setValor(Integer.valueOf(expressaoAlgebrica.getLadoDireito().get(0)));
			return var;
		}
		
		return null;
		
	}
	
	
	/**
	 * Executa as opera��es suportadas pela calculadora em um lado da Equa��o Algebrica recursivamente at� que o lado tenha apenas um valor.
	 * @param lado
	 * @return
	 */
	public List<String> executaOperacoesLado(List<String> lado){
		
		if(lado.size() >= 3){
			String resultado = executaOperacao(lado.get(0), lado.get(1), lado.get(2));
			List<String> novaLado = new ArrayList<String>();
			novaLado.add(resultado);
			for(int i = 3; i < lado.size(); i++){
				novaLado.add(lado.get(i));
			}
			
			return executaOperacoesLado(novaLado);
			
		}
		
		return lado;
	}
	
	/**
	 * M�todo principal da classe, executa uma opera��o dado dois algarismos e um operador.
	 * 
	 * Posui ainda algumas regras que limitam a possibilidades de execu��o, s�o elas:
	 * 
	 * 1 - Os operadores tem de ser n�meros.
	 * 2 - Suporta os operadores SOMA E SUBTRA��O
	 * 
	 * 
	 * @param alg1
	 * @param operador
	 * @param alg2
	 * @return
	 */
	public String executaOperacao(String alg1, String operador, String alg2){
		Integer algarismo1 = Integer.valueOf(alg1);
		Integer algarismo2 = Integer.valueOf(alg2);
		String saida = "";
		if(EnumOperacaoesCalculadoraAlgebrica.getSimboloOperacaoPeloString(operador).equals(EnumOperacaoesCalculadoraAlgebrica.SOMA)){
			saida = String.valueOf(algarismo1 + algarismo2);
		}else if(EnumOperacaoesCalculadoraAlgebrica.getSimboloOperacaoPeloString(operador).equals(EnumOperacaoesCalculadoraAlgebrica.SUBTRACAO)){
			if(algarismo1 >= algarismo2){
				saida = String.valueOf(algarismo1 - algarismo2);
			}else{
				saida = String.valueOf(algarismo2 - algarismo1);
			}
		}
		
		return saida;
		
	}
	
	public void passoParaOutroLado(String operador, String algarismo, List<String> ladoOrigem, List<String> ladoDestino){
		if(operador != null) {
			operador = EnumOperacaoesCalculadoraAlgebrica.getSimboloInversoOperacao(operador);
			ladoOrigem.remove(operador);
		}else {
			operador = EnumOperacaoesCalculadoraAlgebrica.getSimboloInversoOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA);
		}
		
		ladoOrigem.remove(algarismo);
		
		ladoDestino.add(operador);
		ladoDestino.add(algarismo);
	}
	
	public ExpressaoAlgebrica aplicaCondicao(ExpressaoAlgebrica expressaoAlgebrica, Variavel condicao){
		
		if(condicao != null){
			aplicaCondicao(expressaoAlgebrica.getLadoEsquerdo(), condicao);
			aplicaCondicao(expressaoAlgebrica.getLadoDireito(), condicao);
		}
		return expressaoAlgebrica;
	}
	
	private void aplicaCondicao(List<String> lado, Variavel condicaoVariavel){
		int posicao = 0;
		for (String algarismo : lado) {
			if(algarismo.contains(condicaoVariavel.getId())){
				String algarismoString = algarismo.replace(condicaoVariavel.getId(), "");
				Integer valorCompanheiro = Integer.valueOf(algarismoString);
				valorCompanheiro *= condicaoVariavel.getValor();
				
				lado.set(posicao, String.valueOf(valorCompanheiro));
			}
			posicao++;
		}
	}

}
