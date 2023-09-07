def solution(a, b):
    answer = 0
    new1 = int(str(a)+str(b))
    new2 = int(str(b)+str(a))
    
    answer = max(new1,new2)
    
    
    return answer