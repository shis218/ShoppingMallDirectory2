package com.example.ShoppingMallDirectory2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.jena.*;
import org.apache.jena.atlas.lib.StrUtils;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.query.ResultSet;

import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.impl.LiteralImpl;
import org.apache.jena.rdfconnection.RDFConnectionFuseki;
import org.apache.jena.rdfconnection.RDFConnectionRemoteBuilder;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;

//Devolvendo pagina em HTML
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import ch.qos.logback.core.net.SyslogOutputStream;



@RestController
public class restcontroller {
		String urlbase="http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/shoppingmalldirectory2";
		private static final String template = "Hello, %s!";
		private final AtomicLong counter = new AtomicLong();
		
		
	
		@GetMapping("/mapa")
		public String mapear(@RequestParam(value = "lugar", defaultValue = "mapaEach") String nomemapa) throws InterruptedException {
			StringBuilder sb=new StringBuilder();
			int[][][] intmap=getMapa(nomemapa);
			//int[][][] intmap=null;
			//String[][] strPontosDeInteresse=this.getPontosDeInteresse();
			//System.out.println(strPontosDeInteresse[0][1]);
			
			sb.append("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "<body>\r\n"
					+ "\r\n");
			/*for(int i=0;i<255;i++) {
					if(!(strPontosDeInteresse[i][0]==null)) {
						sb.append(strPontosDeInteresse[i][0]+"<br> \n");
				//		sb.append(strPontosDeInteresse[i][1]+"<br> \n");
				//		sb.append(strPontosDeInteresse[i][2]+"<br> \n");
					}
				}*/
					sb.append("<br><canvas id=\"myCanvas\" width=\"1500\" height=\"1500\"\r\n"
					+ "style=\"border:1px solid #c3c3c3;\">\r\n"
					+ "Your browser does not support the canvas element.\r\n"
					+ "</canvas>\r\n"
					+ "\r\n"
					+ "<script>\r\n"
					+ "var canvas = document.getElementById(\"myCanvas\");\r\n"
					+ "var ctx = canvas.getContext(\"2d\");\r\n"
					+ "ctx.lineWidth = 0.3;\r\n");
			
			for(int i=0;i<intmap[0].length-1;i++) {
			
			sb.append("ctx.moveTo("+intmap[0][i][0]+","+ intmap[0][i][1]+");");
			System.out.print("ctx.moveTo("+intmap[0][i][0]+","+ intmap[0][i][1]+");");
			sb.append("ctx.lineTo("+intmap[0][i+1][0]+","+ intmap[0][i+1][1]+");");
			System.out.print("ctx.lineTo("+intmap[0][i+1][0]+","+ intmap[0][i+1][1]+");");
			sb.append("ctx.stroke();");
			//sb.append("ctx.moveTo("+strPontosDeInteresse[i][1]+","+ strPontosDeInteresse[i][2]+");");
		//	int a=Integer.parseInt(strPontosDeInteresse[i][1])+3;
			//int b=Integer.parseInt(strPontosDeInteresse[i][2]);
		//	sb.append("ctx.moveTo("+a+","+ b+");");
		//	sb.append("ctx.stroke();");
			}
			
			
			
					sb.append("</script>\r\n"
					+ "\r\n"
					+ "</body>\r\n"
					+ "</html>\r\n"
					+ "\r\n"
					+ "");
			return sb.toString();
		}
		
		
		

		@GetMapping("/AdicionaLoja")
		public String addLoja(@RequestParam(value = "lugar", defaultValue = "ShoppingTL") String nomemapa,@RequestParam(value = "nomeLoja", defaultValue = "Parede0") String nomeLoja, @RequestParam(value = "atividade", defaultValue = "Atividade") String atividade, @RequestParam(value = "posX1", defaultValue = "0") String posX1,  @RequestParam(value = "posX2", defaultValue = "0") String posX2,  @RequestParam(value = "posX3", defaultValue = "0") String posX3,  @RequestParam(value = "posX4", defaultValue = "0") String posX4,  @RequestParam(value = "posY1", defaultValue = "0") String posY1,  @RequestParam(value = "posY2", defaultValue = "0") String posY2,  @RequestParam(value = "posY3", defaultValue = "0") String posY3,  @RequestParam(value = "posY4", defaultValue = "0") String posY4,  @RequestParam(value = "posZ", defaultValue = "0") String posZ,@RequestParam(value = "siteLoja", defaultValue = "0") String siteLoja,@RequestParam(value = "Con11", defaultValue = "0") String Con11,@RequestParam(value = "Con12", defaultValue = "0") String Con12,@RequestParam(value = "Con21", defaultValue = "0") String Con21,@RequestParam(value = "Con22", defaultValue = "0") String Con22,@RequestParam(value = "Con31", defaultValue = "0") String Con31,@RequestParam(value = "Con32", defaultValue = "0") String Con32,@RequestParam(value = "Con41", defaultValue = "0") String Con41,@RequestParam(value = "Con42", defaultValue = "0") String Con42,@RequestParam(value = "Dist1", defaultValue = "0") String Dist1,@RequestParam(value = "Dist2", defaultValue = "0") String Dist2,@RequestParam(value = "Dist3", defaultValue = "0") String Dist3,@RequestParam(value = "Dist4", defaultValue = "0") String Dist4) {
			StringBuilder sb=new StringBuilder();
			//int[][][] intmap=getMapa(nomemapa);
			
			
			sb.append("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "<body>\r\n"
					+ "\r\n"
					+ "<form>"   //Fazendo um form que leva a propria pagina
					+ "Nome da Parede: <input name='nomeLoja' id='nomeLoja' value='"+nomeLoja+"'><br>"
					+ "Atividade:<input name='atividade' id='atividade' value='"+atividade+"'>"
					+ "PosX1:<input name='posX1' id='posX1' value='"+posX1+"'>"
					+ "PosY1:<input name='posY1' id='posY1' value='"+posY1+"'><br>"
					+ "PosX2:<input name='posX2' id='posX2' value='"+posX2+"'>"
					+ "PosY2:<input name='posY2' id='posY2' value='"+posY2+"'><br>"
					+ "PosX3:<input name='posX3' id='posX3' value='"+posX3+"'>"
					+ "PosY3:<input name='posY3' id='posY3' value='"+posY3+"'><br>"
					+ "PosX4:<input name='posX4' id='posX4' value='"+posX4+"'>"
					+ "PosY4:<input name='posY4' id='posY4' value='"+posY4+"'><br>"
					+ "PosZ:<input name='posZ' id='posZ' value='"+posZ+"'><br>"
					+ "Conecta: 1-2-3-4<br>"
					/*+ "Con1.1:<input name='Con11' id='Con11' value='"+Con11+"'><br>"
					+ "Con1.2:<input name='Con12' id='Con12' value='"+Con12+"'><br>"
					+ "Dist1:<input name='Dist1' id='Dist1' value='"+Dist1+"'><br>"
					+ "Con2.1:<input name='Con21' id='Con21' value='"+Con21+"'><br>"
					+ "Con2.2:<input name='Con22' id='Con22' value='"+Con22+"'><br>"
					+ "Dist2:<input name='Dist2' id='Dist2' value='"+Dist2+"'><br>"
					+ "Con3.1:<input name='Con31' id='Con31' value='"+Con31+"'><br>"
					+ "Con3.2:<input name='Con32' id='Con32' value='"+Con32+"'><br>"
					+ "Dist3:<input name='Dist3' id='Dist3' value='"+Dist3+"'><br>"
					+ "Con4.1:<input name='Con41' id='Con41' value='"+Con41+"'><br>"
					+ "Con4.2:<input name='Con42' id='Con42' value='"+Con42+"'><br>"
					+ "Dist4:<input name='Dist4' id='Dist4' value='"+Dist4+"'><br>"*/
					+ "<button>ProximaLinha</button><br>"
					+ "</form>"
					+ "</body>\r\n"
					+ "</html>\r\n"
					+ "\r\n"
					+ "");
			if(!nomeLoja.equals("Parede0")){
			//Insere coordenadas em gml point
				
			String coordenadas1="PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n prefix owl: <http://www.w3.org/2002/07/owl#> \n"
					+"INSERT DATA{	\r\n"
			+"<"+urlbase+"#quadrado"+nomeLoja+"1> <"+urlbase+"#gml:PosX> "+posX1+";"
			+"<"+urlbase+"#gml:PosY> "+posY1+";"
			+"<"+urlbase+"#gml:PosZ> "+posZ+". }";
			String coordenadas2="PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n prefix owl: <http://www.w3.org/2002/07/owl#> \n"
					+"INSERT DATA{	\r\n"
			+"<"+urlbase+"#quadrado"+nomeLoja+"2> <"+urlbase+"#gml:PosX> "+posX2+";"
			+"<"+urlbase+"#gml:PosY> "+posY2+";"
			+"<"+urlbase+"#gml:PosZ> "+posZ+". }";
			String coordenadas3="PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n prefix owl: <http://www.w3.org/2002/07/owl#> \n"
					+"INSERT DATA{	\r\n"
			+"<"+urlbase+"#quadrado"+nomeLoja+"3> <"+urlbase+"#gml:PosX> "+posX3+";"
			+"<"+urlbase+"#gml:PosY> "+posY3+";"
			+"<"+urlbase+"#gml:PosZ> "+posZ+". }";
			String coordenadas4="PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n prefix owl: <http://www.w3.org/2002/07/owl#> \n"
					+"INSERT DATA{	\r\n"
			+"<"+urlbase+"#quadrado"+nomeLoja+"4> <"+urlbase+"#gml:PosX> "+posX4+";"
			+"<"+urlbase+"#gml:PosY> "+posY4+";"
			+"<"+urlbase+"#gml:PosZ> "+posZ+". }";
			this.InsertGenerico(coordenadas1);
			this.InsertGenerico(coordenadas2);
			this.InsertGenerico(coordenadas3);
			this.InsertGenerico(coordenadas4);
			
			
			//Adiciona referencias externas
			String AdicionaRefs="PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n prefix owl: <http://www.w3.org/2002/07/owl#> \n"
					+"INSERT DATA{	\r\n"
			+"<"+urlbase+"#ref"+nomeLoja+"1> <"+urlbase+"#uri> <"+urlbase+"#quadrado"+nomeLoja+"1>;"
			+"<"+urlbase+"#name> <"+urlbase+"#quadrado"+nomeLoja+"1> ."
			+"<"+urlbase+"#ref"+nomeLoja+"2> <"+urlbase+"#uri> <"+urlbase+"#quadrado"+nomeLoja+"2>;"
			+"<"+urlbase+"#name> <"+urlbase+"#quadrado"+nomeLoja+"2> ."
			+"<"+urlbase+"#ref"+nomeLoja+"3> <"+urlbase+"#uri> <"+urlbase+"#quadrado"+nomeLoja+"3>;"
			+"<"+urlbase+"#name> <"+urlbase+"#quadrado"+nomeLoja+"3> ."
			+"<"+urlbase+"#ref"+nomeLoja+"4> <"+urlbase+"#uri> <"+urlbase+"#quadrado"+nomeLoja+"4>;"
			+"<"+urlbase+"#name> <"+urlbase+"#quadrado"+nomeLoja+"4> . }";
			this.InsertGenerico(AdicionaRefs);
			
			
			//Adiciona indoorcore:CellSpaceBoundaryType baseada nas Refs
			String CSBTToRef="PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n prefix owl: <http://www.w3.org/2002/07/owl#> \n"
					+"INSERT DATA{	\r\n"
					+"<"+urlbase+"#CSBT"+nomeLoja+"> <"+urlbase+"#externalReference> <"+urlbase+"#ref"+nomeLoja+"1>;"
					+"<"+urlbase+"#externalReference> <"+urlbase+"#ref"+nomeLoja+"2>;"
					+"<"+urlbase+"#externalReference> <"+urlbase+"#ref"+nomeLoja+"3>;"
					+"<"+urlbase+"#externalReference> <"+urlbase+"#ref"+nomeLoja+"4>. }"
					;
			this.InsertGenerico(CSBTToRef);
			
			
			//Adiciona CellSpace da loja [Ouve um erro na ontologia de adicionar N refs na CSBoundaryType ao inves de N PartialBounded
			String CellSpace="PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n prefix owl: <http://www.w3.org/2002/07/owl#> \n"
					+"INSERT DATA{	\r\n"
					+"<"+urlbase+"#CSP"+nomeLoja+"> <"+urlbase+"#partialboundedBy> <"+urlbase+"#CSBT"+nomeLoja+">;"
					+"<"+urlbase+"#level> "+posZ+".  }"
					;
			this.InsertGenerico(CellSpace);
			
			//Adiciona Atividade
			String ativi="PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n prefix owl: <http://www.w3.org/2002/07/owl#> \n"
					+"INSERT DATA{	\r\n"
					+"<"+urlbase+"#"+atividade+"> <"+urlbase+"#isThemeOf> <"+urlbase+"#"+nomeLoja+">.  }";
			this.InsertGenerico(ativi);
			
			//Insere Store
			String lojaStore="PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n prefix owl: <http://www.w3.org/2002/07/owl#> \n"
					+"INSERT DATA{	\r\n"
					+"<"+urlbase+"#"+nomeLoja+"> <"+urlbase+"#CellSpace> <"+urlbase+"#CSP"+nomeLoja+">;"
					+"<"+urlbase+"#hasActivity> <"+urlbase+"#"+atividade+">;"
					+"<"+urlbase+"#isLocatedOn> <"+urlbase+"#"+nomemapa+">;"
					+"<"+urlbase+"#SiteLoja> "+siteLoja +";"
					+"<"+urlbase+"#name> \""+nomeLoja+"\" . }"
					;
			this.InsertGenerico(lojaStore);
			
			
			//Insere no Mall
			String Mall="PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n prefix owl: <http://www.w3.org/2002/07/owl#> \n"
					+"INSERT DATA{	\r\n"
					+"<"+urlbase+"#"+nomemapa+"> <"+urlbase+"#hosts> <"+urlbase+"#"+nomeLoja+">. }";
			this.InsertGenerico(Mall);	
			}
			
			return sb.toString();
		}
		
		
		@GetMapping("/listarLojas")
		public nomeLojas[] listarLojas() {
			
			String[][] res=this.genericSearch1("<"+urlbase+"#ShoppingTL>","<"+urlbase+"#hosts>", "?A");
			nomeLojas[] b=new nomeLojas[res.length];
			for(int i=0;i<res.length;i++) {
				nomeLojas gen=new nomeLojas();
				gen.setNome(this.CleanRST(res[i][0],"naoremove"));
				b[i]=gen;
			}
			return b;
		}
		
		
		
		@GetMapping("/listarLojasPorAtividade")
		public nomeLojas[] listarLojasAtv(@RequestParam(value = "atividade", defaultValue = "Atividade") String atividade) {
			//Busca todas lojas que tem o tema
			String[][] res=this.genericSearch1("<"+urlbase+"#"+atividade+">", "<"+urlbase+"#isThemeOf>", "?A");
			nomeLojas[] b=new nomeLojas[res.length];
			for(int i=0;i<res.length;i++) {
				nomeLojas gen=new nomeLojas();
				gen.setNome(this.CleanRST(res[i][0],"naoremove"));
				b[i]=gen;
			}
			return b;
		}
		
		@GetMapping("/listarAtividades")
		public nomeLojas[] listarAtividades() {
			
			String[][] res=this.genericSearch1("?A", "<"+urlbase+"#isThemeOf>", "?C");
			nomeLojas[] b=new nomeLojas[res.length];
			for(int i=0;i<res.length;i++) {
				nomeLojas gen=new nomeLojas();
				gen.setNome(res[i][0]);
				b[i]=gen;
			}
			return b;
		}

		
		//Pra fazer
		@GetMapping("/ListarProdutosPorLoja")
		public Produto[] ListaProdutosPorLoja(@RequestParam(value = "nomeLoja", defaultValue = "lojas_americanas") String nomeLoja) {
			
			
			String[][] te=this.genericSearch1("?A", "<"+urlbase+"#isSoldAt>", "<"+urlbase+"#"+nomeLoja+">");
			System.out.println(te.length+"\n\n"+te[0][0]+"\n \n"+te[0][1]);
			Produto[] resp=new Produto[te.length];
			for(int i=0;i<te.length;i++) {
			String[][] prod=this.genericSearch2("<"+te[i][0]+">", "?A", "?B");
			Produto p=new Produto();	
			for(int j=0;j<prod.length;j++){
				
					prod[j][0]=prod[j][0].replace("h","h");
					prod[j][1]=prod[j][1].replace("h","h");
						if(prod[j][0].equals("http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/shoppingmalldirectory2#price")){
							p.setPrice(this.CleanXSD(prod[j][1])+"");
							System.out.print("\n\n"+this.CleanXSD(prod[j][1]));
						}
						else if(prod[j][0].equals("http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/shoppingmalldirectory2#hasQuantity")){
							p.setHasQuantity(this.CleanXSD(prod[j][1])+"");
						}
						else if(prod[j][0].equals("http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/shoppingmalldirectory2#hasProdName")){
							p.setHasProdName(this.CleanXSD(prod[j][1])+"");
						}
						else if(prod[j][0].equals("http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/shoppingmalldirectory2#hasCode")){
							p.setHasCode(this.CleanXSD(prod[j][1])+"");
						}
						else if(prod[j][0].equals("http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/shoppingmalldirectory2#isSoldAt")){
							p.setStoreName(nomeLoja);
						}
						else {
						System.out.print(prod[j][0]+ " -----"+prod[j][1]+"\n");
						}
					
				}
			
			resp[i]=p;
			}
			return resp;
			
		}
		//Pra fazer
		@GetMapping("/UpdateProduto")
		public String UpdateProduto(@RequestParam(value = "nomeProduto", defaultValue = "Hamburger")  String nomeProduto,@RequestParam(value = "valorNovo", defaultValue = "5")int novoValor) {
			String Dele="PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n prefix owl: <http://www.w3.org/2002/07/owl#> \n"
					+"DELETE WHERE{	\r\n"
					+"<"+urlbase+"#"+nomeProduto+"> <"+urlbase+"#hasQuantity> ?C .}"
					;
			this.InsertGenerico(Dele);
			String Ins="PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n prefix owl: <http://www.w3.org/2002/07/owl#> \n"
					+"INSERT DATA{	\r\n"
					+"<"+urlbase+"#"+nomeProduto+"> <"+urlbase+"#hasQuantity> "+novoValor+" ;}"
					;
			this.InsertGenerico(Ins);
			
			return "Op executada";
		}
		
		//Pra fazer
		@GetMapping("/CadastraVenda")
		public void CadastraVenda() {
			
		}
				
		
		
		//atualizar a rota de dij por lojas
		
		@GetMapping("/rota")
		public Rota[] rota(@RequestParam(value = "lugar", defaultValue = "mapaEach") String mapa,@RequestParam(value = "inicio", defaultValue = "Entrada") String vertIniName,@RequestParam(value = "fim", defaultValue = "Carrefour") String vertFimName) throws InterruptedException {
			String txt=Dijkm(vertIniName,vertFimName);
			
			System.out.println(txt+"\n\n");
		//	Rota[] rtm=getrota(mapa);
			txt=txt.replace("[", "")	;
			txt=txt.replace("]", "")	;
			String[] pontos= txt.split(",");
			//Tamanho ?? -1 pois na ultima posi????o j?? chegou no destino e n??o precisa andar mais
			Rota[] rts=new Rota[pontos.length-1];
			
			for(int in=0;in<pontos.length-1;in++) {
				Rota r=new Rota();
				

				String nome1=pontos[in].trim();
				String nome2=pontos[in+1].trim();
				r.setNumeroSequencia(in);
				
				String[][] rq2=this.genericSearch1("<http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#RST"+nome1+""+nome2+">", "<http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#weight>", "?A");
				if(rq2==null) {
					rq2=this.genericSearch1("<http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#RST"+nome2+""+nome1+">", "<http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#weight>", "?A");
				}
				rq2[0][0]=CleanXSD(rq2[0][0]);
				r.setMetros(Float.parseFloat(rq2[0][0]));
				r.setPassos((int)Float.parseFloat(rq2[0][0])/10);
				String[][] rq3=this.genericSearch1("<http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#RST"+nome1+""+nome2+">", "<http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#direct>", "?A");
				if(rq3==null) {
					rq3=this.genericSearch1("<http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#RST"+nome2+""+nome1+">", "<http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#direct>", "?A");
				}
				rq3[0][0]=CleanXSD(rq3[0][0]);
				r.setDirecao(CleanXSD(rq3[0][0]));
				rts[in]=r;
				}
				
			
			return rts;
		}
		@GetMapping("/dij")
		public String Dijk(@RequestParam(value = "inicio", defaultValue = "Entrada") String vertIniName,@RequestParam(value = "fim", defaultValue = "Saida") String vertFimName) throws InterruptedException {

		        String txt=Dijkm(vertIniName,vertFimName);
		        StringBuilder sb=new StringBuilder();
		        sb.append("<!DOCTYPE html>\r\n"
						+ "<html>\r\n"
						+ "<body>\r\n");
		        sb.append(txt);        
		        sb.append("</body>\r\n"
					+ "</html>\r\n"
					+ "\r\n"
					+ "");
						
			return sb.toString();
		}
		
		public String Dijkm(String vertIniName,String vertFimName) throws InterruptedException {

			//Source: https://gist.github.com/artlovan/a07f29e16ab725f8077157de7abdf125
			String[][] strPontosDeInteresse=this.getPontosDeInteresse();
			
			Hashtable<String, Vertex> g=new Hashtable<String, Vertex>();
			String[] nomes=new String[strPontosDeInteresse.length];
			//Cria todos vertices
			for(int i=0;i<strPontosDeInteresse.length;i++) {
				if(strPontosDeInteresse[i][0]==null) {
					break;
				}
				Vertex v= new Vertex(strPontosDeInteresse[i][0]);
				nomes[i]=strPontosDeInteresse[i][0];
				System.out.println("\n\n"+strPontosDeInteresse[i][0]);
				//Adiciona V no indice que tem o nome do ponto de interesse[i]
				g.put(strPontosDeInteresse[i][0],v);
			}
			//Adiciona as conex??es entre vertices
			 for(int i=0;i<nomes.length;i++) {
			//	 System.out.print("\n"+resp[i][0]+ "   = "+resp[i][1]);
				// Vertex V=g.get(nomes[i]);
				 if(nomes[i]==null) {
					 break;
				 }
				 String[][] conectaCom=this.getConexoesPontosDeInteresse(g.get(nomes[i]).getName());
				 if(conectaCom==null) {
					 //Caso nao tenha, entao continua pro proximo
					 continue;
				 }
				 else {
					 for(int j=0;j<conectaCom.length;j++) {
						 Vertex m=g.get(conectaCom[j][0]);
						g.get(nomes[i]).addNeighbour(new Edge(Float.parseFloat(conectaCom[j][1]),g.get(nomes[i]),g.get(conectaCom[j][0])));
					 }
				 }
				 
			 }
			 Dijkstra dijkstra = new Dijkstra();
			 dijkstra.computePath(g.get(vertIniName));
		
		        String txt=dijkstra.getShortestPathTo(g.get(vertFimName)).toString();
		        StringBuilder sb=new StringBuilder();

		        sb.append(txt);        
		       
						
			return sb.toString();
		}
		
	
		
		
		@GetMapping("/criaPontosDeInteresse")
		public String criaInteresse(@RequestParam(value = "lugar", defaultValue = "mapaEach") String nomemapa,@RequestParam(value = "nomeInteresse", defaultValue = "CH0") String nomeInteresse,  @RequestParam(value = "posXini", defaultValue = "0") String posXini,  @RequestParam(value = "posYini", defaultValue = "0") String posYini) {
			StringBuilder sb=new StringBuilder();
			//int[][][] intmap=getMapa(nomemapa);
			
			
			sb.append("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "<body>\r\n"
					+ "\r\n"
					+ "<form>"   //Fazendo um form que leva a propria pagina
					+ "<input name='nomeInteresse' id='nomeInteresse' value='"+nomeInteresse+"'><br>"
					+ "<input name='posXini' id='posXini' value='"+posXini+"'><br>"
					+ "<input name='posYini' id='posYini' value='"+posYini+"'><br>"					
					+ "<button>Cria ponto de interesse</button><br>"
					+ "</form>"
					+ "</body>\r\n"
					+ "</html>\r\n"
					+ "\r\n"
					+ "");
			if(!nomeInteresse.equals("CH0")){
			//Adiciona GML Point(GML pos foi substituido pra posX e posY na adapta????o de XSD para TTL
			String string="PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n prefix owl: <http://www.w3.org/2002/07/owl#> \n"
					+ "INSERT DATA{	\r\n"
	                + "<http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#"+nomeInteresse+"> <http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#gml:posX> "+posXini+"; \r\n"
	                +"<http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#gml:posY> "+posYini+"."	
	                + "};";
			this.InsertGenerico(string);
			//RDN:RoutenodeType
			String string2="PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n prefix owl: <http://www.w3.org/2002/07/owl#> \n"
					+ "INSERT DATA{	\r\n"
	                + "<http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#rdn"+nomeInteresse+"> <http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#gml:point> <http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#"+nomeInteresse+">. \r\n"
	                + "};";
			this.InsertGenerico(string2);
			}
			
			
			return sb.toString();
		}
		/*@GetMapping("/ConectaPontoDeInteresse")
		public String ConectaPontoDeInteresse(@RequestParam(value = "lugar", defaultValue = "mapaEach") String nomemapa,@RequestParam(value = "nomeInteresse", defaultValue = "CH0") String nomeInteresse,@RequestParam(value = "nomeConecta", defaultValue = "CH1") String nomeConecta,@RequestParam(value = "direcao", defaultValue = "norte") String direcao,@RequestParam(value = "distancia", defaultValue = "0") String distancia ) {
			StringBuilder sb=new StringBuilder();
			//int[][][] intmap=getMapa(nomemapa);
			
			
			sb.append("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "<body>\r\n"
					+ "\r\n"
					+ "<form>"   //Fazendo um form que leva a propria pagina
					+ "<input name='nomeInteresse' id='nomeInteresse' value='"+nomeInteresse+"'> Conecta com: "
					+ "<input name='nomeConecta' id='nomeConecta' value='"+nomeConecta+"'><br>"
					+ "direcao: <input name='direcao' id='direcao' value='"+direcao+"'><br>"					
					+ "distancia: <input name='distancia' id='distancia' value='"+distancia+"'><br>"	
					+ "<button>Cria conexao</button><br>"
					+ "</form>"
					+ "</body>\r\n"
					+ "</html>\r\n"
					+ "\r\n"
					+ "");
			if(!nomeInteresse.equals("CH0")){
			//RST:RouteSegmentType Conecta
			String string="PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n prefix owl: <http://www.w3.org/2002/07/owl#> \n"
					+ "INSERT DATA{	\r\n"
	                + "<http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#RST"+nomeInteresse+""+nomeConecta+"> <http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#indoorgml:RouteNodeType> <http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#rdn"+nomeInteresse+">; \r\n"
	                + "<http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#indoorgml:RouteNodeType> <http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#rdn"+nomeConecta+">;"
	                + "<http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#weight> \""+distancia+"\"^^xsd:double."
	                + "};";
			
			this.InsertGenerico(string);
			}
			
			return sb.toString();
		}
		*/
		public void InsertGenerico(String string) {			
			 RDFConnectionRemoteBuilder builder = RDFConnectionFuseki.create().destination(urlbase+"/update");
			 System.out.println(string);
			 
			 try(RDFConnectionFuseki conn = (RDFConnectionFuseki) builder.build()) {

		        	/*String do insert
		        	String string="PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n prefix owl: <http://www.w3.org/2002/07/owl#> \n"
		        					+ "INSERT DATA{	\r\n"
		        	                + "<http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#Usuario"+rA+"> <http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#idp:key> "+rA+" \r\n"
		        	                + "};";
*/
		            try{
		                UpdateRequest updateDevice = UpdateFactory.create(string);
		                conn.update(updateDevice);
		                System.out.println("Insert feito com sucesso \n"+string);
		            }catch(Exception e){
		                System.out.println("Erro no insert");
		            }

		        }catch (Exception e) {
		            System.out.println("N??o foi poss??vel instanciar conex??o");
		            e.printStackTrace();
		            throw new RuntimeException();
		        }
		
	}
		private String[][] getPontosDeInteresse() throws InterruptedException {
			String[][] resp=new String[2550][3]; //String array[index] 0-: Nome 1:-Pos X, 2:Pos Y
			String uriCoord;
			String[] uriCoords=new String[2550];
			String string="SELECT ?A ?C {?A <http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#gml:point> ?C}";
			String nomeCoord;
			String[] nomesCoords=new String[2550];
			Query query=QueryFactory.create(string);
			int vi=0;
			int maxVi=0;
			
			RDFConnectionRemoteBuilder builder = RDFConnectionFuseki.create().destination("http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning/sparql");
			
		
			try(RDFConnectionFuseki conn = (RDFConnectionFuseki) builder.build()) {
					QueryExecution qe = conn.query(query);
					
					ResultSet rsService = qe.execSelect();
					
			        do {
				            QuerySolution qs = rsService.next();
				            RDFNode uriRDN = qs.get("A");
				            RDFNode uriCoordRaw = qs.get("C");
				            StringBuilder sb=new StringBuilder();
				            
				            
				            System.out.println("\n BareURL "+uriCoordRaw+"\n" );
				            //Remove a uri do nome
				            uriCoord=(uriCoordRaw+"").replace("^^http://www.w3.org/2001/XMLSchema#anyURI","");
				            uriCoord=(uriCoord+"").replace("eger","");
				            sb.append("<");
				            sb.append(uriCoord);
				            sb.append(">"); //Adiciona abre e fecha
				            uriCoord=sb.toString();
				            nomeCoord=uriCoord.replace(urlbase+"#", "");
				            nomeCoord=nomeCoord.replace(">","");
				            System.out.println("Nome coord:"+nomeCoord);
				            uriCoords[vi]=uriCoord+"";
				            resp[vi][0]=nomeCoord+"";
				            vi++;
			        }while(rsService.hasNext());
			        conn.close();
			}
				            
			          //Faz uma nova query pra cima dessas uri
			maxVi=vi;
			vi=0;   
			while(vi<maxVi) {
				String[][] resultsQuery=this.genericSearchPoints(uriCoords[vi], "?B", "?C");        
				System.out.println("Fim do problema1");
				//Primeira [PosX]
				            String coordTypeRaw =resultsQuery[0][1];
				            String value = resultsQuery[0][2];
				            //Descibre de que tipo ??
				            String coordType=(coordTypeRaw+"").replace(urlbase+"#","");
				            int cType=-1;
				            if(coordType.equals("gml:posX")) {
				            	cType=1; 
				            }
				            if(coordType.equals("gml:posY")) {
				            	cType=2; 
				            }
				            
				            System.out.println(""+coordType+ " "+cType);
				            //Se nao for nenhum tipo,vai para o proximo e nao faz nada
				            if(cType!=-1) {
				            	//Remove o XSD:Int e XSD:String do valor
					            String keylimpa=(value+"").replace("^^http://www.w3.org/2001/XMLSchema#int","");
					            keylimpa=(keylimpa+"").replace("^^http://www.w3.org/2001/XMLSchema#String","");
				            	keylimpa=(keylimpa+"").replace("eger","");
					            
					            System.out.print(keylimpa); 	
				            	
					            resp[vi][cType]=keylimpa;
				            }		
				            
		//Segunda [PosY]
				             coordTypeRaw =resultsQuery[1][1];
				             value = resultsQuery[1][2];
				            //Descibre de que tipo ??
				            coordType=(coordTypeRaw+"").replace(urlbase+"#","");
				            cType=-1;
				            if(coordType.equals("gml:posX")) {
				            	cType=1; 
				            }
				            if(coordType.equals("gml:posY")) {
				            	cType=2; 
				            }
				            
				            System.out.println(""+coordType+ " "+cType);
				            //Se nao for nenhum tipo,vai para o proximo e nao faz nada
				            if(cType!=-1) {
				            	//Remove o XSD:Int e XSD:String do valor
					            String keylimpa=(value+"").replace("^^http://www.w3.org/2001/XMLSchema#int","");
					            keylimpa=(keylimpa+"").replace("^^http://www.w3.org/2001/XMLSchema#String","");
				            	keylimpa=(keylimpa+"").replace("eger","");
					            
					            System.out.print(keylimpa); 	
				            	
					            resp[vi][cType]=keylimpa;
					            vi++;
				            }			            				        

				            
					}				
				System.out.println("Prox Exec");
				return resp;
			}
		
		private String UriToURL(String uri) {
			StringBuilder sb=new StringBuilder();
            
            
            System.out.println("\n BareURL "+uri+"\n" );
            //Remove a uri do nome
            uri=(uri+"").replace("^^http://www.w3.org/2001/XMLSchema#anyURI","");
            uri=(uri+"").replace("eger","");
            sb.append("<");
            sb.append(uri);
            sb.append(">"); //Adiciona abre e fecha
            System.out.println("\n Transformed URL "+sb.toString()+"\n" );
			return sb.toString();
		}
		
		
		private String CleanRST(String uri, String nomeConecta) {
			StringBuilder sb=new StringBuilder();
			//"<http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#RST"+nomeInteresse+""+nomeConecta+">
            
            //System.out.println("\n BareURL "+uri+"\n" );
            //Remove a uri do nome
            uri=(uri+"").replace("<"+urlbase+"#RST","");
            uri=(uri+"").replace("<"+urlbase,"");
            uri=(uri+"").replace(">","");
            uri=(uri+"").replace(nomeConecta,"");
           // System.out.println("\n Transformed URL "+sb.toString()+"\n" );
			return sb.toString();
		}
		private String CleanXSD(String value) {
			//"<http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#RST"+nomeInteresse+""+nomeConecta+">
            
			String keylimpa=(value+"").replace("^^http://www.w3.org/2001/XMLSchema#int","");
            keylimpa=(keylimpa+"").replace("^^http://www.w3.org/2001/XMLSchema#String","");
            keylimpa=(keylimpa+"").replace("^^http://www.w3.org/2001/XMLSchema#double","");
        	keylimpa=(keylimpa+"").replace("eger","");
        	keylimpa=(keylimpa+"").replace("e0","");
            return keylimpa;
		}
		
		private String[][] getConexoesPontosDeInteresse(String nomeInteresse) throws InterruptedException {
			
			String[][] resultsQuery=this.genericSearch2("?A", "?B", "<"+urlbase+"#rdn"+nomeInteresse+">");
			if(resultsQuery==null) {
				return null;
			}
			String[][] resp=new String[resultsQuery.length][3]; //String array[index] 0-: NomeConectado 1-:Distancia
			//Pega todas strings RDN e busca novamente pelo peso
			for(int in=0;in<resultsQuery.length;in++) {
			resp[in][0]=CleanRST(resultsQuery[in][0],nomeInteresse);
			resp[in][0]=resp[in][0].replace(urlbase+"#RST", "");
			//System.out.println(resultsQuery[in][0]);
			String[][] rq2=this.genericSearch1(UriToURL(resultsQuery[in][0]), "<"+urlbase+"#weight>", "?A");
			resp[in][1]=CleanXSD(rq2[0][0]);
			String[][] rq3=this.genericSearch1(UriToURL(resultsQuery[in][0]), "<"+urlbase+"#direct>", "?A");
			resp[in][2]=CleanXSD(rq3[0][0]);
			}
			/*for(int i=0;i<resp.length;i++) {
				System.out.print("\n"+resp[i][0]+ "   = "+resp[i][1]);
			}*/
			return resp;
		}
		
		
		
		/*PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
prefix owl: <http://www.w3.org/2002/07/owl#>



INSERT DATA{	
<http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#Usuario2> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#idp::person>
};

INSERT DATA{ 
<http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#Usuario2> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> owl:NamedIndividual
};


INSERT DATA{	
<http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#Usuario2> <http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#idp:key> "29"^^
};		 
		 */
		
		
		public Rota[] getrota(String nomeMapa) {
			Rota[] route=new Rota[200];
			int[][] pontosDoGrafo=new int[50][2];
			int count=0;
			String string="SELECT ?A ?C {?A <http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#idp:coordenadaX> ?C}";
			Query query=QueryFactory.create(string);
			
			RDFConnectionRemoteBuilder builder = RDFConnectionFuseki.create().destination("http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning/sparql");
		
			try(RDFConnectionFuseki conn = (RDFConnectionFuseki) builder.build()) {
			QueryExecution qe = conn.query(query);
			
			ResultSet rsService = qe.execSelect();
			
	        do {
	            QuerySolution qs = rsService.next();
	           
	            RDFNode key = qs.get("C");
	            //Remove a uri do nome
	           
	            
	            //Remove o XSD:INT do valor
	            String keylimpa=(key+"").replace("^^http://www.w3.org/2001/XMLSchema#int","");
	            
	            keylimpa=(keylimpa+"").replace("eger","");
	            pontosDoGrafo[count][0]=Integer.parseInt(keylimpa);
	            count++;
	            
	        } while (rsService.hasNext());
	        
			}
			
			return route;
		}
		
		@GetMapping("/testMap")
		public void tm() {
			getMapa("");
		}
		
		public int[][][] getMapa(String nomeMapa) {
			int[][][] resp=null;
			 String[][] pa=this.genericSearch1("?B", "<"+urlbase+"#uri>", "?A");
			if(pa==null) {
				return resp;
			}
			resp=new int[3][pa.length][4];	
			for(int i=0;i<pa.length;i++) {
				String nome=UriToURL(pa[i][0]);
				String[][] pa2=this.genericSearch2(nome, "?A", "?B");
				for(int j=0;j<pa2.length;j++) {
					System.out.println("\nc:      "+CleanXSD(pa2[j][1]));
					if(pa2[j][0].equals("http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/shoppingmalldirectory2#gml:PosX")) {
						resp[0][i][0]=Integer.parseInt(CleanXSD(pa2[j][1]));
					}
					else if(pa2[j][0].equals("http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/shoppingmalldirectory2#gml:PosY")) {
						resp[0][i][1]=Integer.parseInt(CleanXSD(pa2[j][1]));
					}
				}
			}
			
			return resp;
		}
		public String[][] genericSearch1(String one,String two, String three) {
			
			//Volta apenas o A em [0]
			int count=0;
			String string="SELECT ?A {"+one+" "+two+" "+ three+";}";
			//String string="SELECT ?A ?C {?A <http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#idp:key> ?C}";
			Query query=QueryFactory.create(string);
			//System.out.print("\n\nGeneric Search tp is:"+string);
			
			 
			RDFConnectionRemoteBuilder builder = RDFConnectionFuseki.create().destination(urlbase+"/sparql");
			
			ArrayList<String[]> resp = new ArrayList<String[]>();
			try(RDFConnectionFuseki conn = (RDFConnectionFuseki) builder.build()) {
			QueryExecution qe = conn.query(query);
			qe.setTimeout(400);
			ResultSet rsService = qe.execSelect();
			if(!rsService.hasNext()) {
				return null;
				
			}
	        do {
	            QuerySolution qs = rsService.next();     
	            RDFNode respA = qs.get("A");
	            String[] V= new String[3];
	            V[0]=respA+"";
	            count++;
				resp.add(V);
	            
	            
	        } while (rsService.hasNext());
	        conn.close();
			}
			String[][] moldResp=new String[count][3];
			//Coloca os resultados em um vetor
			for(int i=0;i<moldResp.length;i++) {
			moldResp[i]=resp.get(i);
			}
			
			return moldResp;
			
		}
		public String[][] genericSearch2(String one,String two, String three) {
			int count=0;
			//Volta apenas o A e B em [0] e [1]
			String string="SELECT ?A ?B {"+one+" "+two+" "+ three+";}";
			//String string="SELECT ?A ?C {?A <http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#idp:key> ?C}";
			Query query=QueryFactory.create(string);
			System.out.print("\n\nGeneric Search tp is:"+string);
			
			 
			RDFConnectionRemoteBuilder builder = RDFConnectionFuseki.create().destination(urlbase+"/sparql");
			
			ArrayList<String[]> resp = new ArrayList<String[]>();
			
			try(RDFConnectionFuseki conn = (RDFConnectionFuseki) builder.build()) {
			QueryExecution qe = conn.query(query);
			qe.setTimeout(400);
			ResultSet rsService = qe.execSelect();
			if(!rsService.hasNext()) {
				return null;
				
			}
	        do {
	            QuerySolution qs = rsService.next();     
	            RDFNode respA = qs.get("A");
	            RDFNode respB = qs.get("B");
	            String[] V= new String[3];
	            V[0]=respA+"";
	            V[1]=respB+"";
	            count++;
				resp.add(V);
	            
	            
	        } while (rsService.hasNext());
	        conn.close();
			}
			String[][] moldResp=new String[count][3];
			//Coloca os resultados em um vetor
			for(int i=0;i<moldResp.length;i++) {
			moldResp[i]=resp.get(i);
			}
			
			return moldResp;
			
		}
		public String[][] genericSearchPoints(String one,String two, String three) {
			int count=0;
			String string="SELECT ?B ?C {"+one+" "+two+" "+ three+";}";
			//String string="SELECT ?A ?C {?A <http://ip-50-62-81-50.ip.secureserver.net:8080/fuseki/indoorplaning#idp:key> ?C}";
			Query query=QueryFactory.create(string);
			System.out.print("\n\nGeneric Search tp is:"+string);
			
			 
			RDFConnectionRemoteBuilder builder = RDFConnectionFuseki.create().destination(urlbase+"/sparql");
			
			ArrayList<String[]> resp = new ArrayList<String[]>();
			String[][] moldResp=new String[2][3];
			try(RDFConnectionFuseki conn = (RDFConnectionFuseki) builder.build()) {
			QueryExecution qe = conn.query(query);
			qe.setTimeout(400);
			ResultSet rsService = qe.execSelect();
			if(!rsService.hasNext()) {
				return null;
				
			}
	        do {
	            QuerySolution qs = rsService.next();     
	            RDFNode respB = qs.get("B");
	            RDFNode respC = qs.get("C");
	            String[] V= new String[3];
	            V[1]=respB+"";
	            V[2]=respC+"";
	            count++;
				resp.add(V);
	            
	            
	        } while (rsService.hasNext());
	        conn.close();
			}
			moldResp[0]=resp.get(0);
			moldResp[1]=resp.get(1);
			
			return moldResp;
		}
		
		
		
}
		

