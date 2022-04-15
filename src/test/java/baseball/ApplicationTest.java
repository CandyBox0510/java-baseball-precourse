package baseball;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.*;

class ApplicationTest extends NsTest {
    @Test
    void 게임종료_후_재시작() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("246", "135", "1", "597", "589", "2");
                    assertThat(output()).contains("낫싱", "3스트라이크", "1볼 1스트라이크", "3스트라이크", "게임 종료");
                },
                1, 3, 5, 5, 8, 9
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1234"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }

    @Test
    @DisplayName( "생성 문제 유효성 체크" )
    void createQuestionTest(){
        Computer computer = new Computer();
        assertThat( computer.getQuestion() ).isNotNull().isInstanceOf( List.class ).hasSize( 3 );
    }

    @Test
    @DisplayName( "3자리 수 이상 예외처리 확인" )
    @ParameterizedTest
    @ValueSource (strings = { "123", "456", "158", "가나다", "11111", "가가", "가"})
    void checkStringSizeThree(String text){
        assertThatExceptionOfType( IllegalArgumentException.class ).isThrownBy( ()->{
            if(text.length() != 3){
                throw new IllegalArgumentException("숫자 3자리만 입력해주세요.");
            }
        } ).withMessageMatching( "숫자 3자리만 입력해주세요." );
    }

    @Test
    @ParameterizedTest
    @ValueSource(strings = {"123", "456", "158", "111"})
    void hasDuplicateNumber(String inputNumber){
        String[] inputNumberArray = inputNumber.split("");
        List<String> inputNumberList = new ArrayList<>(Arrays.asList( inputNumberArray ));
        Set<String> inputNumberSet = new HashSet<>( inputNumberList);
        assertThat(inputNumberList.size()).isEqualTo( 3 );
    }
}
