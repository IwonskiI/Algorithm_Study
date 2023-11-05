def solution(arr, n):
    answer = arr
    
    if len(arr) % 2 == 0:
        for i in range(1,len(arr),2):
            answer[i] += n
    else:
        for i in range(0,len(arr),2):
            answer[i] += n
    
    return answer