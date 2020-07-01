import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class EcoClienteConcurrente {
	public static void main(String[] args){
		String host = "127.0.0.1";
		int port = 9999;
		try{
			Socket connection = new Socket(host, port);
			BufferedReader brStdIn = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader brSocketIn = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			PrintWriter pwSocketOutput = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
			String line;
			while(!((line = brStdIn.readLine()).equals("x"))){
				//line = brStdIn.readLine();
				//System.out.println("Interior: " + line);
				pwSocketOutput.flush();
				pwSocketOutput.println(line);
				pwSocketOutput.flush();
				String response = brSocketIn.readLine();
				System.out.println("Eco: " + response);
				

			}
			brSocketIn.close();
			pwSocketOutput.close();
			connection.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}
