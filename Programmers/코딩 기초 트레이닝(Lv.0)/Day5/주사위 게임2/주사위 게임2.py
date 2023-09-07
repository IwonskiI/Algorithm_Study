def solution(a, b, c):
    answer = 0
    lst = [a,b,c]
    sset = set(lst)
    
    if len(sset) == 1:
        answer = (a+b+c)*(pow(a,2)+pow(b,2)+pow(c,2))*(pow(a,3)+pow(b,3)+pow(c,3))
    elif len(sset) == 2:
        answer = (a+b+c)*(pow(a,2)+pow(b,2)+pow(c,2))
    else:
        answer = a+b+c
    
    return answer