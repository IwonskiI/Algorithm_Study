def solution(n):
    answer = 0
    
    if n %2 == 0:
        for i in range((n+1)//2+1):
            answer += (2*i)*(i*2)
        
    else:
        for i in range((n+1)//2):
            answer += 2*i+1
            
    
    return answer