def solution(myString, pat):
    answer = 0
    ptlen = len(pat)
    
    for i in range(len(myString)):
        if myString[i] == pat[0]:
            if myString[i:i+ptlen] == pat:
                answer += 1
    
    return answer