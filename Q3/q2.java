import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.time.*;
import java.time.temporal.*;

public class q2 extends q1{
	//line-wise reading of text file
	/**
	 * line-wise reading of text file
	 * @param fileName the file to be read
	 * @return the list containing the contents of file line-by-line
	 */
	public List<String> readTextFileByLines(String fileName) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(fileName));
		return lines;
	}

	//write to txt file
	/*public void writeToTextFile(String fileName, String content) throws IOException {
		Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
	}*/

	//pseudo-driver-function
	/*public void couplify(Boys []b, Girls []g) throws IOException{
		int j=0;
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
	}*/

	//search for a girlfriend continues
	/**
	* search for a girlfriend continues
	* @param girlfriend name of the girlfriend
	* @param g the array of object Girls
	* @return the index of the corresponding gf
	*/
	int search(String girlfriend, Girls []g) {
		for (int i=0; i<g.length; i++) {
			if (g[i].name.equals(girlfriend)) return i;
		}
		return 0;
	}

	//driver-function
	/**
	 * driver-function
	 * @param b the array of object Boys
	 * @param g the array of object Girls
	 * @param happinessarray an array of Object Happyfy: stores the happiness of couples
	 */
	public void giftify(Boys []b, Girls []g, Happyfy happinessarray[]) throws IOException {
		int i, j=0;
		DataOutputStream dos = new DataOutputStream(new FileOutputStream("log1.txt"));
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
				int idx = search(b[i].girlfriend, g);
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

				happinessarray[j++] = new Happyfy(b[i].name, g[idx].name, b[i].happiness + g[idx].happiness);
			}
		}
	}

	//start of main
	/**
	* constructor doing all the job
	*/
	q2() throws IOException{
		/**
		 * number of boys*/ 
		int nb;
		/**
		 * number of girls*/
		int ng;
		/**
		 * index variable for boys array*/
		int k;
		/**
		 * index variable for girls array*/ 
		int z;
		/**
		 * number of happiest couples*/
		int num_happ_coup;
		/**
		* choice 0 for first couplify, 1 for new couplify
		*/
		nb = 7; ng = 5; k = 0; z = 0; num_happ_coup = 3; //nb = no.of boys, ng = no.of girls, num_happ_coup = no.of happiest couples to be found
		Boys b[] = new Boys[nb];
		Girls g[] = new Girls[ng];
		Happyfy happinessarray[] = new Happyfy[ng];		
		//q2 FilesUtil = new q2();		
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
		giftify(b, g, happinessarray);
		for (int i=0; i<ng-1; i++) {
			for (int j=0; j<ng-1-i; j++) {
				if (happinessarray[j].happiness < happinessarray[j+1].happiness) {
					String tempbf = happinessarray[j].boyfriend;
					String tempgf = happinessarray[j].girlfriend;
					double temphap = happinessarray[j].happiness;
					happinessarray[j].boyfriend = happinessarray[j+1].boyfriend;
					happinessarray[j].girlfriend = happinessarray[j+1].girlfriend;
					happinessarray[j].happiness = happinessarray[j+1].happiness;
					happinessarray[j+1].boyfriend = tempbf;
					happinessarray[j+1].girlfriend = tempgf;
					happinessarray[j+1].happiness = temphap;
				}
			}
		}
	
                DataOutputStream dos = new DataOutputStream(new FileOutputStream("log2.txt"));
		dos.writeBytes("Happiest "+num_happ_coup+" couples are\n");
		for (int i=0; i<num_happ_coup; i++) {
			dos.writeBytes(happinessarray[i].boyfriend+" and "+happinessarray[i].girlfriend+"\n");
		}
	}
}
