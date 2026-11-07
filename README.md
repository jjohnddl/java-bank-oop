# Java Bank Account System

A console-based banking application built in Java, demonstrating core
object-oriented programming principles: interfaces, abstract classes,
inheritance, and polymorphism.

## Features

- Create savings or checking accounts, each with its own interest rate
- Deposit and withdraw funds with balance validation
- Look up accounts by account number
- Input validation with try/catch error handling

## Project Structure

- `Transactable` — interface defining deposit and withdraw behavior
- `Account` — abstract base class holding shared account logic
- `SavingAccount` / `CheckingAccount` — subclasses with their own interest rates
- `Main` — menu-driven console interface

## How to Run

1. Clone the repo
2. Compile and run `Main.java`

## Status

Actively building. Planned: SQLite persistence, transfers between
accounts, transaction history, interest application.