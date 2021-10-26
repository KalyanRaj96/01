package javaTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Test {

private static final Map<String, String> MAPPING = new HashMap<>();

	static {
			MAPPING.put("PM", "PRE_MATCH");
			MAPPING.put("H1", "FIRST_HALF");
			MAPPING.put("HT", "HALF_TIME");
			MAPPING.put("H2", "SECOND_HALF");
			MAPPING.put("FT", "FULL_TIME");
}

public static void main(String[] args) throws IOException {

	ArrayList<String> arr = readTextFile();

	for (String elm : arr) {
		System.out.println(elm + " --- " + getFormatted(elm));
	}
	}

	private static String getFormatted(String elm) {
		String period = elm.substring(1, 3);

		String regex = "\\[(PM|H1|HT|H2|FT)\\]\\s+\\d{1,2}:\\d{1,2}\\.\\d{1,3}";
		if (!elm.matches(regex)) {
			return "INVALID";
}

		String[] split = elm.trim().split("\\s+");
		if (split.length != 2) {
			return "INVALID";
}
		String x = split[0].replaceAll("[\\[\\]]", "").toUpperCase();
		String time = MAPPING.get(x);
		if (time == null) {
			return "INVALID";
		}
		if (split[1].contains("-")) {
			return "INVALID";
		}
		split = split[1].split("\\.");
		int milli = 0;
		if (split.length == 2) {
			milli = Integer.parseInt(split[1].trim());
		}
		split = split[0].split(":");
		int min = 0;
	int sec = 0;
	if (split.length == 2) {
		min = Integer.parseInt(split[0].trim());
	sec = Integer.parseInt(split[1].trim());
} else {
	return "INVALID";
}
	if (milli >= 500) {
	sec = sec + 1;
	}
	if (min < 45) {
	return String.format("%s:%s - %s", prefix(min), prefix(sec), time);
	
}
	else if (min >= 45 && min < 90 && period.equals("H1")) {
	int additional = min - 45;
	if (milli > 0) {
	return String.format("%s:%s +%s:%s - %s", prefix(45), "00", prefix(additional), prefix(sec),
		time);
	} else {
	return String.format("%s:%s - %s", prefix(45), prefix(sec), time);

}

} else if (min >= 45 && min < 90 && period.equals("HT")) {
		int additional = min - 45;
		if (milli > 0) {	
	return String.format("%s:%s +%s:%s - %s", prefix(45), "00", prefix(additional), prefix(sec),
time);
} else {
		return String.format("%s:%s - %s", prefix(45), prefix(sec), time);

}
	}else if (min >= 45 && min < 90 && period.equals("H2")) {
			if (milli > 0) {
				return String.format("%s:%s - %s", prefix(min), prefix(sec), time);
			} else {
				return String.format("%s:%s - %s", prefix(45), prefix(sec), time);
}
	}else if (min <= 90) {
		int additional = min - 90;
		if (milli > 0) {
			return String.format("%s:%s +%s:%s - %s", prefix(90), "00", prefix(additional), prefix(sec), time);
		} else {
	return String.format("%s:%s - %s", prefix(90), prefix(sec), time);
}
}

return null;
}

	private static String prefix(int min) {
		return min < 10 ? "0" + min : String.valueOf(min);
}


	public static ArrayList<String> readTextFile() throws IOException
	{
			ArrayList<String> result = new ArrayList<>();

	try (BufferedReader br = new BufferedReader(new FileReader("../assessment/src/test/resources/inputFile.txt"))) {
		while (br.ready()) {
			result.add(br.readLine());
}
}
		return result;
}

}