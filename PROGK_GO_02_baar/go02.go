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

func producer(c chan int) {
	tmp := 1
	for {
		c <- tmp
		tmp += 1
	}
}

func consumer(c chan int, val int) string {
	res := ""
	for i := 0; i < val; i++ {
		time.Sleep(time.Second)
		tmp := <- c
		res += strconv.Itoa(tmp)
	}
	return res
}

func myalgo(input string) string { // input string e.g. "7"
	val, _ := strconv.Atoi(input)

	ch := make(chan int, 3)

	go producer(ch)
	return consumer(ch, val) // return string e.g.: "1234567"
}
