def solution(arr, k):
    answer = []
    
    for i in range(len(arr)):
        if arr[i] in answer:
            pass
        else:
            answer.append(arr[i])
            if len(answer)>=k:
                break
    
    for i in range(len(answer),k):
        answer.append(-1)
    
    return answer