import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class StringToInt{
	
public static int StringToInt(String str){
    int i = 0;
    int num = 0;
    boolean isNegative = false;

    
    if (str.charAt(0) == '-') {
        isNegative = true;
        i = 1;
    }

    
    while( i < str.length()) {
        num *= 10;
        num += str.charAt(i++) - '0'; 
    }

    if (isNegative)
        num = -num;
    return num;
}
public static void main(String args[]) throws IOException
{
	  //We can take String here , just comment the commented lines 
      // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	  // String str = br.readLine();
	
	 //comment the below lines if want to take input from the user
	  String str="1234" ;
	  
	  System.out.println(StringToInt(str));
  }
}
