import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solution {
    public static int solution1(int[] prices, int[] discounts) {
        int answer = 0;

        Arrays.sort(prices);
        Arrays.sort(discounts);

        for (int i = 0; i < prices.length / 2; i++) {
            int temp = prices[i];
            prices[i] = prices[prices.length - 1 - i];
            prices[prices.length - 1 - i] = temp;
        }

        for (int i = 0; i < discounts.length / 2; i++) {
            int temp = discounts[i];
            discounts[i] = discounts[discounts.length - 1 - i];
            discounts[discounts.length - 1 - i] = temp;
        }

        for (int i = 0; i < prices.length; i++) {
            if (discounts.length - 1 >= i) {
                prices[i] = (int)(prices[i] * ((double)(100 - discounts[i]) / 100));
                if (prices[i] % 5 != 0) {
                    prices[i]++;
                }
            } else {
              break;
            }
        }

        for (int i = 0; i < prices.length; i++) {
            answer += prices[i];
        }

        return answer;
    }

    public static String[] solution2(String s) {
        String[] answer = {};
        ArrayList<String> answerString = new ArrayList<>();
        //int answerIndex = 0;
        int split = 1;
        int last = 0;

        while (true) {
            if (split >= s.length()) {
                answer = new String[1];
                answer[0] = s;

                return answer;
            }

            boolean bFind = false;
            if (!s.substring(last, last + split).equals(s.substring(s.length() - split - last, s.length() - last))) {
                //System.out.println("false : " + s.substring(last, last + split) + " " + s.substring(s.length() - split - last, s.length() - last));
            } else {
                bFind = true;
                answerString.add(s.substring(last, last + split));
                //System.out.println("true : " + s.substring(last, last + split) + " " + s.substring(s.length() - split - last, s.length() - last));
            }

            if (bFind) {
                last = last + split;
                split = 1;
                if (last > s.length() - 1) {
                    break;
                }
            } else {
                split++;
            }
        }

        answer = new String[answerString.size()];
        for (int i = 0; i < answerString.size(); i++) {
            answer[i] = answerString.get(i);
        }

        return answer;
    }

    public static int solution3(String s, String t) {
        int result = 0;
        int tSize = t.length();

        while (s.contains(t)) {
            int compareIndex = s.indexOf(t);
            s = s.substring(0, compareIndex) + s.substring(compareIndex + tSize);
            System.out.println(compareIndex + " " + s);
            result++;
        }

        return result;
    }

    public static String substring(String s, int start, int last) {
        String str = "";
        for (int i = start; i <= last; i++) {
            str += s.indexOf(i);
        }

        return str;
    }
}

/* 1번 문제 지문
문제 설명
쿠폰을 사용하면 물건을 살 때 할인을 받을 수 있습니다. 쿠폰이 여러 장 주어졌을 때, 쿠폰을 적절히 사용해 할인을 최대한 많이 받으려고 합니다. 한 제품에는 쿠폰을 하나만 적용할 수 있으며, 사용한 쿠폰은 사라집니다.

주문할 제품의 가격 prices, 쿠폰별 할인율 discounts가 매개변수로 주어집니다. 할인을 최대한 많이 받도록 쿠폰을 적용했을 때 얼마를 내야 하는지 return 하도록 solution 함수를 완성해주세요.

제한 사항
prices 배열의 길이는 1 이상 1,000 이하입니다.
prices 배열의 원소는 5,000 이상 150,000 이하인 자연수이며, 항상 100으로 나누어 떨어집니다.
discounts 배열의 길이는 1 이상 1,000 이하입니다.
discounts 배열의 원소는 1 이상 100 이하인 자연수입니다.
입출력 예
prices	discounts	result
[13000, 88000, 10000]	[30, 20]	82000
[32000, 18000, 42500]	[50, 20, 65]	45275
입출력 예 설명
입출력 예 #1

13,000원짜리 제품에 20% 쿠폰을 적용해 10,400원을 결제합니다.
88,000원짜리 제품에 30% 쿠폰을 적용해 61,600원을 결제합니다.
10,000원짜리 제품에는 쿠폰을 적용하지 않습니다.

따라서 82,000원을 내야 합니다.

입출력 예 #2

32,000원짜리 제품에 50% 쿠폰을 적용해 16,000원을 결제합니다.
18,000원짜리 제품에 20% 쿠폰을 적용해 14,400원을 결제합니다.
42,500원짜리 제품에 65% 쿠폰을 적용해 14,875원을 결제합니다.

따라서 45,275원을 내야 합니다.
* */

/* 2번 문제 지문
문제 설명
문자열 s가 주어질 때, s를 가능한 많은 개수의 "문자열 조각"으로 자르려 합니다. 단, 잘린 "문자열 조각"을 앞에서부터 순서대로 s(1), s(2), s(3), ..., s(n)이라고 했을 때, 다음 조건을 만족해야 합니다.

1 ≤ i ≤ (n+1)/2 인 모든 자연수 i에 대해서, s(i) = s(n - i + 1)
예를 들어 문자열 "abcxyasdfasdfxyabc"을 "abc", "xy", "asdf", "asdf", "xy", "abc"로 자르고, 각 문자열 조각을 순서대로 s1, s2, s3, s4, s5, s6이라고 하겠습니다.

"abc" = s1
"xy" = s2
"asdf" = s3
"asdf" = s4
"xy" = s5
"abc" = s6
이때, s1 = s6, s2 = s5, s3 = s4로 위 조건을 만족하며, 이보다 더 많은 개수의 "문자열 조각"으로 자르면서 조건을 만족하도록 하는 방법은 없습니다.

문자열 s가 매개변수로 주어질 때, 조건에 맞게 s를 "문자열 조각"으로 자른 후 순서대로 배열에 담아 return 하도록 solution 함수를 완성해주세요.

제한사항
문자열 s의 길이는 1 이상 1,000 이하입니다.
s는 알파벳 소문자로만 이루어져 있습니다.
입출력 예
s	result
"abcxyasdfasdfxyabc"	["abc","xy","asdf","asdf","xy","abc"]
"abcxyqwertyxyabc"	["abc","xy","qwerty","xy","abc"]
"abcabcabcabc"	["abc","abc","abc","abc"]
"llttaattll"	["l","l","t","t","a","a","t","t","l","l"]
"zzzzzz"	["z","z","z","z","z","z"]
"abcdef"	["abcdef"]
입출력 예 설명
입출력 예 #1

문제 예시와 같습니다.

입출력 예 #2

문자열을 "abc", "xy", "qwerty", "xy", "abc"로 자르고, 각 문자열 조각을 순서대로 s1, s2, s3, s4, s5라고 하면 s1 = s5, s2 = s4, s3 = s3를 만족합니다. 이보다 더 많은 개수의 "문자열 조각"으로 자르면서 조건을 만족하도록 하는 방법은 없으므로, ["abc","xy","qwerty","xy","abc"]를 return 합니다.

입출력 예 #3

문자열을 "abc", "abc", "abc", "abc"로 자르고, 각 문자열 조각을 순서대로 s1, s2, s3, s4라고 하면 s1 = s4, s2 = s3를 만족하며, 이보다 더 많은 "문자열 조각"으로 자르면서 조건을 만족하도록 하는 방법은 없습니다.

만약 문자열을 "abcabc", "abcabc"로 자르고, 각 문자열 조각을 순서대로 s1, s2라고 하면 s1 = s2를 만족하지만, s를 가능한 많은 개수의 "문자열 조각"으로 잘라야 하므로 더 많은 조각으로 자른 ["abc","abc","abc","abc"]를 return 해야 합니다.

입출력 예 #4, #5, #6

설명 생략
* */

/* 3번 문제 지문
문제 설명
문서 편집기에는 찾아 바꾸기라는 기능이 있습니다. 찾아 바꾸기를 이용하여 파일에서 바꿀 문자열을 찾은 뒤, 그 문자열이 발견되면 그 문자열을 제거하는 과정을 진행합니다. 찾아 바꾸기는 한 번에 한 개의 문자열만 제거할 수 있습니다. 따라서 제거할 문자열이 여러 번 나타나거나, 문자열을 제거한 결과가 또 다시 바꿀 문자열과 같아지는 경우 한번 더 찾아 바꾸기를 해야 합니다.

예를 들어, aabcbcd 문자열에서 abc 라는 문자열을 제거하고 싶을 때,찾아 바꾸기를 1번 실행하면 abcd가 됩니다.이 때, abc가 1번 더 생기게 되므로, 한번 더 찾아 바꾸기를 실행하여 d로 바꿀 수 있습니다. 더 이상 abc 문자열이 존재하지 않으므로 총 2번의 찾아 바꾸기 과정을 통해 문자열을 모두 바꿀 수 있습니다.

검색하고자 하는 문장 s에서 문자열 t를 찾아 제거할 때, 총 몇 번의 찾아 바꾸기를 진행해야 문장 s에서 t가 더이상 존재하지 않는지 계산하는 함수를 완성하세요.

제한사항
문자열 s : 길이는 1,000,000 이하의 자연수이며, 소문자로만 이루어져 있습니다.
문자열 t : 길이는 10 이하의 자연수이며, 소문자로만 이루어져 있습니다. (단 문자열 t의 문자들은 중복되지 않습니다.)
입출력 예
s	t	result
"aabcbcd"	"abc"	2
"aaaaabbbbb"	"ab"	5
입출력 예 설명
입출력 예 #1

문제의 예시와 같습니다.

입출력 예 #2

"ab"를 제거하면 "aaaabbbb"가 남게 되고, 또 "ab"를 제거할 수 있게 됩니다. 같은 방식으로 총 5번을 진행하면 S 문자열에 "ab"가 더이상 존재하지 않게 됩니다.
* */