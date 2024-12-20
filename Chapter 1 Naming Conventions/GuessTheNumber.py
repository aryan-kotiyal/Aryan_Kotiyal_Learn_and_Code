import random
def isValidGuess(userGuess):
    if userGuess.isdigit() and 1<= int(userGuess) <=100:
        return True
    else:
        return False

def main():
    numberToGuess=random.randint(1,100)
    isCorrectGuess=False
    userGuess=input("Guess a number between 1 and 100:")
    guessCount=0
    while not isCorrectGuess:
        if not isValidGuess(userGuess):
            userGuess=input("I wont count this one Please enter a number between 1 to 100")
            continue
        else:
            guessCount+=1
            userGuess=int(userGuess)

        if userGuess<numberToGuess:
            userGuess=input("Too low. Guess again")
        elif userGuess>numberToGuess:
            userGuess=input("Too High. Guess again")
        else:
            print("You guessed it in",guessCount,"guesses!")
            isCorrectGuess=True

