def solution(strArr):
    answer = 0
    tmp = {}
    
    for i in strArr:
        if len(i) in tmp:
            tmp[len(i)] += 1
        else:
            tmp[len(i)] = 1
    
    answer = max(tmp.values())
        
    return answer