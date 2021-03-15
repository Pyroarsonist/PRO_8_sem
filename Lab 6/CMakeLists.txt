cmake_minimum_required(VERSION 3.17)
project(Lab_5 C)

set(CMAKE_C_STANDARD 11)

add_executable(Lab_5
        blocking_matrix_multiplication.c)
add_executable(Lab_5_1
        non-blocking_matrix_multiplication.c)