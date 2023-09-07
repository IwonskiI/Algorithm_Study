def solution(num_list):
    answer = 0
    summ = 0
    mult = 1
    
    for i in range(len(num_list)):
        summ += num_list[i]
        mult *= num_list[i]
    
    if summ*summ > mult:
        answer = 1
    
    return answer