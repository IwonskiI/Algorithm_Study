def solution(myString):
    answer = []
    
    myString = myString.split('x')
    
    myString = (' ').join(myString).split()
    
    myString.sort()
    
    answer = myString
    
    return answer