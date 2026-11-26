/**
 * Pure Java Linear Algebra & Matrix Operations Engine
 * Harici kütüphane kullanmadan N x N matris aritmetiği, kofaktör determinantı,
 * transpoz, matris izi (trace) ve özel matris analizleri sunar.
 */
public class MatrixEngine {

    // İki matrisin çarpımı (A * B)
    public static int[][] multiply(int[][] A, int[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int rowsB = B.length;
        int colsB = B[0].length;

        if (colsA != rowsB) {
            throw new IllegalArgumentException("Matris boyutlari carpim icin uyumsuz!");
        }

        int[][] result = new int[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }

    // Matris toplama (A + B)
    public static int[][] add(int[][] A, int[][] B) {
        if (A.length != B.length || A[0].length != B[0].length) {
            throw new IllegalArgumentException("Matris boyutlari toplama icin esit olmali!");
        }
        int n = A.length;
        int m = A[0].length;
        int[][] result = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }

    // Matris çıkarma (A - B)
    public static int[][] subtract(int[][] A, int[][] B) {
        if (A.length != B.length || A[0].length != B[0].length) {
            throw new IllegalArgumentException("Matris boyutlari cikarma icin esit olmali!");
        }
        int n = A.length;
        int m = A[0].length;
        int[][] result = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = A[i][j] - B[i][j];
            }
        }
        return result;
    }

    // Skaler çarpım (k * A)
    public static int[][] scalarMultiply(int scalar, int[][] A) {
        int n = A.length;
        int m = A[0].length;
        int[][] result = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = scalar * A[i][j];
            }
        }
        return result;
    }

    // n x n Birim Matris (I_n)
    public static int[][] identity(int n) {
        int[][] I = new int[n][n];
        for (int i = 0; i < n; i++) {
            I[i][i] = 1;
        }
        return I;
    }

    // Kare matrisin üssünü alma (A^exp)
    public static int[][] power(int[][] A, int exp) {
        if (A.length != A[0].length) {
            throw new IllegalArgumentException("Us alma islemi yalnizca kare matrislerde yapilir!");
        }
        int n = A.length;
        int[][] result = identity(n);
        for (int i = 0; i < exp; i++) {
            result = multiply(result, A);
        }
        return result;
    }

    // Transpoz alma (A^T)
    public static int[][] transpose(int[][] A) {
        int rows = A.length;
        int cols = A[0].length;
        int[][] T = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                T[j][i] = A[i][j];
            }
        }
        return T;
    }

    // Matris İzi (Trace - Köşegen toplamı)
    public static int trace(int[][] A) {
        if (A.length != A[0].length) {
            throw new IllegalArgumentException("Trace yalnizca kare matrisler icin tanimlidir!");
        }
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i][i];
        }
        return sum;
    }

    // N x N Rekürsif Kofaktör Determinant Hesabı
    public static int determinant(int[][] M) {
        if (M.length != M[0].length) {
            throw new IllegalArgumentException("Determinant yalnizca kare matrisler icin hesaplanir!");
        }
        int n = M.length;
        if (n == 1) return M[0][0];
        if (n == 2) return (M[0][0] * M[1][1]) - (M[0][1] * M[1][0]);

        int det = 0;
        for (int col = 0; col < n; col++) {
            int sign = (col % 2 == 0) ? 1 : -1;
            det += sign * M[0][col] * determinant(subMatrix(M, 0, col));
        }
        return det;
    }

    private static int[][] subMatrix(int[][] M, int excludeRow, int excludeCol) {
        int n = M.length;
        int[][] sub = new int[n - 1][n - 1];
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (i == excludeRow) continue;
            int c = 0;
            for (int j = 0; j < n; j++) {
                if (j == excludeCol) continue;
                sub[r][c++] = M[i][j];
            }
            r++;
        }
        return sub;
    }

    // İki matrisin eşitlik kontrolü
    public static boolean isEqual(int[][] A, int[][] B) {
        if (A.length != B.length || A[0].length != B[0].length) return false;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] != B[i][j]) return false;
            }
        }
        return true;
    }

    // İnvolütif Matris Kontrolü (A^2 = I)
    public static boolean isInvolutory(int[][] A) {
        if (A.length != A[0].length) return false;
        return isEqual(multiply(A, A), identity(A.length));
    }

    // İdempotent Matris Kontrolü (A^2 = A)
    public static boolean isIdempotent(int[][] A) {
        if (A.length != A[0].length) return false;
        return isEqual(multiply(A, A), A);
    }

    // Matrisi konsola düzenli yazdırma
    public static void printMatrix(String label, int[][] M) {
        System.out.println(label);
        for (int[] row : M) {
            System.out.print("  [ ");
            for (int val : row) {
                System.out.printf("%5d ", val);
            }
            System.out.println("]");
        }
    }

    // Kütüphane yeteneklerini gösteren demo
    public static void main(String[] args) {
        int[][] sample = {
                {2, -3, 1},
                {2,  0, -1},
                {1,  4, 5}
        };

        int[][] involutorySample = {
                {3,  2},
                {-4, -3}
        };

        int[][] idempotentSample = {
                {2, -2},
                {1, -1}
        };

        printMatrix("Sample 3x3 Matrix:", sample);
        System.out.println("Determinant : " + determinant(sample));
        System.out.println("Trace       : " + trace(sample));
        printMatrix("Transpose:", transpose(sample));

        System.out.println("\nSpecial Matrix Checks:");
        System.out.println("Is involutorySample involutory? : " + isInvolutory(involutorySample));
        System.out.println("Is idempotentSample idempotent? : " + isIdempotent(idempotentSample));
    }
}