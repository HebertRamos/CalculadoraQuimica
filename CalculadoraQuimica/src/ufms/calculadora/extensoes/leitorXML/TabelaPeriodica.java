package ufms.calculadora.extensoes.leitorXML;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import ufms.calculadora.modelo.Elemento;
import ufms.calculadora.modelo.EnumSiglaElemento;

public class TabelaPeriodica {
	
	private static final String arquivoElementos = "elementos.xml";
	private static List<Elemento> listaElementos;
	
	
	public List<Elemento> carregarXML(String nomeArquivo) throws JDOMException {

		nomeArquivo = nomeArquivo.toLowerCase();
		if (!nomeArquivo.contains(".xml")) {
			nomeArquivo += ".xml";
		}

		File f = new File(diretorioRecursos() + nomeArquivo);
		return carregarXML(f);
	}

	public List<Elemento> carregarXML(File f) throws JDOMException, NumberFormatException {

		SAXBuilder builder = new SAXBuilder();

		Document doc = builder.build(f);

		Element root = (Element) doc.getRootElement();

		List elementos = root.getChildren();
		Iterator i = elementos.iterator();

		List<Elemento> tabelaPeriodica = new ArrayList<Elemento>();
		
		while (i.hasNext()) {
			Element noElemento = (Element) i.next();

			Elemento elemento = new Elemento();
			try {
				elemento.setNumeroAtomico(Integer.valueOf(noElemento.getChildText("numeroAtomico")));
				elemento.setSigla(EnumSiglaElemento.valueOf(noElemento.getChildText("simbolo")));
				elemento.setNome(noElemento.getChildText("nome"));
				if(noElemento.getChildText("massaAtomica").equals("")){
					elemento.setMassaAtomica(new Double(0));
				}else{
					elemento.setMassaAtomica(Double.valueOf(noElemento.getChildText("massaAtomica")));
				}
				
				
			} catch (IllegalArgumentException e) {
				noElemento.toString();
			}

			tabelaPeriodica.add(elemento);
		}

		return tabelaPeriodica;
	}

	private String diretorioRecursos() {

		File currentDirFile = new File("");
		String helper = currentDirFile.getAbsolutePath();
		return helper + File.separator + "src" + File.separator + "ufms"+ File.separator + "calculadora" + File.separator + "recursos"+ File.separator;
	}
	
	public static List<Elemento> getElementos(){
		
		if(listaElementos == null){
			TabelaPeriodica tabela = new TabelaPeriodica();
			try {
				listaElementos = tabela.carregarXML(arquivoElementos);
			} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listaElementos;
	}

}
