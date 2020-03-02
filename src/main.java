import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class main {
	static BufferedReader reader;
	static Writer fileWriter;
	static String dir = "resources/";
	static String inputFile = "abfallkalender.ics";
	static String outputFile = "AbfallkalenderMitErinnerung-out.ics";

	static String ALARMS = "BEGIN:VALARM\r\n" + "ACTION:DISPLAY\r\n" + "DESCRIPTION:This is an event reminder\r\n"
			+ "TRIGGER:-P0DT8H0M0S\r\n" + "END:VALARM\r\n" + "BEGIN:VALARM\r\n" + "ACTION:DISPLAY\r\n"
			+ "DESCRIPTION:This is an event reminder\r\n" + "TRIGGER:-P0DT9H00M0S\r\n" + "END:VALARM\r\n" + "";

	public static void main(String[] args) {

		try {
			reader = new BufferedReader(new FileReader(dir + inputFile));
			fileWriter = new FileWriter(dir + outputFile);
			String line = reader.readLine();
			while (line != null) {
				checkLine(line);
				System.out.println(line);
				// read next line
				line = reader.readLine();
			}
			reader.close();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void checkLine(String line) {
		if (line.startsWith("DTSTART:")) {
			line += "T050000";
		} else if (line.startsWith("DTEND:")) {
			line += "T060000";
		} else if (line.startsWith("END:VEVENT")) {
			line = ALARMS + line;
		}
		try {
			line += "\n";
			fileWriter.write(line);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
