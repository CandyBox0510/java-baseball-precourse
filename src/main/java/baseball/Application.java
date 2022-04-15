package baseball;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        runGame( );
    }

    public static void runGame( ){
        Computer computer = new Computer();
        while(computer.getWinCount() < 11){
            System.out.printf("숫자를 입력해주세요 : ");
            String userInputNumber = Console.readLine();
            computer.inputNumberResult( userInputNumber );
        }
    }
}
