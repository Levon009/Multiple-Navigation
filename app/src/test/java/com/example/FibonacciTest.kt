package com.example

import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class FibonacciTest {

    @Before
    fun setUp() {

    }

    private fun fibonacciUsingRecursion(num: Int) : Int {
        return if (num <= 1) {
            num
        } else {
            fibonacciUsingRecursion(num - 1) + fibonacciUsingRecursion(num - 2)
        }
    }

    @Test
    fun `Test check if fibonacci using recursion return correct value`() {
        assertThat(fibonacciUsingRecursion(8)).isEqualTo(21)
    }
}