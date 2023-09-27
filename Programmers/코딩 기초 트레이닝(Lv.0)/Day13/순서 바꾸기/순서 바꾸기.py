def solution(num_list, n):
    answer = []
    
    for i in range(n,len(num_list)):
        answer.append(num_list[i])
    for i in range(n):
        answer.append(num_list[i])
    
    return answer