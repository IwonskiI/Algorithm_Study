def solution(numLog):
    answer = ''
    
    for i in range(1,len(numLog)):
        key = numLog[i]-numLog[i-1]
        if key == 1:
            answer += 'w'
        elif key == -1:
            answer += 's'
        elif key == 10:
            answer += 'd'
        elif key == -10:
            answer += 'a'
    
    return answer