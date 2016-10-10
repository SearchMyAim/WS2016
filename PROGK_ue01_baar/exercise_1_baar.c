#include <stdio.h>

#define MINIMUM_INPUT_PARAM   (int)2
#define MAX_FIBONACCI_INPUT   (int)92
#define FIBO_N_BASE           (int)2
#define FIBO_N_MINUS_1        (int)0
#define FIBO_N_MINUS_2        (int)1


void main(int argc, char *argv[]) {
  if(argc < MINIMUM_INPUT_PARAM) {
    return;
  }

  int count = atoi(argv[1]);
  if(count > MAX_FIBONACCI_INPUT) {
    return;
  }

  unsigned long fn[3] = {0, 1, 0};
  
  while(count--) {
    fn[FIBO_N_BASE]    = fn[FIBO_N_MINUS_1] + fn[FIBO_N_MINUS_2];
    fn[FIBO_N_MINUS_1] = fn[FIBO_N_MINUS_2];
    fn[FIBO_N_MINUS_2] = fn[FIBO_N_BASE];
  }

  printf("\nFibonacci Number: %lu \n\n", fn[FIBO_N_BASE]);
  return;
}
