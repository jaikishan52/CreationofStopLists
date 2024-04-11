import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Maven_assignment {

	static <Key,Val extends Comparable<? super Val>> 
    List<Entry<Key, Val>> entriesSortedByValues(Map<Key,Val> map,String outDp) {

List<Entry<Key,Val>> sKeysValues = new ArrayList<Entry<Key,Val>>(map.entrySet());

Collections.sort(sKeysValues, 
    new Comparator<Entry<Key,Val>>() {
        @Override
        public int compare(Entry<Key,Val> e1, Entry<Key,Val> e2) {
            return e2.getValue().compareTo(e1.getValue());
        }
    }
);





try {
	String tokenbyvalueall= outDp+"Totaltokenbyfreq.txt";

	 //File ftokenbyval= new File(tokenbyvalueall);
	FileWriter tokbyvData = new FileWriter(tokenbyvalueall,true);
	BufferedWriter tokbyfreqDataB = new BufferedWriter(tokbyvData);
	
	String Fiftytokenbyval= outDp+"Fiftytokenbyfreq.txt";
	FileWriter fiftyTokbyD = new FileWriter(Fiftytokenbyval,true);
	BufferedWriter FiftytokbyfreqDB = new BufferedWriter(fiftyTokbyD);
	int ct=1;
	for (Map.Entry<Key, Val> entry : sKeysValues) {
       
        String eString = entry.getKey() + ": " + entry.getValue();
        tokbyfreqDataB.write(eString);
        tokbyfreqDataB.write("\n");
        if(ct<51)
		{   
        	FiftytokbyfreqDB.write(eString);
        	FiftytokbyfreqDB.write("\n");
			ct++;
			
		}
		
		
		else if(ct>map.size()-49 && ct<map.size())
		{
			FiftytokbyfreqDB.write(eString);
			FiftytokbyfreqDB.write("\n");
			ct++;
		}
		else {
			ct++;
		}
        
        
    }
	 tokbyfreqDataB.flush();
	 tokbyfreqDataB.close();
	 FiftytokbyfreqDB.flush();
	 FiftytokbyfreqDB.close();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

return sKeysValues;
}

	
	

	
	
	public static void main(String[] args) throws IOException {
		long startT = System.currentTimeMillis();
		//		String input = "'jai";
//
//		String[] msg = input.split("[.@,|!?:,;'()]");
//
//		//
//		if (msg.length > 0) {
//		    System.out.println(msg[1]);
//		} else {
//		    System.out.println("No elements found after splitting.");
//		}

		//java maven_assignment input_dir output_dir
		
		
		//now task is to create a frequency file for all the tokens
		//-->sorted by tokens alphabetic order
		//-->sorted by token s
		if(args.length!=2)
		{
			System.out.println(args[0]);
			System.out.println(args[1]);
			System.out.println("Here we are trying to enter the inputdirectory path and output directory path to tokenize the following input files");
			System.exit(1);
			
		}
		
		String inDp= args[0];
		String outDp = args[1];
		
		//Here we are trying to validate if the input directory exists
		
		
		File inD = new File(inDp);
		if(!inD.isDirectory() || !inD.exists()) {
			System.out.println("The input directory path is invalid");
			System.exit(1);
			
		}
		
		
		File outD = new File(outDp);
		if(!outD.exists()) {
			outD.mkdirs();
			
		}
		String fname="";
		int val=0;
		int c=0;
		TreeMap<String,Integer> mytokenMap=new TreeMap<String,Integer>(); 
		
		 

		
		
	//Here in we try to pass the input and output directory paths below. 	
//	File[] files = new File("/Users/jaikishantimmapatruni/Downloads/IRSassignment/input files/").listFiles();
//	String tokenbyfreqall= "/Users/jaikishantimmapatruni/Downloads/IRSassignment/output files/"+"totalsortbytoken.txt";

		File[] files = new File(inDp).listFiles();
		String tokenbyfreqall=outDp+"totalsortbytoken.txt";
		
	FileWriter tokbyfreqData = new FileWriter(tokenbyfreqall,true);
	BufferedWriter tokbyfreqDataB = new BufferedWriter(tokbyfreqData);
	
	String tfqhalf= outDp+"fiftysortbytoken.txt";
	FileWriter fiftytokfreqd= new FileWriter(tfqhalf,true);
	BufferedWriter fiftytokbyfreqDB = new BufferedWriter(fiftytokfreqd);


	
		for(File file:files)
		{

		fname= file.getName();
		//System.out.println(fname);
		
		
		try {
		String content = Jsoup.parse(new File(inDp+fname), "UTF-8").toString();
		
		Document doc =  Jsoup.parse(content);
		//if body tag not present we need to check if body tag is present or not....
		String text;
		
		
		//Here we keep checking if the content is present in body and head if not we take the head content
		if(doc != null)
		{	
			if(doc.head()!=null && doc.body()!=null)
			{
		     text = doc.body().text() + doc.head().text();
			}else{
			 text = doc.head().text();
			}
		String msg;
		String outname = file.getName()+c;		
		String outfilename = outDp+fname+c+"output"+".html"; 
		//File file =new File(outfilename);
		
		FileWriter tokData = new FileWriter(outfilename,true);
		BufferedWriter tokDataB = new BufferedWriter(tokData);
		
		
		//We try to use the stringtokenizer here as it makes it easy for us to go through each token
		StringTokenizer st1 = new StringTokenizer(text);

		      for (int i = 1; st1.hasMoreTokens(); i++)
		      {
		    	
		         msg = st1.nextToken();
		         if(msg.length()!=0) 
		    	 { 
		      String msgLower = msg.toLowerCase();
		      //System.out.println(msgLower);
		      //String m= msgLower.replaceAll("[\\[\\].@,<>|!?:,;'[()]['']]","");
		      //regular expression which is used to remove all the unecessary symbols and content.
		      String m = msgLower.replaceAll("[^a-zA-Z]","");
		      //msglower= :
		    	  
		      if(m.length()!=0)
		      {
		      
		     // String[] m = msgLower.split("[]+");
		      
		     
		    	  //System.out.println(msgLower+" "+":"+" "+ m[1]);
			      
			      tokDataB.write(m);
			      tokDataB.write("\n");
//			      
			      if(mytokenMap.containsKey(m)==false ){
			    	  val=1;
			    	  mytokenMap.put(m, val);
			    	  //here we try to insert the value of the token if its a new one
			      }
			      else {
			    	  val=mytokenMap.get(m);
			    	  val+=1;
			    	  //otherwise we try to pull the value of the token and increment it.
			    	 mytokenMap.replace(m,mytokenMap.get(m), val);
			    	 
			    	  
			      
		      }
//		     
		      
		    
		        
		      
		      }
		        
		      }   
		      
		     
	                      
		}// close output writer
		      
		      tokDataB.close(); 
		     
			    
	
	}
		}catch (Exception e){
	    System.out.println("Error Exception: "+e.getMessage()+e.getCause());
} 

	
	
	
	}

       int ct=1;
        
		for (Map.Entry<String, Integer> entry : mytokenMap.entrySet()) {
			if(entry.getKey()!=null && entry.getValue()!=null)
			{
				String eString = entry.getKey() + ": " + entry.getValue();
			tokbyfreqDataB.write(eString);
			tokbyfreqDataB.write("\n");// Move to the next line for the next entry
			if(ct<51)
			{   
				fiftytokbyfreqDB.write(eString);
				fiftytokbyfreqDB.write("\n");
				ct++;
			}
			
			
			else if(ct>mytokenMap.size()-49 && ct<mytokenMap.size())
			{
				fiftytokbyfreqDB.write(eString);
				fiftytokbyfreqDB.write("\n");
				ct++;
			}
			else {
				ct++;
			}
			}
        }
		tokbyfreqDataB.flush();
		tokbyfreqDataB.close();
		fiftytokbyfreqDB.flush();
        fiftytokbyfreqDB.close(); 
		

		
		
		
		 entriesSortedByValues(mytokenMap,outDp);
	
		 
		 
		 long eT = System.currentTimeMillis();
		 
		 long timeelapsed = eT-startT;
		 
		 System.out.println("Time taken:" + timeelapsed + " milliseconds");
		
		
}

}
	


