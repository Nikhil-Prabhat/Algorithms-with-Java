public class Robin_Karp_Algorithm {

    // Prime number used for hashing
    private static final int PRIME = 101;

    public static void main(String[] args) {
        String text = "AABAACAADAABAAABAA";
        String pattern = "AABA";
        robinCarpSearch(pattern, text);
    }

    // Function to implement Robin Carp algorithm
    private static void robinCarpSearch(String pattern, String text) {
        int patternLength = pattern.length();
        int textLength = text.length();
        int patternHash = calculateHash(pattern, patternLength);
        int textHash = calculateHash(text, patternLength);

        for (int i = 0; i <= textLength - patternLength; i++) {

            // If hash is equal, then check for equal string
            if (patternHash == textHash) {
                int j;
                for (j = 0; j < patternLength; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j))
                        break;
                }
                if (j == patternLength) {
                    System.out.println("Pattern found at index: " + i);
                }
            }
            if (i < textLength - patternLength) {
                textHash = recalculateHash(text, i, i + patternLength, textHash, patternLength);
            }
        }
    }

    /*
     * Function to calculate hash value of a substring
     * abc = a * 101^0 + b * 101^1 + c * 101^2
     * */
    private static int calculateHash(String str, int length) {
        int hash = 0;
        for (int i = 0; i < length; i++) {
            hash += str.charAt(i) * Math.pow(PRIME, i);
        }
        return hash;
    }

    // Function to recalculate hash value for the next substring
    private static int recalculateHash(String str, int oldIndex, int newIndex, int oldHash, int patternLength) {
        int newHash = oldHash - str.charAt(oldIndex);
        newHash = newHash / PRIME;
        newHash += str.charAt(newIndex) * Math.pow(PRIME, patternLength - 1);
        return newHash;
    }
}
