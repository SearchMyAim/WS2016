#ifndef EAN_H
#define EAN_H

/**
 * Calculates EAN-8 Check Digit.
 * @param length integer length of the values array
 * @param values contain the digits which shall be used for calculation
 * @param digit pointer to the variable the result will be return in
 * @return <b>STATUS_OK</b> if no error occured and the check digit could be calculated.
 *         <b>STATUS_ERR</b> if the digit could not be calculated.
 *         Value of parameter 'digit' is invalid.
 */
int calcCheckDigit(int length, int values[], int *digit);

#endif //EAN_H