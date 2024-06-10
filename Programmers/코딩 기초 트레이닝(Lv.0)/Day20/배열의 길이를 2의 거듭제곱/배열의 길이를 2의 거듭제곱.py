def solution(arr):
    answer = arr
    i = 1
    
    while(1):
        if len(answer) > i:
            i = i * 2
        elif len(answer) < i:
            if len(answer) < i:
                for _ in range(i - len(answer)):
                    answer.append(0)
        else:
            break
    
    return answer