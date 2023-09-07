def solution(a, d, included):
    answer = 0
    total = a
    
    for i in range(len(included)):
        if included[i] == True:
            answer += total
        total += d
    
    return answer