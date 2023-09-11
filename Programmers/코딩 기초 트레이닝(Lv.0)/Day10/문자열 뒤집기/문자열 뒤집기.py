def solution(my_string, s, e):
    answer = ''
    temp1 = ''
    temp2 = ''
    
    for i in range(s):
        answer += my_string[i]
    for i in range(s,e+1):
        temp1 += my_string[i]
    for i in range(e+1,len(my_string)):
        temp2 += my_string[i]
        
    temp1 = list(temp1)
    temp1.reverse()
    temp1 = ''.join(temp1)
    
    
    answer += temp1
    answer += temp2
    
    return answer