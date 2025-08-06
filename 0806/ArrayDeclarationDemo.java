public class ArrayDeclarationDemo {

    public static void main(String[] args) {

        // Method 1: Declare first, then allocate memory
        int[] scores; // Declare an integer array reference variable
        scores = new int[5]; // Allocate space for 5 integers in heap memory

        // Method 2: Declare and allocate memory at the same time
        int[] grades = new int[5]; // All elements are automatically initialized to 0

        // Method 3: Static initialization, providing initial values directly
        int[] numbers = {85, 92, 77, 60, 88}; // The compiler automatically calculates the length

        // Method 4: Another way to write dynamic initialization
        int[] values = new int[] {10, 20, 30, 40, 50};

        System.out.println("Length of each array:");
        System.out.println("scores: " + scores.length);
        System.out.println("grades: " + grades.length);
        System.out.println("numbers: " + numbers.length);
        System.out.println("values: " + values.length);
    }
}
