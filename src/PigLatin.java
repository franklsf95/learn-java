import java.util.Arrays;


public class PigLatin {
	
	private boolean isletter(String word) {
	    String letters[] = { "a","b","c","d","f" ,"g","h","j","k","l","m",
	"e", "i", "o", "u"
	    ,"n","o","p","q","r","s","t","v","w","x","y","z"};
	    if (Arrays.asList(letters).contains(word.toLowerCase())) {
	      return true;
	    }
	    else {
	      return false;
	    }
	  }

	  public String pigLatinWord( String word )
	  {
	    String newString = new String(word);
	    char[] array=newString.toCharArray();
	    String s1="way";
	    String s2="ay";
	    String vowel = new String();
	    String vowelAy = new String();
	    String consonant = new String();
	    for(int i=0; i < newString.length();i++)
	    {
	      char original = newString.charAt(i);
	      if(array[i] == 'a'|| array[i] =='e'|| array[i] =='i'|| array[i]
	=='o'|| array[i] =='u'
	        || array[i] =='A'|| array[i] =='E'|| array[i] =='I'|| array[i]
	=='O'|| array[i] =='U')
	      {
	        vowelAy = newString.substring(i);
	        consonant = vowelAy.concat(newString.substring(0,i));
	        vowel = consonant.concat(s2);
	      }
	      if(array[0] == 'a'|| array[0] =='e'|| array[0] =='i'|| array[0]
	=='o'|| array[0] =='u'
	        || array[0] =='A'|| array[0] =='E'|| array[0] =='I'|| array[0]
	=='O'|| array[0] =='U')
	      {
	        vowel = newString.concat(s1);
	      }
	      else if(isletter(word))
	      {
	        vowel=newString.concat(s2);
	      }
	    else
	    {
	    vowel=newString;
	    }

	  }
	return vowel;
	  }

	public String pigLatin (String s) {
		String answer = "";
		int startPos = 0;  // a new word should start from here
		// set to 0, because there is no previous word
		while (startPos < s.length()) {
			// if startPos reaches the end, then we're done
			
			// get the current letter
			String cur = s.substring(startPos, startPos + 1);
			
			// if this location is NOT a letter, then just copy it
			if (!isletter(cur)) {
				answer += cur;  // concat cur to answer
				startPos++;     // go to next position
				continue;       // skip the rest of the loop body
			}
			
			// Now if it gets here, then "cur" is a letter
			
			// find the end of the current word
			int endPos;
			for (endPos = startPos; endPos < s.length(); endPos++) {
				String currentLetter = s.substring(endPos, endPos + 1);
				if (!isletter(currentLetter)) {
					// this is not a letter
					break;
				}
			}
			// now endPos will store the first char that is NOT A LETTER
			// remember: the second argument of substring is the first char
			//           that you don't want
			String word = s.substring(startPos, endPos);
			answer += pigLatinWord(word);  // equivalent to "concat"
			
			startPos = endPos;  // since we already processed this word
		}
		
		return answer;
	}

	public static void main(String[] args) {
		PigLatin p = new PigLatin();
		System.out.println(p.pigLatin("It's Spring '13, Good luck with your classes!"));

	}

}
