import java.net.*;
import java.time.LocalDate;
import java.io.*;
import java.util.*;

public class Client {

	// private ServerSocketserver;
	private Socket connessione;
	private Scanner dalServer;
	private PrintStream alServer;

	public Client() {
		try {
			connessione = new Socket("127.1.1.0", 1000);
			dalServer = new Scanner(connessione.getInputStream());
			alServer = new PrintStream(connessione.getOutputStream());
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void conversazione() {
		String messaggio = " ";
//		LocalDate data;
		Scanner tastiera = new Scanner(System.in);
//		boolean dd, mm, yy;
		try {
			while (!messaggio.equals("fine")) {
				messaggio = dalServer.nextLine();
				System.out.println(messaggio);
				if (!messaggio.equals("fine")) {
					try {
					int giorno, mese, anno;
					System.out.println("Inserisci giorno (se si mette 0 il programma termina): ");
					giorno = tastiera.nextInt();
					if(giorno==0) messaggio = "fine";
					else {
					System.out.println("Inserisci mese: ");//do while per ogni data per restrizioni
					mese = tastiera.nextInt();
					System.out.println("Inserisci anno: ");
					anno = tastiera.nextInt();
//					data = LocalDate.of(anno, mese, giorno);
					messaggio = giorno + "/" + mese + "/" + anno;
					}
					alServer.println(messaggio);
					}catch(Exception e) {
						System.out.println(e);
					}
				}
			}
			connessione.close();
			tastiera.close();
		} catch (IOException e) {
			System.out.println("Conversazione interrotta");
		}
	}
}
