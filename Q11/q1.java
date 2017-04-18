import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.time.*;
import java.time.temporal.*;

public class q1 {

	/** *line-wise reading of text file
	* @param fileName the file to be read
	* @return the contents of file line-wise*/
	public List<String> readTextFileByLines(String fileName) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(fileName));
		return lines;
	}

	//write to txt file
	/*public void writeToTextFile(String fileName, String content) throws IOException {
		Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
	}*/

	/**
	* @return the minimum 
	* @param a first number
	* @param b second number
	*/
	public int min(int a, int b) {
		return (a > b)?b:a;
	}
	
	/**
	* driver-function
	* @param b the array of object Boys
	* @param g the array og object Girls
	*/
	public void couplify(Boys []b, Girls []g) throws IOException{
		int j=0;
//		System.out.println("Couplify");
		DataOutputStream dos = new DataOutputStream(new FileOutputStream("log.txt")); //created in the current directory, no path specs reqd
		for (int i=0; i<g.length; i++) {
			if (g[i].maintainencebudget < b[j].maintainencebudget && g[i].committed == false && b[j].committed == false) {
				g[i].boyfriend = b[j].name;
				g[i].committed = true;
				b[j].girlfriend = g[i].name;
				b[j].committed = true;				
				dos.writeBytes(g[i].name+" got committed to "+b[j].name+" on "+LocalDate.now()+" "+LocalTime.now()+"\n");
				j++;
			} else if (g[i].maintainencebudget > b[j].maintainencebudget && g[i].committed == false && b[j].committed == false) {
				j++;
				i--;
			}

		}
	}

	/**
	* Second type of couplify function
	* @param b the array of object Boys
	* @param g the array og object Girls
	*/
	public void couplifyoptimized(Boys []b, Girls []g) throws IOException{
		/**
		* loop variable*/
		int j;
//		System.out.println("Couplify Optimized");
		DataOutputStream dos = new DataOutputStream(new FileOutputStream("log.txt"));
		/**
		* 0 for girl's chance, 1 for boy's chance*/
		int flag;
		j=0; flag = 0; //0 for girl's chance, 1 for boy's chance
		for (int i=0; i<min(b.length, g.length); i++) {
			if (flag == 0) {
				if (g[i].maintainencebudget < b[j].maintainencebudget && g[i].committed == false && b[j].committed == false) {
					g[i].boyfriend = b[j].name;
					g[i].committed = true;
					b[j].girlfriend = g[i].name;
					b[j].committed = true;				
					dos.writeBytes(g[i].name+" got committed to "+b[j].name+" on "+LocalDate.now()+" "+LocalTime.now()+"\n");
					j++;
					flag = 1;
				} else if (g[i].maintainencebudget > b[j].maintainencebudget && g[i].committed == false && b[j].committed == false) {
					j++;
					i--;
					flag = 1;
				}
			} else if (flag == 1) { //change it for boy
				if (g[i].maintainencebudget < b[j].maintainencebudget && g[i].committed == false && b[j].committed == false) {
					g[i].boyfriend = b[j].name;
					g[i].committed = true;
					b[j].girlfriend = g[i].name;
					b[j].committed = true;				
					dos.writeBytes(g[i].name+" got committed to "+b[j].name+" on "+LocalDate.now()+" "+LocalTime.now()+"\n");
					j++;
					flag = 1;
				} else if (g[i].maintainencebudget > b[j].maintainencebudget && g[i].committed == false && b[j].committed == false) {
					j++;
					i--;
					flag = 1;
				}
			}
		}
	}

	//start of main
	/*public static void main(String args[]) throws IOException {
	  int nb = 7, ng = 5, k = 0, z = 0; //nb = no.of boys, ng = no.of girls
	  Boys b[] = new Boys[nb];
	  Girls g[] = new Girls[ng];		
	  q1 FilesUtil = new q1();		
	  List<String> alb = FilesUtil.readTextFileByLines("Boys_data.txt"); //read Boys_data
	  Object arrb[] = alb.toArray(); //convert list to array
	  for (int i=0; i<arrb.length; i++) {
	  String str = arrb[i].toString(); //convert Object to String
	  if (str.length() == 0) {	//ignore new-line			
	  continue;
	  }

	  String strarr[] = str.split(",");
	  b[k++] = new Boys(strarr); //constructor call
	  }

	/*for (int i=0; i<nb; i++) {
	b[i].display();
	}*/		

	/*List<String> alg = FilesUtil.readTextFileByLines("Girls_data.txt"); //refer to above comments
	  Object arrg[] = alg.toArray();
	  for (int i=0; i<arrg.length; i++) {
	  String str = arrg[i].toString();
	  if (str.length() == 0) {
	  continue;
	  }

	  String strarr[] = str.split(",");
	  g[z++] = new Girls(strarr);
	  }
	  FilesUtil.couplify(b, g); //need this for real

	  }*/
}

