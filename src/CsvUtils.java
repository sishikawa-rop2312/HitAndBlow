import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {
	// CSVファイルを読み込む
    public static List<CsvData> readCsvFile(String filePath) throws IOException {
        List<CsvData> data = new ArrayList<>();
        
        Path path = Paths.get(filePath);

        // ファイルが存在しない場合は新規作成
        if (!Files.exists(path)) {
            Files.createFile(path);
            return data;
        }

        List<String> lines = Files.readAllLines(Paths.get(filePath));
        for (String line : lines) {
        	CsvData values = CsvData.fromCsvString(line);
            data.add(values);
        }

        return data;
    }

    // CSVファイルに書き込む
    public static void writeCsvFile(String filePath, List<CsvData> data) throws IOException {
    	List<String> lines = new ArrayList<>();
        for (CsvData values : data) {
        	lines.add(values.toCsvString());
        }
        Files.write(Paths.get(filePath), lines);
    }
}
