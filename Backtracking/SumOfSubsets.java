import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SumOfSubsets {

    private static List<String> subsetList = new ArrayList<>();

    public static void main(String[] args) {
        List<Integer> listOfNumbers = Arrays.asList(5,10,12,13,15,18);
        Integer sumOfListOfNumbers = listOfNumbers.stream().reduce(0, (a, b) -> a + b);
        String result = "";
        getSubsets(listOfNumbers, result, 0, 30, 0, sumOfListOfNumbers);
        System.out.println(subsetList);
    }

    private static void getSubsets(List<Integer> listOfNumbers, String result, int index, int targetSum, int sumOfElmentsSoFar, int remainingSumSoFar) {
        // When we find the result
        if (targetSum == sumOfElmentsSoFar) {
            subsetList.add(result);
            return;
        }

        // Bounding condition : if the sumofElementsSoFar is greater than the targetElement or we don't have any elements to add furthur
        if (sumOfElmentsSoFar > targetSum || remainingSumSoFar <= 0 || index >= listOfNumbers.size())
            return;


        // Consider without additon of the current element in the result
        getSubsets(listOfNumbers, result, index + 1, targetSum, sumOfElmentsSoFar, remainingSumSoFar);

        // Consider the addition of the current element in the result
        int currentElement = listOfNumbers.get(index);
        sumOfElmentsSoFar += currentElement;
        remainingSumSoFar -= currentElement;
        result += (currentElement+" ");
        getSubsets(listOfNumbers, result, index + 1, targetSum, sumOfElmentsSoFar, remainingSumSoFar);
    }
}
