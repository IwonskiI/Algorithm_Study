def solution(l, r):
    answer = []
    
    for i in range(l,r+1):
        num = set(str(i))
        if (len(num)==2 and '5' in num and '0' in num) or (len(num)==1 and ('5' in num or '0' in num)):
            answer.append(i)
    if answer == []:
        answer = [-1]
    return answer