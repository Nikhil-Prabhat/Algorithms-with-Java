public class Main {
    public static void main(String[] args) {

        int bitSet = 0;

        // Store numbers
        bitSet |= (1 << 3);
        bitSet |= (1 << 5);
        bitSet |= (1 << 7);

        int number = 5;

        if ((bitSet & (1 << number)) != 0) {
            System.out.println(number + " exists");
        } else {
            System.out.println(number + " doesn't exist");
        }
    }
}

public class Main {

    public static void add(int[] bitSet, int number) {
        int bucket = number / 32;
        int bit = number % 32;

        bitSet[bucket] |= (1 << bit);
    }

    public static boolean contains(int[] bitSet, int number) {
        int bucket = number / 32;
        int bit = number % 32;

        return (bitSet[bucket] & (1 << bit)) != 0;
    }

    public static void main(String[] args) {

        int[] bitSet = new int[1000 / 32 + 1];

        add(bitSet, 70);
        add(bitSet, 450);
        add(bitSet, 999);

        System.out.println(contains(bitSet, 70));   // true
        System.out.println(contains(bitSet, 71));   // false
        System.out.println(contains(bitSet, 999));  // true
    }
}

import java.util.BitSet;

public class Main {
    public static void main(String[] args) {
        BitSet bitSet = new BitSet();

        bitSet.set(70);
        bitSet.set(450);
        bitSet.set(999);

        System.out.println(bitSet.get(70));   // true
        System.out.println(bitSet.get(71));   // false
        System.out.println(bitSet.get(999));  // true
    }
}

public boolean contains(BitSet bitSet, int number) {

    if (number < 0 || number >= LIMIT)
        return false;

    return bitSet.get(number);
}

BitSet bitSet = new BitSet();

add(bitSet, 10);
add(bitSet, 500);

System.out.println(contains(bitSet, 10));    // true
System.out.println(contains(bitSet, 700));   // false
System.out.println(contains(bitSet, 5000));  // false

/*
Suppose you only need to store whether the numbers 0 to 31 are present.
An int has 32 bits, so each bit can represent one number.

Bit position:
31 ... 5 4 3 2 1 0
Number:
31 ... 5 4 3 2 1 0

If bit 5 is 1, it means number 5 exists.
If bit 5 is 0, it means number 5 doesn't exist.

Initially : 
int bitSet = 0; binary : 00000000 00000000 00000000 00000000

Store no 3
1 << 3 , binary : 00000000 00000000 00000000 00001000
now or with bitset
bitSet |= (1 << 3); , binary : 00000000 00000000 00000000 00001000
now 3 exists.

// same process for 5 as well.

Check if no exists
(bitSet & (1 << 5)) ,

00101000
00100000
--------
00100000

Since the result is not zero, number 5 exists.

-------------------------------------------------------------------

For large numbers
Suppose you want to store numbers from 0 to 999.
Since one int stores 32 bits:

1000 numbers
1000 / 32 = 31.25
Need 32 integers.

int[] bitSet = new int[32];
bitSet[0] → numbers 0 - 31
bitSet[1] → numbers 32 - 63
bitSet[2] → numbers 64 - 95
...
bitSet[31] → numbers 992 - 1023

First determine which integer contains 70. : 70 / 32 = 2
now it belongs to bitset[2] : bitSet[2]

Now determine which bit inside bitset[2] : 70 % 32 = 6
set that very bit :bitSet[2] |= (1 << 6);

Final check :(bitSet[bucket] & (1 << bit)) != 0



*/
