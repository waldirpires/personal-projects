#include <stdio.h> 
#include <time.h> 
#include <math.h> 

void clock2(){
    float a; 
    clock_t time_req; 
  
    // Without using pow function 
    time_req = clock(); 
    for(int i=0; i<200000; i++) 
    { 
        a = log(i*i*i*i); 
    } 
    time_req = clock()- time_req;
    printf("Processor time taken for multiplication: %f seconds\n\n",(float)time_req/CLOCKS_PER_SEC); 
  
    // Using pow function 
    time_req = clock(); 
    for(int i=0; i<200000; i++) 
    { 
        a = log(pow(i, 4)); 
    } 
    time_req = clock() - time_req; 
    printf("Processor time taken in pow function: %f seconds\n\n", (float)time_req/CLOCKS_PER_SEC); 
    
}

void currentTime(){
    time_t s, val = 1; 
    struct tm* current_time; 
  
    // time in seconds 
    s = time(NULL);   
// to get current time 
    current_time = localtime(&s); 
  
    // print time in minutes, 
    // hours and seconds 
    printf("Current Time: %02d:%02d:%02d\n\n", 
           current_time->tm_hour, 
           current_time->tm_min, 
           current_time->tm_sec);   

    time(&s); 
      
    printf("Current time = %s\n\n", ctime(&s));     

    printf("Year: %d\n", current_time->tm_year);       
    printf("Month: %d\n", current_time->tm_mon);       
    printf("Day (Month): %d\n", current_time->tm_mday);   
    printf("Day (Week): %d\n", current_time->tm_wday);   
    printf("Day (Year): %d\n", current_time->tm_yday);   
    printf("Timezone: %s\n", current_time->tm_zone);   

    printf("\n\n");    
}

void delay(int number_of_seconds) 
{ 
    // Converting time into milli_seconds 
    int milli_seconds = 1000 * number_of_seconds; 
  
    // Stroing start time 
    clock_t start_time = clock(); 
  
    // looping till required time is not acheived 
    while (clock() < start_time + milli_seconds) 
        ; 
} 

// A function that terminates when enter key is pressed 
void fun() 
{ 
    printf("fun() starts \n"); 
    printf("Press enter to stop fun \n"); 
    while(1) 
    { 
        if (getchar()) 
            break; 
    } 
    printf("fun() ends \n"); 
} 

void time1(){
      time_t seconds; 
    clock_t start, end;
    double cpu_time_used;
      
    seconds = time(NULL); 
    printf("Seconds since January 1, 1970 = %ld\n", seconds); 

     // Stores time seconds 
    time(&seconds); 
    printf("Seconds since January 1, 1970 = %ld\n", seconds); 

}

void time2(){
      time_t seconds; 
    clock_t start, end;
    double cpu_time_used;

      start = clock();
    fun();
    end = clock();
    cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
        printf("%f seconds have passed\n\n", cpu_time_used); 

}

void delay1(){
    int i; 
    for (i = 0; i < 10; i++) { 
        // delay of one second 
        delay(1); 
        printf("%d seconds have passed\n", i + 1); 
    } 
    printf("\n");

}

int main () 
{ 

    time1();

    time2();

    delay1();

currentTime();

clock2();

    return(0); 
} 