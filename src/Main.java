import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("何桁の数字で遊びますか？ >>");
        int numberOfDigits = scanner.nextInt();    // 桁数
        System.out.println("ゲームスタート\n" + numberOfDigits + "桁の数字を当ててください.");

        HitAndBlow hitAndBlow = new HitAndBlow(numberOfDigits);

        while (true) {
            System.out.print("1.回答 2.ヒント 3.終了 >>");
            int select = scanner.nextInt();

            switch (select) {
                case 1:
                    hitAndBlow.answerNums(scanner);
                    break;
                case 2:
                    hitAndBlow.askHint(scanner);
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

}