def solution(a, b, c, d):
    answer = 0
    lists = [a,b,c,d]
    count = set(lists)
    
    if len(count) == 1:
        answer = list(count)[0] * 1111
    elif len(count) == 2:
        if lists.count(list(count)[0]) == 3:
            answer = pow(list(count)[0]*10 + list(count)[1],2)
        elif lists.count(list(count)[1]) == 3:
            answer = pow(list(count)[1]*10 + list(count)[0],2)
        else:
            answer = (list(count)[0]+list(count)[1]) * abs(list(count)[0]-list(count)[1])
    elif len(count) == 3:
        if lists.count(list(count)[0]) == 2:
            answer = list(count)[1]*list(count)[2]
        elif lists.count(list(count)[1]) == 2:
            answer = list(count)[0]*list(count)[2]
        elif lists.count(list(count)[2]) == 2:
            answer = list(count)[0]*list(count)[1]
    else:
        answer = min(a,b,c,d)
            
    return answer