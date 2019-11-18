import java.net.*;
//import java.time.LocalDate;
import java.io.*;
import java.util.*;

public class ServerProfezie {

	private ServerSocket server;
	private Socket connessione;
	private Scanner dalClient;
	private PrintStream alClient;
	private List<String> profezie;

	public ServerProfezie() {
		try {
			server = new ServerSocket(1000);
			System.out.println("Server attivo");
			connessione = server.accept();
			dalClient = new Scanner(connessione.getInputStream());
			alClient = new PrintStream(connessione.getOutputStream());
		} catch (IOException e) {
			System.out.println(e);
		}
		profezie = new ArrayList<>();
		try {
			BufferedReader in = new BufferedReader(
					new FileReader("C:\\Users\\5G 19-20\\Desktop\\eclipse-workspace\\Profezie_TPS\\src\\Profezie"));
			String s = new String();
			do {
				s = in.readLine();
				if (s != null) {
					profezie.add(s);
				}
			} while (s != null);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void conversazione() {
		alClient.println("Inserisci la tua data di nascita");
		Scanner tastiera = new Scanner(System.in);
		String messaggio = " ";
		try {
			while (!messaggio.equals("fine")) {
				messaggio = dalClient.nextLine();
				System.out.println(messaggio);
				if (!messaggio.equals("fine")) {
					messaggio = profezie.get((int) (Math.random() * 9));
					alClient.println(messaggio);
				}
			}
			connessione.close();
			tastiera.close();
		} catch (IOException e) {
			System.out.println("Conversazione interrotta");
		}
	}
}