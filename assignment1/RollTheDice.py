import random
def rollDice(sides):
    number=random.randint(1, sides)
    return number

def main():
    sides=6
    rolling=True
    while rolling:
        userInput=input("Ready to roll? Enter Q to Quit")
        if userInput.lower() !="q":
            rolledNumber=rollDice(sides)
            print("You have rolled a",rolledNumber)
        else:
            rolling=False
