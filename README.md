**# Java Linear Algebra Engine

Zero-dependency, pure Java implementation of fundamental linear algebra and matrix operations. Built from scratch using standard Java SE without external mathematical libraries to demonstrate algorithmic matrix manipulation, recursive cofactor expansion, and algebraic property verification.

## Features

* **Matrix Arithmetic:** Matrix addition (`add`), subtraction (`subtract`), scalar multiplication (`scalarMultiply`), and $O(n^3)$ matrix multiplication (`multiply`) with dimension validation.
* **Structural & Power Operations:** Iterative matrix exponentiation (`power`), matrix transpose (`transpose`), identity matrix generation (`identity`), and main diagonal trace calculation (`trace`).
* **Recursive $N \times N$ Determinant:** General-purpose determinant solver (`determinant`) using recursive cofactor expansion along the first row.
* **Special Matrix Classification:** Automated verification methods for **Involutory** ($A^2 = I$) and **Idempotent** ($A^2 = A$) matrices.

## Project Structure

```text
java-linear-algebra-engine/
├── src/
│   └── MatrixEngine.java
└── README.md**