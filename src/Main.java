import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random rand = new Random();

		final int NUMBER_OF_DIGITS = 3; // 数字の桁数
		int count = 0;	// 回答数

		// ターゲットの数字を生成
		ArrayList<Integer> targetList = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
		Collections.shuffle(targetList);
		ArrayList<Integer> targetNums = new ArrayList<>();
		for (int i = 0; i < NUMBER_OF_DIGITS; i++) {
			targetNums.add(targetList.get(i));
		}

		printArray(targetNums);

		System.out.println("ゲームスタート\n" + NUMBER_OF_DIGITS + "桁の数字を当ててください。");

		while (true) {
			System.out.print("1.回答 2.ヒント 3.終了 >>");
			int select = scanner.nextInt();
			switch (select) {
				case 1:
					count++;
					System.out.print(NUMBER_OF_DIGITS + "桁の数字を入力してください。");
					int answerIntNums = scanner.nextInt();
	
					ArrayList<Integer> answerNums = splitInteger(answerIntNums);
					int[] checkResult = checkHitAndBlow(targetNums, answerNums);
					System.out.println("Hit:" + checkResult[0] + ", Blow:" + checkResult[1]);
					
					if (NUMBER_OF_DIGITS == checkResult[0]) {
						System.out.println("正解です！！！！！！！！！！\n" + count + "回目でクリアしました。");
						return;
					}
					break;
				case 2:
					System.out.println("ヒント");
					break;
				case 3:
					System.out.println("ByeBye");
					scanner.close();
					return;
				default:
					System.out.println("整数1～3で入力してください");
					break;
			}
		}
	}

	private static int[] checkHitAndBlow(ArrayList<Integer> targetList, ArrayList<Integer> answerList) {
		int hit = 0;
		int blow = 0;
		
		for (int i = 0; i < answerList.size(); i++) {
			if (targetList.contains(answerList.get(i))) {
				if (targetList.get(i) == answerList.get(i)) {
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
	private static ArrayList<Integer> splitInteger(int number) {
		ArrayList<Integer> digitList = new ArrayList<>();

		while (number > 0) {
			int digit = number % 10;
			digitList.add(0, digit);
			number /= 10;
		}

		return digitList;
	}

	private static void printArray(List<Integer> list) {
		System.out.printf("%s\n\n", list);
	}
}
