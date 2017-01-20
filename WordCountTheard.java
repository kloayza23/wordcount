import java.io.*;

class Linecount extends Thread{
	String file;
	BufferedReader in = null;
	long[] countline=new long[3];
	public void run(){
		try{
		    FileReader fileReader = new FileReader(this.file);
		    in = new BufferedReader(fileReader);
		    countline=linecountin(this.file,in);
		}
		catch(IOException e){
			countline=new long[3];	
		    e.printStackTrace();
		}
	}	
	public long[] linecountin(String fName, BufferedReader in)throws IOException{
		long numChar = 0;
		long numLine=0;
		long numWords = 0;
		String line;
		long[] countline=new long[3];
		do{
		    line = in.readLine();
		    if (line != null){
				numChar += line.length();
				numWords += wordcount(line);
				numLine++;
		    }
		}while(line != null);		
		System.out.println("File Name: " + fName);
		countline[0]=numChar;
		countline[1]=numWords;
		countline[2]=numLine;
		System.out.println("Number of characters: " + numChar);
		System.out.println("Number of words: " + numWords);
		System.out.println("Number of Lines: " + numLine);
		return countline;	
	}
	private long wordcount(String line){
		long numWords = 0;
		int index = 0;
		boolean prevWhiteSpace = true;
		while(index < line.length()){
		    char c = line.charAt(index++);
		    boolean currWhiteSpace = Character.isWhitespace(c);
		    if(prevWhiteSpace && !currWhiteSpace){
			numWords++;
		    }
		    prevWhiteSpace = currWhiteSpace;
		}
		return numWords;
    }
	public Linecount(String file){
		this.file=file;		
	}

}
public class  WordCountTheard{		
	 private static long wordcount(String line){
		long numWords = 0;
		int index = 0;
		boolean prevWhiteSpace = true;
		while(index < line.length()){
		    char c = line.charAt(index++);
		    boolean currWhiteSpace = Character.isWhitespace(c);
		    if(prevWhiteSpace && !currWhiteSpace){
			numWords++;
		    }
		    prevWhiteSpace = currWhiteSpace;
		}
		return numWords;
    }
	public static void main(String args[]) throws Exception{  
		String line;
		long numChar=0;
		long numLine=0; 		
		long tnumChar = 0;
		long tnumWords=0;
		long tnumLine=0;				
		long [][] acum=new long[args.length][3];
		try{
		    if (args.length == 0)
			{
			    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			    line = in.readLine();
			    numChar = line.length();
			    if (numChar != 0){
					numLine=1;
			    }
			    System.out.println("Number of characters: " + numChar);
			    System.out.println("Number of words: " + wordcount(line));
			    System.out.println("Number of lines: " + numLine);
			}else{
				final long startTime = System.nanoTime();		
				for(int i = 0; i < args.length; i++){					
					Linecount obj=new Linecount(args[i]);  
		    		Thread tobj =new Thread(obj);  		    				    		
		    		tobj.start();	
		    		tobj.sleep(1000);	    				    	
		    		acum[i]=obj.countline;	
				}    	

				System.out.println("Total Summary");
				System.out.println("Number of characters: " + acum[0][0]);
				System.out.println("Number of words: " + acum[1][0]);
				System.out.println("Number of Lines: " + acum[2][0]);
				final long duration = System.nanoTime() - startTime;
				System.out.println("Time Employee: " + (duration/1000000)+ "ms");
		    }
		}
		catch(IOException e){
		    e.printStackTrace();
		}
 	} 
}
