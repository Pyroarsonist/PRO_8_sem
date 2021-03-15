#include<stdio.h>
#include<mpi/mpi.h>

#define N 1000

#define MASTER_TO_SLAVE_TAG 1
#define SLAVE_TO_MASTER_TAG 2

void createMatrixes();

void printMatrixes();

int rank;
int size;
int i, j, k;
double mat_a[N][N];
double mat_b[N][N];
double mat_result[N][N];
double start_time;
double end_time;
int low_bound;
int upper_bound;
int portion;
MPI_Status status;
MPI_Request request;

int main(int argc, char *argv[]) {

    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);

    if (rank == 0) {
        createMatrixes();
        start_time = MPI_Wtime();
        for (i = 1; i < size; i++) {
            portion = (N / (size - 1));
            low_bound = (i - 1) * portion;
            if (((i + 1) == size) &&
                ((N % (size - 1)) != 0)) {
                upper_bound = N;
            } else {
                upper_bound = low_bound + portion;
            }
            MPI_Isend(&low_bound, 1, MPI_INT, i, MASTER_TO_SLAVE_TAG, MPI_COMM_WORLD, &request);
            MPI_Isend(&upper_bound, 1, MPI_INT, i, MASTER_TO_SLAVE_TAG + 1, MPI_COMM_WORLD, &request);
            MPI_Isend(&mat_a[low_bound][0], (upper_bound - low_bound) * N, MPI_DOUBLE, i,
                      MASTER_TO_SLAVE_TAG + 2, MPI_COMM_WORLD, &request);
        }
    }
    MPI_Bcast(&mat_b, N * N, MPI_DOUBLE, 0, MPI_COMM_WORLD);

    if (rank > 0) {
        MPI_Recv(&low_bound, 1, MPI_INT, 0, MASTER_TO_SLAVE_TAG, MPI_COMM_WORLD, &status);
        MPI_Recv(&upper_bound, 1, MPI_INT, 0, MASTER_TO_SLAVE_TAG + 1, MPI_COMM_WORLD, &status);
        MPI_Recv(&mat_a[low_bound][0], (upper_bound - low_bound) * N, MPI_DOUBLE, 0,
                 MASTER_TO_SLAVE_TAG + 2, MPI_COMM_WORLD, &status);
        for (i = low_bound; i < upper_bound; i++) {
            for (j = 0; j < N; j++) {
                for (k = 0; k < N; k++) {
                    mat_result[i][j] += (mat_a[i][k] * mat_b[k][j]);
                }
            }
        }
        MPI_Isend(&low_bound, 1, MPI_INT, 0, SLAVE_TO_MASTER_TAG, MPI_COMM_WORLD, &request);
        MPI_Isend(&upper_bound, 1, MPI_INT, 0, SLAVE_TO_MASTER_TAG + 1, MPI_COMM_WORLD, &request);
        MPI_Isend(&mat_result[low_bound][0], (upper_bound - low_bound) * N, MPI_DOUBLE, 0,
                  SLAVE_TO_MASTER_TAG + 2, MPI_COMM_WORLD, &request);
    }

    if (rank == 0) {
        for (i = 1; i < size; i++) {
            MPI_Recv(&low_bound, 1, MPI_INT, i, SLAVE_TO_MASTER_TAG, MPI_COMM_WORLD, &status);
            MPI_Recv(&upper_bound, 1, MPI_INT, i, SLAVE_TO_MASTER_TAG + 1, MPI_COMM_WORLD, &status);
            MPI_Recv(&mat_result[low_bound][0], (upper_bound - low_bound) * N, MPI_DOUBLE, i,
                     SLAVE_TO_MASTER_TAG + 2, MPI_COMM_WORLD, &status);
        }
        end_time = MPI_Wtime();
        printf("\nExecution time: %fs\n\n", end_time - start_time);
        printMatrixes();
    }
    MPI_Finalize();
    return 0;
}

void createMatrixes() {
    for (i = 0; i < N; i++) {
        for (j = 0; j < N; j++) {
            mat_a[i][j] = i + j;
        }
    }
    for (i = 0; i < N; i++) {
        for (j = 0; j < N; j++) {
            mat_b[i][j] = i * j;
        }
    }
}

void printMatrixes() {
    return;
    printf("Matrix A");
    for (i = 0; i < N; i++) {
        printf("\n");
        for (j = 0; j < N; j++)
            printf("%8.2f  ", mat_a[i][j]);
    }
    printf("\n\n\n");
    printf("Matrix B");
    for (i = 0; i < N; i++) {
        printf("\n");
        for (j = 0; j < N; j++)
            printf("%8.2f  ", mat_b[i][j]);
    }
    printf("\n\n\n");
    printf("Matrix C");
    for (i = 0; i < N; i++) {
        printf("\n");
        for (j = 0; j < N; j++)
            printf("%8.2f  ", mat_result[i][j]);
    }
    printf("\n\n");
}