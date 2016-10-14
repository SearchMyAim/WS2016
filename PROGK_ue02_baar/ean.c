#include "ean.h"
#include "defines.h"
#include <stdio.h>

int calcCheckDigit(int length, int values[], int *digit) {
    if(length != INPUT_DIGIT_COUNT) {
        return STATUS_ERR;
    }

#ifdef DEBUG
    printf("\nInput \t-> Sum");
#endif
    int sum = 0;
    for(int i = 0; i < length; i++) {
        if(i % 2) {
            sum += values[i];
        }
        else {
            sum += values[i] * 3;
        }

#ifdef DEBUG
        printf("\n %d \t-> %d", values[i], sum);
#endif        
    }

    sum = 10 - (sum % 10);
    *digit = (sum == 10) ? 0 : sum;

#ifdef DEBUG
    printf("\nCheck Digit: %d \n", *digit);
#endif
    return STATUS_OK;
}