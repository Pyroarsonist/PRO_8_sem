#include "mpi/mpi.h"
#include <stdio.h>
#include <stdlib.h>

#define N 1000
#define MASTER 0 /* taskid of first task */
#define FROM_MASTER 1 /* setting a message type */
#define FROM_WORKER 2 /* setting a message type */

void createMatrixes(); //makes the [A] and [B] matrixes
void printMatrixes(); //print the content of output matrix [C];
int numtasks,
        taskid,
        numworkers,
        source,
        dest,
        rows, /* rows of matrix A sent to each worker */
averow, extra, offset,
        i, j, k, rc;
double a[N][N], /* matrix A to be multiplied */
b[N][N], /* matrix B to be multiplied */
c[N][N], start_time, end_time; /* result matrix C */

int main(int argc, char *argv[]) {

    MPI_Status status;
    MPI_Init(&argc, &argv);
    MPI_Comm_size(MPI_COMM_WORLD, &numtasks);
    MPI_Comm_rank(MPI_COMM_WORLD, &taskid);
    if (numtasks < 2) {
        printf("Need at least two MPI tasks. Quitting...\n");
        MPI_Abort(MPI_COMM_WORLD, rc);
        exit(1);
    }
    numworkers = numtasks - 1;
    if (taskid == MASTER) {
        printf("mpi_mm has started with %d tasks.\n", numtasks);
        createMatrixes();
        start_time = MPI_Wtime();

        averow = N / numworkers;
        extra = N % numworkers;
        offset = 0;
        for (dest = 1; dest <= numworkers; dest++) {
            rows = (dest <= extra) ? averow + 1 : averow;
            printf("Sending %d rows to task %d offset=                   %d\n", rows, dest, offset
            );
            MPI_Send(&offset, 1, MPI_INT, dest, FROM_MASTER,
                     MPI_COMM_WORLD);
            MPI_Send(&rows, 1, MPI_INT, dest, FROM_MASTER,
                     MPI_COMM_WORLD);
            MPI_Send(&a[offset][0], rows * N, MPI_DOUBLE, dest,
                     FROM_MASTER, MPI_COMM_WORLD);
            MPI_Send(&b, N * N, MPI_DOUBLE, dest, FROM_MASTER,
                     MPI_COMM_WORLD);
            offset = offset + rows;
        }
/* Receive results from worker tasks */
        for (source = 1; source <= numworkers; source++) {
            MPI_Recv(&offset, 1, MPI_INT, source, FROM_WORKER,
                     MPI_COMM_WORLD,
                     &status);
            MPI_Recv(&rows, 1, MPI_INT, source, FROM_WORKER,
                     MPI_COMM_WORLD,
                     &status);
            MPI_Recv(&c[offset][0], rows * N, MPI_DOUBLE,
                     source,
                     FROM_WORKER, MPI_COMM_WORLD,
                     &status);
            printf("Received results from task %d\n", taskid);
        }
        end_time = MPI_Wtime();
        printf("\nExecution time: %fs\n\n", end_time - start_time);
        printMatrixes();
        printf("Done.\n");
    }
/******** worker task *****************/
    else { /* if (taskid > MASTER) */
        MPI_Recv(&offset, 1, MPI_INT, MASTER, FROM_MASTER,
                 MPI_COMM_WORLD,
                 &status);
        MPI_Recv(&rows, 1, MPI_INT, MASTER, FROM_MASTER,
                 MPI_COMM_WORLD, &status);
        MPI_Recv(&a, rows * N, MPI_DOUBLE, MASTER, FROM_MASTER,
                 MPI_COMM_WORLD,
                 &status);
        MPI_Recv(&b, N * N, MPI_DOUBLE, MASTER, FROM_MASTER,
                 MPI_COMM_WORLD,
                 &status);
        for (k = 0; k < N; k++)
            for (i = 0; i < rows; i++) {
                c[i][k] = 0.0;
                for (j = 0; j < N; j++)
                    c[i][k] = c[i][k] + a[i][j] * b[j][k];
            }
        MPI_Send(&offset, 1, MPI_INT, MASTER, FROM_WORKER,
                 MPI_COMM_WORLD);
        MPI_Send(&rows, 1, MPI_INT, MASTER, FROM_WORKER,
                 MPI_COMM_WORLD);
        MPI_Send(&c, rows * N, MPI_DOUBLE, MASTER,
                 FROM_WORKER, MPI_COMM_WORLD);
    }
    MPI_Finalize();
}

void createMatrixes() {
    for (i = 0; i < N; i++) {
        for (j = 0; j < N; j++) {
            a[i][j] = i + j;
        }
    }
    for (i = 0; i < N; i++) {
        for (j = 0; j < N; j++) {
            b[i][j] = i * j;
        }
    }
}

void printMatrixes() {
    return;
    printf("Matrix A");
    for (i = 0; i < N; i++) {
        printf("\n");
        for (j = 0; j < N; j++)
            printf("%8.2f  ", a[i][j]);
    }
    printf("\n\n\n");
    printf("Matrix B");
    for (i = 0; i < N; i++) {
        printf("\n");
        for (j = 0; j < N; j++)
            printf("%8.2f  ", b[i][j]);
    }
    printf("\n\n\n");
    printf("Matrix C");
    for (i = 0; i < N; i++) {
        printf("\n");
        for (j = 0; j < N; j++)
            printf("%8.2f  ", c[i][j]);
    }
    printf("\n\n");
}