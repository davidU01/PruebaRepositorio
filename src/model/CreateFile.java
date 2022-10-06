package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class CreateFile {

	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					Properties properties= new Properties();
					try {
						properties.load(new FileInputStream(new File("./taller/configuration.properties")));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

					String contenido = dtf.format(LocalDateTime.now());
					try(FileWriter fw = new FileWriter(properties.getProperty("PATH")+properties.getProperty("NAME"), true);
							BufferedWriter bw = new BufferedWriter(fw);
							PrintWriter out = new PrintWriter(bw)) {
						out.println(contenido);
						Thread.sleep(3000);
					} catch (IOException e) {
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	
	}

}
