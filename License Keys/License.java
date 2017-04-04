import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class License {


	//function to find the finalLicenseKey
	public static String finalLicense(String S, int K){

		int count =S.length()-1;
		int countOfCharacters =0;
		StringBuffer finalLicenseKey = new StringBuffer();
		while(count > -1)
		{

			if(countOfCharacters % 4 ==0 && countOfCharacters != 0)
			{
			   finalLicenseKey.append("-");
			}
			if(S.charAt(count) != '-')
			{
				finalLicenseKey.append(Character.toUpperCase(S.charAt(count)));
				countOfCharacters++;
				count--;
			}
			else
				count--;

		}
		return finalLicenseKey.reverse().toString();
	}



	public static void main(String args[]) throws IOException
	{

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	//input String S
	String S = br.readLine();

	//input integer K
	int K = Integer.parseInt(br.readLine());


	//output the Final License Key  value after modification
	 System.out.println(finalLicense(S,K));


}
}
