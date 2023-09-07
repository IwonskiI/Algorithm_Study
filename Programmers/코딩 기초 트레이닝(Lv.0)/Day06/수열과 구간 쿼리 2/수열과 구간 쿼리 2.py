def solution(arr, queries):
    answer = []
    
    for i in range(len(queries)):
        minimum = 1000001
        for j in range(queries[i][0],queries[i][1]+1):
            if arr[j]>queries[i][2]:
                if arr[j]<minimum:
                    minimum = arr[j]
        if minimum == 1000001:
            minimum = -1
        answer.append(minimum)
    
    return answer

solution([0,1,2,3,4],[[0,4,2],[0,3,2],[0,2,2]])