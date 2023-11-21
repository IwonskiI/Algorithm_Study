def solution(arr, delete_list):
    answer = []
    
    for i in arr:
        if i in delete_list:
            pass
        else:
            answer.append(i)
    
    return answer