import java.io.*;

public class  WordCount// implements Runnable
{	
	private String[] args=new String[7];
    private static long[] linecount(String fName, BufferedReader in) 
		throws IOException{
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
    private static long[] linecount(String fileName){
		BufferedReader in = null;
		long[] countline=new long[3];
		try{
		    FileReader fileReader = new FileReader(fileName);
		    in = new BufferedReader(fileReader);
		    countline=linecount(fileName,in);
		}
		catch(IOException e){
			countline=new long[3];	
		    e.printStackTrace();
		}
		return countline;
    }
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
    public WordCount(String[] args){

    }
    public static void main(String[] args){	
		String line;
		long numChar=0;
		long numLine=0; 		
		long tnumChar = 0;
		long tnumWords=0;
		long tnumLine=0;		
		long[] countline=new long[3];
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
				    countline=linecount(args[i]);
				    tnumChar+=countline[0];
				    tnumWords+=countline[1];
				    tnumLine+=countline[2];
				}
				System.out.println("Total Summary");
				System.out.println("Number of characters: " + tnumChar);
				System.out.println("Number of words: " + tnumWords);
				System.out.println("Number of Lines: " + tnumLine);
				/*WordCount obj=new WordCount(args);
				Thread tobj =new Thread(obj);  
     			tobj.start();  																*/
				final long duration = System.nanoTime() - startTime;
				System.out.println("Time Employee: " + (duration/1000000)+ "ms");
		    }
		}
		catch(IOException e){
		    e.printStackTrace();
		}
    }
    /*public void run(){  
	    long tnumChar = 0;
		long tnumWords=0;
		long tnumLine=0;		
		long[] countline=new long[3];
		for(int i = 0; i < this.args.length; i++){					
				    countline=linecount(args[i]);
				    tnumChar+=countline[0];
				    tnumWords+=countline[1];
				    tnumLine+=countline[2];
		}
		System.out.println("Total Summary");
		System.out.println("Number of characters: " + tnumChar);
		System.out.println("Number of words: " + tnumWords);
		System.out.println("Number of Lines: " + tnumLine);
  	}*/   
}
