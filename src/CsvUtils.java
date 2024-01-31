import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {
	// CSVファイルを読み込む
    public static List<CsvData> readCsvFile(String filePath) {
        List<CsvData> data = new ArrayList<>();
        
        Path path = Paths.get(filePath);

        // ファイルが存在しない場合は新規作成
        if (!Files.exists(path)) {
            try {
				Files.createFile(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
            return data;
        }

        List<String> lines = new ArrayList<String>();
		try {
			lines = Files.readAllLines(Paths.get(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
        for (String line : lines) {
        	CsvData values = CsvData.fromCsvString(line);
            data.add(values);
        }

        return data;
    }

    // CSVファイルに書き込む
    public static void writeCsvFile(String filePath, List<CsvData> data) {
    	List<String> lines = new ArrayList<>();
        for (CsvData values : data) {
        	lines.add(values.toCsvString());
        }
        try {
			Files.write(Paths.get(filePath), lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
