package main

// Baar Alexander

import (
	"bufio" // input = we read (buffered) text from stdin
	"fmt"
	"os"
	"strconv"
	"strings"
	"time"
)

func main() {
	res := ""
	scanner := bufio.NewReader(os.Stdin)         // read from STDIN
	input, _ := scanner.ReadString('\n')         // read FIRST line
	input = strings.Replace(input, "\n", "", -1) // remove line break

	res = myalgo(input) // caluclate the result

	fmt.Printf("RESULT: '%v'", res) // return the result
}

func simdl(c chan int) {
	time.Sleep(time.Second * 2)
	c <- 7
}

func myalgo(input string) string { // input string
	val, _ := strconv.Atoi(input)

	channel := make(chan int)
	for i := 0; i < val;  i++ {
		go simdl(channel)
	}

	res := 0
	for i := 0; i < val; i++ {
		tmp := <- channel
		res += tmp
	}
	val = res
	return strconv.Itoa(val) // return string
}