  #include <stdio.h> 
  #include <time.h> 
  #include <mpi.h>
  main(int argc, char * argv[]) {
    clock_t tic = clock();
    int rank, size, a[10] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, b[10], search = 6, flag = 0;
    long int i;
    MPI_Init( & argc, & argv);
    MPI_Comm_rank(MPI_COMM_WORLD, & rank);
    MPI_Comm_size(MPI_COMM_WORLD, & size);
    MPI_Scatter( & a, 5, MPI_INT, & b, 5, MPI_INT, 0, MPI_COMM_WORLD);
    if (rank == 0) {
      for (i = 0; i < 5; i++) {
        if (b[i] == search) {
          printf("\nNumber found!\t\t%d\t\t%d", rank, i);
          flag = 1;
        }
        printf("\n%d\t\t%d", b[i], rank);
      }
    }
    if (rank == 1) {
      for (i = 0; i < 5; i++) {
        if (b[i] == search) {
          printf("\nNumber found!\t\t%d\t\t%d", rank, i);
          flag = 1;
        }
        printf("\n%d\t\t%d", b[i], rank);
      }
    }
    MPI_Finalize();
    clock_t toc = clock();
    printf("\n\nElapsed: %f seconds\n", (double)(toc - tic) / CLOCKS_PER_SEC);
  }