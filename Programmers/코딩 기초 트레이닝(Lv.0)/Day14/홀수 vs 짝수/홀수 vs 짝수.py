def solution(num_list):
    answer = 0
    odd = 0
    even = 0
    
    for i in range(len(num_list)//2):
        even += num_list[(i*2)+1]
        odd += num_list[(i*2)]
    if len(num_list)%2 != 0:
        odd += num_list[-1]
        
    answer = max(odd, even)
    return answer