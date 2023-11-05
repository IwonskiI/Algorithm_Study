def solution(myString):
    answer = ''
    
    myString = myString.lower()
    
    for i in range(len(myString)):
        if ord(myString[i]) == 97:
            answer += 'A'
        else:
            answer += myString[i]
    
    return answer