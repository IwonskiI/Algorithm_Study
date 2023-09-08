def solution(number):
    answer = 0
    total = 0
    
    for i in range(len(number)):
        total += int(number[i])
    
    answer = total%9
    
    return answer