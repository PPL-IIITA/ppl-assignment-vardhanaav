import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.time.*;
import java.time.temporal.*;

public class Couplifier extends q2{

	//line-wise reading of text file
	/**
	* function to read from text file
	* @param fileName name of the file to be read
	*/
	public List<String> readTextFileByLines(String fileName) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(fileName));
		return lines;
	}

	public Boys []b;
	public Girls []g;
	//Happyfy []happinessarray;
	//write to txt file
	public void writeToTextFile(String fileName, String content) throws IOException {
		Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
	}

	//driver-function
	/**
	* function that makes couples and gifts
	* @param b array of object of Boys, contains all the data about the boys
	* @param g array of object of Girls, contains all the data about the girls
	*/
	public void couplify(Boys []b, Girls []g) throws IOException{
		int j=0;
//		Boys tempb = new Boys(0);
//		tempb = randomboy(b);
//		Girls tempg = new Girls(0);
//		tempg = randomgirl(g);
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(new FileOutputStream("log.txt"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //created in the current directory, no path specs reqd
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
	 * driver-function
	 * @param b the array of object Boys
	 * @param g the array of object Girls
	 * @param happinessarray an array of Object Happyfy: stores the happiness of couples
	 */
	public void giftify(Boys []b, Girls []g) throws IOException {
		int i, j=0;
		//Boys tempb = new Boys(0);
		//tempb = randomboy(b);
//		Girls tempg = new Girls(0);
//		tempg = randomgirl(g);
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(new FileOutputStream("log1.txt"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (i=0; i<b.length; i++) {
			if (b[i].committed == true) {
				double sumval = 0, sumcost = 0;
				while (sumcost <= b[i].maintainencebudget) {
					List<String> gift = readTextFileByLines("Gifts.txt");
					Object arrgift[] = gift.toArray();
					for (int k=0; k<arrgift.length; k++) {
						String str = arrgift[k].toString();
						if (str.length() == 0) {        //ignore new-line                       
							continue;
						}
						String strarr[] = str.split(",");
						sumval = sumval + Double.parseDouble(strarr[2]);
						sumcost = sumcost + Double.parseDouble(strarr[3]);
						dos.writeBytes(b[i].name+" gifts "+strarr[4]+" to "+b[i].girlfriend+" on "+LocalDate.now()+" "+LocalTime.now()+"\n");
					}
				}
				/*int idx = search(b[i].girlfriend, g);
				if (b[i].type.equals("miser"))
					b[i].happiness = b[i].maintainencebudget-sumcost;
				else if (b[i].type.equals("generous"))
					b[i].happiness = g[idx].happiness; //search for girlfriend via search function
				else //if(b[i].type.equals("geek")
					b[i].happiness = g[idx].intellilevel;

				if (g[idx].type.equals("normal"))
					g[idx].happiness = b[i].maintainencebudget-sumcost;
				else if (g[idx].type.equals("choosy"))
					g[idx].happiness = Math.log(sumcost);
				else //desperate
					g[idx].happiness = Math.exp(sumcost);

				happinessarray[j++] = new Happyfy(b[i].name, g[idx].name, b[i].happiness + g[idx].happiness);*/
			}
		}
	}


	//start of main
	/**
	* Constructor that does all the work
	*/
	Couplifier() throws IOException {
		int nb = 7, ng = 5, k = 0, z = 0; //nb = no.of boys, ng = no.of girls
		b = new Boys[nb];
		g = new Girls[ng];		
		//Couplifier FilesUtil = new q1();		
		List<String> alb = readTextFileByLines("Boys_data.txt"); //read Boys_data
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
		
		List<String> alg = readTextFileByLines("Girls_data.txt"); //refer to above comments
		Object arrg[] = alg.toArray();
		for (int i=0; i<arrg.length; i++) {
			String str = arrg[i].toString();
			if (str.length() == 0) {
				continue;
			}
			
			String strarr[] = str.split(",");
			g[z++] = new Girls(strarr);
		}
		couplify(b, g); //need this for real
		giftify(b, g);
		//display(b, g);

	}
}

