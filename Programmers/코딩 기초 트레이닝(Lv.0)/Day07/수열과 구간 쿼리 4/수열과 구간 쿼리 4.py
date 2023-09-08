def solution(arr, queries):
    answer = arr
    
    for i in range(len(queries)):
        for j in range(queries[i][0],queries[i][1]+1):
            if j%queries[i][2] == 0:
                answer[j] += 1
    
    return answer