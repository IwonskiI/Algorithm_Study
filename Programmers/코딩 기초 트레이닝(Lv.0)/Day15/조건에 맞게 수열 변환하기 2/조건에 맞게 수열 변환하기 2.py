def solution(arr):
    answer = 0
    tmp = []
    
    while arr != tmp:
        tmp = arr.copy()
        for i in range(len(arr)):
            if arr[i] >= 50 and arr[i] % 2 == 0:
                arr[i] = arr[i]//2
            elif arr[i] < 50 and arr[i] % 2 != 0:
                arr[i] = arr[i]*2 + 1
            else:
                arr[i] = arr[i]
        answer += 1
        
    answer -= 1
    return answer