import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
public class EcoServerConcurrente{
	public static void main(String[] args){
		int port = 9999;
		try{
			ServerSocket ssocket = new ServerSocket(port);
			System.err.println("Started server on port " + port);
			
			while(true){
				Socket socketS = ssocket.accept();
				Thread t = new Thread(new EcoServerConcurrente().new ProcesamientoEco(socketS));
				t.start();							
			}
		} catch (IOException e){
			System.err.println("Error I/o");
			e.printStackTrace();
		}
	}
	private class ProcesamientoEco implements Runnable{
		Socket socketS;
		public ProcesamientoEco(Socket socket){
			this.socketS = socket;
		}
		public void run(){
				try{
					System.err.println("Accepted connection from client");
					BufferedReader brSocketIn = new BufferedReader(new InputStreamReader(socketS.getInputStream()));
					PrintWriter pwSocketOut = new PrintWriter(new OutputStreamWriter(socketS.getOutputStream()));
					
					System.out.println("Hasta aqui todo fine");
					while(true){
						String line = brSocketIn.readLine();
						if(line == null){
							break;
						}
						else{
							System.out.println("Eco Server: "+ line);
							pwSocketOut.flush();
							pwSocketOut.println(line);
							pwSocketOut.flush();
						}
					}
					//socketS.close();
					//pwSocketOut.close();
					brSocketIn.close();
				} catch (IOException e){
					System.err.println("Error I/o");
					e.printStackTrace();
				}
		}
	}
}


