package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Computer {
    private List<Integer> question;
    private int ball;
    private int strike;
    private int winCount;
    private boolean isEndGame;
    private String  resultText;

    Computer(){
        this.question = new ArrayList<>();
        this.ball = 0;
        this.strike = 0;
        this.winCount = 0;
        this.isEndGame = false;
        this.resultText = "";
        this.createQuestion();
    }

    //문제 생성
    private void createQuestion(){
        while(this.question.size() < 3){
            this.addNumber( Randoms.pickNumberInRange(1, 9) );
        }
    }

    //문제 번호 추가
    private void addNumber(int randomNumber){
        boolean isExist = this.question.contains( randomNumber );
        if(!isExist){
            this.question.add( randomNumber );
        }
    }

    //게임 시작
    public void gameStart(String inputNumber){
        boolean isValid = this.validation(inputNumber);
        if(isValid){
            this.numberCompare( inputNumber );
            this.showResult();
        }
    }

    //유효성 검사
    private boolean validation(String inputNumber){
        this.checkStringSizeThree( inputNumber );
        return this.oneToNineNumber( inputNumber );
    }

    //3자리 체크
    private void checkStringSizeThree(String inputNumber){
        if(inputNumber.length() != 3){
            throw new IllegalArgumentException("숫자 3자리만 입력해주세요.");
        }
    }

    //1~9까지의 숫자 체크
    private boolean oneToNineNumber(String inputNumber){
        List<String> sList = new ArrayList<>( 3);
        for(int i = 0; i < inputNumber.length(); i ++){
            String number = String.valueOf( inputNumber.charAt( i ) );
            sList.add(number.matches( "^[1-9]" ) ? number : "fail");
        }

        boolean isHasFail = sList.contains( "fail" );

        if(isHasFail){
            System.out.println("1 ~ 9 까지의 유효한 숫자를 입력하세요.");
            return false;
        }
        return true;
    }

    //각 자리별 번호 및 인덱스 추출
    private void numberCompare( String inputNumber ){
        this.strike = 0;
        this.ball = 0;

        Integer firstNumber = Character.getNumericValue( inputNumber.charAt( 0 ) );
        Integer secondNumber = Character.getNumericValue( inputNumber.charAt( 1 ) );
        Integer thirdNumber = Character.getNumericValue( inputNumber.charAt( 2 ) );

        //자리별 번호 비교
        getIndexNumberCompare( firstNumber, 0 );
        getIndexNumberCompare( secondNumber, 1 );
        getIndexNumberCompare( thirdNumber, 2 );
    }

    //자리별 번호 비교
    private void getIndexNumberCompare( Integer indexNumber, int userIndex ){
        for(int idx = 0; idx < this.question.size(); idx ++){
            this.compareWithQuestion( indexNumber, userIndex, new Integer( this.question.get( idx )), new Integer( idx) );
        }
    }

    //문제와 번호 비교
    private void compareWithQuestion( Integer indexNumber, Integer userIndex, Integer questionNumber, Integer questionIndex ){
        //번호가 일치하지 않을 경우 추가가 체크 필요하지 않음
        if(!indexNumber.equals( questionNumber )){
            return;
        }
        //스트라이크
        if(userIndex.equals( questionIndex )){
            this.strike++;
        }
        //볼
        if(!userIndex.equals( questionIndex )){
            this.ball++;
        }
    }

    // 게임 결과 텍스트 보기
    private void showResult(){
        this.makeResultText();
        if(!isEndGame){
            this.winCount++;
        }
        if(this.winCount < 10){
            System.out.println(this.resultText);
        }
        if(this.winCount == 10){
            this.isEndGame = true;
            System.out.println("컴퓨터가 이겼습니다.");
            System.out.println("정답은 : " + this.getQuestion() + " 이였습니다.");
        }
    }

    // 게임 결과 텍스트 생성
    private void makeResultText(){
        this.resultText = "";

        //3 스트라이크
        if(this.strike == 3){
            this.userWinSetting();
        }

        //스트라이크가 없을 경우 컴퓨터 1승 추가
        this.userWrongSetting();
    }

    private void userWinSetting(){
        this.resultText = "3스트라이크\n3개의 숫자를 모두 맞히셨습니다! 게임 종료";
        this.isEndGame = true;
        return;
    }

    private void userWrongSetting(){
        List<String> text = new ArrayList<>();
        //하나도 맞지 않았을 경우
        if(this.strike == 0 && this.ball == 0){
            this.resultText = "낫싱";
            return;
        }
        //볼 존재
        if(this.ball != 0){
            text.add( this.ball + "볼" );
        }
        //스트라이크 존재
        if(this.strike != 0){
            text.add( this.strike + "스트라이크" );
        }
        this.resultText = String.join(" ", text);
    }

    public List< Integer > getQuestion() {
        return question;
    }

    public int getWinCount() {
        return winCount;
    }

    public boolean isEndGame() {
        return isEndGame;
    }
}
