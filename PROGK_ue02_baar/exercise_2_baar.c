#include <stdio.h>
#include <stdlib.h>

#include "defines.h"
#include "ean.h"

int main(int argc, char *argv[]) {
    /* Check input parameters */
    if(argc != INPUT_ARGUMENT_COUNT) {        
        printf("\nError: ");
        if(argc < INPUT_ARGUMENT_COUNT) {
            printf("Too less");
        }
        else {
            printf("Too many");
        }
        printf(" input parameters! \n");
        return STATUS_ERR;
    }

    /* Convert input paremeters to integer */
    int digits[7];
    int inLen = argc - 1; //first parameter is the execution path
    for(int i = 0; i < inLen; i++) {
        digits[i] = atoi(argv[i + 1]);
    }

    /* Calculate the Check Digit */
    int checkDigit = 0;
    int retVal = calcCheckDigit(inLen, digits, &checkDigit);
    if(retVal != STATUS_OK) {
        printf("Error: Could not calculate the Check-Digit!");
        return retVal;
    }

    printf("\nThe calculated check digit is: '%d'.\n", checkDigit);
    return STATUS_OK;
}