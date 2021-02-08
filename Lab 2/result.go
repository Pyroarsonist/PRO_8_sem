package main

import "fmt"

type Result struct {
	data [][]int
}

func NewResult(data [][]int) *Result {
	return &Result{data: data}
}

func (r Result) printMatrix() {
	fmt.Println(r.data)
}
