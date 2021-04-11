package com.financeiro

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FinanceiroApplication

fun main(args: Array<String>) {
	runApplication<FinanceiroApplication>(*args)
}
