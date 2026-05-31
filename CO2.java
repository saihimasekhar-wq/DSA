public class CO2 {

    public static void main(String[] args) {

        // Given values
        long totalRecords = 500_000_000L;

        int pageSize = 4096;          // 4 KB page
        int keySize = 64;             // bytes
        int payloadSize = 100;        // bytes
        int pointerSize = 8;          // bytes

        // -------------------------------------------------
        // (i) Leaf Fanout
        // Each leaf entry = key + payload
        // -------------------------------------------------

        int leafEntrySize = keySize + payloadSize;

        int leafFanout = pageSize / leafEntrySize;

        System.out.println("Leaf Entry Size = " + leafEntrySize + " bytes");
        System.out.println("Leaf Fanout = " + leafFanout + " entries/page");

        // -------------------------------------------------
        // (ii) Internal Node Fanout
        // Each internal entry = key + child pointer
        // -------------------------------------------------

        int internalEntrySize = keySize + pointerSize;

        int internalFanout = pageSize / internalEntrySize;

        System.out.println("\nInternal Entry Size = "
                + internalEntrySize + " bytes");

        System.out.println("Internal Node Fanout = "
                + internalFanout + " keys/page");

        // -------------------------------------------------
        // Number of leaf pages
        // -------------------------------------------------

        long leafPages = (long) Math.ceil(
                (double) totalRecords / leafFanout);

        System.out.println("\nLeaf Pages = " + leafPages);

        // -------------------------------------------------
        // (iii) B+ Tree Height Calculation
        // -------------------------------------------------

        int height = 1; // leaf level

        long nodes = leafPages;

        while (nodes > 1) {
            nodes = (long) Math.ceil(
                    (double) nodes / internalFanout);
            height++;
        }

        System.out.println("B+ Tree Height = " + height);

        // -------------------------------------------------
        // Point Lookup I/O Cost
        // Top 3 levels cached
        // -------------------------------------------------

        int ioCost = Math.max(height - 3, 1);

        System.out.println("\nPoint Lookup I/O Cost = "
                + ioCost + " page reads");
    }
}