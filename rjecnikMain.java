//pretrazuje se baza EH.txt
//izgled baze je:
//engleski	hrvatski
//engleska i hrvatska rijec su odvojene s tabom (^I)
//moze se vidjeti u vim-u -> :set list

//sto je gotovo:
//1. ucitavanje baze iz datoteke u dvije Array Liste (hrv i engl)
//2. nalazenje indexa (samo prvog) od trazene rijeci

//TODO
//1. naci sve indexe trazene rijeci
//2. povezati index sa indexom u drugoj Array Listi i prikazati sadrzaj
//3. prebaciti sve u posebnu klasu

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.*;

class rjecnikMain {
static	List<String> englishWords = new ArrayList<String>();
static	List<String> croatianWords = new ArrayList<String>();
static	List<String> tmpResult = new ArrayList<String>();

public static void main(String[] args) {
	if(args.length == 0)
	{
		System.out.println("run dictionary: java rjecnikMain <word in english>");
		System.exit(0);
	}
	//////try (BufferedReader br = new BufferedReader(new FileReader("testna_baza.txt"))) {
	//String wordsDataBase = "testna_baza.txt"
	String wordsDataBase = "/home/egorpau/programming/java/rijecnik/EH.Txt";
	try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(wordsDataBase),"iso-8859-2"))) {
	String linija;
	while ((linija = br.readLine()) != null) {
			String[] dijelovi = linija.split("\t");
			englishWords.add(dijelovi[0]);
	//		System.out.println("english velicina:" + englishWords.size() + ":");
			croatianWords.add(dijelovi[1]);
	//		System.out.println("hrv velicina:" + croatianWords.size() + ":");
		}
	br.close(); //close wordsDataBase file
//			System.out.println("english velicina:" + englishWords.size() + ":");

		//System.out.println("sve Engl:::" + Arrays.toString(englishWords));
/*
		int i = 0;
		for (String s:englishWords) {
//			System.out.println(i + " - " + s);
			i++;
		}
*/

//		System.out.println("engleski prijevod hrv rijeci " +args[0] +  " je:" + prevediRijecNaEngleski(nadjiPozicijuHrvRijeci(args[0])));
		tmpResult = indexOfAll(args[0],englishWords);	

	} catch (FileNotFoundException e) {
            	//e.printStackTrace();
		System.out.println("Input DataBase file \"" + wordsDataBase  + "\" Not Found!");
	} catch (IOException e) {
    		e.printStackTrace();
	}
}

	static int nadjiPozicijuEngRijeci(String rijec) {
  		return englishWords.indexOf(rijec);
	}
	static int nadjiPozicijuHrvRijeci(String rijec) {
  		return croatianWords.indexOf(rijec);
	}
	static String prevediRijecNaEngleski(int index) {
  		return englishWords.get(index);
	}
	static String prevediRijecNaHrvatski(int index) {
  		return croatianWords.get(index);
	}

	static List<String> indexOfAll(String obj, List list){
		//CharSequence cs1 = (CharSequence)obj;
		CharSequence cs1 = (CharSequence)(obj.toLowerCase()); //to lower case -> to find all words
    		List<String> indexList = new ArrayList<String>();
    		for (int i = 0; i < list.size(); i++) {
		//		System.out.println("broji:" + i);
			//CharSequence cs1 = (CharSequence)list.get(i);
        		//if(obj.contains(cs1)) {
			String tTt = (String)list.get(i);
        		if( tTt.toLowerCase().contains(cs1) ) { //temporary to lower case -> to find all words
        		//if(obj.equals(list.get(i))) {
            			indexList.add(obj);
				//System.out.println("....." + i);
				//System.out.println("hrv prijevod eng rijeci \"" + obj  + "\" je:  " + prevediRijecNaHrvatski(i));
				System.out.println("hrv prijevod eng rijeci \"" + tTt  + "\" je:  " + prevediRijecNaHrvatski(i));
			}
		}
    		return indexList;
	}

/*
//Nadji sva znacenja jedne rijeci
//http://stackoverflow.com/questions/13900585/trying-to-find-all-occurances-of-an-object-in-arraylist-in-java

static ArrayList<Integer> indexOfAll(Object obj, ArrayList list){
    ArrayList<Integer> indexList = new ArrayList<Integer>();
    for (int i = 0; i < list.size(); i++)
        if(obj.equals(list.get(i)))
            indexList.add(i);
    return indexList;
}
*/

	
}
