public class CO3 {

    public static void main(String[] args) {

        int channels = 12000;
        int edges = 240000;

        // -------------------------------------------------
        // (i) Time Complexity
        // Union-Find with:
        // - Path Compression
        // - Union by Rank
        // Complexity: O(E * α(V))
        // α(V) = inverse Ackermann function (~constant)
        // -------------------------------------------------

        System.out.println("(i) Time Complexity:");
        System.out.println("O(E * α(V))");
        System.out.println("O(240000 * α(12000))");
        System.out.println("≈ Nearly Linear Time");

        // -------------------------------------------------
        // (ii) Number of find() Operations
        // Each union operation performs:
        // find(u) + find(v)
        // -------------------------------------------------

        int findOperations = edges * 2;

        System.out.println("\n(ii) find() Operations:");
        System.out.println("Total find() calls = " + findOperations);

        // With path compression,
        // average pointer hops are extremely small (~1 to 3)

        int avgPointerHops = 2;

        long totalPointerHops =
                (long) findOperations * avgPointerHops;

        System.out.println("Approx Total Pointer Hops = "
                + totalPointerHops);

        // -------------------------------------------------
        // (iii) Largest Component Extraction
        // -------------------------------------------------

        System.out.println("\n(iii) Largest Component Size:");

        System.out.println(
                "Maintain a size[] array for each root node."
        );

        System.out.println(
                "Whenever union(x, y) occurs, update the size "
                + "of the new root by adding both component sizes."
        );

        System.out.println(
                "Track a global maximum component size during "
                + "every union operation to get the largest "
                + "connected component efficiently in O(1) extra time."
        );
    }
}