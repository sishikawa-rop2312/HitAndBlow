import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {

    public static List<CsvData> readCsv(String filePath) throws IOException {
        List<CsvData> data = new ArrayList<>();
        
        Path path = Paths.get(filePath);

        // ファイルが存在しない場合は新規作成
        if (!Files.exists(path)) {
            Files.createFile(path);
            return data;
        }

        List<String> lines = Files.readAllLines(Paths.get(filePath));
        for (String line : lines) {
            // カンマで分割する
        	CsvData values = CsvData.fromCsvString(line);
            data.add(values);
        }

        return data;
    }

//    public static void updateCsv(String filePath, int row, String[] values) throws IOException {
//        // ファイルを読み込む
//        List<String[]> data = readCsv(filePath);
//
//        // 指定行のデータを更新する
//        data.set(row, values);
//
//        // ファイルを書き込む
//        writeCsv(filePath, data);
//    }

//    public static void deleteCsv(String filePath, int row) throws IOException {
//        // ファイルを読み込む
//        List<String[]> data = readCsv(filePath);
//
//        // 指定行を削除する
//        data.remove(row);
//
//        // ファイルを書き込む
//        writeCsv(filePath, data);
//    }

//    public static void addCsv(String filePath, CsvData values) throws IOException {
//        // ファイルを読み込む
//        List<CsvData> data = readCsv(filePath);
//
//        // データを末尾に追加する
//        data.add(values);
//
//        // ファイルを書き込む
//        writeCsv(filePath, data);
//    }

    public static void writeCsv(String filePath, List<CsvData> data) throws IOException {
    	List<String> lines = new ArrayList<>();
    	// データを書き込む
        for (CsvData values : data) {
        	lines.add(values.toCsvString());
        }
        Files.write(Paths.get(filePath), lines);
    }
}
