def calculateArmstrongNumber(number):
    # Initializing Sum and Number of Digits
    sumOfDigits = 0
    numberOfDigits = 0

    # Calculating Number of individual digits
    tempNumber = number
    while tempNumber > 0:
        numberOfDigits = numberOfDigits + 1
        tempNumber = tempNumber // 10

    # Finding Armstrong Number
    tempNumber = number
    for n in range(1, tempNumber + 1):
        lastDigit = tempNumber % 10
        sumOfDigits = sumOfDigits + (lastDigit ** numberOfDigits)
        tempNumber //= 10
    return sumOfDigits


# End of Function

# User Input
userInput = int(input("\nPlease Enter the Number to Check for Armstrong: "))

if userInput == calculateArmstrongNumber(userInput):
    print("\n %d is Armstrong Number.\n" % userInput)
else:
    print("\n %d is Not a Armstrong Number.\n" % userInput)