//Author: Chittampally Vasanth Raja
//The following program is parallized version of search algorithm
#include<stdio.h>
#include<omp.h>
#define MAXTHREADS 10
#define ARRAYSIZE 44
int main(void)
{
    int a[]={3,5,1,2,34,67,90,43,53,3,4,26,34,35,54,67,87,21,34,56,33,45,12,34,5,6,7,8,123,45,32,455,666,444,333,222,11,22,44,55,333,222},i,j,found=0,key=222;
    double start_time,run_time;
    for(j=1;j<=5;j++)
    {
        omp_set_num_threads(j);
        found=0;
        start_time = omp_get_wtime();
        #pragma omp parallel private(i)
        { 
            int start,noofsteps;
            #pragma omp single
            printf("num of threads in action: %d\n",j); 
            if(found==0)
            {
                start=(omp_get_thread_num())*(ARRAYSIZE/omp_get_num_threads());
                noofsteps=start+(ARRAYSIZE/omp_get_num_threads());
                if(ARRAYSIZE%j!=0)
                    noofsteps+=(ARRAYSIZE%j);
                for(i=start;i<noofsteps;i++)             
                    if(key==a[i]) {
                        printf("Key has been found in %d thread at %d position\n",omp_get_thread_num(),i+1);
                        found=1;
                        break;
                    } 
            }
        }
        run_time = omp_get_wtime() - start_time;
        printf("\n %f seconds %d threds \n ",run_time,j);
    }
    return 0;
}