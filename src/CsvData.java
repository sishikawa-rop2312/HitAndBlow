import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class CsvData {
	private LocalDateTime currentDateTime;
	private int numberOfDigits;
	private int count;
	final static String CSV_FILE_NAME = "data.csv";
	final static String FORMAT_DATE_TIME = "yyyy/MM/dd HH:mm:ss";

	public CsvData(LocalDateTime currentDateTime, int numberOfDigits, int count) {
		this.currentDateTime = currentDateTime;
		this.numberOfDigits = numberOfDigits;
		this.count = count;
	}

	public LocalDateTime getDateTimeValue() {
		return this.currentDateTime;
	}

	public int getNumberOfDigits() {
		return this.numberOfDigits;
	}

	public int getCount() {
		return this.count;
	}

	// CSVデータオブジェクトをCSV形式の文字列に変換
	public String toCsvString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE_TIME);
		return currentDateTime.format(formatter) + "," + this.numberOfDigits + "," + this.count;
	}

	// CSV形式の文字列からCSVデータオブジェクトを作成
	public static CsvData fromCsvString(String csvString) {
		String[] parts = csvString.split(",");
		LocalDateTime currentDateTime = LocalDateTime.parse(parts[0],
				DateTimeFormatter.ofPattern(FORMAT_DATE_TIME));
		int numberOfDigits = Integer.parseInt(parts[1]);
		int count = Integer.parseInt(parts[2]);
		return new CsvData(currentDateTime, numberOfDigits, count);
	}
}