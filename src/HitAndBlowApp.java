import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class HitAndBlowApp {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("1.遊ぶ 2.履歴を見る 3.アプリ終了 >>");
			int mode = scanner.nextInt();

			switch (mode) {
			case 1:
				playGame(scanner);
				break;
			case 2:
				// CSVファイルを読み込む
				List<CsvData> loadedData = CsvUtils.readCsv("data.csv");
				
				System.out.println("No\t\tクリア日時\t\t\t\t桁数\t\t回数");
				System.out.println("================================================");
				if (loadedData.size() == 0) {
					System.out.println("まだ履歴が存在しません");
				} else {
					for (int i = 0; i < loadedData.size() ;i++) {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
						String dateTimeValue = loadedData.get(i).getDateTimeValue().format(formatter);
						int numberOfDigits = loadedData.get(i).getNumberOfDigits();
						int count = loadedData.get(i).getCount();
						
						System.out.println(i + "\t\t" + dateTimeValue + "\t\t" + numberOfDigits + "\t\t\t" + count);
					}
				}
				break;
			case 3:
				System.out.println("アプリ終了します");
				scanner.close();
				return;
			}
		}
	}

	public static void playGame(Scanner scanner) throws IOException {
		System.out.print("何桁の数字で遊びますか？ >>");
		int numberOfDigits = scanner.nextInt(); // 桁数
		System.out.println("ゲームスタート\n" + numberOfDigits + "桁の数字を当ててください.");

		HitAndBlow hitAndBlow = new HitAndBlow(numberOfDigits);

		while (true) {
			System.out.print("1.回答 2.ヒント 3.Topへ戻る >>");
			int select = scanner.nextInt();

			switch (select) {
			case 1:
				boolean isAnswerEnd = hitAndBlow.answerNums(scanner);
				if (isAnswerEnd)
					return;
				break;
			case 2:
				hitAndBlow.askHint(scanner);
				break;
			case 3:
				return;
			default:
				System.out.println("整数1～3で入力してください");
				break;
			}
		}
	}
}
