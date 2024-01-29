import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class NumberLogic {
	private ArrayList<Integer> targetNums;
	private int numberOfDigits;
	private int count;

	// コンストラクタ
	public NumberLogic(int numberOfDigits) {
		this.numberOfDigits = numberOfDigits;
		this.count = 0;

		// ターゲットの数字を生成
		createTargetNums();
	}

	// ターゲットの数字を生成
	public void createTargetNums() {
		ArrayList<Integer> targetList = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
		Collections.shuffle(targetList);
		this.targetNums = new ArrayList<>();
		for (int i = 0; i < numberOfDigits; i++) {
			this.targetNums.add(targetList.get(i));
		}

		// Todo デバッグ用なので削除
		printArray(this.targetNums);
	}

	// ナンバーを回答
	public boolean answerNums(Scanner scanner) throws IOException {
		boolean isAnswerEnd = false;
		this.count++;
		System.out.print(numberOfDigits + "桁の数字を入力してください。 >>");
		int answerIntNums = scanner.nextInt();

		ArrayList<Integer> answerNums = splitInteger(answerIntNums);
		int[] checkResult = checkHitAndBlow(answerNums);
		System.out.println("Hit:" + checkResult[0] + ", Blow:" + checkResult[1]);

		// Hit数がターゲット桁数と同じなら全問正解のためクリア
		if (numberOfDigits == checkResult[0]) {
			isAnswerEnd = true;
			System.out.println("正解です！！！！！！！！！！\n" + count + "回目でクリアしました。");

			List<CsvData> dataList = CsvUtils.readCsvFile(CsvData.CSV_FILE_NAME);
			// 現在の年月日時分秒を取得
			LocalDateTime currentDateTime = LocalDateTime.now();

			// データを追加
			dataList.add(new CsvData(
					currentDateTime,
					this.numberOfDigits,
					this.count));

			// CSVファイルに書き込む
			try {
				CsvUtils.writeCsvFile(CsvData.CSV_FILE_NAME, dataList);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return isAnswerEnd;
	}

	// 指定した桁の数字をヒントで表示
	public void askHint(Scanner scanner) {
		System.out.print("何桁目の数字を知りたいですか。 >>");
		int digit = scanner.nextInt();
		System.out.printf("%d桁目の数字は%dです\n", digit, targetNums.get(digit - 1));
	}

	// Hit数とBlow数のチェック
	public int[] checkHitAndBlow(ArrayList<Integer> answerList) {
		int hit = 0;
		int blow = 0;

		for (int i = 0; i < answerList.size(); i++) {
			if (targetNums.contains(answerList.get(i))) {
				if (targetNums.get(i).equals(answerList.get(i))) {
					hit++;
				} else {
					blow++;
				}
			}
		}

		int[] hitAndBlow = { hit, blow };
		return hitAndBlow;
	}

	// 整数をArrayListの要素に分割
	public ArrayList<Integer> splitInteger(int number) {
		ArrayList<Integer> digitList = new ArrayList<>();

		while (digitList.size() != numberOfDigits) {
			int digit = number % 10;
			digitList.add(0, digit);
			number /= 10;
		}

		return digitList;
	}

	// ターゲットナンバーを表示
	public void printArray(List<Integer> list) {
		System.out.printf("デバッグ用：%s\n", list);
	}
}