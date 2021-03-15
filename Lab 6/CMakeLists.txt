cmake_minimum_required(VERSION 3.17)
project(Lab_6 C)

set(CMAKE_C_STANDARD 11)

add_executable(Lab_6
        blocking_matrix_multiplication.c)
add_executable(Lab_6_1
        non-blocking_matrix_multiplication.c)