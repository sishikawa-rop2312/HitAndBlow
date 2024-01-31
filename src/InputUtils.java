import java.util.Scanner;

public class InputUtils {
	// 入力値が整数でない場合再度入力させる。
	public static int inputScannerInteger(Scanner scanner) {
		while (true) {
			if (scanner.hasNextInt()) {
				return scanner.nextInt();
			} else {
				System.out.print("整数以外が入力されました。再度入力してください >>");
				// 不正な入力を破棄する
				scanner.next();
			}
		}
	}
}
