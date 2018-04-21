package ConvertionEngine.LegacyToUnicodeFontMappings;

import java.util.*;

/**
 * Created by gayan@ciperlabs.com on 4/21/18.
 */
public class FMAbhaya {
	
	
	private static Map<String, String> con = new HashMap<String,String>() ;
	
	public static String convert(String text) {
		con.put("CI","ක්‍ෂ");
		con.put("Cj" ,"ක්‍ව");
		con.put("Ë" ,"ක්‍ෂ");
		con.put("†" ,"ත්‍ථ");
		con.put("…" ,"ත්‍ව");
		con.put("‡" ,"න්‍ද");
		con.put("JO" ,"න්‍ධ");
		con.put("Š" ,"ද්‍ධ");
		con.put("`O" ,"ද්‍ධ");
		con.put("„" ,"ද්‍ව");
		con.put("`j" ,"ද්‍ව");
		// following are unicode consos
		con.put("`o" ,"ඳ"); // added
		con.put("`P" ,"ඦ"); // added
		con.put("`v" ,"ඬ"); // added
		con.put("`." ,"ඟ"); // added
		con.put("`y" ,"ඟ"); // not sure if this is correct -sagnaka ha does not exist
		con.put("P" ,"ඡ"); //=
		con.put("X" ,"ඞ");
		con.put("r" ,"ර");
		con.put("I" ,"ෂ");
		con.put("U" ,"ඹ");
		con.put("c" ,"ජ");
		con.put("V" ,"ඪ");
		con.put(">" ,"ඝ");
		con.put("L" ,"ඛ");
		con.put("<" ,"ළ");
		con.put("K" ,"ණ");
		con.put("M" ,"ඵ");
		con.put("G" ,"ඨ");
		con.put("¿" ,"ළු");
		con.put("Y" ,"ශ");
		con.put("[" ,"ඤ");
		con.put("{" ,"ඥ");
		con.put("|" ,"ඳ");
		con.put("~" ,"ඬ");
		con.put("CO" ,"ඣ");
		con.put("®" ,"ඣ");
		con.put("Õ" ,"ඟ");
		con.put("n" ,"බ");
		con.put("p" ,"ච");
		con.put("v" ,"ඩ");
		con.put("`M", "ළු");
		con.put("M" ,"ඵ");
		con.put("*" ,"ෆ");
		con.put("." ,"ග");
		con.put("y" ,"හ");
		con.put("c" ,"ජ");
		con.put("l" ,"ක");
		con.put("," ,"ල");
		con.put("u" ,"ම");
		con.put("k" ,"න");
		con.put("m" ,"ප");
		con.put("o" ,"ද");
		con.put("r" ,"ර");
		con.put("i" ,"ස");
		con.put("g" ,"ට");
		con.put("j" ,"ව");
		con.put(");" ,"ත");
		con.put("N" ,"භ");
		con.put("h" ,"ය");
		con.put("O" ,"ධ");
		con.put(":" ,"ථ");
		con.put("sÀ" ,"ඳ");
		
		return text;
	}
	
	
	private void replaceSeq(String fm_pre,String fm_post,String un_pre,String un_post, String text) {
		for	(String fm : con.keySet()) {
			String temp = escape(fm_pre + fm + fm_post);
			text.replaceAll(temp, un_pre + con.get(fm) + un_post);
		}
	}
	
	private String escape(String text) {	
		return text.replaceAll("([\\\\\\.\\[\\{\\(\\*\\+\\?\\^\\$\\|])", "\\\\$1");
	}

}
	