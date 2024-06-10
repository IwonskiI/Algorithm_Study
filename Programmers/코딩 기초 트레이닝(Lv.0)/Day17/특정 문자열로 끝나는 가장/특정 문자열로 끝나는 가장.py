def solution(myString, pat):
    answer = ''
    ptlen = len(pat)
    
    for i in range(len(myString)-1,0,-1):
        if pat[-1] == myString[i]:
            if myString[i-ptlen+1:i+1] == pat:
                answer = myString[:i+1]
                break
    
    return answer